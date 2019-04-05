package com.video.aashi.kmdk.Members.viewMem;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.MainActivity;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.Members.adnew.addNewMem;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.approve.Approve;
import com.video.aashi.kmdk.Members.viewMem.approve.ApproveMember;
import com.video.aashi.kmdk.Members.viewMem.approve.RemoveMem;
import com.video.aashi.kmdk.Members.viewMem.classes.MemList;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPresenter;
import com.video.aashi.kmdk.Members.viewMem.classes.Members;
import com.video.aashi.kmdk.Members.viewMem.memView.ShowMember;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.logout.Expired;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.myview.ShowPic;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberView extends Fragment implements View.OnClickListener,MemPresenter {


    public MemberView() {
        // Required empty public constructor
    }

    Members members;
    ProgressDialog progressDialog;
   @BindView(R.id.memRecycler)
    RecyclerView memRecyler;
   MenuStrings menuStrings;
   UserSession userSession;
   Approve approve;

    public   static int FLAG;
    @BindView(R.id.filterss)
    FloatingActionButton filter ;
    MemberAdapter memberAdapter;
    SearchView searchView;

   static boolean ys;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_member_view, container, false);
        ButterKnife.bind(this,view);
     //   newMembers.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        members = new Members(getActivity(),MemberView.this);
        userSession = new UserSession(getActivity());
        approve = new Approve(getActivity(),MemberView.this);
        FLAG = 1;
        getActivity().invalidateOptionsMenu();
        setHasOptionsMenu(true);
        menuStrings = new MenuStrings(getActivity());
        showMember(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memRecyler.setLayoutManager(layoutManager);
        filter.setOnClickListener(this);
        return  view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.filterss:
                showSortPopup(view);
                break;


          //     case R.id.showMemDetails:
         //       getFragmentManager().beginTransaction().replace(R.id.containers,new ShowMember()).addToBackStack(null).commit();
           //     break;


        }
    }
    private void showSortPopup(View view) {

        PopupWindow changeSortPopUp;
        LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, null);
        // Creating the PopupWindow
        changeSortPopUp = new PopupWindow(getActivity());
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
                 showMember(true);
                 changeSortPopUp.dismiss();
            }
        });
        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMember(false);
                changeSortPopUp.dismiss();
            }
        });
        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());
        //   changeSortPopUp.showAtLocation(layout, Gravity.NO_GRAVITY, OFFSET_X,  OFFSET_Y);
   //  changeSortPopUp.showAsDropDown(filter,0,0,Gravity.TOP);
     changeSortPopUp.showAtLocation(view,Gravity.BOTTOM|Gravity.RIGHT,50,200);
     dimBehind(changeSortPopUp);


        // Inflate the popup_layout.xml

    }
    public static void dimBehind(PopupWindow popupWindow) {
        View container;
        if (popupWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                container = (View) popupWindow.getContentView().getParent();
            } else {
                container = popupWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) popupWindow.getContentView().getParent().getParent();
            } else {
                container = (View) popupWindow.getContentView().getParent();
            }
        }
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.7f;
        wm.updateViewLayout(container, p);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search,menu);
        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView. setFocusableInTouchMode(true);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().  getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        setSearchIcons(searchView);

        SearchView.SearchAutoComplete searchAutoComplete =
                (SearchView.SearchAutoComplete)searchView.findViewById(
                        android.support.v7.appcompat.R.id.search_src_text);

        searchAutoComplete.setTextColor(Color.WHITE);

        /*Code for changing the search icon */
        ImageView searchIcon = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.ic_magnifier);


        // listening to search query text change

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                memberAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                memberAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void setSearchIcons(SearchView search) {
        try
        {
            Field searchField = SearchView.class.getDeclaredField("mCloseButton");
            searchField.setAccessible(true);
            ImageView closeBtn = (ImageView) searchField.get(search);
            closeBtn.setImageResource(R.drawable.ic_close_white_24dp );
            ImageView searchButton = (ImageView) search.
                    findViewById(android.support.v7.appcompat.R.id.search_button );
            searchButton.setImageResource(R.drawable.ic_magnifier);


        } catch (NoSuchFieldException e)
        {
            Log.e("SearchView", e.getMessage(), e);
        } catch (IllegalAccessException e)
        {
            Log.e("SearchView", e.getMessage(), e);
        }
    }
    public static void showMy(boolean yes)
    {
       ys = yes;
      // memberAdapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        progressDialog.setCancelable(false);
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
    public void showMember(boolean yes) {
        if (yes)
        {
            members.showAll(true);
        }
        else
        {
            members.showAll(false);
        }

    }
    @Override
    public void showToast(String toast) {
        Toast.makeText(getActivity(),toast,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showAllMember(ArrayList<MemList> memLists) {
  //      if (userSession.getId().equals(memLists))
        for (int i = 0;i<memLists.size();i++)
        {
            if (userSession.getId().equals(memLists.get(i).getId()))
            {
                memLists.remove(i);
            }

        }
        memberAdapter = new MemberAdapter(getActivity(),memLists);
        memRecyler.setAdapter(memberAdapter);


    }

    @Override
    public void referesh() {
     members.showAll(true);
    }

    @Override
    public void notifys() {
        members.showAll(true);
    }


    public class MemberAdapter extends RecyclerView.Adapter<ViewHoler> implements Filterable
    {

        ArrayList<MemList>  memLists;
        ArrayList<MemList>  memListsFilter;
        Context context;
        View view;
        public MemberAdapter(Context context,ArrayList<MemList> lists)
        {
            this.context = context;
            this.memLists = lists;
           this.  memListsFilter = lists;

        }

        @NonNull
        @Override
        public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           view   = LayoutInflater.from(getActivity()).inflate(R.layout.memdesign, viewGroup, false);
            return new ViewHoler(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoler viewHoler, final int i) {

            MemList mem = memListsFilter.get(i);
            Log.i("TAG","MemPosition"+memListsFilter.get(i).getPhone());

            if (mem.getName().equals("") )
            {
                viewHoler.image.setImageResource(R.drawable.images);
            }
            else
            {
                viewHoler.image.setImageResource(R.drawable.images);
            }
            viewHoler.name.setText (   mem.getName());
            viewHoler.name.setText(mem.getName());
            viewHoler.mobile .setText( " - " + mem.getPhone());
            viewHoler.education .setText ( " : " + mem.getEducation());
            viewHoler.city.setText (" : " +mem.getCity());
            viewHoler.creatdat.setText(mem.getCreateAt());
            viewHoler.memId.setText(" : " +mem.getMemId());
            Log.i("Tag","ImageFile"+mem.getPhoto());

            if (mem.getPhoto() != null)
            {

                Glide.with(context).load(APIUrl.Api+"Uploads/Member/" +mem.getPhoto())
                        .into(viewHoler.image);
                viewHoler.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowPic showPic = new ShowPic(context,APIUrl.Api+"Uploads/Member/" +mem.getPhoto()
                                ,view );
                        showPic.initiateLayout();
                    }
                });

            }


            @SuppressLint("SimpleDateFormat") SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
            Date d = null;
            try
            {
                d = input.parse(mem.getCreateAt());
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            String formatted = output.format(d);
            viewHoler.creatdat.setText(formatted);
            if (userSession.isCanApprove())
            {
                viewHoler.linearLayoutApprove.setVisibility(View.VISIBLE);
            }
            else
            {
                viewHoler.linearLayoutApprove.setVisibility(View.GONE);
            }
            if (mem.getStatus().contains("Approved"))
            {
               viewHoler.edit.setVisibility(View.GONE);
               viewHoler.removee.setVisibility(View.VISIBLE);
            }
            else
            {
                viewHoler.removee.setVisibility(View.VISIBLE);
               viewHoler.edit.setVisibility(View.VISIBLE);
            }
            if (mem.getStatus().contains("Rejected") && !mem.getStatus().contains("Approved") )
            {
                viewHoler.edit.setVisibility(View.VISIBLE);
                viewHoler.removee.setVisibility(View.GONE);
            }

            viewHoler.edit.setOnClickListener(v -> {
                ApproveMember approveMember = new ApproveMember(context);
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
                approveMember.setNegativeButton(neg, (dialog, which) -> dialog.dismiss());
                approveMember.setPositiveButton(pas, (dialog, which) ->

                        approve.memApprove(userSession.getId(),mem.getId()));

                approveMember.show();
        });
            viewHoler.removee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveMem approveMember = new RemoveMem(context);
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
                    approveMember.setNegativeButton(neg, (dialog, which) -> dialog.dismiss());
                    approveMember.setPositiveButton(pas, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            approve.removeMem(mem.getId(),userSession.getId());
                        }
                    });
                    approveMember.show();
                 }
            });
            viewHoler.showMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getActivity(),ShowMember.class);
                    intent.putExtra("id",mem.getId());
                    startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return memListsFilter.size();
        }
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String charString = constraint.toString();
                    if (charString.isEmpty()) {
                        memListsFilter = memLists;
                    } else {
                        ArrayList<MemList> filteredLis = new ArrayList<>();
                        for (MemList row : memListsFilter) {
                           if (row.getPhone() .toLowerCase().contains(charString.toLowerCase())) {
                                filteredLis.add(row);
                            }
                        }
                        memListsFilter = filteredLis;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = memListsFilter;
                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    memListsFilter = (ArrayList<MemList>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
    public  class ViewHoler extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name,id,education,city,creatdat,mobile,memId;
        CardView edit,removee,showMember;
        LinearLayout linearLayoutApprove;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            image =(ImageView)itemView.findViewById(R.id.memPhoto);
            memId =(TextView)itemView.findViewById(R.id.memId);
            name=(TextView)itemView.findViewById(R.id.memNames);
            education =(TextView)itemView.findViewById(R.id.memEdu);
            city =(TextView)itemView.findViewById(R.id.memCitys);
            creatdat =(TextView)itemView.findViewById(R.id.createdAt);
            mobile =(TextView)itemView.findViewById(R.id.memPhones);
            edit =(CardView)itemView.findViewById(R.id.editMem);
            removee =(CardView)itemView.findViewById(R.id.deleteMem);
            showMember =(CardView)itemView.findViewById(R.id.showMemDetails);
            linearLayoutApprove =(LinearLayout)itemView.findViewById(R.id.memApprovelayout);
        }
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
                    if (FLAG ==1)
                    {
                        MembersMain.mBottomNav.setSelectedItemId(R.id.action_home);

                        MembersMain.mBottomNav.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
                return false;
            }
         });
    }
}