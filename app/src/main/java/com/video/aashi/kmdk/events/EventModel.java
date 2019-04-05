package com.video.aashi.kmdk.events;

import android.content.Context;
import android.util.Log;

import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Members.activities.activitylist.ActPojo;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EventModel {


    Context context;
    EventPresenter eventPresenter;
    MenuStrings menuStrings;
    ArrayList<EventList> eventLists;
    public EventModel(Context context,EventPresenter eventPresenter)
    {
        this.context = context;
        this.eventPresenter = eventPresenter;
    }

    public void getEvents()

    {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                eventPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                eventPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            eventPresenter.showProgress();
            if (change)
            {
                eventPresenter.progressMessage("Please wait..!");
            }
            else
            {
                eventPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservables().subscribeWith(getLists());
        }
    }
    public Observable<EventPojo> getObservables() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


  public DisposableObserver<EventPojo> getLists()
  {
      return new DisposableObserver<EventPojo>() {
          @Override
          public void onNext(EventPojo eventPojo) {
              eventLists = new ArrayList<>();

              ArrayList<EventResponse> eventResponses = eventPojo.getResponse();

              Log.i("Events","eventError"+eventPojo.getStatus());
              if (eventPojo.getStatus().equals("true"))
              {
                  for (int i=0;i<eventResponses.size();i++)
                  {
                      EventResponse res =eventResponses.get(i);
                      eventLists.add(new EventList(res.getEventName(),res.get_id(),res.getEventImage().getFilename(),
                           res.getPlace(),res.getDate(),res.getContactName(),res.getContactNumber(),
                          res.getCreatedBy().getName() ,res.getCreatedBy().get_id(),res.getChiefGuest()));
                  }
                  eventPresenter.showLists(eventLists);
              }
              eventPresenter.hideProgress();
          }
          @Override
          public void onError(Throwable e) {
          eventPresenter.hideProgress();
          Log.i("Events","eventError"+e.toString());

          }
          @Override
          public void onComplete() {

          }
      };
  }



}
