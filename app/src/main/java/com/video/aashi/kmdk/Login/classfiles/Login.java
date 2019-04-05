package com.video.aashi.kmdk.Login.classfiles;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.iceteck.silicompressorr.videocompression.Config;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Login.mobile.MobileNoDialog;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePojo;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePost;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Login implements LoginView{


    LoginPresenter loginPresenter;
    Context context;
    MenuStrings menuStrings;
    String number;
    public  Login(LoginPresenter presenter, Context context)
    {
        this.loginPresenter = presenter;
        this.context =  context;
    }
    @Override
    public void doLogin(String mobilenumer) {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        number = mobilenumer;
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {
                loginPresenter.showToast("Check internet connection...!");

            }else
            {
                loginPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
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

            getObservable(mobilenumer).subscribeWith(getMobiles());
        }
       }

    @Override
    public boolean validate(String number) {
            Pattern pattern = Pattern.compile(com.video.aashi.kmdk.Config.Config.MOBILE_NO_PATTERN);
            return pattern.matcher(number).matches() && number.length() <= 10;

    }

    public Observable<LoginPojo> getObservable(String number) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getLogin(new LoginPost(number))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<LoginPojo> getMobiles()
    {
        return new DisposableObserver<LoginPojo>() {
            @Override
            public void onNext(LoginPojo loginPojo) {

                loginPresenter.hideProgress();
                if (loginPojo.getStatus().contains("true"))
                {
                    loginPresenter.showToast(loginPojo.getMessage());
                    loginPresenter.verifyOtp(loginPojo.getResponse(),number);
                 //   loginPresenter.showToast(number);
                }
                else if (loginPojo.getStatus().equals("false"))
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        if (loginPojo.getMessage().contains("Not A Member, Please Register"))
                        {
                            loginPresenter.hideProgress();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginPresenter.shoPop();
                                }
                            }, 100);

                        }
                        else
                        {
                            loginPresenter.showToast(loginPojo.getMessage() +" or not approved");
                        }

                    }
                    else
                    {

                        if (loginPojo.getMessage().contains("Not A Member, Please Register"))
                        {
                            loginPresenter.hideProgress();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginPresenter.shoPop();
                                }
                            }, 100);
                        }
                        else
                        {
                            loginPresenter.hideProgress();
                            loginPresenter.showToast("நீங்கள் இன்னும் அனுமதிக்கப்படவில்லை அல்லது நீங்கள் நிராகரிக்கப்பட்டு விட்டீர்கள்");

                        }
                     }
                }
                loginPresenter.hideProgress();
            }
            @Override
            public void onError(Throwable e) {
                loginPresenter.hideProgress();
                if (menuStrings.getSharedPreferences())
                {
                    loginPresenter.showToast("Please try again..!" );
                }
                else
                {
                    loginPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }
            @Override
            public void onComplete() {

            }
        };
    }

}
