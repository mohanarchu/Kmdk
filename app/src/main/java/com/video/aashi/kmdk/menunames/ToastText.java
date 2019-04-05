package com.video.aashi.kmdk.menunames;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ToastText extends Toast {

    Context context;
    String toast;

    public ToastText(Context context,String string) {
        super(context);
        this.context = context;
        this.toast = string;

    }

    @Override
    public void setText(int resId) {
        super.setText(resId);
    }

    @Override
    public void show() {
        super.show();
    }
}
