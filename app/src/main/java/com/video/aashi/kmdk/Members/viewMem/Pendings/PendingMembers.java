package com.video.aashi.kmdk.Members.viewMem.Pendings;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.Members.viewMem.approve.Approve;
import com.video.aashi.kmdk.Members.viewMem.approve.ApproveMember;
import com.video.aashi.kmdk.Members.viewMem.approve.RemoveMem;
import com.video.aashi.kmdk.Members.viewMem.classes.MemList;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPresenter;
import com.video.aashi.kmdk.Members.viewMem.classes.Members;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingMembers extends Fragment implements View.OnClickListener,MemPresenter {


    public PendingMembers() {
        // Required empty public constructor
    }

    ProgressDialog progressDialog;
    @BindView(R.id.memRecyclers)
    RecyclerView memRecyler;
    MenuStrings menuStrings;
    UserSession userSession;
    Members members;
    Approve approve;
    MemberAdapters memberAdapters;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_members, container, false);
        ButterKnife.bind(this, view);
        ///newMembers.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        members = new Members(getActivity(), PendingMembers.this);
        userSession = new UserSession(getActivity());
        members.showAlls();
        userSession.getSharedPreferences();
        Log.i("TAG","MemIds"+userSession.getId()  +userSession.getStatus() + userSession.isCanAdd());
        approve = new Approve(getActivity(),PendingMembers.this);
        menuStrings = new MenuStrings(getActivity());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memRecyler.setLayoutManager(layoutManager);
        return view;
    }
    @Override
    public void onClick(View v) {
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

    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAllMember(ArrayList<MemList> memLists) {

        memberAdapters = new MemberAdapters(getActivity(), memLists);
        memRecyler.setAdapter(memberAdapters);
     //   memberAdapters.notifyDataSetChanged();

    }

    @Override
    public void referesh() {
      members.showAlls();
    }

    @Override
    public void notifys() {

    }


    public class MemberAdapters extends RecyclerView.Adapter<ViewHoler> {

        ArrayList<MemList> memLists;
        Context context;
        View view;
        public MemberAdapters(Context context, ArrayList<MemList> lists) {
            this.context = context;
            this.memLists = lists;

        }
        @NonNull
        @Override
        public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           view  = LayoutInflater.from(getActivity()).inflate(R.layout.memdesign, viewGroup, false);
            return new ViewHoler(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHoler viewHoler, final int i) {
            if (memLists.get(i).getName().equals("")) {
                viewHoler.image.setImageResource(R.drawable.images);
            }
            else
            {
                viewHoler.image.setImageResource(R.drawable.images);

            }
            viewHoler.name.setText(memLists.get(i).getName());
            viewHoler.mobile.setText(" - " + memLists.get(i).getPhone());
            viewHoler.education.setText(" : " + memLists.get(i).getEducation());
            viewHoler.city.setText(" : " +memLists.get(i).getCity());
            viewHoler.memId.setText(" : " +memLists.get(i).getMemId());
            viewHoler.creatdat.setText(memLists.get(i).getCreateAt());
            if (memLists.get(i).getPhoto() != null)
            {

                Glide.with(context).load(APIUrl.Api+"Uploads/Member/" +memLists.get(i).getPhoto())
                        .into(viewHoler.image);
                viewHoler.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowPic showPic = new ShowPic(context,APIUrl.Api+"Uploads/Member/" +memLists.get(i).getPhoto()
                                ,view );
                        showPic.initiateLayout();
                    }
                });

            }

            @SuppressLint("SimpleDateFormat") SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
            Date d = null;
            try {
                d = input.parse(memLists.get(i).getCreateAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String formatted = output.format(d);
            viewHoler.creatdat.setText(formatted);
            if (userSession.isCanApprove() && userSession.isStateAuthority()) {
                viewHoler.linearLayoutApprove.setVisibility(View.VISIBLE);
            }
            else {
                viewHoler.linearLayoutApprove.setVisibility(View.GONE);
            }
            if (!userSession.isStateAuthority())
            {
                if (userSession.isDistriictAuthority())
                {
                    if (userSession.getDistrict().equals(memLists.get(i).getDistrict()))
                    {
                        viewHoler.linearLayoutApprove.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    viewHoler.linearLayoutApprove.setVisibility(View.GONE);
                }
            }
            if (!userSession.isStateAuthority() && !userSession.isDistriictAuthority())
            {
                if (userSession.isZoneAuthority())
                {
                    if (userSession.getZone().equals(memLists.get(i).getZone()))
                    {
                        viewHoler.linearLayoutApprove.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    viewHoler.linearLayoutApprove.setVisibility(View.GONE);
                }
            }
           if (!userSession.isStateAuthority() && !userSession.isDistriictAuthority() && !userSession.isZoneAuthority())
           {
               if (userSession.isBranchAuthority())
               {
                   if (userSession.getBranch().equals(memLists.get(i).getBranch()))
                   {
                       viewHoler.linearLayoutApprove.setVisibility(View.VISIBLE);
                   }
               }
               else
               {
                   viewHoler.linearLayoutApprove.setVisibility(View.GONE);
               }

           }
            viewHoler.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ApproveMember approveMember = new ApproveMember(context);
                    String neg,pas;
                    if (menuStrings.getSharedPreferences())
                    {
                        neg = "No";
                        pas = "Yes";
                    }
                    else
                    {
                        neg = "இல்லை";
                        pas ="ஆம்";
                    }
                    approveMember.setNegativeButton(neg, (dialog, which) -> dialog.dismiss());

                    approveMember.setPositiveButton(pas, (dialog, which) ->
                            approve.memApprove(memLists.get(i).getId(),userSession.getId() ));
                    approveMember.show();
                    Log.i("TAG","ApprovedMembers"+memLists.get(i).getId()+userSession.getId());

                }
            });
            viewHoler.approve.setOnClickListener(new View.OnClickListener() {
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
                        approve.removeMem(memLists.get(i).getMemId(),userSession.getId());
                     }
                 });
                    approveMember.show();
                }
            });
            viewHoler.showMember.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(),ShowMember.class);
                intent.putExtra("id",memLists.get(i).getId());
                startActivity(intent);
            });

        }

        @Override
        public int getItemCount() {
            return memLists.size();
        }
    }


    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, id, education, city, creatdat, mobile,memId;
        CardView edit, approve, showMember;
        LinearLayout linearLayoutApprove;


        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.memPhoto);
            memId =(TextView)itemView.findViewById(R.id.memId);
            name = (TextView) itemView.findViewById(R.id.memNames);
            education = (TextView) itemView.findViewById(R.id.memEdu);
            city = (TextView) itemView.findViewById(R.id.memCitys);
            creatdat = (TextView) itemView.findViewById(R.id.createdAt);
            mobile = (TextView) itemView.findViewById(R.id.memPhones);
            edit = (CardView) itemView.findViewById(R.id.editMem);
            approve = (CardView) itemView.findViewById(R.id.deleteMem);
            showMember = (CardView) itemView.findViewById(R.id.showMemDetails);
            linearLayoutApprove = (LinearLayout) itemView.findViewById(R.id.memApprovelayout);
        }
    }


}