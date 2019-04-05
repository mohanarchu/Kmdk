package com.video.aashi.kmdk.menunames;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.homepage.Homepage;
import com.video.aashi.kmdk.menunames.arrays.ActivityText;
import com.video.aashi.kmdk.menunames.arrays.ComplaintText;
import com.video.aashi.kmdk.menunames.arrays.Enquiry;
import com.video.aashi.kmdk.menunames.arrays.HomepageTexts;
import com.video.aashi.kmdk.menunames.arrays.LoginStrings;
import com.video.aashi.kmdk.menunames.arrays.MainTExt;
import com.video.aashi.kmdk.menunames.arrays.MemberText;
import com.video.aashi.kmdk.menunames.arrays.MembermainText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuStrings
{
    public SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context ;
    ArrayList<String> stringArray;
    StringBuilder stringBuilder;
    String[] strings;
    private   boolean change ;
    ArrayList<HomepageTexts> homepageTexts ;
    ArrayList<MainTExt>  mainTExts;
    ArrayList<MemberText> memberTexts;
    ArrayList<ComplaintText> complaintTexts;
    ArrayList<Enquiry> enquiries;
    ArrayList<LoginStrings> loginStrings;
    ArrayList<MembermainText> membermainTexts;
    ArrayList<ActivityText> activityTexts;
    ToastText toastText;
    public MenuStrings(Context context )
    {
        this.context = context;
    }
    public boolean getSharedPreferences() {
        sharedPreferences = context.getSharedPreferences("Menus",Context.MODE_PRIVATE);
        change = sharedPreferences.getBoolean("language",false);
        return change;
    }
    public void getStringArray() {

        homepageTexts = new ArrayList<>();
        if (change)
        {
            homepageTexts.clear();
            stringArray  = new ArrayList<>();
            stringBuilder =  new StringBuilder();
            stringArray.add("Add Member");
            stringArray.add("Register Complaints");
            homepageTexts.add(new HomepageTexts("Add Member","Register Complaints",
                    "Gallary","Events"));
            Log.i("TAG","Menus"+homepageTexts.get(0).getMember());
        }
        else
        {
            homepageTexts.clear();
            stringArray  = new ArrayList<>();
            stringBuilder =  new StringBuilder();
            homepageTexts.add(new HomepageTexts ("உறுப்பினராக சேர்வதற்கு",context.getString(R.string.complaint),
                    context.getString(R.string.gallery),context.getString(R.string.events)));
            Log.i("TAG","Menuss"+homepageTexts);
        }
    }
    public ArrayList<HomepageTexts> getAllstrings() {
        homepageTexts = new ArrayList<>();
        getStringArray();
        return  homepageTexts;
    }
    public ArrayList<MainTExt> getMainTExts() {
        mainTExts = new ArrayList<>();
        mainTExts.clear();
        if (change)
        {

            mainTExts.add(new MainTExt("Dashboard","Events","New Member",
                    "Gallery","Register Complaints","Complaints",
                    "Downloads","Contact Admin","Others","Settings","Login",
                    "Visit Website","Change Language"));


        }
        else
        {
            mainTExts.add(new MainTExt(context.getString(R.string.homepage),context.getString(R.string.events),context.getString(R.string.new_member),
                   context.getString(R.string.gallery),context.getString(R.string.complaint),context.getString(R.string.complaints),
                   context.getString(R.string.downloads),context.getString(R.string.contact_admin),context.getString(R.string.then),
                  "அமைப்புகள்","உள் நுழை",context.getString(R.string.website),"மொழியை மாற்ற"
                     )    );


        }
        return mainTExts;
    }
    public ArrayList<MemberText> getMemberTexts() {
        memberTexts = new ArrayList<>();
        if (change)
        {
            memberTexts.clear();
            memberTexts.add(new MemberText("Registration Form","Name","Education","Address",
                    "City","Mobile number","committee","Sub-Caste","State","District"
                       ,"Zone","Branch", "Are you intrested in job?"
            ,"Submit"));
        }
        else
        {
            memberTexts.add(new MemberText(context.getString(R.string.member_form),context.getString(R.string.name),context.getString(R.string.edu_qualification),
                context.getString(R.string.address),context.getString(R.string.place),context.getString(R.string.edt_hint),
                    "பேரவை", "குலம்","மாநிலம்" ,context.getString(R.string.district),
                    context.getString(R.string.union),"கிளை","கட்சி பணிகளில் ஆர்வம் இருக்கிறதா?", context.getString(R.string.submit)));
        }

        return memberTexts;
    }
    public ArrayList<ComplaintText> getComplaintTexts() {
        complaintTexts = new ArrayList<>();
        complaintTexts.clear();
        if (change)
        {
            complaintTexts.add(new ComplaintText("Complaint Form","Name","District","Zone","Branch"
                             ,"City","Mobile Number","Complaint Type","Complaint","Photo","Video"
                        ,"Voice","Description","Submit"  ));

        }
        else
        {
            complaintTexts.add(new ComplaintText(context.getString(R.string.complaint),context.getString(R.string.name),context.getString(R.string.district)
                      ,context.getString(R.string.union),context.getString(R.string.branch),context.getString(R.string.place),context.getString(R.string.mobile_no)
                   ,"புகார் வகை","புகார்", context.getString(R.string.image), "காணொளி",
                       "குரல்",    context.getString(R.string.describe),context.getString(R.string.submit)  ));

        }

        return complaintTexts;
    }
    public ArrayList<Enquiry> getEnquiries() {
        enquiries = new ArrayList<>();
        enquiries.clear();
        if (change)
        {
            enquiries.add(new Enquiry("Enquiry Form","Name","District","Place","Mobile Number",
                     "Description","Submit"  ));
        }
        else
        {
            enquiries.add(new Enquiry(context.getString(R.string.enquiry),context.getString(R.string.name),context.getString(R.string.district)
                             ,context.getString(R.string.place),context.getString(R.string.edt_hint),context.getString(R.string.describe)
                           ,context.getString(R.string.submit) ));

        }


        return enquiries;
    }
    public ArrayList<LoginStrings> getLoginStrings() {
        loginStrings = new ArrayList<>();
        loginStrings.clear();
        if (change)
        {
            loginStrings.add(new LoginStrings("Mobile Number","Submit"));
        }
        else
        {
            loginStrings.add(new LoginStrings(context.getString(R.string.edt_hint),context.getString(R.string.submit)));
        }

        return loginStrings;
    }
    public ArrayList<MembermainText> getMembermainTexts() {

        membermainTexts = new ArrayList<>();
        membermainTexts.clear();
        if (change)
        {
            membermainTexts.add(new MembermainText("Complaints","See all Complaints","New Member",
                    "Gallery","Events","Advertisements","Members",
                    "See all Members","Parties"
                      ,"Home","Complaints","Activities" ));
        }
        else
        {
            membermainTexts.add(new MembermainText("புகார்கள்" ,"புகார்களைப் பார்க்க","புதிய உறுப்பினர்கள்",
                    context.getString(R.string.gallery),context.getString(R.string.events),context.getString(R.string.advertisements),
                    context.getString(R.string.members),"","கட்சி தொடர்பு", context.getString(R.string.homepage),
                     context.getString(R.string.complaints),"செயல்பாடுகள்"  ));
        }


        return membermainTexts;
    }
    public ArrayList<ActivityText> getActivityTexts() {
        activityTexts = new ArrayList<>();
        activityTexts.clear();
        if (change)
        {
            activityTexts.add(new ActivityText("ACTIVITIES","Date","District","Union","Branch","Division", "Heading"
                                   ,"Description","Submit" ));
        }
        else
        {
            activityTexts.add(new ActivityText("செயல்பாடுகள்","தேதி", context.getString(R.string.district),context.getString(R.string.union),
                             context.getString(R.string.branch),"பிரிவு", "தலைப்பு",
                    context.getString(R.string.describe),context.getString(R.string.submit)  ));
        }

        return activityTexts;
    }

    public Toast getToastTamil(String string) {

        Toast toastText = Toast.makeText(context,string,Toast.LENGTH_SHORT);
        toastText.setText(string);
        toastText.show();
        return  toastText;
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context. getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
