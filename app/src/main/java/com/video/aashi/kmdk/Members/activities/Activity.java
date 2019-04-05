package com.video.aashi.kmdk.Members.activities;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.video.aashi.kmdk.Cmplaints.compliatsList.ListCategory;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ListPojo;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Members.activities.classes.ActlistPojo;
import com.video.aashi.kmdk.Members.activities.classes.ActtypeList;
import com.video.aashi.kmdk.Members.activities.classes.PostPojo;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Activity {

    Context context;
    ActPresenter actPresenter;
    MenuStrings menuStrings;
    PopupWindow editPop,videoPop;
    View mView;

    public Activity(Context context,ActPresenter actPresenter)
    {
        this.context = context;
        this.actPresenter = actPresenter;
    }


    public void getPhoto()
    {
        menuStrings = new MenuStrings(context);
        editPop = new PopupWindow(context);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.dialog_photo_chooser, null);
        editPop = new PopupWindow(
                mView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        editPop.setFocusable(true);
        editPop.setAnimationStyle(R.style.popupanimation);
        editPop.showAtLocation(mView, Gravity.BOTTOM|Gravity.END, 0, 0);
        LinearLayout b_camera = (LinearLayout)mView.findViewById(R.id.btnCamera);
        LinearLayout b_gallary = (LinearLayout)mView.findViewById(R.id.btnGallery);
        TextView fromCm =(TextView)mView.findViewById(R.id.fromCamera);
        TextView fromGal=(TextView)mView.findViewById(R.id.fromGallery);
        TextView head =(TextView)mView.findViewById(R.id.headers);

        if (menuStrings.getSharedPreferences())
        {
            fromCm.setText("Camera");
            fromGal .setText("Gallery");
            head.setText("Choose..!");
        }
        b_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actPresenter.requestPhotoPermissions(true);
                 actPresenter.showToast("clicked");
                // checkPermissionREAD_EXTERNAL_STORAGE(context);
            }
        });


        b_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actPresenter.requestPhotoPermissions(false);

            }
        });

        dimBehind(editPop);

    }

    public void getVideo()
    {
        menuStrings = new MenuStrings(context);
        videoPop = new PopupWindow(context);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.dialog_photo_chooser, null);
        videoPop = new PopupWindow(
                mView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        videoPop.setFocusable(true);
        videoPop .setAnimationStyle(R.style.popupanimation);
        videoPop.showAtLocation(mView, Gravity.BOTTOM|Gravity.END, 0, 0);
        LinearLayout b_camera = (LinearLayout)mView.findViewById(R.id.btnCamera);
        LinearLayout b_gallary = (LinearLayout)mView.findViewById(R.id.btnGallery);
        TextView fromCm =(TextView)mView.findViewById(R.id.fromCamera);
        TextView fromGal=(TextView)mView.findViewById(R.id.fromGallery);
        TextView head =(TextView)mView.findViewById(R.id.headers);

        if (menuStrings.getSharedPreferences())
        {
            fromCm.setText("Camera");
            fromGal .setText("Gallery");
            head.setText("Choose..!");
        }

        b_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actPresenter.requestVideoermison(true);
                              // checkPermissionREAD_EXTERNAL_STORAGE(context);
            }
        });


        b_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actPresenter.requestVideoermison(false);

            }
        });

        dimBehind(videoPop);

    }

    public void dismisVideo()
    {
        videoPop.dismiss();
    }
    public void dismissPhoto()
    {
        editPop.dismiss();
    }

    public void getActList()
    {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                actPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                actPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            actPresenter.showProgress();
            if (change)
            {
                actPresenter.progressMessage("Please wait..!");
            }
            else
            {
                actPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservables().subscribeWith(getAlls());
        }


    }
    public Observable<ActlistPojo> getObservables() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .ActivityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public DisposableObserver<ActlistPojo> getAlls()
    {
        return new DisposableObserver<ActlistPojo>() {
            @Override
            public void onNext(ActlistPojo listPojo) {
                Log.i("TAG","Errors"+listPojo.getStatus());

               ArrayList<ActtypeList>   complitsLists = new ArrayList<>();

                if (listPojo.getStatus().contains("true"))
                {
                    for (int i=0;i<listPojo.getResponse().size();i++)
                    {
                        complitsLists.add(new ActtypeList(
                                listPojo.getResponse().get(i).getActivityType(),listPojo.getResponse().get(i).get_id()  ));
                    }

                    actPresenter.showLists(complitsLists);

                }
                actPresenter.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","Errors"+e.toString());
                actPresenter.hideProgress();
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void postActtivity(MultipartBody.Part image,
                              MultipartBody.Part videp,
                              MultipartBody.Part audio,
                              RequestBody type,
                              RequestBody name,
                              RequestBody date,
                              RequestBody place,
                              RequestBody lat,
                              RequestBody lan,
                              RequestBody des,
                              RequestBody userId,
                              RequestBody branchs)
    {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                actPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                actPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            actPresenter.showProgress();
            if (change)
            {
                actPresenter.progressMessage("Please wait..!");
            }
            else
            {
                actPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservables(image, videp, audio, type, name, date, place,lat, lan, des, userId,branchs).subscribeWith(postAct());
        }


    }
    public Observable<PostPojo> getObservables(MultipartBody.Part image,
                                                MultipartBody.Part videp,
                                                MultipartBody.Part audio,
                                                RequestBody type,
                                                RequestBody name,
                                                RequestBody date,
                                                RequestBody place,
                                                RequestBody lat,
                                                RequestBody lan,
                                                RequestBody des,
                                                RequestBody userId,
                                               RequestBody branchs) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .ActPost(image,videp,audio,type,name,date,place,lat,lan,des,userId,branchs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<PostPojo> postAct()
    {
        return new DisposableObserver<PostPojo>() {
            @Override
            public void onNext(PostPojo postPojo) {
                Log.i("Activity","Error"+  postPojo.getMessage());
                if (postPojo.getStatus().contains("true"))
                {
                    actPresenter.hideProgress();
                    if (menuStrings.getSharedPreferences())
                    {
                        actPresenter.showToast("Activity created successfully..!");
                    }
                    else
                    {
                        actPresenter.showToast("வெற்றிகரமாக உருவாக்கப்பட்டது..!");
                    }

                    actPresenter.complete();
                }
                else {
                    if (menuStrings.getSharedPreferences())
                    {
                        actPresenter.showToast("Please try again...!");

                    }
                    else
                    {
                        actPresenter.showToast("தயவுசெய்து மீண்டும் முயற்சி செய்க...!");
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                actPresenter.hideProgress();
                Log.i("Activity","ActivityError"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    actPresenter.showToast("Please try again...!");

                }
                else
                {
                    actPresenter.showToast("தயவுசெய்து மீண்டும் முயற்சி செய்க...!");
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public  void dimBehind(PopupWindow popupWindow) {
        View container;
        if (popupWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) popupWindow.getContentView().getParent();
            } else {
                container = popupWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) popupWindow.getContentView().getParent().getParent();
            } else {
                container = (View) popupWindow.getContentView().getParent();
            }
        }
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.7f;
        wm.updateViewLayout(container, p);
    }
}
