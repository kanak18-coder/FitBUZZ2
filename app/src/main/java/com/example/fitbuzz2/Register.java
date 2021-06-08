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

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mEmail,mPassword,mLearnername,mPhonenum;
    Button mRegister,mTaphere;
    TextView mLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail      = findViewById(R.id.email);
        mPassword   = findViewById(R.id.Password);
        mRegister   = findViewById(R.id.Register);
        mLogin      = findViewById(R.id.Login);
        mTaphere    = findViewById(R.id.button2);
        mLearnername = findViewById(R.id.learnername);
        mPhonenum   = findViewById(R.id.pnum);

        fAuth  = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),NewMainpage.class));
            finish();
        }

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String Password =mPassword.getText().toString().trim();
                String learnername =mLearnername.getText().toString().trim();
                String pnum =mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    mPassword.setError("Password is required.");
                    return;
                }
                if(Password.length() < 6) {
                    mPassword.setError("Password must be >= 6 Characters");
                    return;
                }

                //register the user in fire base

                fAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Full name",learnername);
                            user.put("Phone number",pnum);
                            user.put("email",email);
                            user.put("password",Password);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: user Profile is  created for "+ userID);

                                }
                            }) .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Log.d(TAG,"onFailure: " + e.toString());

                                }
                            }) ;


                            startActivity(new Intent(getApplicationContext(), NewMainpage.class));

                        }else {
                            Toast.makeText(Register.this, "Error  !  " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });



        mTaphere.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CoachSignup.class));
            }
        });
    }

    }
