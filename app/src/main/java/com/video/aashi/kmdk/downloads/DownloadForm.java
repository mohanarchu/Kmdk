package com.video.aashi.kmdk.downloads;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video.aashi.kmdk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadForm extends Fragment {


       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download_form, container, false);
    }

}
