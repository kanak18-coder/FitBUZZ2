package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Dietchart extends AppCompatActivity implements View.OnClickListener {
    public CardView ca1, ca2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietchart);

        ca1 = (CardView) findViewById(R.id.cad1);
        ca2 = (CardView) findViewById(R.id.cad2);


        ca1.setOnClickListener(this);
        ca2.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cad1:
                i = new Intent(this, Weightloss.class);
                startActivity(i);
                break;
            case R.id.cad2:
                i = new Intent(this, Weightgain.class);
                startActivity(i);
                break;

        }
    }
}