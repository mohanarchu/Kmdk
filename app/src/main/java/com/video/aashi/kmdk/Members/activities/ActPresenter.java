package com.video.aashi.kmdk.Members.activities;

import android.graphics.Bitmap;
import android.net.Uri;

import com.video.aashi.kmdk.Cmplaints.compliatsList.ListCategory;
import com.video.aashi.kmdk.Members.activities.classes.ActtypeList;
import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface ActPresenter extends ViewIntract


{


    void requestPhotoPermissions(boolean b);
    void requestVideoermison(boolean b);
    @Override
    void showProgress();
    @Override
    void hideProgress();
    @Override
    void progressMessage(String message);
    void showToast(String message);
    void  showLists(ArrayList<ActtypeList> categories);
    void  complete();




}
