package com.video.aashi.kmdk.events;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.Api;
import com.video.aashi.kmdk.Login.classfiles.Login;
import com.video.aashi.kmdk.Members.activities.activitylist.ActList;
import com.video.aashi.kmdk.Members.activities.activitylist.AllActivities;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.memView.ShowMember;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.myview.ShowPic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Events extends Fragment implements  EventPresenter {


    @BindView(R.id.eventsRecycler)
    RecyclerView recyclerView;
    ProgressDialog  progressDialog;

    EventModel eventModel;
    MenuStrings  menuStrings;
    UserSession userSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this,view);
        eventModel = new EventModel(getActivity(),Events.this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        menuStrings=  new MenuStrings(getActivity());
        userSession = new UserSession(getActivity());
        eventModel.getEvents();
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
    public void showToast(String toast) {

        Toast.makeText(getActivity(),toast,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLists(ArrayList<EventList> eventLists) {

        recyclerView.setAdapter(new Adapter(eventLists));

    }
    public class Adapter extends RecyclerView.Adapter<ViewHolder>
    {
        ArrayList<EventList> actLists;
        public Adapter(ArrayList<EventList> actLists)
        {
            this .actLists = actLists;
        }
        View view;
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

          view = LayoutInflater.from(getActivity()).inflate(R.layout.eventdesign, viewGroup, false);
            return new ViewHolder(view);


        }


        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            EventList act = actLists.get(i);


            if (menuStrings.getSharedPreferences())
            {
                viewHolder.guestText.setText("Chief Guest");
                if( NoonCheck(act.getDate()).equals("AM")  )
                {
                    viewHolder.showTime.setText( "Morning "+ getTime(act.getDate())  );
                }
                else
                {
                    viewHolder.showTime.setText( "Evening "+ getTime(act.getDate())  );
                }
            }
            else
            {
                if( NoonCheck(act.getDate()).equals("AM")  )
                {
                    viewHolder.showTime.setText( "காலை "+ getTime(act.getDate()) + " மணி" );
                }
                else
                {
                    viewHolder.showTime.setText( "பிற்பகல் "+ getTime(act.getDate())+  " மணி" );
                }
            }
            viewHolder.eventtName.setText(act.getEventname());
            viewHolder.contact.setText(act.getContactname() +": ");
            viewHolder.actplace.setText(act.getPlace());
            viewHolder.time.setText(getTime(act.getDate()));
            viewHolder.date.setText(getDay(act.getDate()));
            viewHolder.eventDate.setText(getDate(act.getDate()));
            viewHolder.guest.setText(act.getChief());
            viewHolder.number.setText(act.getNumber());
            if (userSession.getId().isEmpty())
            {
                       viewHolder.created.setVisibility(View.GONE);
              }


            if (act.getEventimage() != null && act.getEventimage().isEmpty() )
            {
                viewHolder.image.setVisibility(View.GONE);
            }
            else
            {
                viewHolder.image.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(APIUrl.Api+"Uploads/Events/"+act.getEventimage()).
                        into(viewHolder.image);
            }

            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowPic showPic = new ShowPic(getActivity(),APIUrl.Api+"Uploads/Events/"+act.getEventimage()
                     , view  );
                    showPic.initiateLayout();

                }
            });
//            if (userSession.getId().isEmpty())
//            {
//                    viewHolder.created.setVisibility(View.GONE);
//            }
//            else
//            {
//                viewHolder.created.setVisibility(View.VISIBLE);
//            }
            viewHolder.created.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ShowMember.class);
                    Log.i("Events","UserId"+ act.getCrId());
                    intent.putExtra("id",act.getCrId());
                    startActivity(intent);
                }
            });

            viewHolder.created.setText(act.getCreated());
        }
        @Override
        public int getItemCount() {
            return actLists.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.eventName)
        TextView eventtName;
        @BindView(R.id.eventContact)
        TextView contact;
        @BindView(R.id.cnNumber)
        TextView number;
        @BindView(R.id.eventDates)
        TextView date;
        @BindView(R.id.eventImage)
        ImageView image;
        @BindView(R.id.eventDate)
        TextView eventDate;
        @BindView(R.id.guests)
        TextView guest;
        @BindView(R.id.showTime)
        TextView showTime;
        @BindView(R.id.eventPlaces)
        TextView actplace;
        @BindView(R.id.eventTime)
        TextView time;
        @BindView(R.id.eventCreated)
        TextView created;
        @BindView(R.id.guestText)
        TextView guestText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public String getDate(String s)
    {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
        //  viewHoler.creatdat.setText(formatted);
        return  formatted+", "+ forma;
    }
   public String getDay(String s    )
   {
       SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
       SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

       Date d = null;
       try
       {
           d = input.parse(s);
       }
       catch (ParseException e)
       {
           e.printStackTrace();
       }
       String formatted = dayFormat.format(d);
       return  formatted;
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
    public  String NoonCheck(String s)
    {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat dayFormas =  new SimpleDateFormat("a");


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

}
