package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Consumption2 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnDisplay;
    RadioButton radioButton;
    Button btnFeedback4;
    TextView txtFeedback4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://mkyong.com/android/android-radio-buttons-example/
        // “onCreate()” method, attach a click listener on button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption2);
        // the function added
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.lifestyleRdbGroup);
        btnDisplay = (Button) findViewById(R.id.btnCalculateLifestyle);
        btnFeedback4 = (Button) findViewById(R.id.btnFeedback4);
        txtFeedback4 = (TextView) findViewById(R.id.txtFeedback4);

        btnFeedback4.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://www.bbc.com/future/article/20200310-sustainable-fashion-how-to-buy-clothes-good-for-the-climate
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback4.setText("You're doing great! Fashion accounts for around 10% of greenhouse gas emissions from human activity.");
                    break;
                case 1:
                    txtFeedback4.setText("Second-hand clothes have a big positive social and environmental impact. They reduce carbon emissions, save lots of resources, water, and energy. They also prevent old clothing from ending up in landfills or incinerators.");
                    break;
                case 2:
                    txtFeedback4.setText("Switching to recycled polyester fabric can help to reduce the carbon emissions – recycled polyester releases half to a quarter of the emissions of virgin polyester. But it isn’t a long-term solution, as polyester takes hundreds of years to decompose and can lead to micro fibres escaping into the environment.");
                    break;
                case 3:
                    txtFeedback4.setText("One solution might be to simply ration the time you spend looking at clothes online, but perhaps a better approach is to find less wasteful ways of achieving the sense of reward that over-spenders are seeking. Mainstream consumers can scratch their itch for new clothes by buying from vintage and secondhand clothing shops.");
                    break;
                default:
                    System.out.println("Error: Please select radio button");
            }
        }); // tab-indent - shift+tab- de-dent
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
                        carbonValue = 1.5;
                        break;
                    case 1:
                        carbonValue = 1.99;
                        break;
                    case 2:
                        carbonValue = 4.74;
                        break;
                    case 3:
                        carbonValue = 5.67;
                        break;
                    default:
                        System.out.println("Error: Please select radio button.");
                }
                // retrieve carbonValue from prev activ
                Intent prevIntent = getIntent();
                double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
                System.out.println("sum of Carbon Footprint is " + (prevCarbon + carbonValue));
                // eventually, gets to activity 3
                // https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html
                Intent intent = new Intent(Consumption2.this, com.example.countyourcarbon00.Living1.class);
                intent.putExtra("carbonValue", prevCarbon + carbonValue);
                startActivity(intent);
            }
        });
    }
}