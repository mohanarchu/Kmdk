package com.video.aashi.kmdk.Cmplaints.addressed;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.video.aashi.kmdk.Cmplaints.Complaints;
import com.video.aashi.kmdk.Members.advertisements.Advertidements;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.myview.ShowPic;
import com.video.aashi.kmdk.myview.ShowVideo;
import com.video.aashi.kmdk.view.ImageUtils;
import com.video.aashi.kmdk.view.LoadImage;
import com.video.aashi.kmdk.view.ZomeImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;



import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AddComplaints extends Fragment implements View.OnClickListener,ComplaintPresenter {


    ProgressDialog progressDialog;
    MenuStrings menuStrings;

    @BindView(R.id.addressedRecycler)
    RecyclerView comRecyycler;

    AddComplaint addComplaint;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_complaints, container, false);
        ButterKnife.bind(this,view);


        menuStrings = new MenuStrings(getActivity());
        addComplaint = new AddComplaint(getActivity(),AddComplaints.this);
        progressDialog = new ProgressDialog(getActivity());
        addComplaint.getComplaints();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        comRecyycler.setLayoutManager(layoutManager);

        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.newComplainnt:
               // getFragmentManager().beginTransaction().replace(R.id.myframe,new Complaints()).addToBackStack(null).commit();

                break;
        }
    }


    @Override
    public void showProgress() {

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
    public void showComplaintss(ArrayList<ComplaiArray> complaiArrays) {
     comRecyycler.setAdapter(new ComplaintAdapter(getActivity(),complaiArrays));

    }

    public class ComplaintAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        View view;
        Context context;
        ArrayList<ComplaiArray > complaiArrays;
        public ComplaintAdapter(Context context,ArrayList<ComplaiArray > complaiArrays)
        {
            this.context = context;
            this.complaiArrays = complaiArrays;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.complaints, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            ComplaiArray complaiArray= complaiArrays.get(i);
            viewHolder.name.setText(complaiArray.name);
            viewHolder.complai.setText(complaiArray.complaint);
            viewHolder.place.setText(complaiArray.place);
            viewHolder.time.setText(getDate(complaiArray.time));
            viewHolder.typeCom.setText(complaiArray.addtype);
            viewHolder.mobilenumber.setText( complaiArray.mobilenumber );

            if (complaiArray.getVideo() != null)
            {
                viewHolder.comVideo.setVisibility(View.VISIBLE);
                viewHolder.videoView.setVisibility(View.VISIBLE);
                try {
                /*Bitmap bitmap = retriveVideoFrameFromVideo(img_name.get(i).toString());
                viewHolder.vid_thumb.setImageBitmap(bitmap);
*/
                String video =APIUrl.Api+"Uploads/Complaint/Video/";
                  viewHolder.play.setVisibility(View.VISIBLE);
                    new LoadImage(viewHolder.comVideo, video+complaiArray.getVideo()).execute();


                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
            else
            {  viewHolder.videoView.setVisibility(View.GONE);

                viewHolder.comVideo.setVisibility(View.GONE);
                viewHolder.play.setVisibility(View.GONE);
            }
                     viewHolder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ShowVideo showVideo = new ShowVideo(context, APIUrl.Api+"Uploads/Complaint/Video/"+
                            complaiArray.getVideo(),view);
                    showVideo.initiatelayout(true);


                }
            });


            if (complaiArray.getImage() != null)
            {
                viewHolder.imageView.setVisibility(View.VISIBLE);
                viewHolder.image.setVisibility(View.VISIBLE);
                Glide.with(context).load(APIUrl.Api+"Uploads/Complaint/Image/"+
                        complaiArray.getImage()).into(viewHolder.image);
            }
            else
            {
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.image.setVisibility(View.GONE);
            }
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ShowPic showPic = new ShowPic(context,APIUrl.Api+"Uploads/Complaint/Image/"+
                    complaiArray.getImage(),view);
                    showPic.initiateLayout();

                }
            });
        }

        @Override
        public int getItemCount() {
            return complaiArrays.size();
        }
    }
    public  class ImageCompressionAsyncTasks extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            if(strings.length == 0 || strings[0] == null)
                return null;
            return ImageUtils.compressImage(strings[0]);
        }

        @Override
        protected void onPostExecute(byte[] bytes) {

            Intent intent= new Intent(getActivity(),ZomeImage.class);
            intent.putExtra("file",bytes);
            startActivity( intent);
            super.onPostExecute(bytes);
        };
    }
    public static  class ViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.nameCom)
        TextView name;
        @BindView(R.id.typeCom)
        TextView typeCom;
        @BindView(R.id.comCom)
        TextView complai;
        @BindView(R.id.placeCom)
        TextView place;
        @BindView(R.id.timeCom)
        TextView time;
        @BindView(R.id.mobileCom)
        TextView mobilenumber;
        @BindView(R.id.comImages)
        ImageView image;
        @BindView(R.id.comVid)
        ImageView comVideo;
        @BindView(R.id.playButton)
        ImageView   play;
        @BindView(R.id.closeCard)
        CardView imageView;
        @BindView(R.id.closeCards)
        CardView videoView;


        PopupWindow vidPopupWindoww;
        PopupWindow changeSortPopUp;
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

}
