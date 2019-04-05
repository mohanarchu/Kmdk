package com.video.aashi.kmdk.tabs.gallery;

import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface GalleryPresenter extends ViewIntract {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

   void showToast(String mesage);

   void showList(ArrayList<GalleryList> galleryLists);
}
