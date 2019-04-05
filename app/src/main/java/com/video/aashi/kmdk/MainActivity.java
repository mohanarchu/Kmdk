package com.video.aashi.kmdk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.video.aashi.kmdk.Cmplaints.AllComplaints;
import com.video.aashi.kmdk.Cmplaints.Complaints;
import com.video.aashi.kmdk.Cmplaints.addressed.AddComplaints;
import com.video.aashi.kmdk.Login.LoginActivity;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.PreferenceValue.SharedPreference;
import com.video.aashi.kmdk.contact.Contact;
import com.video.aashi.kmdk.downloads.Downloads;
import com.video.aashi.kmdk.events.Events;
import com.video.aashi.kmdk.homepage.Homepage;
import com.video.aashi.kmdk.memberJoin.MemberRegister;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.MainTExt;
import com.video.aashi.kmdk.tabs.Gallery;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static RelativeLayout titleVisible;
    private Menu menu;
    TabLayout tabLayout;
    boolean isShow = false;
    ViewPager viewPager;
    @BindView(R.id.events)
    TextView events;
    @BindView(R.id.gallary)
    TextView gallary;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.homePage)
    TextView     homepage;
    @BindView(R.id.newMem)
    TextView newMem;
    @BindView(R.id.complaintsRegister)
    TextView complainetsRegister;
    @BindView(R.id.addComplai)
    TextView addressed;
    @BindView(R.id.logins)
    TextView logins;
    @BindView(R.id.downlods)
    TextView downloads;
    @BindView(R.id.showWeb)
    TextView showWeb;
    @BindView(R.id.adminContact)
    TextView adminContact;
    @BindView(R.id.changeLan)
    TextView changeLan;
    @BindView(R.id.thens)
            TextView thens;
    @BindView(R.id.settings)
            TextView settings;
    boolean value =true;
    MenuStrings menuStrings;
    ArrayList<MainTExt> mainTExts;
    SharedPreference sharedPreference;

    String key ="1";
    int FRAGMENT_POSITIONS;

    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawermain);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        sharedPreference = new SharedPreference(getApplicationContext());
        toolbar.setVisibility(View.VISIBLE);
        menuStrings = new MenuStrings(MainActivity.this);
        menuStrings.getSharedPreferences();
        mainTExts = new ArrayList<>();
        mainTExts = menuStrings.getMainTExts();
        if (getIntent().getExtras() != null) {
            showHme(new MemberRegister());
        }
        else
        {
            showHme(new Homepage());
        }
        for (int i = 0;i<mainTExts.size();i++)
        {
            homepage.setText(mainTExts.get(i).getDashboard());
            events.setText(mainTExts.get(i).getEvents());
            gallary.setText(mainTExts.get(i).getGallery());
            newMem.setText(mainTExts.get(i).getNewMember());
            addressed.setText(mainTExts.get(i).getComplaints());
            complainetsRegister.setText(mainTExts.get(i).getNewComplaints());
            logins.setText(mainTExts.get(i).getLogin());
            downloads.setText(mainTExts.get(i).getDownloads());
            showWeb.setText(mainTExts.get(i).getVisitWebsite());
            adminContact.setText(mainTExts.get(i).getContactAdmin());
            changeLan.setText(mainTExts.get(i).getChangeLan());
            thens.setText(mainTExts.get(i).getOthers());
            settings.setText(mainTExts.get(i).getSettings());
        }
        if (sharedPreference.isLoginBool())
        {
            Intent intent = new Intent(MainActivity.this,MembersMain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        events.setOnClickListener(this);
        gallary.setOnClickListener(this);
        homepage.setOnClickListener(this);
        newMem.setOnClickListener(this);
        addressed.setOnClickListener(this);
        complainetsRegister.setOnClickListener(this);
        logins.setOnClickListener(this);
        downloads.setOnClickListener(this);
        showWeb.setOnClickListener(this);
        adminContact.setOnClickListener(this);
        changeLan.setOnClickListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.mylogo);
        titleVisible =(RelativeLayout)findViewById(R.id.titleVisible);
        titleVisible.setVisibility(View.VISIBLE);


        drawerToggle =  new ActionBarDrawerToggle(this,drawerLayout,toolbar ,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.events:
                showFragments(new Events());
                break;
            case R.id.gallary:
                showFragments(new Gallery());
                FRAGMENT_POSITIONS = 2;
                break;
            case R.id.homePage:
                showFragments(new Homepage());
                break;
            case R.id.newMem:
                showFragments(new MemberRegister());
                break;
            case R.id.complaintsRegister:
                showFragments(new Complaints());
                break;

            case R.id.addComplai:
                showFragments(new AllComplaints());
                FRAGMENT_POSITIONS = 1;
                break;
            case R.id.adminContact:
                showFragments(new Contact());
                break;
            case R.id.logins:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
            case R.id.downlods:
                showFragments(new Downloads());
                break;
            case R.id.showWeb:
                showFragments(new Website());
                break;
            case R.id.changeLan:
                AlertDialogues alertDialogues = new AlertDialogues(MainActivity.this);
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
                default:
                    break;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    void showHme(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
    }
    void showFragments(Fragment f)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,f).commit();
                     titleVisible.setVisibility(View.VISIBLE);
        drawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        switch (FRAGMENT_POSITIONS)
        {
            case 1:
                getFragmentManager().popBackStackImmediate();
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new Homepage()).commit();
            default:
                break;
        }

        super.onBackPressed();
    }
}
