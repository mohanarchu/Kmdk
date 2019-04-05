package com.video.aashi.kmdk.events;

import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface EventPresenter extends ViewIntract {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void showToast(String toast);

    void showLists(ArrayList<EventList> eventLists);
}
