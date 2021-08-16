package com.example.javaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrtionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username;
    private EditText password;
    private EditText email;
    private Button regbtn;
    private String emailStr;
    private String passStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrtion);
        mAuth    = FirebaseAuth.getInstance();
        username = findViewById(R.id.usrtxt);
        password = findViewById(R.id.passtxt);
        email    = findViewById(R.id.emailtxt);
        regbtn   = findViewById(R.id.regbtn);


        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
            finish();
        }

    }

    public void regOnClick(View view) {
        emailStr = email.getText().toString().trim();
        passStr  = password.getText().toString().trim();

        //checking for email and password existence
        if(TextUtils.isEmpty(emailStr)){
            email.setError("Email not Found");
            return;
        }
        if(TextUtils.isEmpty(passStr)){
            password.setError("password not Found");
        }
        if(passStr.length()<8){
            password.setError("password length should be more than 8 characters");
        }
        //registering user to the firebase data
        mAuth.createUserWithEmailAndPassword(emailStr,passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrtionActivity.this, "User Registered Successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();

                }else{
                    Toast.makeText(RegistrtionActivity.this, "Error occurred "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}