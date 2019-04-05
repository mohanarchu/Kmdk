package com.video.aashi.kmdk.Locations.district;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Locations.StatePresenter;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class District {

    Context context;
    StatePresenter statePresenter;
    MenuStrings menuStrings;
    ArrayList<DistrictList> districtLists;
    public District(Context context, StatePresenter statePresenter)
    {
        this.statePresenter = statePresenter;
        this.context = context;

    }

    public  void getDistricts(String stateID)
    {
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
            if (change)
            {
                statePresenter.showProgressMessage("Please wait..!");
            }
            else
            {
                statePresenter.showProgressMessage("காத்திருக்கவும்..!");
            }
            statePresenter.showProgressView();
            getObservable( stateID).subscribeWith(getDistrict());
        }
    }
    public Observable<DistrictPojo> getObservable(String stateid)
    {
        Log.i("Tag","MyDistricts"+ stateid);
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getDistrict(new DistrictPost(stateid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public DisposableObserver<DistrictPojo> getDistrict()
    {
        return new DisposableObserver<DistrictPojo>() {
            @Override
            public void onNext(DistrictPojo statePojo) {

                ArrayList<DistrictResponse> response = statePojo.getResponse();
                districtLists = new ArrayList<>();
                for (int i=0 ; i<response.size();i++)
                {

                    districtLists.add(new DistrictList(response.get(i).getDistrictName(),response.get(i).get_id()));


                    Log.i("Tag","MyDistricts"+ response.get(i).getDistrictName());
                }
                statePresenter.getDistrict(districtLists);
                statePresenter.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                Log.i("Tag","MyDistricts"+  e.toString());
                statePresenter.hideProgress();
            }
            @Override
            public void onComplete() {
            }
        };
    }


}
