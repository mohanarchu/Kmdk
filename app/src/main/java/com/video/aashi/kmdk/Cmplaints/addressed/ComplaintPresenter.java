package com.video.aashi.kmdk.Cmplaints.addressed;

import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface ComplaintPresenter extends ViewIntract {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void showToast(String toast);

    void showComplaintss(ArrayList<ComplaiArray> complaiArrays);
}
