package com.video.aashi.kmdk.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.video.aashi.kmdk.Cmplaints.Complaints;
import com.video.aashi.kmdk.Cmplaints.comClassed.ComplaiPresenter;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ListCategory;
import com.video.aashi.kmdk.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_CANCELED;

public class PhotoChooserDialog extends BottomSheetDialogFragment
        implements PickerBuilder.onImageReceivedListener,ComplaiPresenter {
    private static int MY_PERMISSIONS_REQUEST_CAMERA;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @BindView(R.id.btnCamera)
    LinearLayout btnCamera;
    @BindView(R.id.btnGallery)
    LinearLayout btnGallery;
    Unbinder unbinder;
    ComplaiPresenter complaiPresenter;
    private PickerBuilder pickerCamera, pickerGallery;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback =
            new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }
        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        complaiPresenter = new Complaints();
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    getContext(), Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }

        pickerCamera = new PickerBuilder(getActivity(), PickerBuilder.SELECT_FROM_CAMERA)
                .setOnImageReceivedListener(this)
                .setImageFolderName(getString(R.string.app_name))
                .setCropScreenColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .withTimeStamp(true)
                .setCropScreenColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        pickerGallery = new PickerBuilder(getActivity(), PickerBuilder.SELECT_FROM_GALLERY)
                .setOnImageReceivedListener(this)
                .setImageFolderName(getString(R.string.app_name))
                .setCropScreenColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .withTimeStamp(true)
                .setCropScreenColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.dialog_photo_chooser, null);
        dialog.setContentView(contentView);
        unbinder = ButterKnife.bind(this, contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }
    @Override
    public void onImageReceived(Uri imageUri) {
    }
    @OnClick({R.id.btnCamera, R.id.btnGallery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCamera:
                checkPermissionREAD_EXTERNAL_STORAGE(getContext());


                break;
            case R.id.btnGallery:
              //  pickerGallery.start();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED){
            if (requestCode == 1234 ) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Uri selectedImage = getImageUri(getActivity(), photo);
                // Toast.makeText(getActivity(), " " + selectedImage, Toast.LENGTH_SHORT).show();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query
                        (selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Log.i("TAG","ImageFile"+imgDecodableString);
                File file = new File(selectedImage.getPath());
                //comPhoto(file);
              //  complaiPresenter.comPhoto(file);



                //PhotoChooserDialog dialog = new PhotoChooserDialog();
                //dialog.dismiss();
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
    public void checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
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
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1234);  // Permission has already been granted
        }
    }
    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1234);

                } else {
                    Toast.makeText(getActivity(), "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    @Override
    public void comPhoto(Bitmap image) {

    }

    @Override
    public void requestPermissions(boolean b) {

    }

    @Override
    public void requestVideoermison(boolean b) {

    }

    @Override
    public void getVideo(Uri bitmap) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void progressMessage(String message) {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showLists(ArrayList<ListCategory> categories) {

    }

    @Override
    public void clearTexts() {

    }


    public interface showPhoto
    {
        void fileImage(File file);
    }
}
