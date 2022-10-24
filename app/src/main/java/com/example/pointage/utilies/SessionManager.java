package com.example.pointage.utilies;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SessionManager(Context context){

        sharedPreferences = context.getSharedPreferences("AppKey",0);
        editor = sharedPreferences.edit();
        editor.apply();

    }


    public  void setPassword(String password){

        editor.putString("KEY_PASSWORD",password);
        editor.commit();

    }

    public  String getPassword(){

        return sharedPreferences.getString("KEY_PASSWORD","");

    }


}

