package com.video.aashi.kmdk.Locations.zone;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Locations.StatePresenter;
import com.video.aashi.kmdk.Locations.states.Response;
import com.video.aashi.kmdk.Locations.states.StateList;
import com.video.aashi.kmdk.Locations.states.StatePojo;
import com.video.aashi.kmdk.Locations.states.StatePost;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Zones {

    Context context;
    StatePresenter statePresenter;
    MenuStrings menuStrings;
    ArrayList<ZoneList> arrayList;


    public Zones(Context context, StatePresenter statePresenter)
    {
        this.context = context;
        this.statePresenter = statePresenter;
    }
    public void   getZoneList(String districtId) {

        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                statePresenter.showMessage("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                statePresenter.showMessage("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            statePresenter.showProgressView();
            if (change)
            {
                statePresenter.showProgressMessage("Please wait..!");
            }
            else
            {
                statePresenter.showProgressMessage("காத்திருக்கவும்..!");
            }
            //statePresenter.showProgressMessage("காத்திருக்கவும்..!");
            getObservable(districtId).subscribeWith(getZone());
        }

    }
    public Observable<ZonePojo> getObservable(String district)
    {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getZones(new ZonePost(district))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ZonePojo> getZone()
    {
        return new DisposableObserver<ZonePojo>() {
            @Override
            public void onNext(ZonePojo zonePojo) {
                ArrayList<ZoneResponse> response = zonePojo.getResponse();
                arrayList = new ArrayList<>();
                for (int i=0 ; i<response.size();i++)
                {

                    arrayList.add(new ZoneList(response.get(i).get_id(),response.get(i).getZoneName()));


                    // Log.i("Tag","MyStatess"+ response.get(i).getStateName());
                }
                statePresenter.getZOnes(arrayList);
                statePresenter.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Tag","MyErrors"+ e.toString());
                statePresenter.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
