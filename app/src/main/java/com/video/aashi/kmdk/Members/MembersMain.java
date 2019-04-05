package com.video.aashi.kmdk.Members;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.video.aashi.kmdk.AlertDialogues;
import com.video.aashi.kmdk.Cmplaints.AllComplaints;
import com.video.aashi.kmdk.MainActivity;
import com.video.aashi.kmdk.Members.activities.Activities;
import com.video.aashi.kmdk.Members.activities.activitylist.AllActivities;
import com.video.aashi.kmdk.Members.adnew.addNewMem;
import com.video.aashi.kmdk.Members.complaints.SocialComplaints;
import com.video.aashi.kmdk.Members.home.HomePresenter;
import com.video.aashi.kmdk.Members.home.MemberHome;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.part.PartyAffialation;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.Members.viewMem.memView.ShowMember;
import com.video.aashi.kmdk.PreferenceValue.SharedPreference;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.downloads.Downloads;
import com.video.aashi.kmdk.logout.Expired;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.MembermainText;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MembersMain extends AppCompatActivity  {

    @BindView(R.id.adminTool)
    Toolbar toolbar;
    public static BottomNavigationView mBottomNav;
    private int mSelectedItem;
    ArrayList<MembermainText> membermainTexts;
    MenuStrings menuStrings;
    SharedPreference sharedPreference;
    private Menu menus;
    UserSession userSession;
    SharedPreferences sharedPreferences;
    boolean doubleBackToExitPressedOnce =false;
    SharedPreferences.Editor editors;
       public  static   int MAIN= 1;
    public  static  MenuItem homepages,parties,complaints,activities,downloads,changelan,contactadmin,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);;
        doubleBackToExitPressedOnce = false;
        sharedPreferences  = getSharedPreferences("states",Context.MODE_PRIVATE);
        editors = sharedPreferences.edit();
        getSupportActionBar().setTitle(R.string.party_title);
        sharedPreference = new SharedPreference(MembersMain.this);
        menuStrings = new MenuStrings(MembersMain.this);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        mBottomNav =(BottomNavigationView)findViewById(R.id.navigations);
        mBottomNav.setVisibility(View.VISIBLE);
        userSession = new UserSession(MembersMain.this);

        disableShiftMode(mBottomNav);
        showFragments(new MemberHome());
        mBottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
        Menu menu = mBottomNav.getMenu();
            homepages = menu.findItem(R.id.action_home);
            parties =  menu.findItem(R.id.action_category);
            complaints = menu.findItem(R.id.action_setting);
            activities = menu.findItem(R.id.action_me);

            //downloads.setTitle("gello");
    }
    public void selectFragment(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_home:
                showFragments(new MemberHome());
                MAIN     =1;
                break;
            case R.id.action_category:
                showFragments(new MemberView());

                break;
            case R.id.action_me:
                MemberView.FLAG =2;
                showFragments(new AllActivities());
//                showFragments(new Activities());
                MAIN = 3;
                break;
            case R.id.action_setting:
                showFragments(new AllComplaints());
                MAIN = 2;
                break;
        }
        mSelectedItem = item.getItemId();
        for (int i = 0; i < mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }
    }
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShifting(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            //Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            //Timber.e(e, "Unable to change value of shift mode");
        }
    }
    void showFragments(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,fragment).commit();
    }
    private void updateMenuTitles() {
       downloads = menus.findItem(R.id.myDownloads);
       logout = menus.findItem(R.id.action_logout);
       contactadmin = menus.findItem(R.id.adminContacts);
       changelan = menus.findItem(R.id.changeLans);
        if (menuStrings.getSharedPreferences()) {
            downloads.setTitle("Downloads");
            logout.setTitle("Logout");
            contactadmin.setTitle("Profile");
            changelan.setTitle("Change language");
        } else {
            downloads.setTitle("பதிவிறக்கம்");
            logout.setTitle("வெளியேறு");
            contactadmin.setTitle("சுயவிவரங்கள்");
            changelan.setTitle("மொழி மாற்ற");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        this.menus = menu;
        updateMenuTitles();

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case  R.id.myDownloads:
                showFragments(new Downloads());
                break;
            case R.id.action_logout:
                String finalErrorMessage =  null;
                String passtiveButton = null;
                String negative = null;
                if (menuStrings.getSharedPreferences())
                {
                    finalErrorMessage = "Are you sure you want to log out?";
                    passtiveButton = "Yes";
                    negative = "No";
                }
                else
                {
                    finalErrorMessage = "நிச்சயமாக நீங்கள் வெளியேற வேண்டுமா ?";
                    passtiveButton ="ஆம்";
                    negative = "இல்லை";
                }

                Expired expired = new Expired(MembersMain.this, finalErrorMessage);
                expired.setTitle(finalErrorMessage);
                expired.setCancelable(false);

                expired.setPositiveButton(passtiveButton, (dialog1, which) -> {
                    Intent i = new Intent(MembersMain.this, MainActivity.class);
                    sharedPreference.removeLogin();
                    editors = sharedPreferences.edit();
                    editors.clear();
                    editors.apply();
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                });
                expired.setNegativeButton(negative, (dialog, which) -> {
                    dialog.dismiss();
                });
                expired.show();
                break;
            case R.id.changeLans:
                AlertDialogues alertDialogues = new AlertDialogues(MembersMain.this);
                String neg,pas;
                if (menuStrings.getSharedPreferences())
                {
                    neg = "No";
                    pas ="yes";
                }
                else
                {
                    neg = "இல்லை";
                    pas ="ஆம்";

                }
                alertDialogues.setNegativeButton(neg, (dialog, which) -> dialog.dismiss());
                alertDialogues.setPositiveButton(pas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialogues.clicked();
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialogues.show();
                break;
            case R.id.adminContacts:
                Intent intent = new Intent(getApplicationContext(),ShowMember.class);
                intent.putExtra("id",userSession.getId());
                startActivity(intent);
                break;
                default:
                    break;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mBottomNav.setVisibility(View.VISIBLE);
    }
}
