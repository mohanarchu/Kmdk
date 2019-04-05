package com.video.aashi.kmdk.Members.advertisements.list;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Members.advertisements.types.TypesPojo;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddListView {

    Context context;
    MenuStrings menuStrings;
    AdListPresenter adListPresenter;

    ArrayList<AdvertiseList> advertiselist;

    public  AddListView(Context context,AdListPresenter adListPresenter)
    {
         this.adListPresenter = adListPresenter;
         this.context = context;
    }


    void  getLists()
    {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {
                adListPresenter.showToast("Check internet connection...!");

            }else
            {
                adListPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            adListPresenter.showProgress();
            if (change)
            {
                adListPresenter.progressMessage("Please wait..!");
            }
            else
            {
                adListPresenter.progressMessage("காத்திருக்கவும்..!");
            }
            // advertidePresenter.showProgress();

            getObservable().subscribeWith(getaddTypes());
        }

    }


    public Observable<AdListPojo> getObservable() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .showAdds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<AdListPojo> getaddTypes()
    {
        return new DisposableObserver<AdListPojo>() {
            @Override
            public void onNext(AdListPojo typesPojo) {

                advertiselist = new ArrayList<>();

                if (typesPojo.getStatus().contains("true"))
                {
                  ArrayList<AdListResponse> adListResponse = typesPojo.getResponse();

                    for (   int i=0;i<adListResponse.size();i++)
                    {
                        AdListResponse add = adListResponse.get(i);

                        advertiselist.add(new AdvertiseList(add.get_id(),add.getAdvertisementType(),add.getAdvertisement(),
                                add.getMessage(),add.getAdvertisementImage().getFilename(),add.getPlace()
                                    ,"",add.getCreatedAt()));


                    }
                    adListPresenter.showAdds(advertiselist);
                    adListPresenter.hideProgress();
                }
                adListPresenter.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                adListPresenter.hideProgress();
                Log.i("TAG","AdvertiseError"+e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    adListPresenter.showToast("Please try again..!" );
                }
                else
                {
                    adListPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
