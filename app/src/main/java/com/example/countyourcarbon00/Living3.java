package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Living3 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnDisplay;
    RadioButton radioButton;
    Button btnFeedback7;
    TextView txtFeedback7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://mkyong.com/android/android-radio-buttons-example/
        // “onCreate()” method, attach a click listener on button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living3);
        // the function added

        btnDisplay = (Button) findViewById(R.id.btnPublicServices);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.ServicesRdbGroup);
        btnFeedback7 = (Button) findViewById(R.id.btnFeedback7);
        txtFeedback7 = (TextView) findViewById(R.id.txtFeedback7);

        btnFeedback7.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback7.setText("While these emissions are out of your control, being more environmentally friendly in your own actions will in turn reduce your indirect carbon footprint. This can be done by supporting companies that are environmentally friendly, switching to plant based alternatives, and trying out more sustainable modes of transport!");
                    break;
                default:
                    System.out.println("Error: Please select radio button");
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        carbonValue = 1.2;
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
                Intent intent = new Intent(Living3.this, Result.class);
                intent.putExtra("carbonValue", prevCarbon + carbonValue);
                startActivity(intent);
            }
        });
    }
}