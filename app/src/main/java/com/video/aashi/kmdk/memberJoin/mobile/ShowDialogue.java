package com.video.aashi.kmdk.memberJoin.mobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.video.aashi.kmdk.menunames.MenuStrings;

public class ShowDialogue extends AlertDialog.Builder {
    MenuStrings menuStrings;
    Context context;

    public ShowDialogue(@NonNull Context context) {
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
            setTitle("This number is not accepted by party members?");
        }
        else
        {
            setTitle("நீங்கள் மொழியை மாற்ற விரும்புகிறீர்களா?");
        }
        return super.show();
    }
    void  showDialogue()
    {
        menuStrings = new MenuStrings(context);
        if (menuStrings.getSharedPreferences())
        {
            setTitle("This number is not ");
        }
        else
        {
            setTitle("நீங்கள் மொழியை மாற்ற விரும்புகிறீர்களா?");
        }
    }
    public void clicked()
    {

    }
}
