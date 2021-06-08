package com.example.fitbuzz2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CoachSignup extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mEmail3,mPassword3,mPhone,mFullname,mSport,mAge;
    Button mSignup;
    TextView mSigintap;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String coachID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_signup);

        mEmail3      = findViewById(R.id.email3);
        mPassword3   = findViewById(R.id.coachpassword);
        mFullname    = findViewById(R.id.Fullname);
        mSport       = findViewById(R.id.Sport);
        mAge         = findViewById(R.id.Age);
        mPhone      = findViewById(R.id.phone);
        mSignup     = findViewById(R.id.Signupbtn);
        mSigintap       = findViewById(R.id.sigintap);

        fAuth  = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),CoachDashboard.class));
            finish();
        }

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fullname  =  mFullname.getText().toString().trim();
                String email     =  mEmail3.getText().toString().trim();
                String Password  = mPassword3.getText().toString().trim();
                String Phone     = mPhone.getText().toString().trim();
                String Sport     = mSport.getText().toString().trim();
                String Age       = mAge.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    mEmail3.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    mPassword3.setError("Password is required.");
                    return;
                }
                if(Password.length() < 6) {
                    mPassword3.setError("Password must be >= 6 Characters");
                    return;
                }

                //register the user in fire base

                fAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CoachSignup.this, "User Created", Toast.LENGTH_SHORT).show();
                            coachID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Coach users").document(coachID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fullname",Fullname);
                            user.put("email",email);
                            user.put("password",Password);
                            user.put("Age",Age);
                            user.put("Sport",Sport);
                            user.put("Phone",Phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: user Profile is  created for "+ coachID);

                                }
                            }) .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Log.d(TAG,"onFailure: " + e.toString());

                                }
                            }) ;
                            startActivity(new Intent(getApplicationContext(), CoachDashboard.class));

                        }else {
                            Toast.makeText(CoachSignup.this, "Error  !  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mSigintap.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CoachLogin.class)));




    }
}