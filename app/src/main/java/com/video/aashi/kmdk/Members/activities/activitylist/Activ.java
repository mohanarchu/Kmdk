package com.video.aashi.kmdk.Members.activities.activitylist;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Members.activities.classes.PostPojo;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Activ {
    MenuStrings menuStrings;
    Context context;
    ActPresent actPresent;
    ArrayList<ActList> actLists;
    public Activ(Context context,ActPresent actPresent)
    {
        this.context = context;
        this.actPresent = actPresent;
    }


    public void getList()
    {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                actPresent.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                actPresent.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            actPresent.showProgress();
            if (change)
            {
                actPresent.progressMessage("Please wait..!");
            }
            else
            {
                actPresent.progressMessage("காத்திருக்கவும்..!");
            }
            getObservables().subscribeWith(getLists());
        }
    }
    public Observable<ActPojo> getObservables() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getLists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ActPojo> getLists()
    {
        return new DisposableObserver<ActPojo>() {
            @Override
            public void onNext(ActPojo actPojo) {

                actLists = new ArrayList<>();
                Log.i("Activity","ListError" + actPojo.getStatus());

                ArrayList<ActResponse> actResponses =actPojo.getResponse();

                if (actPojo.getStatus().contains("true"))
                {
                    for (int i=0;i<actResponses.size();i++ )
                    {
                        ActResponse act = actResponses.get(i);
                        actLists.add(new ActList(act.get_id(),act.getActivityName(),act.getActivityDate(),act.getActivityType().getActivityType()
                            ,act.getActivityImage().getFilename(),act.getDescription(),act.getCreatedBy().getName(),act.getCreatedBy().get_id(),
                                act.getPlace(),
                              act.getActivityVideo().getFilename()   ));
                    }
                    actPresent.showActs(actLists);
                }
                actPresent.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                Log.i("Activity","ListError" +e.toString() );
                actPresent.hideProgress();
            }
            @Override
            public void onComplete() {
            }
        };
    }
}
