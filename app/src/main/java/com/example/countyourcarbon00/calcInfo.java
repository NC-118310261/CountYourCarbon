package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class calcInfo extends AppCompatActivity {

    ImageButton startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_info);

        startButton = (ImageButton) findViewById(R.id.btnStart);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }

        });
    }

    public void openMain() {
        Intent intent = new Intent(this, com.example.countyourcarbon00.MainActivity.class);
        startActivity(intent);
    }

    public void btnBackHome(View view) {
        Intent intent = new Intent(this, com.example.countyourcarbon00.HomeScreen.class);
        startActivity(intent);
    }
}