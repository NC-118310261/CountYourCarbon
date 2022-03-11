package com.example.countyourcarbon00;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class thrift extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrift);

        TextView link = (TextView) findViewById(R.id.jones);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link1 = (TextView) findViewById(R.id.northMain);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = (TextView) findViewById(R.id.records);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link3 = (TextView) findViewById(R.id.cancerSoc);
        link3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link4 = (TextView) findViewById(R.id.vincents);
        link4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}