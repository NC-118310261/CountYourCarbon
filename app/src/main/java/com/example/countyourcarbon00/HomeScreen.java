package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {
//https://www.youtube.com/watch?v=VX5nxFwAQ-M
    Button calculatorButton;
    Button donateButton;
    ImageButton loginImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        calculatorButton = (Button) findViewById(R.id.btnCalcul);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalcInfo();
            }
        });
        loginImageButton = (ImageButton) findViewById(R.id.imBtnLogin);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        loginImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        donateButton = (Button) findViewById(R.id.btnDonate);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonate();
            }
        });
        TextView link = (TextView) findViewById(R.id.webLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openCalcInfo() {
        Intent intent = new Intent(this, calcInfo.class);
        startActivity(intent);
    }

    public void openDonate() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


    public void openMoreInfo(View view) {
        Intent intent = new Intent(this, moreInfo.class);
        startActivity(intent);
    }
}