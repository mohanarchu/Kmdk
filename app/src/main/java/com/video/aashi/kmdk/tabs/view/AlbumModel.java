package com.video.aashi.kmdk.tabs.view;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.tabs.gallery.GallerPojo;
import com.video.aashi.kmdk.tabs.gallery.GalleryList;
import com.video.aashi.kmdk.tabs.gallery.GalleryResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AlbumModel {


    AlbumPresenter albumPresenter;
    Context context;
    boolean validate;
    MenuStrings menuStrings;
    ArrayList<GalleryName> galleryLists;
    ArrayList<AllImages> allImages;
    String id;
    public AlbumModel(Context context,AlbumPresenter albumPresenter,boolean validate,String id)
    {
        this.context = context;
        this.albumPresenter = albumPresenter;
        this.validate = validate;
        this.id = id;
    }

    public void getAlbums()
    {


        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                albumPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                albumPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            albumPresenter.showProgress();
            if (change)
            {
                albumPresenter.progressMessage("Please wait..!");
            }
            else
            {
                albumPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservables().subscribeWith(getLists());
        }
    }
    public Observable<AlbumPojo> getObservables() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getGalleryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<AlbumPojo> getLists()
    {
        return new DisposableObserver<AlbumPojo>() {
            @Override
            public void onNext(AlbumPojo gallerPojo) {

                galleryLists = new ArrayList<>();
                allImages = new ArrayList<>();

                ArrayList<AlbumResponse> galleryResponses = gallerPojo.getResponse();
                if (gallerPojo.getStatus().equals("true"))
                {
                    if (validate)
                    {

                        for (int i=0;i<galleryResponses.size();i++)
                        {
                            if (galleryResponses.get(i).getAlbum_Id().equals(id) )
                            {
                                galleryLists.add(new GalleryName(galleryResponses.get(i).getGallery_Name(),galleryResponses.get(i).get_id()));
                            }

                            Log.i("TAG","AlbumError" + id + gallerPojo.getStatus()   );
                        }
                        albumPresenter.showList(galleryLists);
                    }
                    else
                    {

                        for (int i=0;i<galleryResponses.size();i++)
                        {
                            if (galleryResponses.get(i).get_id().equals(id))
                            {
                                ArrayList<GalleryImage> galleryImages = galleryResponses.get(i).getGallery_Image();
                              //  Log.i("TAG","PhotoErrors" + id + galleryImages.get(i).getFilename() );
                                for (int j=0;j<galleryImages.size();j++)
                                {
                                    allImages.add(new AllImages(galleryImages.get(j).getFilename()));

                                }

                                albumPresenter.showPhotos(allImages);
                                albumPresenter.hideProgress();
                            }


                        }
                        albumPresenter.showList(galleryLists);

                    }

                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        albumPresenter.showToast("Please try again..!" );
                    }
                    else
                    {
                        albumPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                    }
                }


                albumPresenter.hideProgress();





            }

            @Override
            public void onError(Throwable e) {
                albumPresenter.hideProgress();
                if (menuStrings.getSharedPreferences())
                {
                    albumPresenter.showToast("Please try again..!" );
                }
                else
                {
                    albumPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }




}
