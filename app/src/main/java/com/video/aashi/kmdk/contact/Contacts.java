package com.video.aashi.kmdk.contact;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Locations.branch.BranchPOst;
import com.video.aashi.kmdk.Locations.branch.BranchPojo;
import com.video.aashi.kmdk.menunames.MenuStrings;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Contacts implements ContactView {

    ContactPresenter contactPresenter;
    MenuStrings menuStrings;
    Context context;
    public Contacts(Context context,ContactPresenter contactPresenter)
    {
        this.contactPresenter = contactPresenter;
        this.context = context;

    }

    @Override
    public void putEnquiry(ContactPost contactPost) {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                contactPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                contactPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            contactPresenter.showProgress();
            if (change)
            {
                contactPresenter.progressMessage("Please wait..!");
            }
            else
            {
                contactPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservable(contactPost).subscribeWith(getEnquiry());
        }
    }
    public Observable<ContactPojo> getObservable(ContactPost contactPost)
    {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getContact(contactPost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ContactPojo> getEnquiry()
    {
        return new DisposableObserver<ContactPojo>() {
            @Override
            public void onNext(ContactPojo contactPojo) {

                contactPresenter.hideProgress();
                if (contactPojo.getStatus().equals("true"))

                {
                    if (menuStrings.getSharedPreferences())
                    {
                        contactPresenter.showToast(contactPojo.getMessage());
                    }
                    else
                    {
                        contactPresenter.showToast("விசாரணை சமர்ப்பிக்கப்பட்டது");
                    }
                    contactPresenter.clearText();

                }
                else
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        contactPresenter.showToast(contactPojo.getMessage());
                    }
                    else
                    {
                        contactPresenter.showToast("விசாரணை சமர்ப்பிக்கப்பட்டது");
                    }
                }




            }

            @Override
            public void onError(Throwable e) {
                contactPresenter.hideProgress();
                Log.d("TAG","EnquiryError"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    contactPresenter.showToast("Error..!");
                }
                else
                {
                    contactPresenter.showToast("ஏதோ தவறு நடந்துவிட்டது..!");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
