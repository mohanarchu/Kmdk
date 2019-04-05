package com.video.aashi.kmdk.downloads;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.video.aashi.kmdk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Downloads extends Fragment  {


    public Downloads() {
        // Required empty public constructor
    }

    @BindView(R.id.votersRegister)
    TextView voterss;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_downloads, container, false);
        ButterKnife.bind(this,view);
      voterss.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             getFragmentManager().beginTransaction().replace(R.id.downloadContainer,new DownloadForm()).addToBackStack(null).commit();


         }
     });

       return  view;
    }


}
