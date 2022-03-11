package com.example.countyourcarbon00;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class category extends AppCompatActivity {
    private ImageView environment;
    private ImageView transport;
    private ImageView lifestyle;
    private ImageView local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        environment = (ImageView) findViewById(R.id.environment);
        transport = (ImageView) findViewById(R.id.transport);
        lifestyle = (ImageView) findViewById(R.id.lifestyle);
        local = (ImageView) findViewById(R.id.local);


        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this, com.example.countyourcarbon00.AdminAddNewProductActivity.class);
                intent.putExtra("Category", "Environment");
                startActivity(intent);
            }
        });



        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this, com.example.countyourcarbon00.AdminAddNewProductActivity.class);
                intent.putExtra("Category", "Transport");
                startActivity(intent);
            }
        });




        lifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(category.this, com.example.countyourcarbon00.AdminAddNewProductActivity.class);
                intent.putExtra("Category", "Lifestyle");
                startActivity(intent);
            }
        });

        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this, com.example.countyourcarbon00.AdminAddNewProductActivity.class);
                intent.putExtra("Category", "Local");
                startActivity(intent);
            }
        });

    }
}