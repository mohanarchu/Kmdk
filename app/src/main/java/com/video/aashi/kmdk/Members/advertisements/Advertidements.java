package com.video.aashi.kmdk.Members.advertisements;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.video.aashi.kmdk.AlertDialogues;
import com.video.aashi.kmdk.Members.advertisements.types.TypeResponse;
import com.video.aashi.kmdk.Members.memsession.UserSession;
import com.video.aashi.kmdk.R;
import com.video.aashi.kmdk.gpstracker.GPSTracker;
import com.video.aashi.kmdk.menunames.MenuStrings;
import com.video.aashi.kmdk.view.ImageUtils;
import com.video.aashi.kmdk.view.ZomeImage;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class Advertidements extends Fragment implements  AdvertidePresenter{



    ProgressDialog progressDialog;
    MenuStrings menuStrings;
    @BindView(R.id.addHead)
    TextView addHead;
    @BindView(R.id.addDes)
    EditText adddescribe;
    @BindView(R.id.addPlace)
    EditText addPlace;
    @BindView(R.id.addReason)
    EditText addReason;
    @BindView(R.id.addverPhoto)
    CardView addPhoto;
    @BindView(R.id.addImage)
    ImageView addImage;
    @BindView(R.id.invisibleImage)
    ImageView invisibleImage;
    @BindView(R.id.submitAdd)
    CardView submit;
    @BindView(R.id.submitAddText)
    TextView submitText;

    File file;
    AlertDialog alertDialogues;
    Advertise advertise;
    public  static  int MY_PERMISION_CODE=1;
    private static  int MY_PERMISSIONS_REQUEST_CAMERA;
    boolean Permission_is_granted;
    String typeId;
    UserSession userSession;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    String lattiduew,langitude;
    Bitmap myfiles;
    ImageCompressionAsyncTask imageCompression;
    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advertidements, container, false);
        ButterKnife.bind(this,view);
        menuStrings= new MenuStrings(getActivity());
        advertise = new Advertise(getActivity(),Advertidements.this);
        userSession= new UserSession(getActivity());
        if (menuStrings.getSharedPreferences())
        {
            addHead.setHint("Heading");
            adddescribe.setHint("Description");
            addPlace.setHint("Place");
            addReason.setHint("Reason");
            submitText.setText("Submit");
        }
        else
        {
            addHead.setHint("தலைப்பு");
            adddescribe.setHint(getString(R.string.describe));
            addPlace.setHint(getString(R.string.place));
            addReason.setHint(getString(R.string.reason));
            submitText.setText(getString(R.string.submit));
        }
        GPSTracker gpsTracker = new GPSTracker(getActivity());

        if (gpsTracker.getIsGPSTrackingEnabled())
        {
            getUserLocation();
        }
        else
        {
            gpsTracker.showSettingsAlert();
        }
        addHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             advertise.getLists();
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  requestPermissions(true);
                if (file == null)
                {
                    advertise.getImage();
                }
                else
                {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    new ImageCompressionAsyncTasks().execute(file.getPath());

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserLocation();

                if (addHead.getText().toString().isEmpty() ||addPlace.getText().toString().isEmpty() ||
                        addReason.getText().toString().isEmpty() || adddescribe.getText().toString().isEmpty() )
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
                else if (file == null)
                {
                    if (menuStrings.getSharedPreferences())
                    {
                        showToast("Please add ad Advertisement image..!");
                    }
                    else
                    {
                        showToast("விளம்பர புகைப்படத்தை உள்ளிடுக ");
                    }
                }
                else if (langitude  == null)
                {
                    getUserLocation();
                }
                else
                {
                    MultipartBody.Part  body = null;
                    if (file != null)
                    {
                        RequestBody requestFile =
                                RequestBody.create(MediaType.parse("multipart/form-data"), file);

                        body  =
                                MultipartBody.Part.createFormData("AdvertisementImage",
                                        file.getName(), requestFile);
                    }
                    RequestBody adtype =
                            RequestBody.create(MediaType.parse("multipart/form-data"), typeId );
                    RequestBody addplace =
                            RequestBody.create(MediaType.parse("multipart/form-data"), addPlace.getText().toString());
                    RequestBody advertises =
                            RequestBody.create(MediaType.parse("multipart/form-data"), addReason.getText().toString());
                    RequestBody lat =
                            RequestBody.create(MediaType.parse("multipart/form-data"), lattiduew);
                    RequestBody lan =
                            RequestBody.create(MediaType.parse("multipart/form-data"), langitude);
                    RequestBody userid =
                            RequestBody.create(MediaType.parse("multipart/form-data"), userSession.getId());
                    RequestBody message =
                            RequestBody.create(MediaType.parse("multipart/form-data"), adddescribe.getText().toString());
                    RequestBody branch =
                            RequestBody.create(MediaType.parse("multipart/form-data"), adddescribe.getText().toString());
                    advertise.postAdd(body,adtype,advertises,addplace,lat,lan,userid,message,branch);


                }

            }
        });

        return view;
    }
    void showFile(Bitmap bitmap)
    {
        myfiles = bitmap;
    }

    public  class ImageCompressionAsyncTasks extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            if(strings.length == 0 || strings[0] == null)
                return null;
            return ImageUtils.compressImage(strings[0]);
        }

        @Override
        protected void onPostExecute(byte[] bytes) {

            Intent intent= new Intent(getActivity(),ZomeImage.class);
            intent.putExtra("file",bytes);
            startActivity( intent);
            super.onPostExecute(bytes);
        };
    }
public abstract static class ImageCompressionAsyncTask extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            if(strings.length == 0 || strings[0] == null)
                return null;
            return ImageUtils.compressImage(strings[0]);
        }

        protected abstract void onPostExecute(byte[] imageBytes) ;
}
    void getImageUris(Uri files,Bitmap bitmap)
    {

        file  = new File(getRealPathFromUri(getActivity(),files));
        Log.i("TAG","ImageName"+ file.getPath());

        String pathName;
    //    myfiles = bitmap;
        @SuppressLint("StaticFieldLeak") ImageCompressionAsyncTask imageCompression = new ImageCompressionAsyncTask() {
            @Override
            protected void onPostExecute(byte[] imageBytes) {
                // image here is compressed & ready to be sent to the server
                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                myfiles = bmp;
                addImage .setImageBitmap(bmp);
               // addImage
            }
        };
        imageCompression.execute(getRealPathFromUri(getActivity(),files));
       ;

    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
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
    public void showTypes(ArrayList<TypeResponse> typeResponses) {
        final String[] countryType = new String[typeResponses.size()];
        for (int i = 0; i < typeResponses.size(); i++) {
            countryType[i] = typeResponses  .get(i).getAdvertisementType();
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

                addHead.setText(typeResponses.get(position).getAdvertisementType());
                typeId = typeResponses.get(position).get_id();

                alertDialogues.dismiss();
            }
        });
        alertDialogues=b.create();
        Window window = alertDialogues.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        alertDialogues.show();

    }
    public void requestPermissions(boolean b) {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            if (b)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1234);  // Permission has already been granted
            }
            else
            {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1000);
            }


        }
    }


    @Override
    public void clear() {
        addReason.setText("");
        addImage.setVisibility(View.GONE);
        file= null;
        addPlace.setText("");
        invisibleImage.setVisibility(View.VISIBLE);
        addHead.setText("");
        addReason.setText("");
        adddescribe.setText("");
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
    }
    private void getUserLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        ACCESS_COARSE_LOCATION) )
                {
                    showAlert();

                }
                else
                    if(isFirstTimeAskingPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION))
                    {

                            firstTimeAskingPermission(getActivity() ,
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},
                                1);
                }
                    else {

                        Toast.makeText(getActivity(),"You won't be able to access the features of this App",Toast.LENGTH_LONG).show();
                        // progress.setVisibility(View.GONE);
                        //Permission disable by device policy or user denied permanently. Show proper error message
                    }


            }
            else Permission_is_granted = true;
        }
        else
        {

            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                lattiduew = String.valueOf(location.getLatitude());
                langitude = String.valueOf(location.getLongitude());
                    Log.i("Tag","Mylocation :"+ lattiduew+ langitude);
                }
            });


//            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener((Executor) this, new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//
//                    if (location != null) {
//
//                   //     mLastLocation = location;
//                       // source_lat = location.getLatitude();
//                       // source_long = location.getLongitude();
//
//                       // fetchStores("restaurant");
//
//                    }
//                    else {
//                      //  progress.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(),"Error in fetching the location",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }
    public static void firstTimeAskingPermission(Context context, String permission, boolean isFirstTime){
        SharedPreferences sharedPreference = context.getSharedPreferences("filename", MODE_PRIVATE);
        sharedPreference.edit().putBoolean(permission, isFirstTime).apply();
    }
    public static boolean isFirstTimeAskingPermission(Context context, String permission){
        return context.getSharedPreferences("filename", MODE_PRIVATE).getBoolean(permission, true);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        Log.i("On request permiss","executed");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case 1:

                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Permission_is_granted = true;
                    getUserLocation();
                }
                else
                {
                    showAlert();
                    Permission_is_granted = false;
                    Toast.makeText(getActivity(),"Please switch on GPS to access the features",Toast.LENGTH_LONG).show();
                  //  progress.setVisibility(View.GONE);

                }
                break;

        }
    }
    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings are OFF \nPlease Enable Location")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {


                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},
                                MY_PERMISION_CODE);


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            try {
                // When an Image is picked
                if (requestCode == 1000 && resultCode == RESULT_OK
                        && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity(). getContentResolver().query(selectedImage,filePathColumn,
                            null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    invisibleImage.setVisibility(View.GONE);
                  // addImage.see
                //    ImageView imageView = (ImageView) findViewById(R.id.imgView);
                    //addImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    Bitmap photo = (Bitmap) data.getExtras().get("data");

                    advertise.hidePop();
                    getImageUris(selectedImage,photo);
                  //  Uri selectedImage = getImageUri(getActivity(), photo);

                } else if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
                    //  imgPopupWindoww.dismiss();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    Uri selectedImage = getImageUri(getActivity(), photo);
                    getImageUris(selectedImage,photo);


                     // Toast.makeText(getActivity(), " " + selectedImage, Toast.LENGTH_SHORT).show();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    invisibleImage.setVisibility(View.GONE);
                    // addImage.see
                    //    ImageView imageView = (ImageView) findViewById(R.id.imgView);
                    advertise.hidePop();
                    showFile(photo);

                 //   addImage.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                                        //   selected_file.add(getPath(getActivity(), selectedImage));

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
    public Uri getImageUri(Context inContext, Bitmap inImage)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(
                inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
