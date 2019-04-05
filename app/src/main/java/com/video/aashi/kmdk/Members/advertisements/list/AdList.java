package com.video.aashi.kmdk.Members.advertisements.list;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.Members.advertisements.Advertidements;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.homepage.Homepage;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.myview.ShowPic;
import com.video.aashi.kmdk.view.ZomeImage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdList extends Fragment implements AdListPresenter {


    public AdList() {
        // Required empty public constructor
    }
    @BindView(R.id.addRecycler)
    RecyclerView adRecycler;

    @BindView(R.id.nnewadAdds)
    CardView newAds;
    @BindView(R.id.newAddTexts)
    TextView texts;



     ProgressDialog progressDialog;
     AddListView addListView;
     MenuStrings menuStrings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ad_list, container, false);
        ButterKnife.bind(this,view);
        addListView = new AddListView(getActivity(),AdList.this);
        addListView.getLists();
        menuStrings = new MenuStrings(getActivity());
        if  (menuStrings.getSharedPreferences())
        {
            texts.setText("New Advertisements");
        }
        newAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.containers,new Advertidements()).addToBackStack(null).commit();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adRecycler.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void progressMessage(String message) {

         progressDialog.setMessage(message);
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(),string,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showAdds(ArrayList<AdvertiseList> adListResponses) {
        hideProgress();
        adRecycler.setAdapter(new AdverAdapter(getActivity(),adListResponses));

    }


    public class AdverAdapter extends RecyclerView.Adapter<ViewHolders>
    {
        ArrayList<AdvertiseList> advertiseLists;
        Context context;
        View view;
        public AdverAdapter(Context context,ArrayList<AdvertiseList> advertiseLists)
        {
            this.context = context;
            this.advertiseLists = advertiseLists;

        }

        @NonNull
        @Override
        public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           view = LayoutInflater.from(getActivity()).inflate(R.layout.advertisement, viewGroup, false);
            return new ViewHolders(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolders viewHolders, int i) {
            viewHolders.adhead.setText("Meeting");
            viewHolders.advertise.setText(advertiseLists.get(i).getAdvertise());
            viewHolders.place.setText(advertiseLists.get(i).getPlace());
            viewHolders.adTime.setText(getDate(advertiseLists.get(i).getCreatedat()));
            viewHolders.adMessage.setText(advertiseLists.get(i).getMessage());
            Glide.with(context).load( APIUrl.Api+"Uploads/Advertisement/" +
                    advertiseLists.get(i).getImage()).into(viewHolders.adImage);
            viewHolders.adImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ShowPic showPic= new ShowPic(context,APIUrl.Api+"Uploads/Advertisement/" +
                            advertiseLists.get(i).getImage(),view);
                    showPic.initiateLayout();

                }
            });
        }
        @Override
        public int getItemCount() {
            return advertiseLists.size();
        }
    }
    public static class ViewHolders extends RecyclerView.ViewHolder
    {
        @BindView(R.id.adHea)
        TextView adhead;
        @BindView(R.id.adTime)
        TextView adTime;
        @BindView(R.id.adds)
        TextView advertise;
        @BindView(R.id.addMessages)
        TextView adMessage;
        @BindView(R.id.addsPlace)
        TextView place;
        @BindView(R.id.adImages)
        ImageView adImage;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }  public String getDate(String s)
    {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try
        {
            d = input.parse(s);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        String formatted = output.format(d);
        //  viewHoler.creatdat.setText(formatted);
        return  formatted;
    }


}
