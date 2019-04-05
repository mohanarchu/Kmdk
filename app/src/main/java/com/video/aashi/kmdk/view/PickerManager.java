package com.video.aashi.kmdk.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import com.video.aashi.kmdk.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Mickael on 10/10/2016.
 */

public abstract class PickerManager {
    public static final int REQUEST_CODE_SELECT_IMAGE = 200;
    public static final int REQUEST_CODE_IMAGE_PERMISSION = 201;
    public Uri mProcessingPhotoUri;
    public String fileName;
    protected Activity activity;
    protected PickerBuilder.onImageReceivedListener imageReceivedListener;
    protected PickerBuilder.onPermissionRefusedListener permissionRefusedListener;
    private boolean withTimeStamp = true;
    private String folder = null;
    private String imageName;
    private UCrop uCrop;
    private int cropActivityColor;

    public PickerManager(Activity activity) {
        this.activity = activity;
        this.imageName = activity.getString(R.string.app_name);
        this.cropActivityColor =  ContextCompat.getColor(this.activity.getApplicationContext(), R.color.colorPrimary);
    }

    public PickerManager setOnImageReceivedListener(PickerBuilder.onImageReceivedListener listener) {
        this.imageReceivedListener = listener;
        return this;
    }

    public PickerManager setOnPermissionRefusedListener(PickerBuilder.onPermissionRefusedListener listener) {
        this.permissionRefusedListener = listener;
        return this;
    }

    public void pickPhotoWithPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_IMAGE_PERMISSION);
        } else
            sendToExternalApp();
    }

    public void handlePermissionResult(int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // permission was granted

            sendToExternalApp();

        } else {

            // permission denied
            if (permissionRefusedListener != null)
                permissionRefusedListener.onPermissionRefused();
            activity.finish();
        }
    }

    public String getImageName(){
        String finalPhotoName = imageName +
                (withTimeStamp ? "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(System.currentTimeMillis())) : "");
        return finalPhotoName;
    }


    protected abstract void sendToExternalApp();

    protected Uri getImageFile() {
        Context appContext = this.activity.getApplicationContext();

        File storageDir = appContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        String finalPhotoName = imageName +
                (withTimeStamp ? "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(System.currentTimeMillis())) : "");


        File imageFile = null;
        try {
            imageFile = File.createTempFile(
                    finalPhotoName,
                    ".jpg",
                    storageDir
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        // File file = new File(this.activity.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+finalPhotoName);
        // long currentTimeMillis = System.currentTimeMillis();
        // String photoName = imageName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(currentTimeMillis)) + ".jpg";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri());
        } else {
            Uri photoUri = FileProvider.getUriForFile(this.activity, "com.kmdk.provider", imageFile);
            return photoUri;
        }
        return Uri.fromFile(imageFile);
    }

    public void setUri(Uri uri) {

    }

    public void startCropActivity() {
        //if (uCrop == null) {
            //ImageFile imageFile = new ImageFile();
            String imageFileName = "KMDK_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(System.currentTimeMillis()));
//            try {
//                imageFile.createImageFile(activity.getApplicationContext(), imageFileName, ".jpg");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Uri finalUri = Uri.fromFile(new File(imageFileName));

            uCrop = UCrop.of(mProcessingPhotoUri, Uri.fromFile(new File(activity.getCacheDir(), imageFileName+".jpg")));
            uCrop = uCrop.useSourceImageAspectRatio();
            UCrop.Options options = new UCrop.Options();
            options.setFreeStyleCropEnabled(true);
            options.setToolbarColor( cropActivityColor);
            options.setStatusBarColor(cropActivityColor);
            options.setActiveWidgetColor(cropActivityColor);
            uCrop = uCrop.withOptions(options);
       // }

        uCrop.start(activity);
    }

    public void handleCropResult(Intent data) {
        Uri resultUri = UCrop.getOutput(data);
        if (imageReceivedListener != null)
            imageReceivedListener.onImageReceived(resultUri);

        activity.finish();
    }

    public PickerManager setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public PickerManager setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public PickerManager setCropActivityColor(int cropActivityColor) {
        this.cropActivityColor = cropActivityColor;
        return this;
    }

    public PickerManager withTimeStamp(boolean withTimeStamp) {
        this.withTimeStamp = withTimeStamp;
        return this;
    }

    public PickerManager setImageFolderName(String folder) {
        this.folder = folder;
        return this;
    }

    public PickerManager setCustomizedUcrop(UCrop customizedUcrop) {
        this.uCrop = customizedUcrop;
        return this;
    }
}
