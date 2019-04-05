package com.video.aashi.kmdk.contact;

import com.video.aashi.kmdk.view.ViewIntract;

public interface ContactPresenter extends ViewIntract {

    @Override
    void showProgress();

    @Override
    void hideProgress();
    @Override
    void progressMessage(String message);
    void showToast(String toast);
    void clearText();
}
