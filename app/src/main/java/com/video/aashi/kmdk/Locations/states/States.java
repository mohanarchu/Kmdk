package com.video.aashi.kmdk.Locations.states;

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

public class States  {
     Context context;
     MenuStrings menuStrings;
    ArrayList<StateList> stateList;
     StatePresenter statePresenter;
    public  States(Context context,StatePresenter statePresenter)
    {
        this.context = context;
        this.statePresenter =  statePresenter;

    }

    public void   getStateList() {

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
           getObservable().subscribeWith(getStates());
       }

    }


    public Observable<StatePojo> getObservable()
    {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                 .getStates(new StatePost(""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    public DisposableObserver<StatePojo> getStates()
    {
        return new DisposableObserver<StatePojo>() {
            @Override
            public void onNext(StatePojo statePojo) {

                ArrayList<Response> response = statePojo.getResponse();
                stateList = new ArrayList<>();
                for (int i=0 ; i<response.size();i++)
                {

                    stateList.add(new StateList(response.get(i).get_id(),response.get(i).getStateName()));


                   // Log.i("Tag","MyStatess"+ response.get(i).getStateName());
                }
                statePresenter.getStateList(stateList);
                statePresenter.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                Log.i("Tag","MyStatess"+ e.toString());
                statePresenter.hideProgress();
            }
            @Override
            public void onComplete() {
            }
        };
    }
}
