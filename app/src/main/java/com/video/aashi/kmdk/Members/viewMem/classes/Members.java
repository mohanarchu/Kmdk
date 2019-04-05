package com.video.aashi.kmdk.Members.viewMem.classes;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Locations.states.StatePost;
import com.video.aashi.kmdk.Login.classfiles.LoginPojo;
import com.video.aashi.kmdk.Login.classfiles.LoginPost;
import com.video.aashi.kmdk.Login.otp.classfiles.OtpRespons;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Members {
    Context context;
    MemPresenter memPresenter;
    MenuStrings menuStrings;
    ArrayList<MemList> memLists;
    boolean yess;
    public Members(Context context,MemPresenter memPresenter)
    {
        this.context = context;
        this.memPresenter = memPresenter;
    }


    public void showAll(boolean yes)
    {
        yess = yes;
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {
                  memPresenter.showToast("Check internet connection...!");
            }
            else
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

            getObservable().subscribeWith(getMembers());
        }
    }
    public Observable<MemPojo> getObservable() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getAllMembers(new MemPost(""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public DisposableObserver<MemPojo> getMembers()
    {
        return new DisposableObserver<MemPojo>() {
            @Override
            public void onNext(MemPojo memPojo) {

                ArrayList<OtpRespons> memResponses = memPojo.getResponse();
                memLists = new ArrayList<>();
                Log.i("TAG","MemResponse"+ memPojo.getStatus() + memPojo.getResponse());
                if (memPojo.getStatus().contains("true"))
                {
                  for (int i =0;i<memResponses.size();i++)
                  {
                      OtpRespons memResponse = memResponses.get(i);


                      if (yess)
                      {
                          if (memResponse.getStatus().contains("Approved") || memResponse.getStatus().contains("Requested")) {

                              memLists.add(new MemList(memResponse.getName(), memResponse.getCreatedAt(), memResponse.get_id(),
                                      memResponse.getMobileNumber()
                                      , memResponse.getEducation(), memResponse.getMemberImage().getFilename(),
                                      memResponse.getCity(), memResponse.getStatus()
                                      ,"", ""
                                      ,"", "", memResponse.getMemberReferenceId()));
                              Log.i("TAG", "MemResponse" + memResponse.getMemberImage().getFilename());
                          }
                      }
                      else
                      {
                          if (memResponse.getStatus().contains("Rejected"))
                          {
                              memLists.add(new MemList(memResponse.getName(),memResponse.getCreatedAt(),memResponse.get_id(),
                                      memResponse.getMobileNumber()
                                      ,memResponse.getEducation(),memResponse.getMemberImage().getFilename() ,memResponse.getCity(),memResponse.getStatus()
                                      ,"", ""
                                      ,"", "",memResponse.getMemberReferenceId()));
                              Log.i("TAG","MemResponses"+ memResponse.getMemberImage().getFilename());
                          }
                      }

                  }

                  memPresenter.showAllMember(memLists);
                    memPresenter.hideProgress();
                }
            }

            @Override
            public void onError(Throwable e) {
                memPresenter.hideProgress();
                Log.i("TAG","MemResponses"+ e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
    }
    public void showAlls()
    {  menuStrings = new MenuStrings(context);
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

            getObservablse().subscribeWith(getMemberss());
        }
    }
    public Observable<MemPojo> getObservablse() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getAllMembers(new MemPost(""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public DisposableObserver<MemPojo> getMemberss()
    {
        return new DisposableObserver<MemPojo>() {
            @Override
            public void onNext(MemPojo memPojo) {
                ArrayList<OtpRespons> memResponses = memPojo.getResponse();
                memLists = new ArrayList<>();
                Log.i("TAG","MemResponse"+ memPojo.getStatus() + memPojo.getResponse());
                if (memPojo.getStatus().contains("true"))
                {
                    for (int i =0;i<memResponses.size();i++)
                    {
                        OtpRespons memResponse = memResponses.get(i);
                        if (memResponse.getStatus().contains("Requested"))
                        {
                            memLists.add(new MemList(memResponse.getName(),memResponse.getCreatedAt(),memResponse.get_id(),
                                    memResponse.getMobileNumber()
                                    ,memResponse.getEducation(),memResponse.getMemberImage().getFilename() ,memResponse.getCity(),memResponse.getStatus()
                                 ,memResponse.getState().getStateName(),memResponse.getDistrict().getDistrictName()
                               ,memResponse.getZone().getZoneName(),memResponse.getBranch().getBranchName(),memResponse.getMemberReferenceId()));
                        }
                    }
                    memPresenter.showAllMember(memLists);
                    memPresenter.hideProgress();
                }
            }
            @Override
            public void onError(Throwable e) {
                memPresenter.hideProgress();
                Log.i("TAG","MemResponses"+ e.toString());
            }
            @Override
            public void onComplete() {
            }
        };
    }
}
