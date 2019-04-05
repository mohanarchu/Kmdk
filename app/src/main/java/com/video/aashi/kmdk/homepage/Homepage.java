package com.video.aashi.kmdk.homepage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.Cmplaints.Complaints;
import com.video.aashi.kmdk.Locations.states.States;
import com.video.aashi.kmdk.Login.LoginActivity;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.events.EventList;
import com.video.aashi.kmdk.events.EventModel;
import com.video.aashi.kmdk.events.EventPresenter;
import com.video.aashi.kmdk.events.Events;
import com.video.aashi.kmdk.memberJoin.MemberRegister;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.HomepageTexts;
import com.video.aashi.kmdk.myview.ShowPic;
import com.video.aashi.kmdk.tabs.Gallery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.video.aashi.kmdk.MainActivity.titleVisible;

public class Homepage extends Fragment implements View.OnClickListener,EventPresenter {

    @BindView(R.id.images)
    RecyclerView images;
    @BindView(R.id.showGallary)
    CardView showGallay;
    @BindView(R.id.showEvents)
    CardView showEvents;
    @BindView(R.id.newMember)
    ImageView newMember;
    @BindView(R.id.newComplaint)
    ImageView newComplaint;
    @BindView(R.id.newMemText)
    TextView newMemText;
    @BindView(R.id.newCompText)
    TextView newComText;
    @BindView(R.id.gallaryText)
    TextView galaryText;
    @BindView(R.id.eventText)
    TextView eventText;
    MenuStrings menuStrings;
    @BindView(R.id.homeRecycle)
     RecyclerView homeRecycler;
    String[] allStrings;
    ArrayList<HomepageTexts> homepageTexts;
    States states;
    EventModel eventModel;
    ProgressDialog progressDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this,view);

        FloatingActionButton fab = (FloatingActionButton)view. findViewById(R.id.login);
        homepageTexts = new ArrayList<>();
        titleVisible.setVisibility(View.GONE);
        menuStrings = new MenuStrings(getActivity());
        menuStrings.getSharedPreferences();
        menuStrings.getStringArray();
        homepageTexts = menuStrings.getAllstrings();
        getAlTexts();
        LinearLayoutManager layoutManagerCenter
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        images.setLayoutManager(layoutManagerCenter);
        SnapHelper snapHelperCenter = new LinearSnapHelper();
        snapHelperCenter.attachToRecyclerView(images);
        eventModel = new EventModel(getActivity(),Homepage.this );
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecycler.setLayoutManager(layoutManager);
        eventModel.getEvents();
        images.setAdapter(new ImageAdapter(getActivity()));

        showGallay.setOnClickListener(this);
        showEvents.setOnClickListener(this);
        newMember.setOnClickListener(this);
        newComplaint.setOnClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  states.getStateList();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
        titleVisible.setVisibility(View.VISIBLE);
        AppBarLayout mAppBarLayout = (AppBarLayout)view. findViewById(R.id.app_bar);
//        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
//            {
//                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
//                {
//                    titleVisible.setVisibility(View.VISIBLE);
//
//                }
//                else
//                {
//                    titleVisible.setVisibility(View.GONE);
//
//                }
//            }
//        });
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.showGallary:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new Gallery()).addToBackStack(null).commit();
                titleVisible.setVisibility(View.VISIBLE);
                break;
            case R.id.showEvents:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new Events()).addToBackStack(null).commit();
                titleVisible.setVisibility(View.VISIBLE);
                break;
            case R.id.newComplaint:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new Complaints()).addToBackStack(null).commit();
                titleVisible.setVisibility(View.VISIBLE);
                break;
            case R.id.newMember:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new MemberRegister()).addToBackStack(null).commit();
                titleVisible.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void showProgress() {

        progressDialog = new ProgressDialog(getActivity());

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void progressMessage(String message) {


    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void showLists(ArrayList<EventList> eventLists) {
        if (eventLists.size() <= 3)
        {
            homeRecycler.setAdapter(new Adapter(eventLists));
        }
        else
        {
            for (int i =0;i<eventLists.size();i++)
            {
            }
            Adapter  adapter= new Adapter(eventLists);

        }



    }

    class ImageAdapter extends RecyclerView.Adapter<Homepage.ViewHolder>
    {
       private Context context;
        public ImageAdapter(Context context)
        {
            this.context = context;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.imagefile, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }


      public   class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageFile)
          ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Glide.with(getContext())
                    .load(R.drawable.ic_splash)
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean change = menuStrings.getSharedPreferences();

                    if (change)
                    {
                        menuStrings.getToastTamil("Clicked");
                    }
                    else
                    {
                        menuStrings.getToastTamil("உறுதிசெய்யப்பட்டது");
                    }
                }
            });
        }
    }
    void  getAlTexts()
    {
        for(int i=0;i<homepageTexts.size();i++)
        {
            newMemText.setText(homepageTexts.get(i).getMember() );
            newComText.setText(homepageTexts.get(i).getComplaints());
            galaryText.setText(homepageTexts.get(i).getGallary());
            Log.i("Tag","Gallery"+ homepageTexts.get(i).getGallary() );
            eventText.setText(homepageTexts.get(i).getEvents() );
        }
    }

    public class Adapter extends RecyclerView.Adapter<ViewHolders>
    {
        ArrayList<EventList> eventLists;
        public Adapter(ArrayList<EventList> eventLists)
        {
            this.eventLists = eventLists;
        }
        View view;
        @NonNull
        @Override
        public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           view = LayoutInflater.from(getActivity()).inflate(R.layout.homeevents, viewGroup, false);
            return new ViewHolders(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolders viewHolder, int i) {

            EventList event = eventLists.get(i);
            viewHolder.heading.setText(event.getEventname());
            if (event.getEventimage() != null && event.getEventimage().isEmpty() )
            {

                viewHolder.images.setVisibility(View.GONE);
            }
            else
            {
                viewHolder.images.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load("http://159.89.163.3:5000/API/Uploads/Events/"+event.getEventimage()).
                        into(viewHolder.images);
            }
            viewHolder.images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPic showPic = new ShowPic(getActivity(),"http://159.89.163.3:5000/API/Uploads/Events/"+event.getEventimage()
                    , view  );
                    showPic.initiateLayout();
                }
            });
            viewHolder.eventDate.setText(getDate(event.getDate()));
            viewHolder.eventTimes.setText(getTime(event.getDate()   ));
            viewHolder.places.setText(event.getPlace());
        }
        @Override
        public int getItemCount() {
            if (eventLists.size() <=3)
            {
                return eventLists.size();
            }
            else
            {
                return 3;
            }
        }
    }
    public class ViewHolders extends RecyclerView.ViewHolder {
       @BindView(R.id.eventHead)
       TextView heading;
       @BindView(R.id.eventTimes)
       TextView eventTimes;
       @BindView(R.id.eventDa)
        TextView eventDate;

       @BindView(R.id.eventImaes)
       ImageView images;
        @BindView(R.id.eventPlaces)
        TextView places;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public  String getTime(String s)
    {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat dayFormas =  new SimpleDateFormat("hh:mm a");


        Date d = null;
        try
        {
            d = input.parse(s);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        String formatted = dayFormas.format(d);

        return  formatted;

    }
    public String getDate(String s)
    {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat out = new SimpleDateFormat("dd");
        SimpleDateFormat output = new SimpleDateFormat("MMM");
        SimpleDateFormat outputs= new SimpleDateFormat("yyyy");

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
        String forma = outputs.format(d);
        String fors = out.format(d);
        //  viewHoler.creatdat.setText(formatted);
        return  fors+ " "+ formatted+", "+ forma;
    }
}
