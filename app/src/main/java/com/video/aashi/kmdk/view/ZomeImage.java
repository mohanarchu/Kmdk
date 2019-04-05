/**
 * Copyright 2016 Jeffrey Sibbold
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.video.aashi.kmdk.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jsibbold.zoomage.ZoomageView;
import com.video.aashi.kmdk.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class ZomeImage extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private ZoomageView demoView;
    private View optionsView;
    private AlertDialog optionsDialog;
    URL url;
    String key ="1";

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        demoView = (ZoomageView)findViewById(R.id.demoView);
        if (getIntent().getExtras() != null)
        {

                byte[] byteArray = getIntent().getByteArrayExtra("file");
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                demoView.setImageBitmap(bmp);
        }
        prepareOptions();
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Zoom", "Error getting bitmap", e);
        }
        return bm;
    }
    private void prepareOptions() {
        optionsView = getLayoutInflater().inflate(R.layout.zoomage_options, null);
        setSwitch(R.id.zoomable, demoView.isZoomable());
        setSwitch(R.id.translatable, demoView.isTranslatable());
        setSwitch(R.id.animateOnReset, demoView.getAnimateOnReset());
        setSwitch(R.id.autoCenter, demoView.getAutoCenter());
        setSwitch(R.id.restrictBounds, demoView.getRestrictBounds());
        optionsView.findViewById(R.id.reset).setOnClickListener(this);
        optionsView.findViewById(R.id.autoReset).setOnClickListener(this);

        optionsDialog = new AlertDialog.Builder(this).setTitle("Zoomage Options")
                .setView(optionsView)
                .setPositiveButton("Close", null)
                .create();
    }

    private void setSwitch(int id, boolean state) {
        final SwitchCompat switchView = (SwitchCompat) optionsView.findViewById(id);
        switchView.setOnCheckedChangeListener(this);
        switchView.setChecked(state);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!optionsDialog.isShowing()) {
            optionsDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.zoomable:
                demoView.setZoomable(isChecked);
                break;
            case R.id.translatable:
                demoView.setTranslatable(isChecked);
                break;
            case R.id.restrictBounds:
                demoView.setRestrictBounds(isChecked);
                break;
            case R.id.animateOnReset:
                demoView.setAnimateOnReset(isChecked);
                break;
            case R.id.autoCenter:
                demoView.setAutoCenter(isChecked);
                break;
        }
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.reset) {
            demoView.reset();
        }
        else {
            showResetOptions();
        }
    }

    private void showResetOptions() {
        CharSequence[] options = new CharSequence[]{"Under", "Over", "Always", "Never"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                demoView.setAutoResetMode(which);
            }
        });

        builder.create().show();
    }
}
