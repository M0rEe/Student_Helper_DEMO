package com.example.javaapp;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class User {
    private String username;
    private String phonenumber;
    private String email;
    private String password;
    private boolean gender;

    public User(String username, String phonenumber, String email, String password, boolean gender) {
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }



    public String getUsername() {
        return username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isGender() {
        return gender;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkFormatted (User u, EditText email, EditText password){
        if(TextUtils.isEmpty(u.email)){
            email.setError("Email not Found");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(u.email).matches()){
            email.setError("Email Formatted not eligible ");
            return false;
        }
        if(TextUtils.isEmpty(u.password)){
            password.setError("password not Found");
            return false;
        }
        if(u.password.length()<8){
            password.setError("password length should be more than 8 characters");
            return false;
        }
        return true;
    }

    public boolean checkFormatted (@NotNull User u, EditText email, EditText password, EditText phonenumber){
        if(TextUtils.isEmpty(u.email)){
            email.setError("Email not Found");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(u.email).matches()){
            email.setError("Email Formatted not eligible ");
            return false;
        }
        if(TextUtils.isEmpty(u.password)){
            password.setError("password not Found");
            return false;
        }
        if(u.password.length()<8){
            password.setError("password length should be more than 8 characters");
            return false;
        }
        if(TextUtils.isEmpty(u.phonenumber)){
            phonenumber.setError("Phone number  not Found");
            return false;
        }
        if(u.phonenumber.length()<11){
            phonenumber.setError("Phone number is not correct ");
            return false;
        }
        return true;
    }
}
