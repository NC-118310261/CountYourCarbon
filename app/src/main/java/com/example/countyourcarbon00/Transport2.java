package com.example.countyourcarbon00;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Transport2 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnDisplay;
    RadioButton radioButton;
    Button btnFeedback2;
    TextView txtFeedback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://mkyong.com/android/android-radio-buttons-example/
        // “onCreate()” method, attach a click listener on button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport2);

        // the function added
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.tGroup);
        btnDisplay = (Button) findViewById(R.id.btnCalculateFlights);
        btnFeedback2 = (Button) findViewById(R.id.btnFeedback2);
        txtFeedback2 = (TextView) findViewById(R.id.txtFeedback2);

        btnFeedback2.setOnClickListener(v1 -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            //System.out.println(selectedId);
            //https://www.bbc.com/future/article/20200218-climate-change-how-to-cut-your-carbon-emissions-when-flying
            //https://coderanch.com/t/441739/java/Calculations-Based-Selected-Radio-Button
            int optionPage1 = radioGroup.indexOfChild(radioButton);
            switch (optionPage1) {
                case 0:
                    txtFeedback2.setText("You're doing great! Around 2.4% of global CO2 emissions come from aviation. Together with other gases and the water vapour trails produced by aircraft, the industry is responsible for around 5% of global warming.");
                    break;
                case 1:
                    txtFeedback2.setText("A return flight from London to Berlin emits around 0.6 tonnes CO2e – three times the emissions saved from a year of recycling. Choosing alternative ways of travelling, like rail, are the most effective way of reducing the carbon footprint our travel can have.");
                    break;
                case 2:
                    txtFeedback2.setText("A return flight from London to San Francisco emits around 5.5 tonnes of CO2 equivalent (CO2e) per person – more than twice the emissions produced by a family car in a year, and about half of the average carbon footprint of someone living in Britain.");
                    break;
                case 3:
                    txtFeedback2.setText("A single passenger travelling on a domestic flight in Britain, for example, can lead to climate impacts equivalent to 254g of CO2 for every kilometre they travel, according the UK’s Department for Business, Energy and Industrial Strategy (BEIS). The same calculations estimate a long-haul flight can release the equivalent to 102g of CO2 for every kilometre – a lower figure on average per kilometre because of the huge amount of emissions given off during take-off and landing.");
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
                        carbonValue = 0.0001;
                        break;
                    case 1:
                        carbonValue = 1.31;
                        break;
                    case 2:
                        carbonValue = 2.62;
                        break;
                    case 3:
                        carbonValue = 6.55;
                        break;
                    default:
                        System.out.println("error for radio button");
                }
                // retrieve carbonValue from prev activ
                Intent prevIntent = getIntent();
                double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
                System.out.println("sum of 2 activ is: " + (prevCarbon + carbonValue));

                // eventually, gets to activity 3
                // https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html
                Intent intent = new Intent(Transport2.this, Consumption1.class);
                intent.putExtra("carbonValue", prevCarbon + carbonValue);
                startActivity(intent);
            }
        });
    }
}
