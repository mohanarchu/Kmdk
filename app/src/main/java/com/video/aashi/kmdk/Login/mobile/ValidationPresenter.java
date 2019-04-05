package com.video.aashi.kmdk.Login.mobile;

import android.content.Context;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Login.classfiles.LoginPresenter;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePojo;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePost;
import com.video.aashi.kmdk.menunames.MenuStrings;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ValidationPresenter {



    LoginPresenter loginPresenter;
    Context context;
    MenuStrings menuStrings;
    public ValidationPresenter(Context context,LoginPresenter loginPresenter)
    {
        this.context = context;
        this.loginPresenter = loginPresenter;
    }

    public void getMobile(String string) {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                loginPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                loginPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            loginPresenter.showProgress();
            if (change)
            {
                loginPresenter.setProgressMessage("Please wait..!");
            }
            else
            {
                loginPresenter.setProgressMessage("காத்திருக்கவும்..!");
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
    public DisposableObserver<MobilePojo> getMobiles()  {
        return new DisposableObserver<MobilePojo>() {
            @Override
            public void onNext(MobilePojo mobilePojo) {

                if (mobilePojo.getCanSave().contains("true"))
                {

                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        loginPresenter.showToast("This number is already present in member list..!");
                    }
                    else
                    {

                    }


                }

                loginPresenter.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                if (menuStrings.getSharedPreferences())
                {
                    loginPresenter.showToast("Please try again..!" );
                }
                else
                {
                    loginPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
                loginPresenter.hideProgress();
            }
            @Override
            public void onComplete() {
            }
        };
    }



}
