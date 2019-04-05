package com.video.aashi.kmdk.Members.viewMem.classes;

import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface MemPresenter extends ViewIntract {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);


    void showMember(boolean yes);

    void showToast(String toast);

    void showAllMember(ArrayList<MemList> memLists);
    void referesh();

    void notifys();


}
