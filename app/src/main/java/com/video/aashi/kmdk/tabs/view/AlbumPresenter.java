package com.video.aashi.kmdk.tabs.view;

import com.video.aashi.kmdk.tabs.gallery.GalleryList;
import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface AlbumPresenter extends ViewIntract {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void showToast(String mesage);

    void showList(ArrayList<GalleryName> galleryLists);

    void showPhotos(ArrayList<AllImages> galleryLists);


}
