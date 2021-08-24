package com.example.javaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button Registerationbtn;
    private Button loginbtn;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private String emailStr;
    private String passStr;
    private User temp_login_usr_checker;
    private DbHelper DB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Registerationbtn = (Button) findViewById(R.id.loginbtn);
        loginbtn         = (Button) findViewById(R.id.loginbtn);
        email            = (EditText) findViewById(R.id.emailtxt);
        password         = (EditText) findViewById(R.id.passtxt);
        mAuth            = FirebaseAuth.getInstance();


    }


    public void onbtnclick(View view) {
        emailStr               = email.getText().toString().trim();
        passStr                = password.getText().toString();
        temp_login_usr_checker = new User(emailStr,passStr);
        DB                     = new DbHelper(this);




        if(!temp_login_usr_checker.checkFormatted(temp_login_usr_checker,email,password)){
            return;
        }
        Cursor res = DB.getdata(emailStr);
        if (res.getCount() == 0){
            Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            return;
        }

        //checking log-in info for user
        mAuth.signInWithEmailAndPassword(emailStr,passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "User logged in Successfully ", Toast.LENGTH_SHORT).show();
                    // passing data between activities till we  have database so we can retrieve the data from it
                    Intent intent =new Intent(getApplicationContext(),Dashboard.class);
                    intent.putExtra("email",temp_login_usr_checker.getEmail());
//                    intent.putExtra("pass",temp_login_usr_checker.getPassword());
//                    intent.putExtra("phone",res.getString(0));
//                    intent.putExtra("username",res.getString(1));

                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Error occurred "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void gotoreg(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistrtionActivity.class);
        startActivity(intent);
        finish();
    }
}