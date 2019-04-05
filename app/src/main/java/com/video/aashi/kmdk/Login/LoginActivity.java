package com.video.aashi.kmdk.Login;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.video.aashi.kmdk.Login.classfiles.Login;
import com.video.aashi.kmdk.Login.classfiles.LoginPresenter;
import com.video.aashi.kmdk.Login.classfiles.LoginView;
import com.video.aashi.kmdk.Login.mobile.MobileNoDialog;
import com.video.aashi.kmdk.Login.mobile.ValidationPresenter;
import com.video.aashi.kmdk.Login.otp.classfiles.OtpVerification;
import com.video.aashi.kmdk.MainActivity;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.LoginStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginPresenter,
        MobileNoDialog.ActionCallback{

    @BindView(R.id.submitLogin)
    CardView submit;
    @BindView(R.id.logMobile)
    EditText loginMobile;
    @BindView(R.id.logSubmitText)
    TextView logSubText;
    MenuStrings menuStrings;
    ArrayList<LoginStrings> loginStrings;
    ProgressDialog progressDialog;
    LoginView login;
    ValidationPresenter validationPresenter;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getStrings();
        submit.setOnClickListener(this);
        login = new Login(LoginActivity.this,getApplicationContext());
        validationPresenter = new ValidationPresenter( getApplicationContext(),LoginActivity .this);
        progressDialog = new ProgressDialog(LoginActivity.this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submitLogin:



                submit.setOnClickListener(v1 -> {
                   if (loginMobile.getText().toString().isEmpty() ||loginMobile.getText().toString().length() != 10 )
                    {
                        if (menuStrings.getSharedPreferences())
                        {
                            showToast("Enter valid mobile number..!");
                        }
                        else
                        {
                            showToast("சரியான கைபேசி எண்ணை உள்ளிடவும்..!");
                        }

                    }

                    else
                   {
                       conFormMobile(loginMobile.getText().toString());;
                   }

                });

                break;
                default:
                    break;

        }

    }

    public void getStrings()
    {
        menuStrings = new MenuStrings(LoginActivity.this);
        loginStrings = new ArrayList<>();
        menuStrings.getSharedPreferences();
        loginStrings = menuStrings.getLoginStrings();
        for(int i=0;i<loginStrings.size();i++)
        {
            loginMobile.setHint(loginStrings.get(i).getMobNumber());
            logSubText.setText(loginStrings.get(i).getSubmit());
        }

    }

    @Override
    public void showProgress()
    {
        progressDialog.show();
    }
    @Override
    public void hideProgress() {
       progressDialog.dismiss();
    }

    @Override
    public void setProgressMessage(String message) {
        progressDialog.setMessage(message);
    }
    @Override
    public void showToast(String Toast_string) {
        Toast.makeText(getApplicationContext(),Toast_string,Toast.LENGTH_SHORT).show();

    }
    @Override
    public void verifyOtp(String otp,String number) {
        Intent intent = new Intent(getApplicationContext(),OtpVerification.class);
        intent.putExtra("OTP",otp);
        intent.putExtra("mobileNumber",number);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    @Override
    public void otpScreen()
    {
    }
    @Override
    public void conFormMobile(String string) {
        if (login. validate(string)) {
            MobileNoDialog mobileNoDialog = new MobileNoDialog();
            mobileNoDialog.setCallback(this);
            mobileNoDialog.setCancelable(true);
            Bundle args = new Bundle();
            args.putString(MobileNoDialog.EXTRA_NUMBER, string);
            mobileNoDialog.setArguments(args);
            mobileNoDialog.show(getSupportFragmentManager() , "mobileNoDialog");

        }
        else
        {

        }
    }

    @Override
    public void canVlidate(boolean b,String number) {
        if (b)
        {

        }
        else
        {
            login.doLogin(number);
        }
    }

    @Override
    public void shoPop() {
            String positive ="",negative ="",message="";
            if (menuStrings.getSharedPreferences())
            {
                positive = "yes";
                negative = "No";
                message =  "Do you want to register?";
            }
            else
            {
                message ="நீங்கள் உறுப்பினராக பதிவு செய்ய விரும்புகிறீர்களா?";
                positive  = "ஆம்";
                negative ="இல்லை";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage(message);
            builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);

                    i.putExtra("key","2");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    @Override
    public void onOk(String number) {
       // validationPresenter.getMobile(number);
        login.doLogin(number);
    }
}
