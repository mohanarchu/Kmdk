package com.video.aashi.kmdk.Locations.branch;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Locations.StatePresenter;
import com.video.aashi.kmdk.Locations.zone.ZoneList;
import com.video.aashi.kmdk.Locations.zone.ZonePojo;
import com.video.aashi.kmdk.Locations.zone.ZonePost;
import com.video.aashi.kmdk.Locations.zone.ZoneResponse;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Branch {


    Context context;
    MenuStrings menuStrings;
    StatePresenter statePresenter;
    ArrayList<BranchList> arrayList;
    public Branch(Context context, StatePresenter statePresenter)
    {
        this.context = context;
        this.statePresenter = statePresenter;
    }

    public  void getBranch(String id)
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
            statePresenter.showProgressView();
            if (change)
            {
                statePresenter.showProgressMessage("Please wait..!");
            }
            else
            {
                statePresenter.showProgressMessage("காத்திருக்கவும்..!");
            }

            getObservable(id).subscribeWith(getBranch());
        }
    }
    public Observable<BranchPojo> getObservable(String district)
    {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getBranch(new BranchPOst(district))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BranchPojo> getBranch()
    {
        return new DisposableObserver<BranchPojo>() {
            @Override
            public void onNext(BranchPojo branchPojo) {
                ArrayList<BranchResponse> response = branchPojo.getResponse();
                arrayList = new ArrayList<>();
                for (int i=0 ; i<response.size();i++)
                {

                    arrayList.add(new BranchList(response.get(i).getBranchName(),response.get(i).get_id()));


                    // Log.i("Tag","MyStatess"+ response.get(i).getStateName());
                }
                statePresenter.getBranches(arrayList);
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
