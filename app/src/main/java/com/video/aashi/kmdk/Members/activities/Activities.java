package com.video.aashi.kmdk.Members.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.video.aashi.kmdk.Cmplaints.Complaints;
import com.video.aashi.kmdk.Cmplaints.comClassed.Complaint;
import com.video.aashi.kmdk.Members.activities.classes.ActtypeList;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.menunames.arrays.ActivityText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static com.video.aashi.kmdk.Cmplaints.Complaints.MY_PERMISSIONS_REQUEST_CAMERA;

public class Activities extends Fragment implements ActPresenter {

    @BindView(R.id.actName) EditText name;
    @BindView(R.id.actHeader)
    TextView header;
    @BindView(R.id.actDate)
    TextView  date;
    @BindView(R.id.actDistrict)
    EditText  district;
    @BindView(R.id.actUnuin)
    EditText  union;
    @BindView(R.id.actBranch)
    EditText  branch;
    @BindView(R.id.actDivision)
    EditText  division;
    @BindView(R.id.actHeading)
    TextView  heading;
    @BindView(R.id.actDescription)
    EditText  description;
    @BindView(R.id.actSubmitTExt)
    TextView submitTex;
    @BindView(R.id.actSubmit)
    CardView actSubmit;
    @BindView(R.id.comVideoss)
    CardView videos;
    @BindView(R.id.addPhotos)
    CardView images;
    @BindView(R.id.actPhotos)
    ImageView MyImages;
    @BindView(R.id.comMyVideos)
    ImageView Myvideos;
    @BindView(R.id.goneViewPhotos)
    LinearLayout gonePhoto;
    @BindView(R.id.goneLayouts)
    LinearLayout goneVideos;
    @BindView(R.id.ImageText)
    TextView imageText;
    @BindView(R.id.VideoText)
    TextView  videoText;
    MenuStrings menuStrings;
    File video,image,audio;
    ArrayList<ActivityText> activityTexts;
    UserSession userSession;
    Activity activity;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    public static final int GALLERY_PERMISSIONS_REQUEST = 4;
    private static final int PERMISSION_REQUEST_CODE = 200;
    AlertDialog alertDialog2;
    String dates = null;
    ProgressDialog progressDialog;
    DatePickerDialog datePickerDialog;
    String actId;
    boolean valis;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        ButterKnife.bind(this,view);

        union.setVisibility(View.GONE);
        activity = new Activity(getActivity(),Activities.this);
        userSession = new UserSession(getActivity());
        menuStrings = new MenuStrings(getActivity());
        activityTexts = new ArrayList<>();
        menuStrings.getSharedPreferences();
        activityTexts = menuStrings.getActivityTexts();
        if (menuStrings.getSharedPreferences())
        {
            name.setHint("Activity Name");
            imageText.setText("Image");
            videoText.setText("Video");
        }
        else
        {
            name.setHint("செயல்பாடுகளின் பெயர்");
        }
        for (int i=0;i<activityTexts.size();i++)
        {
            header.setText(activityTexts.get(i).getHeader());
            date.setHint(activityTexts.get(i).getDate());
            district.setHint(activityTexts.get(i).getDistrict());
            union.setHint(activityTexts.get(i).getZone());
            branch.setHint(activityTexts.get(i).getBranch());
            division.setHint(activityTexts.get(i).getDivision());
            heading.setHint(activityTexts.get(i).getHeading());
            description.setHint(activityTexts.get(i).getDescription());
            submitTex.setText(activityTexts.get(i).getSubmit());
        }
        date.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                    String dates =String.valueOf(monthOfYear) +"-"+ String.valueOf(dayOfMonth)
                            +"-"+String.valueOf(year) ;
                     date.setText(dates);
                }
            }, yy, mm, dd);
            datePicker.show();
        });
        heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getActList();
            }
        });
        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getPhoto();
            }
        });
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video!=null)
                {
                }
                else
                {
                    activity.getVideo();
                }

            }
        });
        actSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heading.getText().toString().isEmpty())
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        showToast("Please choose category..!");
                    }
                    else
                    {
                        showToast("வகை தேர்வு செய்யவும்");
                    }
                }
                else if (name.getText().toString().isEmpty() || date.getText().toString().isEmpty()
                        || district.getText().toString().isEmpty())
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
                else {

                    MultipartBody.Part body = null;
                    MultipartBody.Part bodys = null;
                    MultipartBody.Part bodyss = null;
                    if (image != null) {
                        RequestBody requestFile =
                                RequestBody.create(MediaType.parse("multipart/form-data"), image);
                        body =
                                MultipartBody.Part.createFormData("ActivityImage", image.getName(), requestFile);
                    } else {
                        RequestBody requestFile =
                                RequestBody.create(MediaType.parse("multipart/form-data"), "");
                        body =
                                MultipartBody.Part.createFormData("ActivityImage", "", requestFile);
                    }
                    if (video != null) {
                        RequestBody requestFiles =
                                RequestBody.create(MediaType.parse("multipart/form-data"), video);
                        bodys =
                                MultipartBody.Part.createFormData("ActivityVideo", video.getName(), requestFiles);
                    } else {
                        RequestBody requestFiles =
                                RequestBody.create(MediaType.parse("multipart/form-data"), "");
                        bodys =
                                MultipartBody.Part.createFormData("ActivityVideo", "", requestFiles);
                    }
                    if (audio != null) {
                        RequestBody requestFiles =
                                RequestBody.create(MediaType.parse("multipart/form-data"), video);
                        bodyss =
                                MultipartBody.Part.createFormData("ActivityAudio", video.getName(), requestFiles);
                    } else {
                        RequestBody requestFiles =
                                RequestBody.create(MediaType.parse("multipart/form-data"), "");
                        bodyss =
                                MultipartBody.Part.createFormData("ActivityAudio", "", requestFiles);
                    }
                    MediaType contentType;
                    String content;
                    RequestBody branchs =RequestBody.create(MediaType.parse("multipart/form-data"), userSession.getBranchId());
                    RequestBody ids = RequestBody.create(MediaType.parse("multipart/form-data"), userSession.getId());
                    RequestBody actname = RequestBody.create(MediaType.parse("multipart/form-data"), name.getText().toString());
                    RequestBody dat = RequestBody.create(MediaType.parse("multipart/form-data"), date.getText().toString());
                    RequestBody place = RequestBody.create(MediaType.parse("multipart/form-data"), district.getText().toString());
                    RequestBody complaint = RequestBody.create(MediaType.parse("multipart/form-data"), description.getText().toString());
                    RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), actId);
                    RequestBody lat = RequestBody.create(MediaType.parse("multipart/form-data"), "12345");
                    RequestBody lan = RequestBody.create(MediaType.parse("multipart/form-data"), "12345");
                    Log.d("TAG","Users"+userSession.getId() +image);
                    activity.postActtivity(body, bodys, bodyss, type, actname , dat, place,lat, lan, complaint, ids,branchs);
                }
            }
        });
        return view;
    }
    void convertdate()
    {
    }
    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri),
                                100);

                gonePhoto.setVisibility(View.GONE);
                MyImages.setVisibility(View.VISIBLE);
                activity.dismissPhoto();

                MyImages.setImageBitmap(bitmap);
                image  = new File(getRealPathFromUri(getActivity(),uri));
            } catch (IOException e) {
                Log.d("TAG", "Image picking failed because " + e.getMessage());
            }
        }
        else
       {
            Log.d("Tag", "Image picker gave us a null image.");
       }
    } private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;
        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }
    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(getActivity(), GALLERY_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE) && PermissionUtils.requestPermission(getActivity(),
                GALLERY_PERMISSIONS_REQUEST,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)  ) {
            @SuppressLint("IntentReset") Intent i =
                    new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, GALLERY_IMAGE_REQUEST);

        }
    }
    public void startCamera() {


            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);

    }
    @Override
    public void requestPhotoPermissions(boolean b) {
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
        } else
            if (b)
           {
            startCamera();
          }
          else
          {
            startGalleryChooser();
          }
    }

    @Override
    public void requestVideoermison(boolean b) {

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
                if (b) {
                    Intent captureVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);


                    startActivityForResult(captureVideoIntent, 1200);
                } else {
                    Intent intent =
                            new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    intent.setType("video/*");

                    startActivityForResult(Intent.createChooser(intent,"Select Video"), 1100);

                }
            }


        }


    @Override
    public void showProgress()
    {

        progressDialog= new ProgressDialog(getActivity());
        progressDialog.setCancelable(true);
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
    public void showToast(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showLists(ArrayList<ActtypeList> categories) {
        final String[] countryType = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            countryType[i] = categories  .get(i).getType();
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
            country.setText("Select Category ");
        }
        else
        {
            country.setText("வகையைத் தேர்ந்தெடுக்கவும்");
        }
        final ArrayAdapter<String> adapters = new ArrayAdapter<String>
                (getActivity(),
                        R.layout.listview, countryType);
        adapters.notifyDataSetChanged();
        county.setAdapter(adapters);
        county.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                              alertDialog2.dismiss();
                                              heading.setText(categories.get(position).getType());
                                              actId = categories.get(position).getId();


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
    public void complete() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }

    void getVideo(Uri uri)
    {
        Myvideos.setVisibility(View.VISIBLE);
        goneVideos.setVisibility(View.GONE);

        video = new File(Complaints. getPath(getActivity(),uri));

        Bitmap bitmap = null;
        try {
//                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            bitmap   = ThumbnailUtils.createVideoThumbnail(video.getPath(), MediaStore.Video.Thumbnails.MINI_KIND);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Myvideos.setImageBitmap(bitmap);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity(). getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            //  image = new File(picturePath);
            uploadImage(selectedImage);
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK &&  data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri selectedImage = getImageUri(getActivity(), photo);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity(). getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            image = new File(getRealPathFromUri(getActivity(),selectedImage));
            uploadImage(selectedImage);
        }
        else if (requestCode == 1200 && resultCode == RESULT_OK)
        {
            Uri videoFileUri;
            videoFileUri = data.getData();
            video = new File(getRealPathFromUri(getActivity(),videoFileUri));
            activity.dismisVideo();
            getVideo(videoFileUri);
        }
        else if (requestCode == 1100 && resultCode == RESULT_OK)
        {
            Uri selectedImageUri = data.getData();
                 getVideo(selectedImageUri);
            activity.dismisVideo();
        }


    }
    public String getAbsolutePath(Uri uri) {

        String[] projection = { MediaStore.MediaColumns.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
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
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    if (ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

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
                        } else if (ActivityCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(getActivity(), new String[]
                                            {Manifest.permission.RECORD_AUDIO},
                                    Complaints.MY_PERMISSIONS_REQUEST_CAMERA);

                        } else {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                                    READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                        }
                    }
                    else
                    {
                        startGalleryChooser();
                    }

                }

                break;
        }
    }

}
