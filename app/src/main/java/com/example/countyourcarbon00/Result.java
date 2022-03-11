package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    TextView textResult;
    Button btnLogin;
    Button btnRegister;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent prevIntent = getIntent();
        double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
        System.out.println("sum on result page is: " + prevCarbon);


        //http://tutorials.jenkov.com/android/textview.html
        textResult = findViewById(R.id.textResult); //in your OnCreate() method
        //https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        double result = Math.round(prevCarbon * 100.0) / 100.0; // rounded to 2 decimal places
        textResult.setText("Your total Carbon Footprint is: " + result + " Tonnes of C02.");
        // gets to next activity
        // https://www.thecrazyprogrammer.com/2016/12/pass-data-one-activity-another-in-android.html

        btnLogin = (Button) findViewById(R.id.btnLogin);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        btnRegister = (Button) findViewById(R.id.btnRegisterResult);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prevIntent = getIntent();
                double prevCarbon = prevIntent.getDoubleExtra("carbonValue", 0.0);
                System.out.println("sum on result page is: " + prevCarbon);


                //http://tutorials.jenkov.com/android/textview.html
                textResult = findViewById(R.id.textResult); //in your OnCreate() method
                //https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
                double result = Math.round(prevCarbon * 100.0) / 100.0; // rounded to 2 decimal places
                Intent intent = new Intent(Result.this, Register.class);
                intent.putExtra("resultValue", result);
                startActivity(intent);
            }
        });
    }

        public void openLogin () {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

    }





   /* public void addListenerOnButton() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, Register.class);
                intent.putExtra("resultValue", result);
                startActivity(intent);
            }
        });




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, Login.class);
                //intent.putExtra("resultValue", result);
                startActivity(intent);
            }
        });
    }*/