package com.video.aashi.kmdk.Members.advertisements;

import com.video.aashi.kmdk.Members.advertisements.types.TypeResponse;
import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface AdvertidePresenter extends ViewIntract {

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void showToast(String message);

    void showTypes(ArrayList<TypeResponse> typeResponses);
    void requestPermissions(boolean b);
    void clear();

}
