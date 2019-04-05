package com.video.aashi.kmdk.Members.complaints;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video.aashi.kmdk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialComplaints extends Fragment {


    public SocialComplaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_social_complaints, container, false);

      return view;
    }

}
