package com.video.aashi.kmdk.tabs.photos;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.Members.advertisements.Advertidements;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.myview.ShowPic;
import com.video.aashi.kmdk.tabs.view.AlbumModel;
import com.video.aashi.kmdk.tabs.view.AlbumPresenter;
import com.video.aashi.kmdk.tabs.view.Albums;
import com.video.aashi.kmdk.tabs.view.AllImages;
import com.video.aashi.kmdk.tabs.view.GalleryName;
import com.video.aashi.kmdk.view.ImageUtils;
import com.video.aashi.kmdk.view.ZomeImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPhotos extends BottomSheetDialogFragment implements AlbumPresenter {


    public ViewPhotos() {
        // Required empty public constructor
    }
    @BindView(R.id.allPhotos)
    GridView allphotos;
    ProgressDialog progressDialog;
    AlbumModel albumModel;
    String ids;
  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_photos, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ids = bundle.getString("id", "");
        }
        albumModel = new AlbumModel(getActivity(),ViewPhotos.this,false,ids);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                albumModel.getAlbums();
            }
        }, 500);


        return view;
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
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
    public void showToast(String mesage) {
        Toast.makeText(getActivity(),mesage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(ArrayList<GalleryName> galleryLists) {


    }

    @Override
    public void showPhotos(ArrayList<AllImages> galleryLists) {

        allphotos.setAdapter(new ImageAdapter(getActivity(),galleryLists));
    }

    class ImageAdapter extends BaseAdapter
    {
        private Context context;
        ArrayList<AllImages> galleryLists;
        public ImageAdapter(Context context, ArrayList<AllImages> galleryLists)
        {
            this.context = context;
            this.galleryLists   = galleryLists;
        }
        @Override
        public int getCount() {
            return galleryLists.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                LayoutInflater inflater =  LayoutInflater.from(context);
                row = inflater.inflate(R.layout.imagefile, parent, false);
                holder = new  ViewHolder();

                holder.imageView =(ImageView)row.findViewById(R.id.imageFile) ;

                row.setTag(holder);
            }
            else
            {
                holder = ( ViewHolder) row.getTag();
            }

            Glide.with(context).load(APIUrl.Api+"Uploads/Gallery/"+galleryLists.get(position).getImage())
                    .into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ShowPic showPic=  new ShowPic(getActivity(),APIUrl.Api+"Uploads/Gallery/"+galleryLists.get(position).getImage()
                    ,view
                      );
                    showPic.initiateLayout();

                }
            });


            Log.i("TAG","AlbumError" +galleryLists.get(position).getImage());

            return row;
        }

    }
    class ViewHolder {
        ImageView imageView;



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
}
