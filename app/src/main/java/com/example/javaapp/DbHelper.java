package com.example.javaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DbHelper extends SQLiteOpenHelper {
    public DbHelper( Context context) {
        super(context,"UserData.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails (username TEXT ,email TEXT , phonenumber TEXT Primary key , password TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
        onCreate(db);
    }

    public boolean insert_userdata (String usrname,String mail,String phone,String passwrd){
        SQLiteDatabase DB           = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",usrname);
        contentValues.put("email",mail);
        contentValues.put("phonenumber",phone);
        contentValues.put("password",passwrd);
        try{
            long result = DB.insertOrThrow("Userdetails",null,contentValues);
                if(result == -1){
                    return false;
                }else {
                    return true;
                }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean update_userdata (String usrname,String mail,String phone,String passwrd){
        SQLiteDatabase DB           = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",usrname);
        contentValues.put("email",mail);
        contentValues.put("password",passwrd);

        Cursor cursor = DB.rawQuery("select * from Userdetails where phonenumber = ?",new String[]{phone});

        if(cursor.getCount()>0){

            long result = DB.update("Userdetails",contentValues,"phonenumber =?",new String[]{phone});
            if(result == -1){
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }
    }


    public boolean delete_userdata (String phone){
        SQLiteDatabase DB  = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from Userdetails where phonenumber = ?", new String[]{phone});

        if(cursor.getCount()>0){

            long result = DB.delete("Userdetails","phonenumber =?",new String[]{phone});
            if(result == -1){
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }
    }


    public Cursor getdata (String mail){
        SQLiteDatabase DB  = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from Userdetails where email =?", new String[] {mail});

       return cursor;
    }



}
