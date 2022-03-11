package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ImportSurvey extends AppCompatActivity {

    RecyclerView recyclerView;
    SurveyAdapter surveyAdapter;
    ArrayList<SurveyData> list;
    private SearchView searchView;
    private String currentSearchText = "";

    private static Cell cell;
    private static Sheet sheet;
    private Button btnHome;
    private File filePath = new File(Environment.getExternalStorageDirectory() + "/StudentData.xls");
    ArrayList<SurveyData> listItems=new ArrayList<SurveyData>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_survey);

        //setupData();
        searchView = findViewById(R.id.shapeListSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchText = s;
                ArrayList<SurveyData> filteredList = new ArrayList<SurveyData>();

                for (SurveyData survey : listItems) {
                    if (survey.getSchools().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(survey);
                    } // have spinner - for searching term
                }
                for (SurveyData survey : listItems) {
                    if (survey.getAge().toLowerCase().contains(currentSearchText.toLowerCase())) {
                        filteredList.add(survey);
                    }
                }
                SurveyAdapter adapter = new SurveyAdapter(getApplicationContext(), filteredList);
                recyclerView.setAdapter(adapter);
                return false;
            }
        });

        btnHome = findViewById(R.id.btnHome);
        initSearchWidgets();

        recyclerView = findViewById(R.id.lvSurveyData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        populateList();

        //list = new ArrayList<>();
        surveyAdapter = new SurveyAdapter(this,listItems);
        recyclerView.setAdapter(surveyAdapter);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
    }

//https://github.com/codeWithCal/Shapelist/tree/shapelistWithSearch/app/src
    private void initSearchWidgets() {
        searchView = (SearchView) findViewById(R.id.shapeListSearchView);


    }


   /* private void setUpList()
    {
        recyclerView = (RecyclerView) findViewById(R.id.lvSurveyData);

        SurveyAdapter adapter = new SurveyAdapter(getApplicationContext(), 0, surveyAdapter);
        recyclerView.setAdapter(adapter);
    }*/


    public void openHome() {
            Intent intent = new Intent(this, AdminHomePage.class);
            startActivity(intent);
        }

    private void populateList() {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = null;

        int rowNum = 0; // index 0 is header
        // https://stackoverflow.com/questions/22441425/how-to-append-to-existing-excel-file-using-java-hssf
        try {
            FileInputStream file = new FileInputStream(filePath);
            hssfWorkbook = new HSSFWorkbook(file); // get file to hssfBook
            hssfSheet = hssfWorkbook.getSheetAt(0); // get sheet in Book
            HSSFRow currentRow =  null;
            HSSFCell currentCell = null;
            try {
                while (hssfSheet.getRow(rowNum).getCell(2) != null) {
                    currentRow = hssfSheet.getRow(rowNum);
                    ArrayList<String> dataList = new ArrayList<String>();
                    for (int i = 0; i < 9; i++) {
                        if (currentRow.getCell(i) == null) {
                            dataList.add("null");
                            continue; // bypass the empty cell
                        }
                        //System.out.println(currentRow.getCell(i).getStringCellValue());
                        currentCell = currentRow.getCell(i);
                        String curValue = currentCell.getStringCellValue();
                        dataList.add(curValue);
                    }
                    listItems.add(new SurveyData(dataList.get(0), dataList.get(1), dataList.get(2), dataList.get(3), dataList.get(4), dataList.get(5), dataList.get(6), dataList.get(7), dataList.get(8)));
                    //adapter.notifyDataSetChanged(); // adapter not relavent, no value assigned
                    rowNum++;
                }
            } catch (Exception e){
                System.out.println("Exception: " + e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(surveyAdapter);

        /*filePath.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    SurveyData surveyData = dataSnapshot.getValue(SurveyData.class);
                    list.add(surveyData);
                }
                surveyAdapter.notifyDataSetChanged();
            }

        });*/
    }

}

/*
    private static final String TAG = "ImportSurvey";
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    File file;

    Button btnUpDirectory, btnSDCard;

    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;

    ArrayList<SurveyData> uploadData;

    ListView lvInternalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_survey);

        lvInternalStorage = (ListView) findViewById(R.id.lvInternalStorage);
        btnUpDirectory = (Button) findViewById(R.id.btnUpDirectory);
        btnSDCard = (Button) findViewById(R.id.btnViewSDCard);
        uploadData = new ArrayList<>();

        //need to check the permission
        checkFilePermission();

        lvInternalStorage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if(lastDirectory.equals(adapterView.getItemAtPosition(i))){
                    Log.d(TAG, "lvInternalStorage: Selected a file for upload: " + lastDirectory);

                    //Execute method for reading the excel data.
                    readExcelData(lastDirectory);

                }else
                {
                    count++;
                    pathHistory.add(count,(String) adapterView.getItemAtPosition(i));
                    checkInternalStorage();
                    Log.d(TAG, "lvInternalStorage: " + pathHistory.get(count));
                }
            }

            //Filter & Search List View Android Studio Tutorial
            //https://www.youtube.com/watch?v=M73Vec1oieM
            private void initSearchWidgets() {
                SearchView searchView = (SearchView) findViewById(R.id.surveySearchView);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        ArrayList<SurveyData> filteredData = new ArrayList<SurveyData>();
                        for (int i = 0; i < databaseList().length; i++){
                            SurveyData surveyData = databaseList.get(i);

                            if(surveyData.getName().toLowerCase().contains(s.toLowerCase())){
                                filteredData.add(surveyData);

                            }
                        }

                        SurveyAdapter adapter = new SurveyAdapter(getApplicationContext(), 0, filteredData);
                        lvInternalStorage.setAdapter(adapter);


                        return false;
                    }
                });
            }

            private void readExcelData(String filePath) {
                Log.d(TAG, "readExcelData: Reading Excel File");

                //declare input file
                File inputFile = new File(filePath);

                try {
                    InputStream inputStream = new FileInputStream(inputFile);
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    int rowsCount = sheet.getPhysicalNumberOfRows();
                    FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    StringBuilder sb = new StringBuilder();

                    //outer loop, loops through rows
                    for (int r = 1; r < rowsCount; r++) {
                        Row row = sheet.getRow(r);
                        int cellsCount = row.getPhysicalNumberOfCells();
                        //inner loop, loops through columns
                        for (int c = 0; c < cellsCount; c++) {
                            //handles if there are to many columns on the excel sheet.
                            if(c>7){
                                Log.e(TAG, "readExcelData: ERROR. Excel File Format is incorrect! " );
                                toastMessage("ERROR: Excel File Format is incorrect!");
                                break;
                            }else{
                                String value = getCellAsString(row, c, formulaEvaluator);
                                String cellInfo = "r:" + r + "; c:" + c + "; v:" + value;
                                Log.d(TAG, "readExcelData: Data from row: " + cellInfo);
                                sb.append(value + ", ");
                            }
                        }
                        sb.append(":");
                    }
                    Log.d(TAG, "readExcelData: STRINGBUILDER: " + sb.toString());

                    parseStringBuilder(sb);

                }catch (FileNotFoundException e) {
                    Log.e(TAG, "readExcelData: FileNotFoundException. " + e.getMessage() );
                } catch (IOException e) {
                    Log.e(TAG, "readExcelData: Error reading inputstream. " + e.getMessage() );
                }

            }

            private void parseStringBuilder(StringBuilder sb) {
                Log.d(TAG, "parseStringBuilder: Started parsing.");

                // splits the sb into rows.
                String[] rows = StringBuilder.String();

                //Add to the ArrayList<XYValue> row by row
                for(int i=0; i<rows.length; i++) {
                    //  String[] columns = rows[i].split(",");

                    try{
                        String schools = String.parseString(columns[0]);
                        String age = String.parseString(columns[1]);
                        String satisfaction = String.parseString(columns[2]);
                        String recycling = String.parseString(columns[3]);
                        String food = String.parseString(columns[4]);
                        String foodOptions = String.parseString(columns[5]);
                        String cups = String.parseString(columns[6]);
                        String feedback = String.parseString(columns[7]);

                        String cellInfo = "Please select ..";
                        Log.d(TAG, "ParseStringBuilder: Data from row: " + cellInfo);

                        //add the the uploadData ArrayList
                        uploadData.add(new SurveyData());

                    }catch (NumberFormatException e){you

                        Log.e(TAG, "parseStringBuilder: NumberFormatException: " + e.getMessage());

                    }
                }

            }

            private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
                String value = "";
                try {
                    Cell cell = row.getCell(c);
                    CellValue cellValue = formulaEvaluator.evaluate(cell);
                    switch (cellValue.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            value = ""+cellValue.getBooleanValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            double numericValue = cellValue.getNumberValue();
                        case Cell.CELL_TYPE_STRING:
                            value = ""+cellValue.getStringValue();
                            break;
                        default:
                    }
                } catch (NullPointerException e) {

                    Log.e(TAG, "getCellAsString: NullPointerException: " + e.getMessage() );
                }
                return value;
            }

            private void checkInternalStorage() {
                try{
                    if (!Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED)) {
                        toastMessage("No SD card found.");
                    }
                    else{
                        // Locate the image folder in your SD Car;d
                        file = new File(pathHistory.get(count));
                        Log.d(TAG, "checkInternalStorage: directory path: " + pathHistory.get(count));
                    }

                    listFile = file.listFiles();

                    // Create a String array for FilePathStrings
                    FilePathStrings = new String[listFile.length];

                    // Create a String array for FileNameStrings
                    FileNameStrings = new String[listFile.length];

                    for (int i = 0; i < listFile.length; i++) {
                        // Get the path of the image file
                        FilePathStrings[i] = listFile[i].getAbsolutePath();
                        // Get the name image file
                        FileNameStrings[i] = listFile[i].getName();
                    }

                    for (int i = 0; i < listFile.length; i++)
                    {
                        Log.d("Files", "FileName:" + listFile[i].getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout., ImportSurvey);
                    lvInternalStorage.setAdapter(adapter);

                }catch(NullPointerException e){
                    Log.e(TAG, "checkInternalStorage: NULLPOINTEREXCEPTION " + e.getMessage() );
                }
            }
        });
    }

    private void checkFilePermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

     * customizable toast
     * @param message


    public void allFilterTapped(View view) {
    }

    public void schoolFilterTapped(View view) {
    }

    public void ageFilterTapped(View view) {
    }

    public void recyclingFilterTapped(View view) {
    }

    public void feedbackFilterTapped(View view) {
    }

    public void foodFilterTapped(View view) {
    }
 */