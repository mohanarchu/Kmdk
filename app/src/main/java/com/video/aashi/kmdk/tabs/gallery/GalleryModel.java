package com.video.aashi.kmdk.tabs.gallery;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Members.activities.activitylist.ActPojo;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GalleryModel {

    Context context;
    GalleryPresenter galleryPresenter;
    MenuStrings menuStrings;
    ArrayList<GalleryList> galleryLists;
    public GalleryModel(Context context,GalleryPresenter galleryPresenter)
    {
        this.context = context;
        this.galleryPresenter = galleryPresenter;
    }
    public void getAlbums()
    {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                galleryPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                galleryPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            galleryPresenter.showProgress();
            if (change)
            {
                galleryPresenter.progressMessage("Please wait..!");
            }
            else
            {
                galleryPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservables().subscribeWith(getLists());
        }
    }
    public Observable<GallerPojo> getObservables() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<GallerPojo> getLists()
    {
        return new DisposableObserver<GallerPojo>() {
            @Override
            public void onNext(GallerPojo gallerPojo) {

                galleryLists = new ArrayList<>();

                ArrayList<GalleryResponse> galleryResponses = gallerPojo.getResponse();
                if (gallerPojo.getStatus().equals("true"))
                {
                    for (int i=0;i<galleryResponses.size();i++)
                    {
                        galleryLists.add(new GalleryList(galleryResponses.get(i).getAlbum_Name(),galleryResponses.get(i).get_id() ));
                        Log.i("TAG","AlbumError" +galleryResponses.size() + gallerPojo.getStatus()   );
                    }
                    galleryPresenter.showList(galleryLists);
                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        galleryPresenter.showToast("Please try again..!" );
                    }
                    else
                    {
                        galleryPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                    }
                }


                galleryPresenter.hideProgress();





            }

            @Override
            public void onError(Throwable e) {
                galleryPresenter.hideProgress();
                if (menuStrings.getSharedPreferences())
                {
                    galleryPresenter.showToast("Please try again..!" );
                }
                else
                {
                    galleryPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
