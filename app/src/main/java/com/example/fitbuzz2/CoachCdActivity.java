package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CoachCdActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView c1,c2,c3,c4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_cd_activity);

        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        c3 = (CardView) findViewById(R.id.card3);
        c4 = (CardView) findViewById(R.id.card4);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.card1:
                i= new Intent(this,CoachProfileActivity.class);
                startActivity(i);
                break;
            case R.id.card2:
                i= new Intent(this,CoachProfile2.class);
                startActivity(i);
                break;
            case R.id.card3:
                i= new Intent(this,CoachProfile3.class);
                startActivity(i);
                break;
            case R.id.card4:
                i= new Intent(this,CoachProfile4.class);
                startActivity(i);
                break;
        }

    }
}


