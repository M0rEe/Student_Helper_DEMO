package com.example.javaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
    private EditText phone;
    private Button regbtn;
    private Switch gender;
    private String emailStr;
    private String passStr;
    private String phonenumberInt;
    private String usernameStr;
    private User temp_user;
    private boolean genderbool;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrtion);
        mAuth    = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.usrtxt);
        password = (EditText) findViewById(R.id.passtxt);
        email    = (EditText) findViewById(R.id.emailtxt);
        phone    = (EditText) findViewById(R.id.phontxt);
        regbtn   = (Button) findViewById(R.id.regbtn);
        gender   = (Switch) findViewById(R.id.genderswtch);

        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
            finish();
        }

    }

    public void regOnClick(View view) {
        emailStr       = email.getText().toString().trim();
        passStr        = password.getText().toString().trim();
        phonenumberInt = phone.toString();
        usernameStr    = username.getText().toString().trim();
        genderbool     = gender.isChecked();
        temp_user      = new User(usernameStr,phonenumberInt,emailStr,passStr,false);

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
                    Toast.makeText(RegistrtionActivity.this, temp_user.toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();

                }else{
                    Toast.makeText(RegistrtionActivity.this, "Error occurred "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}