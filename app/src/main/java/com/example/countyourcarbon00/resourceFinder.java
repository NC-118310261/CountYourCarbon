package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class resourceFinder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_finder);
    }

    public void openBikes(View view) {
        Intent intent = new Intent(this, cokeBike.class);
        startActivity(intent);
    }
    public void openVegan(View view) {
        Intent intent = new Intent(this, vegan.class);
        startActivity(intent);
    }

    public void openThrift(View view) {
        Intent intent = new Intent(this, thrift.class);
        startActivity(intent);
    }

    public void openRecycle(View view) {
        Intent intent = new Intent(this, recycle.class);
        startActivity(intent);
    }

}