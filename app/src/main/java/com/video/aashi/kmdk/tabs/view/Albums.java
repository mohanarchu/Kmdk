package com.video.aashi.kmdk.tabs.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
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

import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.tabs.Gallery;
import com.video.aashi.kmdk.tabs.gallery.GalleryList;
import com.video.aashi.kmdk.tabs.gallery.GalleryModel;
import com.video.aashi.kmdk.tabs.photos.ViewPhotos;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Albums extends Fragment implements AlbumPresenter {


    @BindView(R.id.albumgrid)
    GridView gridView;
    ProgressDialog progressDialog;
    AlbumModel albumModel;
   String ids = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ids = bundle.getString("id", "");
        }
        albumModel = new AlbumModel(getActivity(),Albums.this,true,ids );
        albumModel.getAlbums();
        return  view;
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


        gridView.setAdapter(new ImageAdapter(getActivity(),galleryLists));
    }

    @Override
    public void showPhotos(ArrayList<AllImages> galleryLists) {

    }

    class ImageAdapter extends BaseAdapter
    {
        private Context context;
        ArrayList<GalleryName> galleryLists;
        public ImageAdapter(Context context, ArrayList<GalleryName> galleryLists)
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
                row = inflater.inflate(R.layout.albumdesign, parent, false);
                holder = new ViewHolder();

                holder.imageView =(TextView)row.findViewById(R.id.albumName) ;
                holder.cardView =(LinearLayout)row.findViewById(R.id.showPhotos);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomSheetDialogFragment albums = new ViewPhotos();
                    Bundle bundle = new Bundle();
                    bundle.putString("id",galleryLists.get(position).getId() );
                    albums.setArguments(bundle);
                    albums.show(getChildFragmentManager(),"TAG");


                 }
            });
            holder.imageView.setText(galleryLists.get(position).getName());

            Log.i("TAG","AlbumError" +galleryLists.get(position).getName());

            return row;
        }

    }
    class ViewHolder {
        TextView imageView;
        LinearLayout cardView;


    }
}
