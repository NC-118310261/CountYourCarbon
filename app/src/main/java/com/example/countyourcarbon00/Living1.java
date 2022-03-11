package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Living1 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnDisplay;
    RadioButton radioButton;
    Button btnFeedback5;
    TextView txtFeedback5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://mkyong.com/android/android-radio-buttons-example/
        // “onCreate()” method, attach a click listener on button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living1);
        // the function added
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.householdRdbGroup);
        btnDisplay = (Button) findViewById(R.id.btnCalculateHouseholdLiving);
        btnFeedback5 = (Button) findViewById(R.id.btnFeedback5);
        txtFeedback5 = (TextView) findViewById(R.id.txtFeedback5);

        btnFeedback5.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback5.setText("The average carbon footprint in Europe for one-person households amounts to the equivalent of nine tonnes of CO₂ per person — about twice as high as the footprint of someone living in a household of five or more people.");
                    break;
                case 1:
                    txtFeedback5.setText("When people live together, they share appliances, tools and equipment, cook together, heat and cool a common living space. These acts of sharing reduce the direct and embodied carbon emissions per person, compared to living alone.");
                    break;
                default:
                    System.out.println("Error: Please select radio button");
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                //System.out.println(selectedId);
                //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
                int index = radioGroup.indexOfChild(radioButton);
                System.out.println("radio_Number is: " + index);
                // index represents the option number
                // calculating with switch
                double carbonValue = 0;
                switch (index) {
                    case 0:
                        carbonValue = 0.8;
                        break;
                    case 1:
                        carbonValue = 1.6;
                        break;
                    default:
                        System.out.println("Error: Please select radio button.");
                }
                // retrieve carbonValue from the last activity
                Intent prevIntent = getIntent();
                double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
                System.out.println("sum of Carbon Footprint is " + (prevCarbon + carbonValue));
                // get to next activity
                // https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html
                Intent intent = new Intent(Living1.this, com.example.countyourcarbon00.Living2.class);
                intent.putExtra("carbonValue", prevCarbon + carbonValue);
                startActivity(intent);
            }
        });
    }
}