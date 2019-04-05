package com.video.aashi.kmdk.memberJoin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Locations.StatePresenter;
import com.video.aashi.kmdk.Locations.branch.BranchPOst;
import com.video.aashi.kmdk.Locations.branch.BranchPojo;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.memberJoin.classfiles.REgisterPojo;
import com.video.aashi.kmdk.memberJoin.classfiles.RegisterPost;
import com.video.aashi.kmdk.memberJoin.mobile.Mobile;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePojo;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePost;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RegisteerPresenter {

    RegisterPresen registerPresen;
    Context context;
    StatePresenter statePresenter;
    ArrayList<Mobile> arrayList;
    MenuStrings menuStrings;
    public RegisteerPresenter(Context context, RegisterPresen registerPresen, StatePresenter statePresenter)
    {
        this.context = context;
        this.registerPresen = registerPresen;
        this.statePresenter = statePresenter;
    }
    public void getMobile(String string) {
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

            getObservable(string).subscribeWith(getMobiles());
        }
    }
    public Observable<MobilePojo> getObservable(String district) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getMobile(new MobilePost(district))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public DisposableObserver<MobilePojo>  getMobiles()  {
        return new DisposableObserver<MobilePojo>() {
            @Override
            public void onNext(MobilePojo mobilePojo) {

                if (mobilePojo.getCanSave().contains("true"))
                {
                    statePresenter.showMessage("Validate");
                    registerPresen.showImage(R.drawable.ic_checked);
                }
                else
                {
                    statePresenter.showMessage("This number is already present in member list..!");
                    registerPresen.showImage(R.drawable.ic_cancel);

                }
                statePresenter.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                if (menuStrings.getSharedPreferences())
                {
                    statePresenter.showMessage("Please try again..!" );
                }
                else
                {
                    statePresenter.showMessage("மீண்டும் முயற்சிக்கவும்");
                }
                statePresenter.hideProgress();
            }
            @Override
            public void onComplete() {
            }
        };
    }



    @SuppressLint("CheckResult")
    public void Register(String name, String education, String address, String city, String mobileNumber, String state, String district
            , String zone, String branch, String user_Id, String volunteer, String committe, String subcaste)
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

            getRegister(name,education,address,city,mobileNumber,state,district,zone,branch,user_Id,volunteer,
                    committe,subcaste  ).subscribeWith(getRegisterResponse());
        }
    }
    public Observable<REgisterPojo> getRegister(String name,String education,String address,String city,String mobileNumber,String state,String district
            ,String zone,String branch,String user_Id,String volunteer,String committe,String subcaste)
    {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getRegister(new RegisterPost(name,education,address,city,mobileNumber,state,district,zone,branch,user_Id,volunteer,
                        committe,subcaste))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public  DisposableObserver<REgisterPojo> getRegisterResponse()
    {
        return  new DisposableObserver<REgisterPojo>() {
            @Override
            public void onNext(REgisterPojo rEgisterPojo) {

                if (rEgisterPojo.getStatus().contains("true"))
                {
                    statePresenter.hideProgress();

                    if (menuStrings.getSharedPreferences())
                    {
                        statePresenter.showMessage(rEgisterPojo.getMessage() );
                    }
                    else
                    {
                        statePresenter.showMessage("உறுப்பினர் வெற்றிகரமாக சேர்ந்துள்ளார்");
                    }
                    registerPresen.clear();

                }
                else
                {
                    statePresenter.hideProgress();
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","ERROR :"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    statePresenter.showMessage("Please try again..!" );
                }
                else
                {
                    statePresenter.showMessage("மீண்டும் முயற்சிக்கவும்");
                }
                statePresenter.hideProgress();

            }

            @Override
            public void onComplete() {

            }
        };
    }

}
