package com.video.aashi.kmdk.memberJoin;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.video.aashi.kmdk.Locations.branch.Branch;
import com.video.aashi.kmdk.Locations.branch.BranchList;
import com.video.aashi.kmdk.Locations.district.District;
import com.video.aashi.kmdk.Locations.district.DistrictList;
import com.video.aashi.kmdk.Locations.states.StateList;
import com.video.aashi.kmdk.Locations.StatePresenter;
import com.video.aashi.kmdk.Locations.states.States;
import com.video.aashi.kmdk.Locations.zone.ZoneList;
import com.video.aashi.kmdk.Locations.zone.Zones;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.MemberText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberRegister extends Fragment implements StatePresenter ,RegisterPresen{


    public MemberRegister() {
        // Required empty public constructor
    }
    Spinner spinnerDropDown;
    String[] cities = {
            "Mumbai",
            "Delhi",
            "Bangalore",
            "Hyderabad",
            "Ahmedabad",
            "Chennai",
            "Kolkata",
            "Pune",
            "Jabalpur"
    };
   // private HintSpinner<String> defaultHintSpinner;
    @BindView(R.id.checkState)
    CheckBox checkBox;
    @BindView(R.id.casteType)
    LinearLayout casteType;
    @BindView(R.id.memHead)
    TextView memHead;
    @BindView(R.id.memName)
    EditText memName;
    @BindView(R.id.memEducation)
    EditText memeducation;
    @BindView(R.id.memAddress)
    EditText memAddress;
    @BindView(R.id.memPlace)
    EditText memPlace;
    @BindView(R.id.memPhone)
    EditText memPhone;
    @BindView(R.id.subCaste)
    EditText subCaste;
    @BindView(R.id.chackText)
    TextView checkText;
    @BindView(R.id.submitMemText)
    TextView submitText;
    @BindView(R.id.jobText)

    TextView jobText ;
    @BindView(R.id.submitMember)
    CardView submit;
    MenuStrings menuStrings;
    States states;
    Zones zones ;
    ArrayList<MemberText> memberTexts;
    Branch branch;
    District district;
    AlertDialog alertDialog ,alertDialog1,alertDialog2;
    @BindView(R.id.regState)
    TextView regState;
    @BindView(R.id.regDistrict)
    TextView  districts;
    @BindView(R.id.regZone)
    TextView regZone;
    @BindView(R.id.regBranch)
    TextView regBranch;
    @BindView(R.id.errorImagae)
    ImageView errorImaage;
    @BindView(R.id.jobs)
    CheckBox jobs;
    String mykey = "0";
    RegisteerPresenter registeerPresenter;

    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences,sharedPreferences1;
    UserSession userSession;
    String stateId,disId,zoneId,branchid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_member_register, container, false);
        ButterKnife.bind(this,view);
        chechMenus();
        states = new States(getActivity(), MemberRegister.this);
        district = new District(getActivity(),MemberRegister.this);
        zones = new Zones(getActivity(),MemberRegister.this);
        branch = new Branch(getActivity(),MemberRegister.this);
        userSession = new UserSession(getActivity());
        registeerPresenter = new RegisteerPresenter(getActivity(),MemberRegister.this,
                MemberRegister.this);
        sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
        Log.i("TAG","Sessions"+ userSession.getId());

        if (!userSession.getId().isEmpty())
        {
            updateList();
            Log.i("TAG","SessionIds"+ userSession.getId());
        }
        else
        {

            regState.setEnabled(true);
            districts.setEnabled(true);
            regState.setClickable(true);

            regZone.setClickable(false);
            regBranch.setClickable(false);
            regState.setClickable(true);
            regState.setText("");
            regZone.setText("");
            districts.setText("");
            regBranch.setText("");
            districts.setClickable(false);
            regZone.setClickable(false);
            regBranch.setClickable(false);
        }
        errorImaage.setVisibility(View.GONE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mykey = bundle.getString("key");

        }
        if (mykey .contains("1"))
        {
                  updateList();
        }
        memPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 10)
                {
                    registeerPresenter.getMobile(s.toString());
                }
            }
        });
        progressDialog = new ProgressDialog(getActivity()) ;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String State;
                String District;
                String Zone ;
                String Branch ;
                 if (memeducation.getText().toString().isEmpty() || memName.getText().toString().isEmpty() ||
                        memAddress.getText().toString().isEmpty() || memPhone.getText().toString().isEmpty()
                        || memPhone.getText().toString().isEmpty())
                {
                    boolean cnage = menuStrings.getSharedPreferences();
                    if (cnage)
                    {
                        showMessage("Plase check all details..!");
                    }
                    else
                    {
                        showMessage("எல்லா விவரங்களையும் சரிபார்க்கவும்..!");
                    }
                }

                else if (memPhone.getText().toString() .length() !=  10)
                 {
                     boolean cnage = menuStrings.getSharedPreferences();
                     if (cnage)
                     {
                         showMessage("Enter valid mobile number..!");
                     }
                     else
                     {
                         showMessage("சரியான கைபேசி எண்ணை உள்ளிடவும்..!");
                     }
                 }
                 else
                {
                    String	Name = memName.getText().toString();
                    String Education = memeducation.getText().toString();
                    String Address = memAddress.getText().toString();
                    String City = memPlace.getText().toString();
                    String MobileNumber = memPhone.getText().toString();
                    String valunterr;
                    String committe,caste;
                    String User_Id = "";
                    if (userSession.getId() == null)
                    {
                        User_Id = "";
                    }
                    else
                    {
                        User_Id = userSession.getId()   ;
                    }

                    if (regState.getText().toString().isEmpty() )
                    {
                        State = "";
                        Log.i("tag","StateId"+ "error");
                    }
                    else
                    {
                        sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                        stateId = sharedPreferences.getString("state1","");
                        Log.i("tag","StateId"+ stateId);
                        State = stateId;
                    }
                    if (!districts.getText().toString().isEmpty())
                    {
                        sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                        disId = sharedPreferences.getString("state2","");
                        District = disId;
                     }
                     else
                    {
                        District ="";
                    }
                    if (!regZone.getText().toString().isEmpty())
                    {
                        sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                        zoneId = sharedPreferences.getString("state3","");
                        Zone = zoneId;
                    }
                    else
                    {
                        Zone ="";
                    }
                    if (!regBranch.getText().toString().isEmpty())

                    {
                        sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                        branchid = sharedPreferences.getString("state4","");
                        Branch = branchid;
                    }
                    else
                    {
                        Branch ="";
                    }
                    if (checkBox.isChecked())
                    {
                        committe = "true";
                        caste = subCaste.getText().toString();
                    }
                    else
                    {
                        committe = "false";
                        caste =  "";
                    }

                    if (jobs.isChecked())
                    {
                        valunterr = "true";
                    }
                    else
                    {
                        valunterr = "false";

                    }
                    registeerPresenter.Register(Name,Education,Address,City,MobileNumber,State,
                            District,Zone,Branch,User_Id,valunterr,committe,caste);
                }
            }
        });




        regState.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {



                 if (userSession.getId().isEmpty())
                 {
                     states.getStateList();
                 }
                 else
                 {
                     if (userSession.isStateAuthority()) {

                         if (menuStrings.getSharedPreferences())
                         {
                             showMessage("You can't change this field..!");
                         }
                         else
                         {
                             showMessage("உங்களுக்கு மற்ற அனுமதி இல்லை..!");

                         }
                     }

                 }


            }
          });
          districts.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                  stateId =sharedPreferences.getString("state1","");
                  Log.i("tag","StateId"+ stateId);
                  district.getDistricts(stateId);
              }
          });
          regZone.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                  disId = sharedPreferences.getString("state2","");
                  zones.getZoneList(disId);
              }
          });
          regBranch.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  sharedPreferences  = getActivity().getSharedPreferences("states",Context.MODE_PRIVATE);
                  zoneId = sharedPreferences.getString("state3","");
                  branch.getBranch(zoneId);
               }
          });
        spinnerDropDown =(Spinner)view. findViewById(R.id.spinner1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    casteType.setVisibility(View.VISIBLE);
                }
                else
                {
                    casteType.setVisibility(View.GONE);
                }
            }
        });
        spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return  view;
    }
    public void chechMenus()
    {
        menuStrings = new MenuStrings(getActivity());
        menuStrings.getSharedPreferences();
        memberTexts = new ArrayList<>();
        memberTexts = menuStrings.getMemberTexts();
        for (int i=0;i<memberTexts.size();i++)
        {
            memHead.setText(memberTexts.get(i).getHeader());
            memName.setHint(memberTexts.get(i).getName());
            memAddress.setHint(memberTexts.get(i).getAddress());
            memeducation.setHint(memberTexts.get(i).getEducation());
            memPlace.setHint(memberTexts.get(i).getPlace());
            memPhone.setHint(memberTexts.get(i).getMobNumber());
            subCaste.setHint(memberTexts.get(i).getCaste());
            checkText.setText(memberTexts.get(i).getCommity());
            submitText.setText(memberTexts.get(i).getSubmit());
            jobText.setText(memberTexts.get(i).getJob());
            regState.setHint(memberTexts.get(i).getState());
            districts.setHint(memberTexts.get(i).getDistrict());
            regZone.setHint(memberTexts.get(i).getZone());
            regBranch.setHint(memberTexts.get(i).getBranch());
        }

    }
    public  boolean validate()
    {
        if (memeducation.getText().toString().isEmpty() && memName.getText().toString().isEmpty() &&
                memAddress.getText().toString().isEmpty() && memPhone.getText().toString().isEmpty()
                && memPhone.getText().toString().isEmpty()
               && regState.getText().toString().isEmpty() && districts.getText().toString().isEmpty()
               && regBranch.getText().toString().isEmpty() && regZone.getText().toString().isEmpty())
        {

            return false;

        }
      return  true;
    }
    @Override
    public void getStateList(final ArrayList<StateList> stateLists) {


        final String[] countryType = new String[stateLists.size()];
        for (int i = 0; i < stateLists.size(); i++) {
            countryType[i] = stateLists.get(i).getName();
          //  Log.i("Tag","MyStates"+ districtLists.get(i).getName()  );

        }
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.countrylist, null);
        final ListView county;
        county=dialogView.findViewById(R.id.country_list);
        county.setVisibility(View.VISIBLE);
        b.setView(dialogView);
        final TextView country;
        country = (TextView)dialogView.findViewById(R.id.alertHead);
        boolean change = menuStrings.getSharedPreferences();
        if (change)
        {
            country.setText("Select State ");
        }
        else
        {
            country.setText("மாவட்டத்தை தேர்ந்தெடுக்கவும்");
        }

        final ArrayAdapter<String> adapters =
                new ArrayAdapter<String> (getActivity()  ,  R.layout.listview, countryType);
        adapters.notifyDataSetChanged();
        county.setAdapter(adapters);
        county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
           //     SharedPreferences sharedPreferences = getActivity().getSharedPreferences("state1",Context.MODE_PRIVATE);

                editor = sharedPreferences.edit();
                editor.putString("state1",stateLists.get(position).getId());
                editor.apply();
              //  districts.setEnabled(true);
                districts.setClickable(true);
                districts.setText("");
                regBranch.setText("");
                regZone.setText("");
                alertDialog.dismiss();
                regState.setText(stateLists.get(position).getName());
            }
        });
        alertDialog=b.create();
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }
    @Override
    public void getDistrict(final ArrayList<DistrictList> districtLists) {
        final String[] countryType = new String[districtLists.size()];
        for (int i = 0; i < districtLists.size(); i++) {
            countryType[i] = districtLists  .get(i).getName();
            //  Log.i("Tag","MyStates"+ districtLists.get(i).getName()  );
        }
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.countrylist, null);
        final ListView county;
        county=dialogView.findViewById(R.id.country_list);
        county.setVisibility(View.VISIBLE);
        b.setView(dialogView);
        final TextView country;
        country = (TextView)dialogView.findViewById(R.id.alertHead);
        boolean change = menuStrings.getSharedPreferences();
        if (change)
        {
            country.setText("Select District ");
        }
        else
        {
            country.setText("மாநிலத்தை தேர்ந்தெடுக்கவும்");
        }
        final ArrayAdapter<String> adapters = new ArrayAdapter<String>
                (getActivity(),
                        R.layout.listview, countryType);
        adapters.notifyDataSetChanged();
        county.setAdapter(adapters);
        county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  SharedPreferences.Editor editor;
              //  editor = sharedPreferences.edit();
                editor.putString("state2",districtLists.get(position).getId());
                editor.apply();
                regZone.setText("");
                regBranch.setText("");
                alertDialog1.dismiss();
                regZone.setClickable(true);
                districts.setText(districtLists.get(position).getName());
            }
        });
        alertDialog1=b.create();
        Window window = alertDialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
       alertDialog1.show();
    }

    @Override
    public void getZOnes(final ArrayList<ZoneList> zoneLists) {
        final String[] countryType = new String[zoneLists.size()];
        for (int i = 0; i < zoneLists.size(); i++) {
            countryType[i] = zoneLists  .get(i).getZoneName();
            //  Log.i("Tag","MyStates"+ districtLists.get(i).getName()  );
        }
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.countrylist, null);
        final ListView county;
        county=dialogView.findViewById(R.id.country_list);
        county.setVisibility(View.VISIBLE);
        b.setView(dialogView);
        final TextView country;
        country = (TextView)dialogView.findViewById(R.id.alertHead);
        boolean change = menuStrings.getSharedPreferences();
        if (change)
        {
            country.setText("Select Zone ");
        }
        else
        {
            country.setText("ஒன்றியத்தை தேர்ந்தெடுக்கவும்");
        }
        final ArrayAdapter<String> adapters = new ArrayAdapter<String>
                (getActivity(),
                        R.layout.listview, countryType);
        adapters.notifyDataSetChanged();
        county.setAdapter(adapters);
        county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   SharedPreferences.Editor editor;
               // editor = sharedPreferences.edit();
                editor.putString("state3",zoneLists.get(position).getZoneId());
                editor.apply();
                alertDialog2.dismiss();
            //    regBranch.setEnabled(true);
                regBranch.setClickable(true);
                regBranch.setText("");
                regZone.setText(zoneLists.get(position).getZoneName());
            }
        });
        alertDialog2=b.create();
        Window window = alertDialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog2.show();
    }

    @Override
    public void getBranches(final ArrayList<BranchList> branchLists) {
        final String[] countryType = new String[branchLists.size()];
        for (int i = 0; i < branchLists.size(); i++) {
            countryType[i] = branchLists  .get(i).getName();
            //  Log.i("Tag","MyStates"+ districtLists.get(i).getName()  );
        }
        final AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.countrylist, null);
        final ListView county;
        county=dialogView.findViewById(R.id.country_list);
        county.setVisibility(View.VISIBLE);
        b.setView(dialogView);
        final TextView country;
        country = (TextView)dialogView.findViewById(R.id.alertHead);
        boolean change = menuStrings.getSharedPreferences();
        if (change)
        {
            country.setText("Select Zone ");
        }
        else
        {
            country.setText("ஒன்றியத்தை தேர்ந்தெடுக்கவும்");
        }
        final ArrayAdapter<String> adapters = new ArrayAdapter<String>
                (getActivity(),
                        R.layout.listview, countryType);
        adapters.notifyDataSetChanged();
        county.setAdapter(adapters);
        county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // SharedPreferences.Editor editor;
               // editor = sharedPreferences.edit();
                editor.putString("state4",branchLists.get(position).getId());
                editor.apply();
                alertDialog2.dismiss();
                regBranch.setText(branchLists.get(position).getName());

            }
         }
        );
        alertDialog2=b.create();
        Window window = alertDialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog2.show();
    }

    @Override
    public void showProgressView() {
        progressDialog.setCancelable(false);

        progressDialog.show();
    }
    @Override
    public void hideProgress()
    {
     progressDialog.dismiss();
    }
    @Override
    public void showProgressMessage(String string) {
        progressDialog.setMessage(string);
    }
    @Override
    public void showMessage(String string) {
        Toast.makeText(getActivity(),string,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showImage(int drawable) {
        errorImaage.setVisibility(View.VISIBLE);
        errorImaage.setImageResource(drawable);
    }
    @Override
    public void showErrorImage(int drawable) {
        errorImaage.setVisibility(View.VISIBLE  );
       errorImaage.setImageResource(drawable);
    }
    @Override
    public void clear() {
                memName.setText("");
                memeducation.setText("");
                memName.setText("");
                memAddress.setText("");
                memPhone.setText("");
                regState.setText("");
                regBranch.setText("");
                regZone.setText("");
                districts.setText("");
                checkText.setText("");
                checkBox.setChecked(false);
                jobs.setChecked(false);
    }
    @Override
    public void updateList() {
        if (userSession.isStateAuthority()) {

            if (userSession.isIfOfficial() && userSession.isCanAdd()) {

                Log.i("TAG", "Authorities" + userSession.getStateId() + userSession.getDistrictId() +
                        userSession.isZoneAuthority() + userSession.isBranchAuthority());
                sharedPreferences = getActivity().getSharedPreferences("states", Context.MODE_PRIVATE);
                regState.setText(userSession.getState());
                editor = sharedPreferences.edit();
                editor.putString("state1", userSession.getStateId());

                editor.apply();
                regZone.setText("");
                districts.setText("");
                regBranch.setText("");
                regState.setEnabled(false);
                districts.setEnabled(true);

            }
        } else if (!userSession.isStateAuthority()) {
            if (userSession.isDistriictAuthority()) {
                districts.setText(userSession.getDistrict());
                regState.setText(userSession.getDistrict());
                editor = sharedPreferences.edit();
                editor.putString("state2", userSession.getDistrictId());
                editor.apply();
                regZone.setText("");
                regBranch.setText("");
                districts.setEnabled(false);
                regState.setEnabled(false);

            }

        } else if (!userSession.isStateAuthority() && !userSession.isDistriictAuthority()) {
            if (userSession.isZoneAuthority()) {
                districts.setText(userSession.getDistrict());
                regZone.setText(userSession.getZone());
                regState.setText(userSession.getState());
                editor = sharedPreferences.edit();
                editor.putString("state3", userSession.getZoneId());
                editor.apply();
                districts.setEnabled(false);
                regState.setEnabled(false);
                regZone.setEnabled(false);
                regBranch.setText("");


            }
        }else if (!userSession.isStateAuthority() && !userSession.isDistriictAuthority() && !userSession.isZoneAuthority()) {
            if
            (userSession.isBranchAuthority()) {
            districts.setText(userSession.getDistrict());
            regZone.setText(userSession.getZone());
            regState.setText(userSession.getState());
            editor = sharedPreferences.edit();
            editor.putString("state4", userSession.getBranchId());
            editor.apply();
            showMessage(userSession.getStateId());
            districts.setEnabled(false);
            regState.setEnabled(false);
            regZone.setEnabled(false);
            regBranch.setEnabled(false);
            regBranch.setText(userSession.getBranch());


         }
      }
    }

}
