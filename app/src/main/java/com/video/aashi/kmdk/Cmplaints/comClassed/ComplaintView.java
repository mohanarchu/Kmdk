package com.video.aashi.kmdk.Cmplaints.comClassed;

import android.content.Context;
import android.graphics.Bitmap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public interface ComplaintView {

    void getPhoto();
    void dismissPop();
    void  viewImage(Context context, Bitmap bitmap);
    void getVideo();
    void dismissvideoPop();
    void uploadComplaint(RequestBody id, RequestBody fullName,
                         MultipartBody.Part image, MultipartBody.Part video, MultipartBody.Part audio,
                         RequestBody mobile, RequestBody type, RequestBody complaint, RequestBody message,
                         RequestBody place,RequestBody places);

    void getLists();
}
