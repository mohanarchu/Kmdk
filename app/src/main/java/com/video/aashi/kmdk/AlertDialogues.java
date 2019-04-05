package com.video.aashi.kmdk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.video.aashi.kmdk.menunames.MenuStrings;

public class AlertDialogues extends AlertDialog.Builder {


    MenuStrings menuStrings;
    Context context;

    public AlertDialogues(@NonNull Context context) {
        super(context);
        this.context =context;
    }

    @Override
    public AlertDialog.Builder setTitle(int titleId) {
        return super.setTitle(titleId);
    }

    @Override
    public AlertDialog.Builder setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
        return super.setPositiveButton(textId, listener);
    }

    @Override
    public AlertDialog.Builder setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
        return super.setNegativeButton(textId, listener);
    }

    @Override
    public AlertDialog show() {
        menuStrings = new MenuStrings(context);
        if (menuStrings.getSharedPreferences())
        {
            setTitle("Are sure you want to change the language ?");
        }
        else
        {
            setTitle("நீங்கள் மொழியை மாற்ற விரும்புகிறீர்களா?");
        }
        return super.show();
    }
    public  void clicked()
    {
        boolean changes =context. getSharedPreferences("Menus",0).getBoolean("language",true);
        if (!changes)
        {
            SharedPreferences sharedPreferences =context. getSharedPreferences("Menus",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = sharedPreferences.edit();
            editor.putBoolean("language",true);
            editor.apply();
            Toast.makeText( context,"Language changed successfully..!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            SharedPreferences sharedPreferences =context. getSharedPreferences("Menus",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = sharedPreferences.edit();
            editor.putBoolean("language",false);
            editor.apply();
            Toast.makeText(context,"மொழி மாற்றப்பட்டது..!",Toast.LENGTH_SHORT).show();
        }
    }
}
