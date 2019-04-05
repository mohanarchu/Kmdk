package com.video.aashi.kmdk.Members.viewMem.approve;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.video.aashi.kmdk.menunames.MenuStrings;

public class RemoveMem  extends AlertDialog.Builder{

    Context context;
    MenuStrings strings;
    public RemoveMem(Context context) {
        super(context);
        this.context = context;
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
        strings = new MenuStrings(context);
        if (strings.getSharedPreferences())
        {
            setTitle("Are you sure you want to reject this member ?");
            // setPositiveButton("");
        }
        else
        {
            setTitle("இந்த உறுப்பினரை நிராகரிக்க விரும்புகிறீர்களா??");
        }
        return super.show();
    }
}
