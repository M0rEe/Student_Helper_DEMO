package com.example.javaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
    private String phonenumberStr;
    private String usernameStr;
    private User temp_user;
    private boolean genderbool;
/// Database setup
    private DbHelper DB;
    private boolean check_insertion;

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


        DB       = new DbHelper(this);

        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
            finish();
        }

    }

    public void regOnClick(View view) {
        emailStr         = email.getText().toString().trim();
        passStr          = password.getText().toString();
        phonenumberStr   = phone.getText().toString();
        usernameStr      = username.getText().toString().trim();
        genderbool       = gender.isChecked();
        temp_user        = new User(usernameStr,phonenumberStr,emailStr,passStr,genderbool);


        if(!temp_user.checkFormatted(temp_user,email,password,phone)){
            return;
        }

        check_insertion  = DB.insert_userdata(usernameStr,emailStr,phonenumberStr,passStr);


        if (check_insertion == true ){
            Toast.makeText(this, "Successfully Insertion  ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Failed Insertion  ", Toast.LENGTH_SHORT).show();
        }

    //registering user to the firebase data
        mAuth.createUserWithEmailAndPassword(emailStr,passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrtionActivity.this, "User Registered Successfully ", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(getApplicationContext(),Dashboard.class);
                    //passing data
                    intent.putExtra("email",temp_user.getEmail());
                    intent.putExtra("pass",temp_user.getPassword());
                    intent.putExtra("phone",temp_user.getPhonenumber());
                    intent.putExtra("username",temp_user.getUsername());

                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(RegistrtionActivity.this, "Error occurred "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}