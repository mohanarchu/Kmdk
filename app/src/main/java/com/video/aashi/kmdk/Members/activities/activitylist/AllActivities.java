package com.video.aashi.kmdk.Members.activities.activitylist;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.Members.activities.Activities;
import com.video.aashi.kmdk.Members.home.MemberHome;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.Members.viewMem.memView.ShowMember;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.myview.ShowPic;
import com.video.aashi.kmdk.myview.ShowVideo;
import com.video.aashi.kmdk.view.LoadImage;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllActivities extends Fragment implements ActPresent
{
    @BindView(R.id.createText)
    TextView createtText;
    @BindView(R.id.createActivity)
    CardView createt;
    @BindView(R.id.activityRecycle)
    RecyclerView activityRecycle;
    ProgressDialog progressDialog;
    MenuStrings menuStrings;
    Activ activ;
    UserSession userSession;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_activities, container, false);
        ButterKnife.bind(this,v);
        activ = new Activ(getActivity(),AllActivities.this);
        activ.getList();
        menuStrings = new MenuStrings(getActivity());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityRecycle.setLayoutManager(layoutManager);
        userSession = new UserSession(getActivity());
        if (menuStrings.getSharedPreferences())
        {
            createtText.setText("Create");
        }
        else
        {
            createtText.setText("உருவாக்க");
        }
        createt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.containers,
                       new Activities()).addToBackStack(null).commit();
            }
        });
        return v;
    }
    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
    }
    @Override
    public void hideProgress() {
        progressDialog.hide();
    }
    @Override
    public void progressMessage(String message) {
          progressDialog.setMessage(message);
    }
    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showActs(ArrayList<ActList> actLists) {
        activityRecycle.setAdapter(new Adapter(actLists));
    }
  public class Adapter extends RecyclerView.Adapter<ViewHolder>
  {
      ArrayList<ActList> actLists;
      public Adapter(ArrayList<ActList> actLists)
      {
          this .actLists = actLists;
      }
      View view;
      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.activitydesign, viewGroup, false);
          return new ViewHolder(view);
      }
      @Override
      public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
          ActList act = actLists.get(i);
          viewHolder.actName.setText(act.getName());
          viewHolder.actDes.setText(act.getDes());
          viewHolder.actplace.setText(act.getPlace());
          viewHolder.time.setText(getDate(act.getDate()));
          viewHolder.actType.setText(act.getType());
          if (act.getImage() == null) {
              viewHolder.image.setVisibility(View.GONE);
              viewHolder.removeImage.setVisibility(View.GONE);
          }
          else
          {
              viewHolder.image.setVisibility(View.VISIBLE);
              Glide.with(getActivity()).load(APIUrl.Api+"Uploads/Activity/Image/" + act.getImage()).
                      into(viewHolder.image);
              viewHolder.removeImage.setVisibility(View.VISIBLE);
          }
          viewHolder.removeImage.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  ShowPic showPic = new ShowPic(getActivity(), APIUrl.Api+"Uploads/Activity/Image/" + act.getImage()
                          , view);
                  showPic.initiateLayout();
              }
          });
          if (act.getVideo() != null) {

              String video = APIUrl.Api+"Uploads/Activity/Video/";
              viewHolder.remoeVideo.setVisibility(View.VISIBLE);
              new LoadImage(viewHolder.videoView, video + act.getVideo()).execute();
          }
          else
          {
              viewHolder.videoView.setVisibility(View.GONE);
              viewHolder.remoeVideo.setVisibility(View.GONE);
          }
          viewHolder.play.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String video = APIUrl.Api+"Uploads/Activity/Video/";
                  ShowVideo showVideo = new ShowVideo(getActivity(), video + act.getVideo(), view);
                  showVideo.initiatelayout(true);
              }
          });
          if(!userSession.isCanView() && !act.getUserId().equals(userSession.getId() ))
          {
              viewHolder.created.setVisibility(View.GONE);
           }
           else
           {
              viewHolder.created.setVisibility(View.VISIBLE);
              viewHolder.created.setText(act.getCreatedBy() );
           }
           viewHolder.created.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v)
              {
                  Intent intent = new Intent(getActivity(),ShowMember.class);
                  intent.putExtra("id", act.getUserId());
                  startActivity(intent);
              }
          });
      }
      @Override
      public int getItemCount() {
          return actLists.size();
      }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.actNames)
        TextView actName;
        @BindView(R.id.actType)
        TextView actType;
        @BindView(R.id.actDes)
        TextView actDes;
        @BindView(R.id.actCreate)
        TextView created;
        @BindView(R.id.actVideoImage)
        ImageView videoView;
        @BindView(R.id.actImage)
        ImageView image;
        @BindView(R.id.actPlace)
        TextView actplace;
        @BindView(R.id.actTme)
        TextView time;
        @BindView(R.id.goneLay)
        LinearLayout gonelay;
        @BindView(R.id.removeCard)
        CardView removeImage;
        @BindView(R.id.removeCards)
        CardView remoeVideo;
        @BindView(R.id.playButtons)
        ImageView play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public String getDate(String s)
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
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK)
                {
                    if (MemberView.FLAG != 2)
                    {
                        MembersMain.mBottomNav.setSelectedItemId(R.id.action_home);
                        MembersMain.mBottomNav.setVisibility(View.VISIBLE);
                        getFragmentManager().beginTransaction().replace(R.id.containers,new MemberHome()).commit();
                    }
                    else
                    {
                        MembersMain.mBottomNav.setSelectedItemId(R.id.action_home);
                        MembersMain.mBottomNav.setVisibility(View.VISIBLE);
                        getFragmentManager().beginTransaction().replace(R.id.containers,new MemberHome()).commit();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
