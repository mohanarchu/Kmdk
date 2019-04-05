package com.video.aashi.kmdk.Cmplaints.addressed;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Login.classfiles.LoginPojo;
import com.video.aashi.kmdk.Login.classfiles.LoginPost;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddComplaint {

    ComplaintPresenter complaintPresenter;
    Context context;
    MenuStrings menuStrings;
    public AddComplaint(Context context,ComplaintPresenter complaintPresenter)
    {
        this.context = context;
        this.complaintPresenter = complaintPresenter;
    }
    public  void getComplaints()
    {
        menuStrings = new MenuStrings( context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {

                complaintPresenter.showToast("Check internet connection...!");

            }else
            {
                complaintPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            complaintPresenter.showProgress();
            if (change)
            {
                complaintPresenter.progressMessage("Please wait..!");
            }
            else
            {
                complaintPresenter.progressMessage("காத்திருக்கவும்..!");
            }
            getObservable().subscribeWith(getComplaintss());
        }
    }
    public Observable<AddPojo> getObservable() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getComplaints()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public DisposableObserver<AddPojo> getComplaintss()
    {
        return new DisposableObserver<AddPojo>() {
            @Override
            public void onNext(AddPojo addPojo) {
                ArrayList<ComplaiArray> complaiArrays= new ArrayList<>();
                if (addPojo.getStatus().contains("true"))
                {


                    ArrayList<AddResponse> addPojos = addPojo.getResponse();
                    for (int i=0;i<addPojos.size();i++)
                    {
                        AddResponse addResponse =  addPojos.get(i);
                        complaiArrays.add(new ComplaiArray(
                                addResponse.getName(),addResponse.getMobileNumber(),
                                addResponse.getComplaintType().getComplaintCategory()
                                ,addResponse.getMessage(),addResponse.getComplaint(),addResponse.getCreatedAt(),
                                addResponse.getPlace(),addResponse.getComplaintVideo().getFilename(),addResponse.getComplaintImage().getFilename()

                        ));


                    }
                    complaintPresenter.showComplaintss(complaiArrays);
                    complaintPresenter.hideProgress();

                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        complaintPresenter.showToast("Please try again..!" );
                    }
                    else
                    {
                        complaintPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                    }
                }
                complaintPresenter.hideProgress();
           }
          @Override
            public void onError(Throwable e) {
                Log.i("TAG","ComlaintError"+e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    complaintPresenter.showToast("Please try again..!" );
                }
                else
                {
                    complaintPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
