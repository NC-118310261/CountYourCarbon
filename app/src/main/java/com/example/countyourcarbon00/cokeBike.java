package com.example.countyourcarbon00;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cokeBike extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coke_bike);

        TextView link = (TextView) findViewById(R.id.bikeLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

}