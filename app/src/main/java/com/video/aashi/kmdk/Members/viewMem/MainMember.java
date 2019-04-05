package com.video.aashi.kmdk.Members.viewMem;


import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.video.aashi.kmdk.Cmplaints.AllComplaints;
import com.video.aashi.kmdk.Cmplaints.addressed.AddComplaints;
import com.video.aashi.kmdk.Members.adnew.addNewMem;
import com.video.aashi.kmdk.Members.complaints.SocialComplaints;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.Pendings.PendingMembers;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPresenter;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.memberJoin.MemberRegister;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMember extends Fragment {

    @BindView(R.id.newMembers)
    CardView newMembers;
    TabLayout tabLayout;
    ViewPager viewPager;
    String complaint,addrressed;
    MenuStrings menuStrings;
    UserSession userSession;
    @BindView(R.id.memAddForm)
    RelativeLayout memAddform;
    @BindView(R.id.newMemberText)
    TextView newMemberText;
    @BindView(R.id.filters)
    ImageView filters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_main_member, container, false);
       ButterKnife.bind(this,view);

        viewPager = (ViewPager)view. findViewById(R.id.viewpagers);
        menuStrings = new MenuStrings(getActivity());
        setupViewPager(viewPager);
        userSession = new UserSession(getActivity());

        tabLayout = (TabLayout)view. findViewById(R.id.tabss);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#029358")));
        if (userSession.isCanAdd() && userSession.isCanEdit())
        {
            memAddform.setVisibility(View.VISIBLE);
        }
        else
        {
            memAddform.setVisibility(View.GONE);
        }
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortPopup(getActivity(),view);
            }
        });


        newMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberRegister memberRegister = new MemberRegister();
                Bundle bundle= new Bundle();
                bundle.putString("key","1");
                memberRegister.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.containers,
                        memberRegister).addToBackStack(null).commit();

            }
        });
        return  view;
    }
    private void showSortPopup(Activity activity,View view) {
        PopupWindow changeSortPopUp;

            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.popup, null);

            // Creating the PopupWindow
            changeSortPopUp = new PopupWindow(activity);
            changeSortPopUp.setContentView(layout);
            changeSortPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            changeSortPopUp.setFocusable(true);
            TextView all =(TextView)layout.findViewById(R.id.approved);
           TextView rejected =(TextView)layout.findViewById(R.id.rejected);
            int OFFSET_X = -20;
            int OFFSET_Y = 95;
            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MemberView.showMy(true);

                    changeSortPopUp.dismiss();
                }
            });
            rejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    changeSortPopUp.dismiss();
                }
            });
            changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());
           //   changeSortPopUp.showAtLocation(layout, Gravity.NO_GRAVITY, OFFSET_X,  OFFSET_Y);
            changeSortPopUp.showAsDropDown(filters);

          // Inflate the popup_layout.xml

    }
    private void setupViewPager(ViewPager viewPager)
    {
        if (menuStrings.getSharedPreferences())
        {
            newMemberText.setText("Add");
            complaint = "Members";
            addrressed = "Pendings";
        }
        else
        {
            newMemberText.setText("சேர்க்க");
            complaint = getString(R.string.new_member);
            addrressed  = "நிலுவைகள்";
        }
        String marks = complaint;
        String table = addrressed;
     ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MemberView(), marks);
        adapter.addFragment(new PendingMembers(), table);
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
}
