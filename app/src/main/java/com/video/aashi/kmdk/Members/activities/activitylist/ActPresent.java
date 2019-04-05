package com.video.aashi.kmdk.Members.activities.activitylist;

import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface ActPresent extends ViewIntract {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void showToast(String message);
    void  showActs(ArrayList<ActList> actLists);

}
