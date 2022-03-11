package com.example.countyourcarbon00;
//https://developer.android.com/guide/topics/ui/controls/spinner#java

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Survey extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8;
    private EditText txtUserFeedback;
    private static Cell cell;
    private File filePath = new File(Environment.getExternalStorageDirectory() + "/StudentData.xls");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        prepareDropDownList();
        prepareDropDownList2();
        prepareDropDownList3();
        prepareDropDownList4();
        prepareDropDownList5();
        prepareDropDownList6();
        prepareDropDownList7();
        prepareDropDownList8();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        spinner8 = findViewById(R.id.spinner8);
        txtUserFeedback = findViewById(R.id.txtUserFeedback);


    }
//https://programmerworld.co/android/how-to-create-an-excel-file-from-your-android-app/

    //https://stackoverflow.com/questions/68140893/android-studio-throwing-ioexception-operation-not-permitted
    protected void askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
                return;
            }
        }
    }

    protected void prepareDropDownList(){
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.schools, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
    }

    protected void prepareDropDownList2(){
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter);
    }

    protected void prepareDropDownList3(){
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.satisfaction, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3.setAdapter(adapter);
    }

    protected void prepareDropDownList4(){
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.confidence, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner4.setAdapter(adapter);
    }

    protected void prepareDropDownList5(){
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recycling, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner5.setAdapter(adapter);
    }

    protected void prepareDropDownList6(){
        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner6.setAdapter(adapter);
    }

    protected void prepareDropDownList7(){
        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner7.setAdapter(adapter);
    }

    protected void prepareDropDownList8(){
        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cups, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner8.setAdapter(adapter);
    }

    public void buttonCreateExcel(View view){
        askForPermissions();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = null;

        //https://medium.com/geekculture/creating-an-excel-in-android-cd9c22198619
        // Cell style for header row
       /* CellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);*/

        int rowNum = 0;
        // https://stackoverflow.com/questions/22441425/how-to-append-to-existing-excel-file-using-java-hssf
        try {
            if (filePath.exists()){
                //filePath.createNewFile();// invalid excel file created
                // here's the valid - https://stackoverflow.com/questions/36119130/notole2fileexception-invalid-header-signature-read-0x0000000000000000-expecte
                //Workbook workbook = new HSSFWorkbook();
                FileInputStream file = new FileInputStream(filePath);
                hssfWorkbook = new HSSFWorkbook(file); // get file to hssfBook
                hssfSheet = hssfWorkbook.getSheetAt(0); // get sheet in Book
                HSSFRow currentRow =  hssfSheet.getRow(rowNum);
                if (currentRow == null)
                    currentRow = hssfSheet.createRow(rowNum);
                HSSFCell currentCell = currentRow.getCell(1);
//            String text = currentCell.getStringCellValue();
                while (currentCell != null) {
                    rowNum++;
                    currentRow =  hssfSheet.getRow(rowNum); // row 1
                    try {
                        currentCell = currentRow.getCell(1);
                    } catch (Exception e){
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // if excel file doesn't exist - create a new one
        if (!filePath.exists()) {
            hssfWorkbook = new HSSFWorkbook();
            hssfSheet = hssfWorkbook.createSheet("Survey Data");
       /* cell = null;
        //https://medium.com/geekculture/creating-an-excel-in-android-cd9c22198619
        // Cell style for header row
        CellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        */
//        int rowNum = getRowNum();
//        System.out.println("rowNum " + rowNum);

            //https://medium.com/geekculture/creating-an-excel-in-android-cd9c22198619
            // Generate column headings
            Row row = hssfSheet.createRow(0);

            cell = row.createCell(0);
            cell.setCellValue("School");
            //  cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Age");
            //  cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Satisfaction");
            //  cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Confidence");
            //  cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Recycling");
            //  cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Food");
            // cell.setCellStyle(cellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Food Options");
            // cell.setCellStyle(cellStyle);

            cell = row.createCell(7);
            cell.setCellValue("Cups");
            // cell.setCellStyle(cellStyle);

            cell = row.createCell(8);
            cell.setCellValue("Feedback");
            //cell.setCellStyle(cellStyle);
        } // endif - file doesn't exist
        /* if (hssfSheet == null) {
            System.out.println("it's creating another Sheet");
            hssfSheet = hssfWorkbook.createSheet("Survey Data");
        } */
        HSSFRow hssfRow = hssfSheet.createRow(rowNum);
        HSSFCell hssfCell = hssfRow.createCell(0);
        hssfCell.setCellValue(spinner1.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue(spinner2.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(2);
        hssfCell.setCellValue(spinner3.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(3);
        hssfCell.setCellValue(spinner4.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(4);
        hssfCell.setCellValue(spinner5.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(5);
        hssfCell.setCellValue(spinner6.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(6);
        hssfCell.setCellValue(spinner7.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(7);
        hssfCell.setCellValue(spinner8.getSelectedItem().toString());

        //hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(8);
        hssfCell.setCellValue(txtUserFeedback.getText().toString());

        try {
            if (!filePath.exists()){
                filePath.createNewFile();
            }

            FileOutputStream fileOutputStream= new FileOutputStream(filePath);
            hssfWorkbook.write(fileOutputStream);

            if (fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close(); // write to file success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}