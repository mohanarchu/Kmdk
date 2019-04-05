package com.video.aashi.kmdk.Members.viewMem.approve;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Login.otp.classfiles.OtpRespons;
import com.video.aashi.kmdk.Members.viewMem.classes.MemList;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPojo;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPost;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPresenter;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Approve {

    Context context;
    MemPresenter memPresenter;
    MenuStrings menuStrings;
 //   ArrayList<MemList> memLists;
    public Approve(Context context,MemPresenter memPresenter)
    {
        this.context = context;
        this.memPresenter = memPresenter;
    }


    public void memApprove(String id,String memid)
    {  menuStrings = new MenuStrings(context);
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("User_Id",id);
        jsonObject.addProperty("Member_Id",memid);

        Log.i("TAG","MemReject"+  jsonObject);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {
                memPresenter.showToast("Check internet connection...!");

            }else
            {
                memPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            memPresenter.showProgress();
            if (change)
            {
                memPresenter.progressMessage("Please wait..!");
            }
            else
            {
                memPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservable(jsonObject).subscribeWith(getMembers());
            Log.i("TAG","MemIds"+ memid+ id );
        }

    }

    public Observable<ApprovePojo> getObservable(JsonObject jsonObject) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .memApprove(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public DisposableObserver<ApprovePojo> getMembers()
    {
        return new DisposableObserver<ApprovePojo>() {
            @Override
            public void onNext(ApprovePojo memPojo) {

                Log.i("TAG","MemResponse"+ memPojo.getStatus() );
                if (memPojo.getStatus().contains("true"))
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        memPresenter.showToast(memPojo.getMessage());
                    //    memPresenter.showToast("Approved successfully" );
                    }
                    else
                    {
                        memPresenter.showToast("வெற்றிகரமாக அங்கீகரிக்கப்பட்டது");
                    }
                    // memPresenter.showAllMember(memLists);
                    memPresenter.referesh();
                    memPresenter.hideProgress();
                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        memPresenter.showToast("Please try again..!" );
                    }
                    else
                    {
                        memPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                memPresenter.hideProgress();
                Log.i("TAG","approve"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    memPresenter.showToast("Please try again..!" );
                }
                else
                {
                    memPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
    public void removeMem(String id,String memid)
    {
        menuStrings = new MenuStrings(context);

        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("User_Id",memid);
        jsonObject.addProperty("Member_Id",id);

        Log.i("TAG","MemReject"+  jsonObject);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {
                memPresenter.showToast("Check internet connection...!");

            }else
            {
                memPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            memPresenter.showProgress();
            if (change)
            {
                memPresenter.progressMessage("Please wait..!");
            }
            else
            {
                memPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservablse(jsonObject).subscribeWith(getMemberss());
        }

    }

    public Observable<ApprovePojo> getObservablse(JsonObject jsonObject) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .RejectMember(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ApprovePojo> getMemberss()
    {
        return new DisposableObserver<ApprovePojo>() {
            @Override
            public void onNext(ApprovePojo memPojo) {

                if (memPojo.getStatus().contains("true"))
                {

                    if (menuStrings.getSharedPreferences())
                    {
                        memPresenter.showToast(memPojo.getMessage());
                    }
                    else
                    {
                        memPresenter.showToast("வெற்றிகரமாக நிராகரிக்கப்பட்டது");
                    }

                    memPresenter.notifys();
                    memPresenter.hideProgress();

                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        memPresenter.showToast("Please try again..!" );
                    }
                    else
                    {
                        memPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                    }
                }
                memPresenter.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                memPresenter.hideProgress();
                Log.i("TAG","MemReject"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    memPresenter.showToast("Please try again..!" );
                }
                else
                {
                    memPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }
            @Override
            public void onComplete() {

            }
        };
    }
}
