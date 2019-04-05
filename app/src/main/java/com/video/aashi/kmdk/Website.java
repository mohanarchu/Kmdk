package com.video.aashi.kmdk;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Website extends Fragment {


   @BindView(R.id.website)
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_website, container, false);
        ButterKnife.bind(this,view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.kmdk.in");
        webView.setHorizontalScrollBarEnabled(false);

        return  view;
    }

}
