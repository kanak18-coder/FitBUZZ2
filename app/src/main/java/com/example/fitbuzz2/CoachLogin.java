package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class CoachLogin extends AppCompatActivity {

    EditText mCoachemail,mCoachpass;
    Button msiginin;


    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_login);

        mCoachemail = findViewById(R.id.coachemail);
        mCoachpass = findViewById(R.id.coachpass);

        fAuth = FirebaseAuth.getInstance();
        msiginin = findViewById(R.id.coachsignin);



        msiginin.setOnClickListener(v -> {

            String email = mCoachemail.getText().toString().trim();
            String password = mCoachpass.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mCoachemail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mCoachpass.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mCoachpass.setError("Password Must be >= 6 Characters");
                return;
            }


            // authenticate the user

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(CoachLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),CoachDashboard.class));
                }else {
                    Toast.makeText(CoachLogin.this, "Error  !  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }



            });

        });


    }
}