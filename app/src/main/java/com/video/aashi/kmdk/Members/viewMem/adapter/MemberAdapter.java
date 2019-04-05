package com.video.aashi.kmdk.Members.viewMem.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.Members.viewMem.classes.MemList;
import com.video.aashi.kmdk.Members.viewMem.memView.ShowMember;
import com.video.aashi.kmdk.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHoler>
    {

        ArrayList<MemList>  memLists;
        Context context;
        UserSession userSession;
        public MemberAdapter(Context context,ArrayList<MemList> lists,UserSession userSession)
        {
            this.context = context;
            this.memLists = lists;
            this.userSession = userSession;

        }

        @NonNull
        @Override
        public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.memdesign, viewGroup, false);
            return new ViewHoler(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoler viewHoler, final int i) {

            if (memLists.get(i).getName().equals("") )
            {
                viewHoler.image.setImageResource(R.drawable.images);
            }
            else
            {
                viewHoler.image.setImageResource(R.drawable.images);
            }
            viewHoler.name.setText(memLists.get(i).getName());
            viewHoler.mobile .setText( " - " + memLists.get(i).getPhone());
            viewHoler.education .setText ( " : " + memLists.get(i).getEducation());
            viewHoler.city.setText(memLists.get(i ).getCity());
            viewHoler.creatdat.setText(memLists.get(i).getCreateAt());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
            Date d = null;
            try
            {
                d = input.parse(memLists.get(i).getCreateAt());
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
            if (userSession.getStatus().contains("Approved"))
            {
              //  viewHoler.edit.setVisibility(View.GONE);
            }
            else
            {
               // viewHoler.edit.setVisibility(View.VISIBLE);
            }
            viewHoler.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                //    new removeMem().execute(memLists.get(i).getId(),"");
                }
            });
            viewHoler.showMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {


                }
            });
        }
        @Override
        public int getItemCount() {
            return memLists.size();
        }

        public  class ViewHoler extends RecyclerView.ViewHolder
        {
            ImageView image;
            TextView name,id,education,city,creatdat,mobile;
            CardView edit,approve,showMember;
            LinearLayout linearLayoutApprove;


            public ViewHoler(@NonNull View itemView) {
                super(itemView);
                image =(ImageView)itemView.findViewById(R.id.memPhoto);
                name=(TextView)itemView.findViewById(R.id.memNames);
                education =(TextView)itemView.findViewById(R.id.memEdu);
                city =(TextView)itemView.findViewById(R.id.memCitys);
                creatdat =(TextView)itemView.findViewById(R.id.createdAt);
                mobile =(TextView)itemView.findViewById(R.id.memPhones);
                edit =(CardView)itemView.findViewById(R.id.editMem);
                approve =(CardView)itemView.findViewById(R.id.deleteMem);
                showMember =(CardView)itemView.findViewById(R.id.showMemDetails);
                linearLayoutApprove =(LinearLayout)itemView.findViewById(R.id.memApprovelayout);
            }
        }

    }