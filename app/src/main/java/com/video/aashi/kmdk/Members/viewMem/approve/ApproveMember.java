package com.video.aashi.kmdk.Members.viewMem.approve;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;

import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.menunames.MenuStrings;

public class ApproveMember extends AlertDialog.Builder {

    Context context;
    MenuStrings strings;
    public ApproveMember(Context context) {
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
            setTitle("Are you sure you want to approve this member ?");
           // setPositiveButton("");
        }
        else
        {
            setTitle("நிச்சயமாக நீங்கள் இந்த உறுப்பினரை அங்கீகரிக்க வேண்டுமா?");
        }
        return super.show();
    }
}
