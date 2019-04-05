package com.video.aashi.kmdk.PreferenceValue;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreference {
    public  static final String MAIN_STRInG = "Logins";
    SharedPreferences sharedPreferences;
    Context context;
    boolean loginBool;
    public SharedPreference(Context context)
    {
        this.context = context;
    }
    public boolean isLoginBool() {
        sharedPreferences = context.getSharedPreferences(SharedPreference.MAIN_STRInG ,Context.MODE_PRIVATE);
        loginBool = sharedPreferences.getBoolean("isLogin",false);
        return loginBool;
    }
    public void setSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(SharedPreference.MAIN_STRInG ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",true);
        editor.apply();
    }
    public void removeLogin()
    {
        sharedPreferences = context.getSharedPreferences(SharedPreference.MAIN_STRInG ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        SharedPreferences sharedPreferencess = context.getSharedPreferences("states",Context.MODE_PRIVATE);
        sharedPreferencess.edit().clear().apply();
        sharedPreferences.edit().clear().apply();
        SharedPreferences sharedPreferenc = context.getSharedPreferences("Users",Context.MODE_PRIVATE);
         sharedPreferenc.edit().clear().apply();
        editor = sharedPreferences.edit();
        editor.clear();
        editor.putBoolean("isLogin",false);
        editor.apply();
      }
}
