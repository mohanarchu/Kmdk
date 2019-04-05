package com.video.aashi.kmdk.Cmplaints.comClassed;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import com.video.aashi.kmdk.Cmplaints.compliatsList.ListCategory;
import com.video.aashi.kmdk.view.ViewIntract;

import java.io.File;
import java.util.ArrayList;

public interface ComplaiPresenter extends ViewIntract {

    void comPhoto(Bitmap image);
    void requestPermissions(boolean b);
    void requestVideoermison(boolean b);
    void getVideo(Uri bitmap);

    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);
    void showToast(String message);

    void  showLists(ArrayList<ListCategory> categories);
    void clearTexts();


}
