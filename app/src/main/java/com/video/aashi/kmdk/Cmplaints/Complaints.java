package com.video.aashi.kmdk.Cmplaints;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.video.aashi.kmdk.Cmplaints.comClassed.BottomSheetFragment;
import com.video.aashi.kmdk.Cmplaints.comClassed.ComplaiPresenter;
import com.video.aashi.kmdk.Cmplaints.comClassed.Complaint;
import com.video.aashi.kmdk.Cmplaints.comClassed.ComplaintView;
import com.video.aashi.kmdk.Cmplaints.comClassed.RecorderActivity;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ListCategory;
import com.video.aashi.kmdk.Locations.StatePresenter;
import com.video.aashi.kmdk.Locations.branch.Branch;
import com.video.aashi.kmdk.Locations.branch.BranchList;
import com.video.aashi.kmdk.Locations.district.District;
import com.video.aashi.kmdk.Locations.district.DistrictList;
import com.video.aashi.kmdk.Locations.states.StateList;
import com.video.aashi.kmdk.Locations.states.States;
import com.video.aashi.kmdk.Locations.zone.ZoneList;
import com.video.aashi.kmdk.Locations.zone.Zones;
import com.video.aashi.kmdk.MainActivity;
import com.video.aashi.kmdk.Members.MembersMain;
import com.video.aashi.kmdk.Members.advertisements.Advertidements;
import com.video.aashi.kmdk.Members.home.MemberHome;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.Members.viewMem.MemberView;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.memberJoin.MemberRegister;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.ComplaintText;
import com.video.aashi.kmdk.view.CameraUtils;
import com.video.aashi.kmdk.view.PhotoChooserDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WAKE_LOCK;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.AUDIO_SERVICE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * A simple {@link Fragment} subclass.
 */
public class Complaints extends Fragment implements ComplaiPresenter,StatePresenter
{
      @BindView(R.id.headComplaint)
      TextView headComplaint;
      @BindView(R.id.complainterName)
      EditText comName;
      @BindView(R.id.comDistrict)
      TextView district;
    @BindView(R.id.comZone)
    TextView zone;
    @BindView(R.id.comBranch)
    TextView branch;
    @BindView(R.id.comPlace)
    EditText place;
    @BindView(R.id.comMobile)
    EditText  mobile;
    @BindView(R.id.comType)
    TextView comType;
    @BindView(R.id.complaintMain)
    EditText comMain;
    @BindView(R.id.comDescribe)
    EditText describe;
    @BindView(R.id.comImage)
    TextView comImage;
    @BindView(R.id.comVideo)
    TextView comVideo;
    @BindView(R.id.comVoice)
    TextView comVoice;
    @BindView(R.id.comSubmit)
    TextView comSubmbmit;
    MenuStrings menuStrings;
    ArrayList<ComplaintText>  complaintTexts;
    @BindView(R.id.comPhotos)
    ImageView comPhoto;
    @BindView(R.id.addPhoto)
    CardView addPhoto;
    @BindView(R.id.comState)
    TextView state;
    @BindView(R.id.goneViewPhoto)
    LinearLayout goneviewphoto;
    ComplaintView complaintView;
    PhotoChooserDialog dialog;
    @BindView(R.id.comMyVideo)
    ImageView videoView;
    @BindView(R.id.comlaintGone)
    LinearLayout complaintGone;
    @BindView(R.id.goneLayout)
    LinearLayout goneLayout;
    @BindView(R.id.VoiveRecord)
    CardView voiceRecord;
    @BindView(R.id.comVideos)
    CardView comVideos;
    @BindView(R.id.comSubmits)
    CardView comSubmit;
    View view;
    Bitmap photo;
    File file;
    File video;
    Zones zones ;
    Branch branchs;
    States statess;
    AlertDialog alertDialog;
    District districts;
     public static File audio;
     public static  String audioFle;
    RecorderActivity recorderActivity;
    ProgressDialog progressDialog;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";
    private static String imageStoragePath;
    public static int MY_PERMISSIONS_REQUEST_CAMERA;
    private static final int PERMISSION_REQUEST_CODE = 200;
    AlertDialog alertDialog2 = null;
    String stateid,districtd,branchid,zoneid;
    String comID;
    String validateTring ="2";
    UserSession userSession;
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
      {

       view  = inflater.inflate(R.layout.fragment_complaints, container, false);
       userSession = new UserSession(getActivity());
          districts = new District(getActivity(),this);
          zones = new Zones(getActivity(),this);
          branchs = new Branch(getActivity(),this);
          statess = new States(getActivity(),this);


          if (!CameraUtils.isDeviceSupportCamera(getActivity())) {
              Toast.makeText(getActivity(),
                      "Sorry! Your device doesn't support camera",
                      Toast.LENGTH_LONG).show();
              // will close the app if the device doesn't have camera
          }

          ButterKnife.bind(this,view);
          complaintGone.setVisibility(View.VISIBLE);
          if (!userSession.getId().isEmpty())
          {
             branchid = userSession.getBranchId();
              complaintGone.setVisibility(View.GONE);
              comName.setText(userSession.getName());
              mobile.setText(userSession.getMobilenumber());
              validateTring ="1";

          }
          getStrings();
          complaintView = new Complaint(Complaints.this,getActivity());
            dialog  = new PhotoChooserDialog();
            progressDialog = new ProgressDialog(getActivity());
          addPhoto.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (photo == null)
                  {
                      complaintView.getPhoto();
                  }
                  else
                  {
                      PopupWindow   viewImage = new PopupWindow(getActivity());
                      ImageView imageView;
                      LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                      View layout = layoutInflater.inflate(R.layout.test, null);
                      imageView =(ImageView)layout.findViewById(R.id.loadimage);
                      viewImage.setContentView(layout);
                      PhotoView photoView = new PhotoView(getActivity());
                      viewImage.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                      viewImage.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                      viewImage.setFocusable(true);
                      viewImage.setContentView(imageView);
                      viewImage.setOutsideTouchable(true);
                      viewImage.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                      PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
                      mAttacher.setZoomable(true);
                  }

              }
          });
          state.setClickable(false);
          zone.setClickable(false);
          branch.setClickable(false);
          district.setClickable(false);
          state.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  statess.getStateList();

              }
          });
          district.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  districts.getDistricts(stateid);
              }
          });
          zone.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  zones.getZoneList(districtd);
              }
          });
          branch.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  branchs.getBranch(zoneid);
              }
          });
          comType.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  complaintView.getLists();
              }
          });
          comVideos.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  complaintView.getVideo();

              }
          });

          voiceRecord.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
//                  BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
//                  bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
                  if (!checkPermission()) {
                      openActivity();
                  } else {
                      if (checkPermission()) {
                          requestPermissionAndContinue();
                      } else {
                          openActivity();
                      }
                  }

              }
          });
          comSubmit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 // Log.i("TAG","AudioFile"+ audio+ audioFle);


                 //     audio = new File(getRealPathFromUri(getActivity(),audio.getPath())
                  if    (comName.getText().toString().isEmpty() || place.getText().toString().isEmpty()
                          || mobile.getText().toString().isEmpty()
                          || comMain.getText().toString().isEmpty()
                          ||comType.getText().toString().isEmpty() )
                   {
                       boolean cnage = menuStrings.getSharedPreferences();
                      if (cnage)
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
                      MultipartBody.Part body = null;
                      MultipartBody.Part bodys = null;
                      MultipartBody.Part bodyss = null;
                      if (file != null)
                      {
                          RequestBody requestFile =
                                  RequestBody.create(MediaType.parse("multipart/form-data"), file);
                          body  =
                                  MultipartBody.Part.createFormData("ComplaintImage", file. getName(), requestFile);
                      }
                      else
                      {
                          RequestBody requestFile =
                                  RequestBody.create(MediaType.parse("multipart/form-data"), "");
                          body  =
                                  MultipartBody.Part.createFormData("ComplaintImage", "", requestFile);
                      }
                      if (video != null)
                      {
                          RequestBody requestFiles =
                                  RequestBody.create(MediaType.parse("multipart/form-data"), video);
                          bodys  =
                                  MultipartBody.Part.createFormData("ComplaintVideo", video.getName(), requestFiles);
                      }
                      else
                      {
                          RequestBody requestFiles =
                                  RequestBody.create(MediaType.parse("multipart/form-data"), "");
                          bodys  =
                                  MultipartBody.Part.createFormData("ComplaintVideo", "", requestFiles);
                      }

                      if (audioFle != null)
                      {

                          audio = new File(audioFle);
                          audio.getAbsoluteFile();
                          RequestBody requestFiless = RequestBody.create(MediaType.parse("multipart/form-data"), audio);

                          bodyss  = MultipartBody.Part.createFormData("ComplaintAudio", audio.getName(), requestFiless);
                      }
                      else
                      {
                          RequestBody requestFiless = RequestBody.create(MediaType.parse("multipart/form-data"), "");

                          bodyss  = MultipartBody.Part.createFormData("ComplaintAudio", "", requestFiless);
                      }

                      String id  = "";
                      if(!userSession.getId().isEmpty())
                      {
                          id = userSession.getId();
                      }
                      Log.i("Complaint","UserId"+id);
                      RequestBody  ids = RequestBody.create(MediaType.parse("multipart/form-data"),  id );
                      RequestBody fullName = RequestBody.create(MediaType.parse("multipart/form-data"), comName.getText().toString() );
                      RequestBody mobiles =RequestBody.create(MediaType.parse("multipart/form-data"), mobile.getText().toString());
                      RequestBody ftype = RequestBody.create(MediaType.parse("multipart/form-data"),  comID);
                      RequestBody complaint = RequestBody.create(MediaType.parse("multipart/form-data"),comMain.getText().toString() );
                      RequestBody message = RequestBody.create(MediaType.parse("multipart/form-data"), describe.getText().toString());
                      RequestBody  places = RequestBody.create(MediaType.parse("multipart/form-data"), place.getText().toString());
                      RequestBody  branch = RequestBody.create(MediaType.parse("multipart/form-data"), branchid);
                      complaintView.uploadComplaint(ids,fullName,body,bodys,bodyss,mobiles,ftype,complaint,message,places,branch);
                  }
              }
          });
        return  view;
    }

    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }
    void openActivity()
    {
        startActivity(new Intent(getActivity(),RecorderActivity.class));
    }
    public void getStrings() {
        menuStrings = new MenuStrings(getActivity());
        menuStrings.getSharedPreferences();
        complaintTexts = new ArrayList<>();
        complaintTexts = menuStrings.getComplaintTexts();
        if (menuStrings.getSharedPreferences())
        {
            state.setHint("State");
        }
        else
        {
            state.setHint("மாநிலம்");
        }
        for(int i = 0;i<complaintTexts.size();i++)
        {
            headComplaint .setText(complaintTexts.get(i).getHeader());
            district.setHint(complaintTexts.get(i).getDistrict());
            comName.setHint(complaintTexts.get(i).getName());
            zone.setHint(complaintTexts.get(i).getZone());
            branch .setHint(complaintTexts.get(i).getBranch());
            place.setHint(complaintTexts.get(i).getPlace());
            mobile.setHint(complaintTexts.get(i).getMobile());
            comType.setHint(complaintTexts.get(i).getComType());
            comMain.setHint(complaintTexts.get(i).getComplaint());
            describe.setHint(complaintTexts.get(i).getDiscription());
            comVideo .setText(complaintTexts.get(i).getVideo());
            comVoice .setText(complaintTexts.get(i).getAudio());
            comImage .setText(complaintTexts.get(i).getPhoto());
            comSubmbmit .setText(complaintTexts.get(i).getSubmit());
        }
    }
    void getImageUris(Uri files,Bitmap bitmap)
    {
        file  = new File(getRealPathFromUri(getActivity(),files));
        Log.i("TAG","ImageName"+ file.getPath());
        String pathName;
        //    myfiles = bitmap;
        @SuppressLint("StaticFieldLeak") Advertidements.ImageCompressionAsyncTask imageCompression =
                new Advertidements.ImageCompressionAsyncTask() {
            @Override
            protected void onPostExecute(byte[] imageBytes) {
                // image here is compressed & ready to be sent to the server
                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                comPhoto.setVisibility(View.VISIBLE);
                comPhoto .setImageBitmap(bmp);
                goneviewphoto.setVisibility(View.GONE);
                // addImage
            }
        };
        imageCompression.execute(getRealPathFromUri(getActivity(),files));
    }
    @Override
    public void comPhoto(Bitmap image) {
         //comPhoto = (ImageView)
         comPhoto.setVisibility(View.VISIBLE);
         goneviewphoto.setVisibility(View.GONE);
        //comPhoto.setTag(image);
        BitmapDrawable ob = new BitmapDrawable(getResources(), image);
        comPhoto.setBackgroundDrawable(ob);
      //  comPhoto.setImageResource(ob);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
        }
    }
    @Override
    public void requestPermissions(boolean b) {

            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(getActivity(),
                    WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        WRITE_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CAMERA);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                if (b)
                {
                    File fileUri;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, 1234);  // Permission has already been granted


                }
                else
                {

                    @SuppressLint("IntentReset") Intent i =
                            new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, 1000);

                }


            }
    }
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "Videos");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Videos", "Oops! Failed create "
                        + "Videos" + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
    @Override
    public void requestVideoermison(boolean b) {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        else
        {
            if (b)
            {
                Intent captureVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                // start the video capture Intent
                startActivityForResult(captureVideoIntent, 1200);
            }
            else
            {
                Intent intent =
                        new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);

                intent.setType("video/*");

                startActivityForResult(Intent.createChooser(intent,"Select Video"), 1100);
            }


        }



    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("path", imageStoragePath);
    }

    @Override
    public void getVideo( Uri bitmap) {
        videoView.setVisibility(View.VISIBLE);
        goneLayout.setVisibility(View.GONE);
//        videoView.setVideoURI(bitmap);
        Bitmap bitmaps = null;
        try {
//                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            bitmaps   = ThumbnailUtils.createVideoThumbnail(video.getPath(), MediaStore.Video.Thumbnails.MINI_KIND);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        videoView.setImageBitmap(bitmaps);
        video = new File(getPath(getActivity(), bitmap));
    }
    public String getPaths(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getActivity(). getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    @Override
    public void showProgress() {

          progressDialog.show();

    }

    @Override
    public void getStateList(ArrayList<StateList> stateLists) {

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


                //  districts.setEnabled(true);
                stateid = stateLists.get(position).getId();
                district.setClickable(true);
                district.setText("");
                branch.setText("");
                zone.setText("");
                alertDialog.dismiss();
                state.setText(stateLists.get(position).getName());
            }
        });
        alertDialog=b.create();
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    @Override
    public void getDistrict(ArrayList<DistrictList> districtLists) {
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
                districtd = districtLists.get(position).getId();
                zone.setText("");
                zone.setText("");
                alertDialog.dismiss();
                zone.setClickable(true);
                district.setText(districtLists.get(position).getName());
            }
        });
        alertDialog=b.create();
        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }

    @Override
    public void getZOnes(ArrayList<ZoneList> zoneLists) {
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

                alertDialog2.dismiss();
                //    regBranch.setEnabled(true);
                branch.setClickable(true);
                zoneid = zoneLists.get(position).getZoneId();
                branch.setText("");
                zone.setText(zoneLists.get(position).getZoneName());
            }
        });
        alertDialog2=b.create();
        Window window = alertDialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog2.show();
    }

    @Override
    public void getBranches(ArrayList<BranchList> branchLists) {
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
                                                branchid = branchLists.get(position).getId();
                                              alertDialog2.dismiss();
                                              branch.setText(branchLists.get(position).getName());

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

    }

    @Override
    public void hideProgress() {
          progressDialog.dismiss();

    }

    @Override
    public void showProgressMessage(String string) {

    }

    @Override
    public void showMessage(String string) {

    }

    @Override
    public void progressMessage(String message) {
          progressDialog.setMessage(message);

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLists(ArrayList<ListCategory> categories) {

        final String[] countryType = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            countryType[i] = categories  .get(i).getCategory();
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
            country.setText("Select Category ");
        }
        else
        {
            country.setText("தயவுசெய்து புகார் வகையைத் தேர்ந்தெடுக்கவும்");
        }

        final ArrayAdapter<String> adapters = new ArrayAdapter<String>
                (getActivity(),
                        R.layout.listview, countryType);
        adapters.notifyDataSetChanged();
        county.setAdapter(adapters);
        county.setOnItemClickListener((parent, view, position, id) -> {
            alertDialog2.dismiss();
            comType.setText(categories.get(position).getCategory());
            comID = categories.get(position).getId();
          }
        );
        alertDialog2=b.create();
        Window window = alertDialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialog2.show();
    }

    @Override
    public void clearTexts() {
        comType.setText("");
        comName.setText("");
        file =null;
        audio = null;
        video = null;
        mobile.setText("");
        describe.setText("");
        comMain.setText("");
        place.setText("");
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){

            try {
                // When an Image is picked
                if (requestCode == 1000 && resultCode ==  Activity.RESULT_OK) {

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity(). getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    // addImage.see
                    //    ImageView imageView = (ImageView) findViewById(R.id.imgView);
                    //addImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    complaintView.dismissPop();
                    getImageUris(selectedImage,photo);


                }
                else if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
                   //  imgPopupWindoww.dismiss();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    Uri selectedImage = getImageUri(getActivity(), photo);
                    // Toast.makeText(getActivity(), " " + selectedImage, Toast.LENGTH_SHORT).show();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
               //     comPhoto(photo);
                    getImageUris(selectedImage,photo);
                    file = new File(getRealPathFromUri(getActivity(),selectedImage));
                    complaintView.dismissPop();


                }
                else if (requestCode == 1200 && resultCode == Activity.RESULT_OK)
                {
                     Uri videoFileUri;
                     videoFileUri = data.getData();

                     video = new File(getRealPathFromUri(getActivity(),videoFileUri));
                    complaintView.dismissvideoPop();
                     getVideo(videoFileUri);
                }
                else if (requestCode == 1100 && resultCode == Activity.RESULT_OK)
                {

                    Uri selectedImageUri = data.getData();



                    complaintView.dismissvideoPop();
                    getVideo(selectedImageUri);
                }

                else

                {

                    Toast.makeText(getActivity(), "You haven't picked Image",
                            Toast.LENGTH_LONG).show();
                }



            }
            catch (Exception e) {

                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {
                    openActivity();
                }
                else
                {
                    showToast("Error..!");
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage)
    {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(
                inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);

    }
    public static String getPath(final Context context, final Uri uri)
    {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Accept permission and Continue..!");
                alertBuilder.setMessage(" If you want to record you want to accpt the permission");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            }
            else
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]
                                {Manifest.permission.RECORD_AUDIO},
                        Complaints.MY_PERMISSIONS_REQUEST_CAMERA);

            }


            else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openActivity();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                return false;
            }
        });
    }

}
