package com.video.aashi.kmdk.Members.advertisements.list;

import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

public interface AdListPresenter extends ViewIntract {
    @Override
    void showProgress();

    @Override
    void hideProgress();

    @Override
    void progressMessage(String message);

    void  showToast(String string);

    void showAdds(ArrayList<AdvertiseList> adListResponses);
}
