package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CoachDashboard extends AppCompatActivity {

    FloatingActionButton addvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_dashboard);


        addvideo=(FloatingActionButton)findViewById(R.id.addvideo);
        addvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),addvideo.class));
            }
        });
    }
}