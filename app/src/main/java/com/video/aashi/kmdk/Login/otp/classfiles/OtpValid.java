package com.video.aashi.kmdk.Login.otp.classfiles;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Config.Permission;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Login.classfiles.LoginPojo;
import com.video.aashi.kmdk.Login.classfiles.LoginPost;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.PreferenceValue.SharedPreference;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class OtpValid {

    Context context;
    otpPresenter otpPresenter;
    MenuStrings menuStrings;
    SharedPreference sharedPreference;
    UserSession userSession;
    public OtpValid(otpPresenter otpPresenter, Context context)
    {
        this.otpPresenter = otpPresenter;
        this.context = context;
    }

    public  void getOtp(String otp,String mobile) {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change) {

                otpPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            } else {
                otpPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        } else {
            otpPresenter.showProgress();
            if (change) {
                otpPresenter.progressMessage("Please wait..!");
            } else {
                otpPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservable(otp,mobile).subscribeWith(getMobiles());
        }

    }
    public Observable<otpPojo> getObservable(String otp,String mobile) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getOtp(new otpPost(mobile,otp))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<otpPojo> getMobiles()
    {
        return new DisposableObserver<otpPojo>() {
            @Override
            public void onNext(otpPojo otpPojo) {

                userSession = new UserSession(context);

                otpPresenter.hideProgress();

                if (otpPojo.getStatus() .contains("true"))
                {
               //     OtpRespons otpRespons =otpPojo.getResponse();
                    sharedPreference = new SharedPreference(context);
                    OtpRespons otpResponss = otpPojo.getResponse();

                    Designations otpRespons = otpPojo.getResponse().getDesignation();

                    sharedPreference.setSharedPreferences();


                    if (otpResponss.getIf_Official().contains("true"))
                    {
                        userSession.setZoneAuthority(Boolean.valueOf(otpRespons.getZone_Authority()));
                        userSession.setBranchAuthority(Boolean.valueOf(otpRespons.getBranch_Authority()));
                        userSession.setDistriictAuthority(Boolean.valueOf(otpRespons.getDistrict_Authority()));
                        userSession.setStateAuthority(Boolean.valueOf(otpRespons.getState_Authority()));
                        userSession.setCanAdd(Boolean.valueOf(otpRespons.getCan_Add()));
                        userSession.setCanApprove(Boolean.valueOf(otpRespons.getCan_Approval()));
                        userSession.setCanEdit(Boolean.valueOf(otpRespons.getCan_Edit()));
                        userSession.setCanView(Boolean.valueOf(otpRespons.getCan_View()));
                    }
                    userSession.setId(otpResponss.get_id());
                    userSession.setStatus(otpResponss.getStatus());
                    userSession.setIfCommitte(Boolean.valueOf(otpResponss.getIf_Committee()));
                    userSession.setIfOfficial(Boolean.valueOf(otpResponss.getIf_Official()));
                    userSession.setState(otpResponss.getState().getStateName());
                    userSession.setDistrict(otpResponss.getDistrict().getDistrictName());
                    userSession.setZone(otpResponss.getZone().getZoneName());
                    userSession.setBranch(otpResponss.getBranch().getBranchName());
                    userSession.setIfCommitte(Boolean.valueOf(otpResponss.getIf_Committee()));
                    userSession.setStateId(otpResponss.getState().get_id());
                    userSession.setDistrictId(otpResponss.getDistrict().get_id());
                    userSession.setZoneId(otpResponss.getZone().get_id());
                    userSession.setBranchId(otpResponss.getBranch().get_id());
                    userSession.setName(otpResponss.getName());
                    userSession.setMobilenumber(otpResponss.getMobileNumber());
                    userSession.applyShared();
                    Log.i("TAG","MyId"+otpResponss.getName() );
                    if (menuStrings.getSharedPreferences())
                     {
                    otpPresenter.showToast("Thank you..!" );
                }
                else
                {
                    otpPresenter.showToast("நன்றி");
                }
                otpPresenter.showScreen();








                }



                 else
                 {
                     Log.i("TAG","MyId"+ otpPojo.getResponse());
                    if (menuStrings.getSharedPreferences())
                    {
                        otpPresenter.showToast("Please try again..!" );
                    }
                    else
                    {
                        otpPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","MyId"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    otpPresenter.showToast("Please try again..!" );
                }
                else
                {
                    otpPresenter.showToast("மீண்டும் முயற்சிக்கவும்");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public     boolean isSmsPermissionGranted()
    {
        return  Permission.isReceiveSmsPermissionGranted(context);
    }

}
