package com.video.aashi.kmdk.contact;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.Enquiry;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact extends Fragment  implements ContactPresenter,View.OnClickListener {


    public Contact() {
        // Required empty public constructor
    }
    @BindView(R.id.conHead)
    TextView conHead;
    @BindView(R.id.conName)
    EditText conName;
    @BindView(R.id.conDistrict)
    EditText conDistrict;
    @BindView(R.id.conPlace)
    EditText conPlace;
    @BindView(R.id.conNumber)
    EditText conNumber;
    @BindView(R.id.conDescribe)
    EditText conDescribe;
    @BindView(R.id.conSubmitText)
    TextView conSubmitText;
    @BindView(R.id.conSubmit)
    CardView contactSubmit;
    MenuStrings menuStrings;
    ArrayList<Enquiry> enquiries;
    ProgressDialog progressDialog;
    ContactView contactView;
    UserSession userSession;
 //   MenuStrings menuStrings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view =  inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this,view);
        getStrings();
        contactView = new Contacts(getActivity(),Contact.this);
        progressDialog = new ProgressDialog(getActivity());
        contactSubmit.setOnClickListener(this);
        userSession = new UserSession(getActivity());
        if (!userSession.getId().isEmpty())
        {
            conName.setText(userSession.getName());
            conNumber.setText(userSession.getMobilenumber());

        }




        return  view;
    }
    public  void getStrings()
    {
        menuStrings = new MenuStrings(getActivity());
        menuStrings.getSharedPreferences();
        enquiries = menuStrings.getEnquiries();
        for(int i =0;i<enquiries.size();i++)
        {
            conHead.setText(enquiries.get(i).getHedings() );
            conName.setHint(enquiries.get(i).getName());
            conDescribe.setHint(enquiries.get(i).getDescription());
            conDistrict.setHint(enquiries.get(i).getDistrict());
            conPlace.setHint(enquiries.get(i).getCity()  );
            conNumber.setHint(enquiries.get(i).getMobilenumber());
            conSubmitText.setText(enquiries.get(i).getSubmit());
        }
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

    @Override
    public void showToast(String toast) {
        Toast.makeText(getActivity(),toast,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearText() {

        conName.setText(""); ;
        conPlace.setText("");
                conNumber.setText("");;
                 conDescribe.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.conSubmit:  if (conName.getText().toString().isEmpty() || conPlace.getText().toString().isEmpty() ||
                    conNumber.getText().toString().isEmpty() || conDescribe.getText().toString().isEmpty()   )
            {
                if (menuStrings.getSharedPreferences())
                {
                    showToast("Plase check all details..!");
                }
                else

                {
                    showToast("எல்லா விவரங்களையும் சரிபார்க்கவும்..!");
                }
            }
            else
            {
                contactView.putEnquiry(new ContactPost("5c64101b88e43b08d4c4f8c2",conName.getText().toString(),
                        conPlace.getText().toString(), conDescribe.getText().toString(),conNumber.getText().toString() ));
            }

                break;
        }
    }
}
