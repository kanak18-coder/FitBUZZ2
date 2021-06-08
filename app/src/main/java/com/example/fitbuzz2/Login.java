package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail2,mPassword2;
    Button mLoginBtn;
    TextView mCreateBtn;


    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail2 = findViewById(R.id.email2);
        mPassword2 = findViewById(R.id.password2);

        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.Loginbtn);
        mCreateBtn = findViewById(R.id.Createbtn);


        mLoginBtn.setOnClickListener(v -> {

            String email = mEmail2.getText().toString().trim();
            String password = mPassword2.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mEmail2.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword2.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mPassword2.setError("Password Must be >= 6 Characters");
                return;
            }


            // authenticate the user

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),NewMainpage.class));
                }else {
                            Toast.makeText(Login.this, "Error  !  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }



            });

        });

        mCreateBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Register.class)));



    }
}