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

public class LoginActivity extends AppCompatActivity {
    private Button Registerationbtn;
    private Button loginbtn;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private String emailStr;
    private String passStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Registerationbtn = (Button) findViewById(R.id.loginbtn);
        loginbtn         = (Button) findViewById(R.id.loginbtn);
        email         = (EditText) findViewById(R.id.emailtxt);
        password         = (EditText) findViewById(R.id.passtxt);
        mAuth            = FirebaseAuth.getInstance();

    }


    public void onbtnclick(View view) {
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

        //checking log in info for user
        mAuth.signInWithEmailAndPassword(emailStr,passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "User logged in Successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Error occurred "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void gotoreg(View view) {
        Intent intent = new Intent(this, RegistrtionActivity.class);
        startActivity(intent);
        finish();
    }
}