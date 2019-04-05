package com.video.aashi.kmdk.Login.otp.classfiles;

import com.video.aashi.kmdk.view.ViewIntract;

public interface otpPresenter extends ViewIntract {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void showScreen();
    void showToast(String string);
    void seetOtp(String text);

}
