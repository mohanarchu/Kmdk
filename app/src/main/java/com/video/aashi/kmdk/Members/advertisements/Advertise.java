package com.video.aashi.kmdk.Members.advertisements;

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

import com.video.aashi.kmdk.Cmplaints.addressed.AddPojo;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Members.advertisements.types.TypesPojo;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPojo;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPost;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Advertise {



    Context context;
    AdvertidePresenter advertidePresenter;
    MenuStrings menuStrings;
    PopupWindow videoPop;
    View mView;
     public  Advertise(Context context,AdvertidePresenter advertidePresenter)
     {
         this.context = context;
         this.advertidePresenter = advertidePresenter;

     }

     public void getImage()
     {
         menuStrings = new MenuStrings( context);
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
         TextView header = null,photo,galery;
         header = (TextView)mView.findViewById(R.id.headers);
         galery =(TextView)mView.findViewById(R.id.fromGallery);
         photo =(TextView)mView.findViewById(R.id.fromCamera);
         if (menuStrings.getSharedPreferences())
         {
             header.setText("Choose");
             photo.setText("Cemara");
             galery.setText("Gallery");
         }

         LinearLayout b_gallary = (LinearLayout)mView.findViewById(R.id.btnGallery);
         b_camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 advertidePresenter.requestPermissions(true);

                 // checkPermissionREAD_EXTERNAL_STORAGE(context);
             }
         });


         b_gallary.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 advertidePresenter.requestPermissions(false);

             }
         });

         dimBehind(videoPop);
     }

      void hidePop()
      {

          videoPop.dismiss();
      }

     void  getLists()
     {

         menuStrings = new MenuStrings(context);
         boolean change = menuStrings.getSharedPreferences();
         if (!menuStrings.isNetworkAvailable(context))
         {
             if (change)
             {
                 advertidePresenter.showToast("Check internet connection...!");

             }else
             {
                 advertidePresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
             }
         }
         else
         {
             advertidePresenter.showProgress();
             if (change)
             {
                 advertidePresenter.progressMessage("Please wait..!");
             }
             else
             {
                 advertidePresenter.progressMessage("காத்திருக்கவும்..!");
             }
            // advertidePresenter.showProgress();

             getObservable().subscribeWith(getaddTypes());
         }

     }


    public Observable<TypesPojo> getObservable() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .showADDTypes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<TypesPojo>  getaddTypes()
    {
        return new DisposableObserver<TypesPojo>() {
            @Override
            public void onNext(TypesPojo typesPojo) {

                if (typesPojo.getStatus().contains("true"))
                {
                    advertidePresenter.showTypes(typesPojo.getResponse());
                }
                advertidePresenter.hideProgress();

            }

            @Override
            public void onError(Throwable e) {
                advertidePresenter.hideProgress();
                if (menuStrings.getSharedPreferences())
                {
                    advertidePresenter.showToast("Please try again..!" );
                }
                else
                {
                    advertidePresenter.showToast("மீண்டும் முயற்சிக்கவும்");
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

  void  postAdd( MultipartBody.Part image, RequestBody fullName, RequestBody mobile, RequestBody type, RequestBody complaint,
                 RequestBody message, RequestBody plac, RequestBody place,RequestBody branch)
  {

      menuStrings = new MenuStrings(context);
      boolean change = menuStrings.getSharedPreferences();
      if (!menuStrings.isNetworkAvailable(context))
      {
          if (change)
          {
              advertidePresenter.showToast("Check internet connection...!");

          }else
          {
              advertidePresenter.showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
          }
      }
      else
      {
          advertidePresenter.showProgress();
          if (change)
          {
              advertidePresenter.progressMessage("Please wait..!");
          }
          else
          {
              advertidePresenter.progressMessage("காத்திருக்கவும்..!");
          }
          // advertidePresenter.showProgress();

          getObservables( image, fullName, mobile, type,  complaint, message,  plac, place,branch).subscribeWith(postAdds());
      }


  }


  public Observable<AdvertidePojo> getObservables(MultipartBody.Part image, RequestBody fullName, RequestBody mobile, RequestBody type, RequestBody complaint,
                                            RequestBody message, RequestBody plac, RequestBody place,RequestBody branch)
  {
      return NetworkClient.getRetrofit().create(MainInterface.class)
              .Adversisement( image, fullName, mobile, type,  complaint, message,  plac, place,branch)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread());
  }
  public DisposableObserver<AdvertidePojo> postAdds()
  {
      return new DisposableObserver<AdvertidePojo>() {
          @Override
          public void onNext(AdvertidePojo advertidePojo) {

              Log.i("TAG","AdvertiseResumt" + advertidePojo.getMessage());
              advertidePresenter.hideProgress();
              if (menuStrings.getSharedPreferences())
              {
                  advertidePresenter.showToast(advertidePojo.getMessage());
              }
              else
              {
                  advertidePresenter.showToast("விளம்பரம் உருவாக்கப்பட்டது");
              }
              advertidePresenter.clear();

          }

          @Override
          public void onError(Throwable e) {
              advertidePresenter.hideProgress();
              Log.i("TAG","AdvertiseResumt" + e.toString());
          }

          @Override
          public void onComplete() {

          }
      };
  }

}
