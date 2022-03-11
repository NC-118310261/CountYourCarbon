package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class offsetMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offset_menu);
    }

    public void btnShop(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openResources(View view) {
        Intent intent = new Intent(this, resourceFinder.class);
        startActivity(intent);
    }
}