package com.video.aashi.kmdk;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity  {
    int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Resources res = getResources();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
             startActivity(mainIntent);
               finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
