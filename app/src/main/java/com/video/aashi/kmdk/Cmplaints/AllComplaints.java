package com.video.aashi.kmdk.Cmplaints;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.video.aashi.kmdk.Cmplaints.addressed.AddComplaints;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.Members.complaints.SocialComplaints;
import com.video.aashi.kmdk.Members.home.MemberHome;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllComplaints extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    String complaint,addrressed;
    MenuStrings menuStrings;
    @BindView(R.id.newComplainnt)
    LinearLayout newComplaint;
    @BindView(R.id.newCompTexts)
    TextView newComtext;
    @BindView(R.id.visibleLayouts)
    RelativeLayout vivibleLayout;
   UserSession userSession;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_complaints, container, false);
        ButterKnife.bind(this,view);
        userSession = new UserSession(getActivity());
        newComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.containers,new Complaints()).addToBackStack(null).commit();
            }
        });
        viewPager = (ViewPager)view. findViewById(R.id.viewpager);
        menuStrings = new MenuStrings(getActivity());
        setupViewPager(viewPager);
        tabLayout = (TabLayout)view. findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#029358")));
        if (userSession.getId().equals(""))
        {
            vivibleLayout.setVisibility(View.GONE);
        }
        else
        {
            vivibleLayout.setVisibility(View.VISIBLE);
        }
        if (menuStrings.getSharedPreferences())
        {
            newComtext.setText("New Complaint");
        }
        else
        {
            newComtext.setText("புதிய புகார்");
        }
        return  view;
    }
    private void setupViewPager(ViewPager viewPager)
    {

        if (menuStrings.getSharedPreferences())
        {
            complaint = "Complaints";
            addrressed = "Addressed complaints";
        }
        else
        {
            complaint = getString(R.string.complaints);
            addrressed  = "அங்கீகரிக்கப்பட்டது";
        }

        String marks = complaint;
        String table = addrressed;
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AddComplaints(), marks);
        adapter.addFragment(new SocialComplaints(), table);
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {

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
