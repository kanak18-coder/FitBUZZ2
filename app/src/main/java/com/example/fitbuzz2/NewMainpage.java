package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class NewMainpage extends AppCompatActivity implements View.OnClickListener{
    public CardView cd1,cd2,cd3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mainpage);


        cd1 = (CardView) findViewById(R.id.CCd1);
        cd2 = (CardView) findViewById(R.id.CCd2);
        cd3 = (CardView) findViewById(R.id.CCd3);



        cd1.setOnClickListener(this);
        cd2.setOnClickListener(this);
        cd3.setOnClickListener(this);

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
            case R.id.CCd1:
                i= new Intent(this,WorkoutActivity.class);
                startActivity(i);
                break;
            case R.id.CCd2:
                i= new Intent(this,CoachCdActivity.class);
                startActivity(i);
                break;
            case R.id.CCd3:
                i= new Intent(this,Dietchart.class);
                startActivity(i);
                break;
        }

    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}