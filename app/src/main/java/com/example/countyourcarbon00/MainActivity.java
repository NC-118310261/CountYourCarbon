package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    private Button button;
    RadioButton radioButton;
    TextView txtFeedback1;
    Button btnFeedback1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        button = (Button) findViewById(R.id.btnCalculateTransport);
        txtFeedback1 = (TextView) findViewById(R.id.txtFeedback1);
        btnFeedback1 = (Button) findViewById(R.id.btnFeedback1);

        //  the function added
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        //https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        button = (Button) findViewById(R.id.btnCalculateTransport);
        txtFeedback1 = (TextView) findViewById(R.id.txtFeedback1);
        btnFeedback1 = (Button) findViewById(R.id.btnFeedback1);


        btnFeedback1.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://www.bbc.com/future/article/20200317-climate-change-cut-carbon-emissions-from-your-commute#:~:text=Smart%20Guide%20to%20Climate%20Change&text=Private%20transport%20is%20one%20of,with%20emissions%20rising%20every%20year.&text=Globally%2C%20transport%20accounts%20for%20around,remain%20focused%20around%20the%20car.
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback1.setText("You're doing great! Walking or cycling for more of our shorter journeys helps to protect biodiversity. It creates less noise, less air pollution, and results in fewer emissions that are warming the atmosphere.");
                    break;
                case 1:
                    txtFeedback1.setText("Public transportation is reducing energy consumption and harmful carbon dioxide (CO2) greenhouse gas emissions that damage the environment. Traveling by public transportation uses less energy and produces less pollution than comparable travel in private vehicles.");
                    break;
                case 2:
                    txtFeedback1.setText("Road vehicles – cars, trucks, buses and motorbikes – account for nearly three quarters of the greenhouse gas emissions that come from transport. Cycling, which shares many of the climate benefits of walking, is increasingly a viable alternative to car journeys, too.");
                    break;
                case 3:
                    txtFeedback1.setText("Driving a newer vehicle can reduce these emissions – in Europe the average emissions for a new petrol car in 2018 was 123g of CO2/km. Travelling on light rail emits around a sixth of the equivalent car journey. A coach ride produces lower emissions still. Taking a local bus emits a little over half the greenhouse gases of a single occupancy car journey and also help to remove congestion from the roads. Bus emissions will go down further as more and more cities implement plans for electric and hydrogen buses.");
                    break;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to do the calculation here
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                //System.out.println(selectedId);
                //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
                int index = radioGroup.indexOfChild(radioButton);
                System.out.println("radio_Number is: " + index);
                // calculating with switch
                double carbonValue = 0;
                switch (index) {
                    case 0:
                        carbonValue = 0.0001;
                        break;
                    case 1:
                        carbonValue = 0.003;
                        break;
                    // 27.1.1
                    case 2:
                        carbonValue = 0.27;
                        break;
                    case 3:
                        carbonValue = 0.65;
                        break;
                    default:
                        System.out.println("Error: Please select radio button");
                }

           /* btnFeedback1.setOnClickListener(v1 -> {
                // to do the calculation here
                int selectedId1 = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId1);
                //System.out.println(selectedId);
                //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
                int index1 = radioGroup.indexOfChild(radioButton);
                System.out.println("radio_Number is: " + index1);
                // calculating with switch
                boolean checked = ((RadioButton) v1).isChecked();
                switch (index1) {
                    case 0:
                        if (checked)
                            ((TextView) findViewById(R.id.txtFeedback1)).setText("Button1 has been chosen");
                        break;
                    case 1:
                        if (checked)
                            ((TextView) findViewById(R.id.txtFeedback1)).setText("Button 2 has been chosen");
                        break;
                    case 2:
                        if (checked)
                            ((TextView) findViewById(R.id.txtFeedback1)).setText("Button 3 has been chosen");
                        break;
                    case 3:
                        if (checked)
                            ((TextView) findViewById(R.id.txtFeedback1)).setText("Button 4 has been chosen");
                        break;
                    default:
                        System.out.println("Error: Please select radio button");
                }


            });*/

                // eventually, gets to activity 2
                //https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html
                Intent intent = new Intent(MainActivity.this, com.example.countyourcarbon00.Transport2.class);
                intent.putExtra("carbonValue", carbonValue);
                // intent.putExtra("optionPage1", radioGroup.indexOfChild(radioButton) + 1);
                startActivity(intent);
            }
        });
    }
}