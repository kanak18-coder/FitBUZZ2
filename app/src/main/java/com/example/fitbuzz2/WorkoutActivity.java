package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutActivity extends AppCompatActivity {

    private View BtnExercise;

        TextView  fitonetitle, fitonedesc, fittwotitle, fittwodesc, fitthreetitle,
                fitthreedesc, fitfourtitle, fitfourdesc;

        View divpage, bgprogress;



        LinearLayout fitone, fittwo, fitthree, fitfour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);




            BtnExercise= findViewById(R.id.exercise);
            fitonetitle =  findViewById(R.id.fitonetitle);
            fitonedesc =  findViewById(R.id.fitonedesc);
            fittwotitle =  findViewById(R.id.fittwotitle);
            fittwodesc =  findViewById(R.id.fittwodesc);
            fitthreetitle =  findViewById(R.id.fitthreetitle);
            fitthreedesc =  findViewById(R.id.fitthreedesc);
            fitfourtitle =  findViewById(R.id.fitfourtitle);
            fitfourdesc = findViewById(R.id.fitfourdesc);

            //give an event to another page
            BtnExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity (new Intent(WorkoutActivity.this,StartWorkAct.class));
                }
            });



            fitone = (LinearLayout) findViewById(R.id.fitone);
            fittwo = (LinearLayout) findViewById(R.id.fittwo);
            fitthree = (LinearLayout) findViewById(R.id.fitthree);
            fitfour = (LinearLayout) findViewById(R.id.fitfour);

            divpage = (View) findViewById(R.id.divpage);
            bgprogress = (View) findViewById(R.id.bgprogress);


    }
}