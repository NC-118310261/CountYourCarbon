
package com.example.countyourcarbon00;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class recycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        TextView link = (TextView) findViewById(R.id.recycle);
        link.setMovementMethod(LinkMovementMethod.getInstance());

    }
}