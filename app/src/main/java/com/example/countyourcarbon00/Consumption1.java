package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Consumption1 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnDisplay;
    RadioButton radioButton;
    Button btnFeedback3;
    TextView txtFeedback3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://mkyong.com/android/android-radio-buttons-example/
        // “onCreate()” method, attach a click listener on button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption1);
        // the function added
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.foodRdbGroup);
        btnDisplay = (Button) findViewById(R.id.btnCalculateFood);
        btnFeedback3 = (Button) findViewById(R.id.btnFeedback3);
        txtFeedback3 = (TextView) findViewById(R.id.txtFeedback3);

        btnFeedback3.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://thehumaneleague.org/article/environmental-benefits-of-veganism
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback3.setText("You're doing great! Going vegan stops the deforestation, soil degradation, and greenhouse gas emissions associated with meat production, helping to slow climate change and secure our global food supply.");
                    break;
                case 1:
                    txtFeedback3.setText("You're on the right track! producing plant-based meat emits up to 90% fewer greenhouse gases than producing conventional meat. Just one plant-based meal can save the same amount of carbon emissions it takes to drive a car across the country. If that’s the impact one meal can make, it’s no surprise that following a plant-based diet full-time can result in a much smaller carbon footprint.");
                    break;
                case 2:
                    txtFeedback3.setText("Try to reduce to 2 days meat free! Producing just one hamburger uses enough fossil fuel to drive a small car 20 miles. Of all raw materials and fossil fuels used in the U.S., more than one-third are devoted to raising animals for food.");
                    break;
                case 3:
                    txtFeedback3.setText("Methane is 80 times more potent at trapping heat in the atmosphere than carbon dioxide, and it accounts for up to 30% of global warming since pre-industrial times. Each year, a single cow belches 220 pounds of methane into the atmosphere. Multiply those emissions by the 1.5 billion cows in our global food system.");
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
                        carbonValue = 0.31;
                        break;
                    case 1:
                        carbonValue = 0.46;
                        break;
                    case 2:
                        carbonValue = 1.85;
                        break;
                    case 3:
                        carbonValue = 3.1;
                        break;
                    default:
                        System.out.println("error for radio button");
                }
                // retrieve carbonValue from prev activ
                Intent prevIntent = getIntent();
                double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
                System.out.println("now in Consumption 1");
                System.out.println("sum of 3 activ is: " + (prevCarbon + carbonValue));
                // eventually, gets to activity 3
                // https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html
                Intent intent = new Intent(Consumption1.this, com.example.countyourcarbon00.Consumption2.class);
                intent.putExtra("carbonValue", prevCarbon + carbonValue);
                startActivity(intent);
            }
        });
    }
}
