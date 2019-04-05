package com.video.aashi.kmdk.Members.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.video.aashi.kmdk.Cmplaints.AllComplaints;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.Members.advertisements.Advertidements;
import com.video.aashi.kmdk.Members.advertisements.list.AdList;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.MainMember;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.contact.Contact;
import com.video.aashi.kmdk.downloads.Downloads;
import com.video.aashi.kmdk.events.Events;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.MembermainText;
import com.video.aashi.kmdk.tabs.Gallery;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberHome extends Fragment implements View.OnClickListener ,HomePresenter{

    @BindView(R.id.advertise)
    CardView advertide;
    @BindView(R.id.memEvents)
    CardView memEents;
    @BindView(R.id.memGallary)
    CardView memGallry;
    @BindView(R.id.memMember)
    CardView memMember;

    @BindView(R.id.memNewCom)
     TextView memNewCom;
    @BindView(R.id.memNewMem)
     TextView memNewMem;
    @BindView(R.id.memSeeAll)
      TextView memSeeAll;
    @BindView(R.id.memHomeGal)
       TextView gallery;
    @BindView(R.id.memHomeAd)
            TextView advertise;
    @BindView(R.id.memHomeEvents)
            TextView events;
    @BindView(R.id.memHomeMem)
            TextView memberss;
    @BindView(R.id.memHomeAds)
    TextView advertisse;
    @BindView(R.id.complaintsNew)
    TextView complaints;
    @BindView(R.id.homeDownloads)
    TextView downloads;
    @BindView(R.id.homeContact)
    TextView contact;
    @BindView(R.id.homecontactAdmins)
    CardView  contactADmin;
    @BindView(R.id.homeDown)
    CardView homwDownloads;

    @BindView(R.id.homeComplaint)
    CardView  homeComplaint;
    @BindView(R.id.homeAdvertise)
    CardView homeAdvertise;

    ArrayList<MembermainText> membermainTexts;
    MenuStrings menuStrings;
    UserSession userSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_member_home, container, false);
        ButterKnife.bind(this,view);
       advertide.setOnClickListener(this);
       memEents.setOnClickListener(this);
       memMember.setOnClickListener(this);
       memGallry.setOnClickListener(this);
       homwDownloads.setOnClickListener(this);
       contactADmin.setOnClickListener(this);
       homeAdvertise.setOnClickListener(this);
       homeComplaint.setOnClickListener(this);
       menuStrings = new MenuStrings(getActivity());
       menuStrings.getSharedPreferences();
       membermainTexts = menuStrings.getMembermainTexts();
       userSession = new UserSession(getActivity());
       if (menuStrings.getSharedPreferences())
       {
           downloads.setText("Downloads");
           contact.setText("Contact Admin");
       }
       else
       {
           downloads.setText("பதிவிறக்கம்");
           contact.setText(getString(R.string.contact_admin));
       }
       for (int i=0;i<membermainTexts.size() ;i++)
       {
           complaints .setText(membermainTexts.get(i).getNewComplaints());
           memSeeAll.setText(membermainTexts.get(i).getSeeAll());
           memNewMem.setText(membermainTexts.get(i).getNewMembers());
           gallery.setText(membermainTexts.get(i).getGallery());
           events.setText(membermainTexts.get(i).getEvents());
           advertise.setText(membermainTexts.get(i).getAdvertisements());
           memberss.setText(membermainTexts.get(i).getMembers());
           advertisse.setText(membermainTexts.get(i).getAdvertisements());
           MembersMain.homepages.setTitle(membermainTexts.get(i).getHomepage());
           MembersMain.parties.setTitle(membermainTexts.get(i).getPartyAffialation());
           MembersMain.complaints.setTitle(membermainTexts.get(i).getComplaints());
           MembersMain.activities.setTitle(membermainTexts.get(i).getActivities());
       }
       if (userSession.isIfOfficial() && userSession.isCanView())
       {
           memMember.setVisibility(View.VISIBLE);
       }
       else
       {
           memMember.setVisibility(View.GONE);
       }
       return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.advertise:
                showFragments(new Advertidements());
               break;
            case R.id.memEvents:
                showFragments(new Events());
                break;
            case R.id.memGallary:
                Gallery gallery = new Gallery();
                Bundle bundle =new Bundle();
                bundle.putString("key","1");
                gallery.setArguments(bundle);
                showFragments(gallery);
                break;
            case R.id.memMember:

                showFragments(new MainMember());
                break;
            case R.id.homecontactAdmins:
                showFragments(new Contact());
                break;
            case R.id.homeDown:
                showFragments(new Downloads());
                break;
            case R.id.homeComplaint:
                MemberView.FLAG =2;
                showFragments(new AllComplaints());
                break;
            case R.id.homeAdvertise:
                showFragments(new AdList());
                break;
        }
    }
    void showFragments(Fragment fragment)
    {
        getFragmentManager().beginTransaction().replace(R.id.containers, fragment).addToBackStack(null).commit();
        MembersMain.mBottomNav.setVisibility(View.GONE);

    }
    public  void showfrag()
    {
      //  getFragmentManager().beginTransaction().replace(R.id.containers, fragment).addToBackStack(null).commit();
    }
    @Override
    public void showFrag(Fragment fragment) {
    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK)
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                        // Setting Dialog Title
                        alertDialog.setTitle("Exit alert");
                        alertDialog.setMessage("Do You want Exit??");
                        alertDialog .setIcon(R.drawable.ic_error_red_a400_24dp);


                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {

                                dialog.cancel();
                            }
                        });


                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                startActivity(intent);


                            }
                        });


                        alertDialog.show();
                    }
                    else
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                        alertDialog.setMessage("நிச்சயமாக வெளியேற விரும்புகிறீர்களா?");
                        alertDialog .setIcon(R.drawable.ic_error_red_a400_24dp);


                        alertDialog.setNegativeButton("இல்லை", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {

                                dialog.cancel();
                            }
                        });


                        alertDialog.setPositiveButton("ஆம்", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                startActivity(intent);


                            }
                        });


                        alertDialog.show();
                    }

                    return true;
                }





                return false;
            }
        });
    }
}
