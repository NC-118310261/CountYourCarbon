package com.example.countyourcarbon00;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class vegan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vegan);

        TextView link = (TextView) findViewById(R.id.myGoodness);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView deliLink = (TextView) findViewById(R.id.deli);
        deliLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView pinkLink = (TextView) findViewById(R.id.pink);
        pinkLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView sonLink = (TextView) findViewById(R.id.sonflour);
        sonLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView izzLink = (TextView) findViewById(R.id.izz);
        izzLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView shopLink = (TextView) findViewById(R.id.shop);
        shopLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
