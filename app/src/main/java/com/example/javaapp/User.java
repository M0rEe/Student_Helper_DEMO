package com.example.javaapp;

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
                ", phonenumber=" + phonenumber +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }
}
