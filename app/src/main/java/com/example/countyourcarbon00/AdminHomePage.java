package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomePage extends AppCompatActivity {
    Button btnProductPage;
    Button btnUserDetails;
    Button btnSurveyData;
    Button btnSurvey;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        btnProductPage = (Button) findViewById(R.id.btnProductPage);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnProductPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategory();
            }

        });

        btnUserDetails = (Button) findViewById(R.id.btnUserDetails);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUsers();
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


        btnSurveyData = (Button) findViewById(R.id.btnSurveyData);
//https://www.youtube.com/watch?v=bgIUdb-7Rqo
        btnSurveyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSurveyData();
            }
        });

    }

    public void openCategory () {
        Intent intent = new Intent(this, AdminCategoryActivity.class);
        startActivity(intent);
    }

    public void openUsers () {
        Intent intent = new Intent(this, com.example.countyourcarbon00.UserDetails.class);
        startActivity(intent);
    }

    public void openSurveyData () {
        Intent intent = new Intent(this, ImportSurvey.class);
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
