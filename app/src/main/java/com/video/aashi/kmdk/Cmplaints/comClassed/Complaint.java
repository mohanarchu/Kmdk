package com.video.aashi.kmdk.Cmplaints.comClassed;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ComplitsLists;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ListCategory;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ListPojo;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePojo;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePost;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.view.PhotoChooserDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Complaint implements ComplaintView{

    ComplaiPresenter complaiPresenter;
    Context context;
    View mView;
    PopupWindow editPop,videoPop;
    PopupWindow viewImage;
    MenuStrings menuStrings;
    ArrayList<ListCategory> complitsLists;
    public Complaint (ComplaiPresenter presenter, Context context)
    {
        this.complaiPresenter = presenter;
        this.context = context;
    }


    @Override
    public void getPhoto() {
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
                 complaiPresenter.requestPermissions(true);

                // checkPermissionREAD_EXTERNAL_STORAGE(context);
            }
        });


        b_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaiPresenter.requestPermissions(false);

            }
        });

        dimBehind(editPop);

    }

    @Override
    public void dismissPop() {
        editPop.dismiss();
    }

    @Override
    public void viewImage(Context context, Bitmap bitmap) {

    }

    @Override
    public void getVideo() {
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
                complaiPresenter.requestVideoermison(true);
                complaiPresenter.showToast("ViewPermissin");

                // checkPermissionREAD_EXTERNAL_STORAGE(context);
            }
        });


        b_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaiPresenter.requestVideoermison(false);

            }
        });

        dimBehind(videoPop);

    }

    @Override
    public void dismissvideoPop() {
        videoPop.dismiss();
    }

    @Override
    public void uploadComplaint(RequestBody id, RequestBody fullName, MultipartBody.Part image,
                                MultipartBody.Part video, MultipartBody.Part audio, RequestBody mobile,
                                RequestBody type, RequestBody complaint, RequestBody message, RequestBody place,RequestBody places) {
        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))
        {
            if (change)
            {

                complaiPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                complaiPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            complaiPresenter.showProgress();
            if (change)
            {
                complaiPresenter.progressMessage("Please wait..!");
            }
            else
            {
                complaiPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservable(id,fullName,image,video,audio,mobile,type,complaint,message,place,places).subscribeWith(getCoomplaint());
        }

    }

    @Override
    public void getLists() {



        menuStrings = new MenuStrings(context);
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(context))

        {
            if (change)
            {

                complaiPresenter.showToast("Check internet connection...!");
                // menuStrings.getToastTamil("Check internet connection...!");
            }else
            {
                complaiPresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
                //      menuStrings.getToastTamil("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        }
        else
        {
            complaiPresenter.showProgress();
            if (change)
            {
                complaiPresenter.progressMessage("Please wait..!");
            }
            else
            {
                complaiPresenter.progressMessage("காத்திருக்கவும்..!");
            }

            getObservables().subscribeWith(getAlls());
        }



    }

    public Observable<ResponseBody> getObservable(RequestBody id, RequestBody fullName, MultipartBody.Part image,
                                                  MultipartBody.Part video, MultipartBody.Part audio, RequestBody mobile,
                                                  RequestBody type, RequestBody complaint, RequestBody message, RequestBody place,
                                                  RequestBody branch) {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .updateComplaint(id,fullName,image,video,audio,mobile,type,complaint,message,place,branch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ResponseBody> getCoomplaint()
    {
        return new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                menuStrings = new MenuStrings(context);
                complaiPresenter.hideProgress();
                String bodyString = null;

                    try {
                        bodyString = responseBody.string();


                        String source;
                        JSONObject jsonObject = new JSONObject(bodyString);
                        if(jsonObject.getString("Status").contains("true") )
                        {
                            if (menuStrings.getSharedPreferences())
                            {
                                complaiPresenter.showToast("Complaint Registered...!");

                            }
                            else
                            {
                                complaiPresenter.showToast("புகார் பதிவுசெய்யப்பட்டது...!");
                            }

                            complaiPresenter.clearTexts();
                        }
                        else
                        {

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                Log.i("Tag","ComplaintsError"+ bodyString);

            }

            @Override
            public void onError(Throwable e) {
                complaiPresenter.hideProgress();
                Log.i("Tag","ComplaintsError"+ e.toString());
                if (menuStrings.getSharedPreferences())
                {
                    complaiPresenter.showToast("Please try again...!");

                }
                else
                {
                    complaiPresenter.showToast("தயவுசெய்து மீண்டும் முயற்சி செய்க...!");
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
    public Observable<ListPojo> getObservables() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .CategoryComp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
  public DisposableObserver<ListPojo> getAlls()
  {
      return new DisposableObserver<ListPojo>() {
          @Override
          public void onNext(ListPojo listPojo) {
              Log.i("TAG","Errors"+listPojo.getStatus());

              complitsLists = new ArrayList<>();

              if (listPojo.getStatus().contains("true"))
              {
                  for (int i=0;i<listPojo.getResponse().size();i++)
                  {
                      complitsLists.add(new ListCategory(listPojo.getResponse().get(i).getComplaintCategory(),
                              listPojo.getResponse().get(i).get_id()));
                  }

                  complaiPresenter.showLists(complitsLists);

              }
              complaiPresenter.hideProgress();
          }

          @Override
          public void onError(Throwable e) {
              Log.i("TAG","Errors"+e.toString());
             complaiPresenter.hideProgress();
          }

          @Override
          public void onComplete() {

          }
      };
  }

}
