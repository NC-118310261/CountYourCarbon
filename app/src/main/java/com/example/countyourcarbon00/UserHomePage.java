package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomePage extends AppCompatActivity {
    Button btnCalculate;
    Button btnOffset;
    Button btnLogOut;
    Button btnEditUser;
    Button btnSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        btnCalculate = (Button) findViewById(R.id.btnProductPage);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCal();
            }

        });

        btnEditUser = (Button) findViewById(R.id.btnSurveyData);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        btnOffset = (Button) findViewById(R.id.btnUserDetails);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnOffset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShop();
            }
        });

        btnLogOut = (Button) findViewById(R.id.btnLogOut);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        btnSurvey = (Button) findViewById(R.id.btnSurvey);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSurvey();
            }
        });
    }

        public void openCal () {
            Intent intent = new Intent(this, calcInfo.class);
            startActivity(intent);
        }

        public void openShop () {
            Intent intent = new Intent(this, com.example.countyourcarbon00.offsetMenu.class);
            startActivity(intent);
        }

        public void openSurvey () {
        Intent intent = new Intent(this, Survey.class);
        startActivity(intent);
    }

    public void openProfile () {
        // get email from login page
        Intent prevIntent = getIntent();
        String userEmail = prevIntent.getStringExtra("emailLoggedIn");
        Intent intent = new Intent(this, com.example.countyourcarbon00.Profile.class);
        // pass email to profile page
        intent.putExtra("emailLoggedIn", userEmail);
        startActivity(intent);
    }

    public void openHome () {
        Intent intent = new Intent(this, com.example.countyourcarbon00.HomeScreen.class);
        startActivity(intent);
    }
}
