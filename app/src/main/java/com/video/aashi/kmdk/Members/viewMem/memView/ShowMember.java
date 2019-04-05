package com.video.aashi.kmdk.Members.viewMem.memView;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.video.aashi.kmdk.Interface.MainInterface;
import com.video.aashi.kmdk.Interface.NetworkClient;
import com.video.aashi.kmdk.Login.otp.classfiles.OtpRespons;
import com.video.aashi.kmdk.Login.otp.classfiles.otpPojo;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.classes.MemList;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPojo;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPost;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.Urls.APIUrl;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.myview.ShowPic;
import com.video.aashi.kmdk.view.ViewIntract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMember extends AppCompatActivity implements ViewIntract {


    @BindView(R.id.memberAddressText)
    TextView addreesText;
    @BindView(R.id.memberDesignation)
    TextView designation;
    @BindView(R.id.memberName)
    TextView name;
    @BindView(R.id.memberCity)
    TextView city;
    @BindView(R.id.memBranchNo)
    TextView branchNo;
    @BindView(R.id.memberEducation)
    TextView education;
    @BindView(R.id.memberEducationText)
    TextView educationText;
    @BindView(R.id.memberStateText)
    TextView stateText;
    @BindView(R.id.memberAddress)
    TextView address;
    @BindView(R.id.memberState)
    TextView state;

    @BindView(R.id.memberZoneText)
    TextView zoneText;
    @BindView(R.id.memberZone)
    TextView zone;
    @BindView(R.id.memberDistrictText)
    TextView districtText;
    @BindView(R.id.memberDistrict)
    TextView district;
    @BindView(R.id.memberCommittieText)
    TextView committieText;
    @BindView(R.id.memberCommittie)
    TextView committe;
    @BindView(R.id.memberStatusText)
    TextView statusText;
    @BindView(R.id.memberStatus)
    TextView statsu;
    String id;
    @BindView(R.id.memberImage)
    ImageView memImage;

    @BindView(R.id.profileTool)
    Toolbar toolbar;

    MenuStrings menuStrings;
    ProgressDialog progressDialog;
    UserSession userSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_member);
        ButterKnife.bind(this);

        menuStrings = new MenuStrings(getApplicationContext());
        progressDialog = new ProgressDialog(ShowMember.this);
        id = getIntent().getStringExtra("id");
        Log.i("TAG","IDD"+ id);
        setSupportActionBar(toolbar);
        userSession = new UserSession(getApplicationContext());
        if (menuStrings.getSharedPreferences())
        {
            setTitle("Profile");
        }
        else
        {
            setTitle("சுயவிவரங்கள்");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getTexts();
        showAll();
    }

    public void getTexts() {
        if (menuStrings.getSharedPreferences()) {
            addreesText.setText("Address");
            educationText.setText("Education");
            stateText.setText("State");
            districtText.setText("District");
            zoneText.setText("Union");
            committieText.setText("Member ID");
            statusText.setText("Status");

        } else {
            addreesText.setText(getString(R.string.address));
            educationText.setText(getString(R.string.education));
            stateText.setText("மாநிலம்");
            districtText.setText(getString(R.string.district));
            zoneText.setText(getString(R.string.union));
            committieText.setText("உறுப்பினர் எண்");
            statusText.setText("நிலை");

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
    public void showAll() {
        boolean change = menuStrings.getSharedPreferences();
        if (!menuStrings.isNetworkAvailable(getApplicationContext())) {
            if (change) {
                showToast("Check internet connection...!");

            } else {
                showToast("இணைய இணைப்பை சரிபார்க்கவும்..!");
            }
        } else {
            showProgress();
            if (change) {
             progressMessage("Please wait..!");
            } else {
               progressMessage("காத்திருக்கவும்..!");
            }

            getObservable().subscribeWith(getMembers());
        }

    }
    public Observable<ViewPojo> getObservable() {
        return NetworkClient.getRetrofit().create(MainInterface.class)
                .showMember(new ShowPost(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

     private DisposableObserver<ViewPojo> getMembers()
     {
         return new DisposableObserver<ViewPojo>() {
             @Override
             public void onNext(ViewPojo otpPojo) {

                 if (otpPojo. getStatus().contains("true")) {
                     ViewResponse otpRespons = otpPojo.getResponse();
                     name.setText(otpPojo.getResponse().getName());
                     education.setText(otpPojo.getResponse().getEducation());
                     if (designation.getText().toString().equals(""))
                     {
                         designation.setVisibility(View.GONE);
                     }
                     else
                     {
                         if (otpRespons.getDesignation() != null)
                         {
                             designation.setText(otpRespons.getDesignation().getOfficialDesignation());
                         }
                     }
                     city.setText(otpRespons.getCity());
                     address.setText(otpRespons.getAddress());
                     if (otpRespons.getState() != null)
                     {
                         state.setText(otpRespons.getState().getStateName());
                     }
                     else
                     {
                         state.setText("-");
                     }
                     if (otpRespons.getBranch()   != null)
                     {
                         branchNo.setText(otpRespons.getBranch().getBranchName());
                     }
                     else
                     {
                         branchNo.setText("-");
                     }
                     if (otpRespons.getDistrict()  != null)
                     {

                         district.setText(otpRespons.getDistrict().getDistrictName());
                     }
                     else
                     {

                         district.setText("-");
                     }
                     if (otpRespons.getZone() != null)
                     {
                         zone.setText(otpRespons.getZone().getZoneName());
                     }

                     else
                     {
                         zone.setText("-");
                     }


                     committe.setText(otpPojo.getResponse().getMemberReferenceId());
                     statsu.setText(otpRespons.getStatus());

                     if (otpPojo.getResponse().getMemberImage().getFilename() != null)
                     {
                         Glide.with(getApplicationContext()).load(APIUrl.Api +"Uploads/Member/" +
                                 otpPojo.getResponse().getMemberImage().getFilename()).into(memImage);


                         memImage.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {

                             }
                         });

                     }





                      hideProgress();
                 }
                 else
                 {
                     if (menuStrings.getSharedPreferences())
                     {
                        showToast("Please try again..!" );
                     }
                     else
                     {
                        showToast("மீண்டும் முயற்சிக்கவும்");
                     }
                 }
                 hideProgress();
             }
             @Override
             public void onError(Throwable e) {
                 hideProgress();
                 Log.i("TAG","ErrorProfile"+e.toString());
                 if (menuStrings.getSharedPreferences())
                 {
                     showToast("Please try again..!" );
                 }
                 else
                 {
                   showToast("மீண்டும் முயற்சிக்கவும்");
                 }
             }
             @Override
             public void onComplete() {
             }
         };
     }
    @Override
    public void showProgress() {


        progressDialog.show();

    }

    @Override
    public void hideProgress() {
      progressDialog.dismiss();
    }

    @Override
    public void progressMessage(String message) {

        progressDialog.setMessage(message);

    }

    void showToast(String mesage)
    {
        Toast.makeText(getApplicationContext(),mesage,Toast.LENGTH_SHORT).show();
    }
}
