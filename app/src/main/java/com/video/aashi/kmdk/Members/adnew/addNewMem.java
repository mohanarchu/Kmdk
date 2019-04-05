package com.video.aashi.kmdk.Members.adnew;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video.aashi.kmdk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class addNewMem extends Fragment {


    public addNewMem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.addnewmember, container, false);
    }

}
