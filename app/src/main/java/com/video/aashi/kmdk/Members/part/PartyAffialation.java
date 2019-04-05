package com.video.aashi.kmdk.Members.part;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video.aashi.kmdk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartyAffialation extends Fragment {


    public PartyAffialation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_party_affialation, container, false);


        return  view;
    }
}
