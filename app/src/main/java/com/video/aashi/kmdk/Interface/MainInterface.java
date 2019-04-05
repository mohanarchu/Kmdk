package com.video.aashi.kmdk.Interface;


import com.google.gson.JsonObject;
import com.video.aashi.kmdk.Cmplaints.addressed.AddPojo;
import com.video.aashi.kmdk.Cmplaints.compliatsList.ListPojo;
import com.video.aashi.kmdk.Locations.branch.BranchPOst;
import com.video.aashi.kmdk.Locations.branch.BranchPojo;
import com.video.aashi.kmdk.Locations.district.DistrictPojo;
import com.video.aashi.kmdk.Locations.district.DistrictPost;
import com.video.aashi.kmdk.Locations.states.StatePojo;
import com.video.aashi.kmdk.Locations.states.StatePost;
import com.video.aashi.kmdk.Locations.zone.ZonePojo;
import com.video.aashi.kmdk.Locations.zone.ZonePost;
import com.video.aashi.kmdk.Login.classfiles.LoginPojo;
import com.video.aashi.kmdk.Login.classfiles.LoginPost;
import com.video.aashi.kmdk.Login.otp.classfiles.otpPojo;
import com.video.aashi.kmdk.Login.otp.classfiles.otpPost;
import com.video.aashi.kmdk.Members.activities.activitylist.ActPojo;
import com.video.aashi.kmdk.Members.activities.classes.ActlistPojo;
import com.video.aashi.kmdk.Members.activities.classes.PostPojo;
import com.video.aashi.kmdk.Members.advertisements.AdvertidePojo;
import com.video.aashi.kmdk.Members.advertisements.list.AdListPojo;
import com.video.aashi.kmdk.Members.advertisements.types.TypesPojo;
import com.video.aashi.kmdk.Members.viewMem.approve.ApprovePojo;
import com.video.aashi.kmdk.Members.viewMem.approve.ApprovePost;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPojo;
import com.video.aashi.kmdk.Members.viewMem.classes.MemPost;
import com.video.aashi.kmdk.Members.viewMem.memView.ShowPost;
import com.video.aashi.kmdk.Members.viewMem.memView.ViewPojo;
import com.video.aashi.kmdk.contact.ContactPojo;
import com.video.aashi.kmdk.contact.ContactPost;
import com.video.aashi.kmdk.events.EventPojo;
import com.video.aashi.kmdk.memberJoin.classfiles.REgisterPojo;
import com.video.aashi.kmdk.memberJoin.classfiles.RegisterPost;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePojo;
import com.video.aashi.kmdk.memberJoin.mobile.MobilePost;
import com.video.aashi.kmdk.tabs.gallery.GallerPojo;
import com.video.aashi.kmdk.tabs.view.AlbumPojo;

import io.reactivex.Observable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MainInterface {
    @POST("appLogin")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    io.reactivex.Observable<ResponseBody> getLoginstatus(@Body StatePost statePost);
    @POST("Settings/AppStateList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<StatePojo> getStates(@Body StatePost statePost);
    @POST("Settings/AppDistrictList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<DistrictPojo> getDistrict(@Body DistrictPost statePost);
    @POST("Settings/AppZoneList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ZonePojo> getZones(@Body ZonePost statePost);
    @POST("Settings/AppBranchList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<BranchPojo> getBranch(@Body BranchPOst statePost);
    @POST("Members/AppMemberMobileNumberValidate")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<MobilePojo> getMobile(@Body MobilePost statePost);
    @POST("Members/AppMemberCreate")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<REgisterPojo> getRegister(@Body RegisterPost statePost);

    @POST("Members/AppMembersLoginValidate")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<LoginPojo> getLogin(@Body LoginPost loginpost);

    @POST("Members/AppMembersOTPValidate")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<otpPojo> getOtp(@Body otpPost loginpost);
    @POST("Members/AppMemberList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<MemPojo> getAllMembers(@Body MemPost loginpost);
    @POST("Events/AppEnquiryCreate")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ContactPojo> getContact(@Body ContactPost contactPost);
    @Multipart
    @POST("Complaint/AppComplaintCreate")
    Observable<ResponseBody> updateComplaint(@Part("User_Id") RequestBody id,
                                           @Part("Name") RequestBody fullName,
                                           @Part MultipartBody.Part image,
                                           @Part MultipartBody.Part video,
                                           @Part MultipartBody.Part audio,
                                           @Part("MobileNumber") RequestBody mobile,
                                           @Part("ComplaintType") RequestBody type,
                                           @Part("Complaint") RequestBody complaint,
                                           @Part("Message") RequestBody message,
                                           @Part("Place") RequestBody place,
                                           @Part("Branch") RequestBody branch);
    @POST("Complaint/AppComplaintList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<AddPojo> getComplaints();

    @POST("Members/AppMemberApprove")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ApprovePojo> memApprove(@Body JsonObject jsonObject);

    @POST("Members/AppMemberView")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ViewPojo> showMember(@Body ShowPost approvePost);
    @Multipart
    @POST("Advertisement/AppAdvertisementCreate")
    Observable<AdvertidePojo> Adversisement(@Part MultipartBody.Part image,
                                            @Part("AdvertisementType") RequestBody type,
                                            @Part("Advertisement") RequestBody adder,
                                            @Part("Place") RequestBody place,
                                            @Part("Latitude") RequestBody lat,
                                            @Part("Longitude") RequestBody lan,
                                            @Part("User_Id") RequestBody usrid,
                                            @Part("Message") RequestBody message,
                                             @Part("Branch") RequestBody branch);
    @POST("Settings/AppAdvertisementTypeList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<TypesPojo> showADDTypes();
    @POST("Advertisement/AppAdvertisementList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<AdListPojo> showAdds();
    @POST("Settings/AppComplaintCategoryList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ListPojo> CategoryComp();
    @POST("Members/AppMemberRejected")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ApprovePojo> RejectMember(@Body JsonObject approvePost);
    @POST("Settings/AppActivityTypeList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ActlistPojo> ActivityList();
    @Multipart
    @POST("Activity/AppActivityCreate")
    Observable<PostPojo> ActPost(@Part MultipartBody.Part image,
                                 @Part MultipartBody.Part videp,
                                 @Part MultipartBody.Part audio,
                                 @Part("ActivityType") RequestBody type,
                                 @Part("ActivityName") RequestBody name,
                                 @Part("ActivityDate") RequestBody date,
                                 @Part("Place") RequestBody place,
                                 @Part("Latitude") RequestBody lat,
                                 @Part("Longitude") RequestBody lan,
                                 @Part("Description") RequestBody des,
                                 @Part("User_Id") RequestBody userId,
                                 @Part("Branch") RequestBody branches);
    @POST("Activity/AppActivityList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<ActPojo> getLists();
    @POST("Events/AppEventList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<EventPojo> getEvents();
    @POST("Album/AppAlbumList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<GallerPojo> getGallery();
    @POST("Gallery/AppGalleryList")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<AlbumPojo> getGalleryList();


}
