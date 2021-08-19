package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    private TextView username;
    private TextView email;
    private TextView phonenumber;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        username    = (TextView) findViewById(R.id.userlbl);
        email       = (TextView) findViewById(R.id.emaillbl);
        phonenumber = (TextView) findViewById(R.id.phonelbl);
        clearlabels();
        email.setText(email.getText() +getIntent().getStringExtra("email"));
        username.setText(username.getText()+getIntent().getStringExtra("username"));       
        phonenumber.setText(phonenumber.getText() +getIntent().getStringExtra("phone"));
        
    }

    private void clearlabels() {
        email.setText("E-mail :");
        username.setText("Username :");
        phonenumber.setText("Phonenumber :");
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}