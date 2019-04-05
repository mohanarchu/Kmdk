package com.video.aashi.kmdk.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mickael on 10/10/2016.
 */

public class CameraPickerManager extends PickerManager {

    public CameraPickerManager(Activity activity) {
        super(activity);
    }

    protected void sendToExternalApp() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ImageFile imageFile = new ImageFile();
        String imageFileName = "KMDK_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(System.currentTimeMillis()));
        try {
            imageFile.createImageFile(activity.getApplicationContext(), imageFileName, ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileName = imageFileName;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mProcessingPhotoUri = FileProvider.getUriForFile( activity.getApplicationContext(),
                    "com.kmdk.provider", imageFile.getFile() );
        } else {
            mProcessingPhotoUri = Uri.fromFile(imageFile.getFile());
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,  mProcessingPhotoUri);
        activity.startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }
}
