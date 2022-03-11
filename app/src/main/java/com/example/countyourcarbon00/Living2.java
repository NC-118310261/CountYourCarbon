package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Living2 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnDisplay;
    RadioButton radioButton;
    Button btnFeedback6;
    TextView txtFeedback6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://mkyong.com/android/android-radio-buttons-example/
        // “onCreate()” method, attach a click listener on button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living2);
        // the function added
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.houseStructureRdbGroup);
        btnDisplay = (Button) findViewById(R.id.btnCalculateLiving2);
        btnFeedback6 = (Button) findViewById(R.id.btnFeedback6);
        txtFeedback6 = (TextView) findViewById(R.id.txtFeedback6);

        btnFeedback6.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://www.greensquare.co.uk/blog/is-new-build-or-old-property-better-for-the-environment
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback6.setText("Newer homes feature cavity wall foam and double-glazing windows which help to keep the heat in. A new build property is built to be more energy efficient and can help homeowners save hundreds of pounds off their energy bills as well as helping the environment.");
                    break;
                case 1:
                    txtFeedback6.setText("Older properties usually have thicker walls compared to new builds, but they have poorer and outdated heating systems and less insulation. As old houses are more spacious, this means that it takes more time and energy to heat up the air. ");
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
                        carbonValue = 0.56;
                        break;
                    case 1:
                        carbonValue = 1.35;
                        break;
                    default:
                        System.out.println("Error: Please select radio button.");
                }
                // retrieve carbonValue from the last activity
                Intent prevIntent = getIntent();
                double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
                System.out.println("sum of Carbon Footprint is " + (prevCarbon + carbonValue));
                // gets to next activity
                // https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html
                Intent intent = new Intent(Living2.this, Living3.class);
                intent.putExtra("carbonValue", prevCarbon + carbonValue);
                startActivity(intent);
            }
        });
    }
}