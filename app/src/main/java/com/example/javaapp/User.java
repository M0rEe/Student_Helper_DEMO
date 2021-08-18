package com.example.javaapp;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
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
}
