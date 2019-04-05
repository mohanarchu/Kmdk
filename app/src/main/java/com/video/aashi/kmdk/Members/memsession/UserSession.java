package com.video.aashi.kmdk.Members.memsession;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.video.aashi.kmdk.PreferenceValue.SharedPreference;

public class UserSession {


 //   SharedPreference sharedPreference;
    SharedPreferences sharedPreferences;
    boolean ifCommitte,ifOfficial,stateAuthority,zoneAuthority,distriictAuthority,branchAuthority;
    boolean canAdd,canView,canEdit,canApprove;
    Context context;
    String state,zone,district,mobilenumber,image,id,status,branch;
    String stateId,branchId,districtId,zoneId,subcaste;
    String phonee;
    SharedPreferences.Editor editor;
    String name;
    public UserSession(Context context)
    {
        this.context = context;
    }

    @SuppressLint("CommitPrefEdits")
    public void getSharedPreferences()
    {
        sharedPreferences = context.getSharedPreferences("Users",Context.MODE_PRIVATE);
        editor   = sharedPreferences.edit();
    }

    public String getSubcaste() {
        getSharedPreferences();
        subcaste = sharedPreferences.getString("subcaste","");
        return subcaste;
    }

    public void setSubcaste(String subcaste) {
        getSharedPreferences();
        editor.putString("subcaste",subcaste);
        applyShared();

        this.subcaste = subcaste;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
    }

    public void applyShared()
    {
        editor.apply();
    }

    public boolean isBranchAuthority() {
        getSharedPreferences();

        branchAuthority = sharedPreferences.getBoolean("branches" ,false);

        return branchAuthority;
    }

    public String getPhonee() {
        getSharedPreferences();
        phonee= sharedPreferences.getString("phone","");
        return phonee;
    }


    public String getName() {
        getSharedPreferences();
        name = sharedPreferences.getString("names","");
        return name;
    }

    public void setName(String name) {
        getSharedPreferences();
        editor.putString("names",name);
        applyShared();
        this.name = name;
    }
    public String getBranch() {
        getSharedPreferences();
        branch = sharedPreferences.getString("branch","");
        return branch;
    }
    public void setBranchId(String branchId) {
        getSharedPreferences();
        editor.putString("branchId",branchId);
        applyShared();
        this.branchId = branchId;
    }
    public String getZoneId() {
        getSharedPreferences();
        zoneId = sharedPreferences.getString("zoneId","");
        return zoneId;
    }

    public String getBranchId() {
        getSharedPreferences();
        branchId = sharedPreferences.getString("branchId","");

        return branchId;
    }

    public String getDistrictId() {

        getSharedPreferences();
        districtId = sharedPreferences.getString("districtId","");
        return districtId;
    }

    public String getStateId() {
        stateId = sharedPreferences.getString("stateId","");
        return stateId;
    }

    public void setBranch(String branch) {
        getSharedPreferences();
        editor.putString("branch",branch);
        applyShared();

        this.branch = branch;
    }

    public boolean isCanAdd() {
        getSharedPreferences();

        canAdd = sharedPreferences.getBoolean("canadd" ,false);

        return canAdd;
    }

    public boolean isCanApprove() {
        getSharedPreferences();

        canApprove = sharedPreferences.getBoolean("approve" ,false);


        return canApprove;
    }

    public boolean isCanEdit() {

        getSharedPreferences();

        canEdit = sharedPreferences.getBoolean("canedit" ,false);

        return canEdit;
    }

    public boolean isCanView() {
        getSharedPreferences();

        canView = sharedPreferences.getBoolean("canview" ,false);

        return canView;
    }

    public boolean isDistriictAuthority() {
        getSharedPreferences();

        distriictAuthority = sharedPreferences.getBoolean("districts" ,false);

        return distriictAuthority;
    }

    public boolean isIfCommitte() {
        getSharedPreferences();

        ifCommitte = sharedPreferences.getBoolean("committie" ,false);

        return ifCommitte;
    }

    public boolean isIfOfficial() {
        getSharedPreferences();

        ifOfficial = sharedPreferences.getBoolean("official" ,false);

        return ifOfficial;
    }

    public boolean isStateAuthority() {
        getSharedPreferences();

        stateAuthority = sharedPreferences.getBoolean("states" ,false);


        return stateAuthority;
    }

    public boolean isZoneAuthority() {
        getSharedPreferences();

        zoneAuthority = sharedPreferences.getBoolean("zones" ,false);

        return zoneAuthority;
    }

    public void setBranchAuthority(boolean branchAuthority) {
        getSharedPreferences();

        editor.putBoolean("branches" ,branchAuthority);
        applyShared();

        this.branchAuthority = branchAuthority;
    }

    public void setCanAdd(boolean canAdd) {
        getSharedPreferences();

        editor.putBoolean("canadd" ,canAdd);
        applyShared();

        this.canAdd = canAdd;
    }

    public void setCanApprove(boolean canApprove) {
        getSharedPreferences();

        editor.putBoolean("approve" ,canApprove);
        applyShared();

        this.canApprove = canApprove;
    }

    public void setCanEdit(boolean canEdit) {
        getSharedPreferences();

        editor.putBoolean("canedit" ,canEdit);
        applyShared();

        this.canEdit = canEdit;
    }

    public void setCanView(boolean canView) {
        getSharedPreferences();

        editor.putBoolean("canview" ,canView);
        applyShared();

        this.canView = canView;
    }

    public void setDistriictAuthority(boolean distriictAuthority) {
        getSharedPreferences();

        editor.putBoolean("districts" ,distriictAuthority);
        applyShared();

        this.distriictAuthority = distriictAuthority;
    }


    public void setIfCommitte(boolean ifCommitte) {
        getSharedPreferences();

        editor.putBoolean("committie" ,ifCommitte);
        applyShared();

        this.ifCommitte = ifCommitte;
    }


    public void setIfOfficial(boolean ifOfficial) {
        getSharedPreferences();

        editor.putBoolean("official" ,ifOfficial);
        applyShared();


        this.ifOfficial = ifOfficial;
    }

    public String getState() {

        getSharedPreferences();
        state = sharedPreferences.getString("state","");
        return state;
    }

    public String getId() {
        getSharedPreferences();
        id = sharedPreferences.getString("id","");
        return id;
    }

    public String getZone() {
        getSharedPreferences();
        zone = sharedPreferences.getString("zone","");
        return zone;
    }

    public String getDistrict() {
        getSharedPreferences();
        district = sharedPreferences.getString("district","");
        return district;
    }

    public void setZoneId(String zoneId) {
        getSharedPreferences();
        editor.putString("zoneId",zoneId);
        applyShared();
        this.zoneId = zoneId;
    }


    public void setStateId(String stateId) {
        getSharedPreferences();
        editor.putString("stateId",stateId);

        applyShared();
        this.stateId = stateId;
    }

    public void setDistrictId(String districtId) {

        getSharedPreferences();
        editor.putString("districtId",districtId);
        applyShared();
        this.districtId = districtId;
    }

    public String getMobilenumber() {
        getSharedPreferences();
        mobilenumber = sharedPreferences.getString("mobile","");
        return mobilenumber;
    }

    public String getImage() {
        getSharedPreferences();
        image = sharedPreferences.getString("image","");
        return image;
    }

    public String getStatus() {
        getSharedPreferences();
        status = sharedPreferences.getString("status","");
        applyShared();

        return status;
    }

    public void setState(String state) {
        getSharedPreferences();

        editor.putString("state" ,state);
        applyShared();
        this.state = state;
    }

    public void setStateAuthority(boolean stateAuthority) {


        getSharedPreferences();

        editor.putBoolean("states" ,stateAuthority);
        applyShared();
        this.stateAuthority = stateAuthority;
    }

    public void setZoneAuthority(boolean zoneAuthority) {

        getSharedPreferences();

        editor.putBoolean("zones" ,zoneAuthority);
        applyShared();

        this.zoneAuthority = zoneAuthority;
    }

    public void setDistrict(String district) {
        getSharedPreferences();

        editor.putString("district",district);
        applyShared();

        this.district = district;
    }

    public void setId(String id) {
        getSharedPreferences();

        editor.putString("id",id);
        applyShared();


        this.id = id;
    }

    public void setImage(String image) {
        getSharedPreferences();

        editor.putString("image",image);
        applyShared();

        this.image = image;
    }

    public void setZone(String zone) {
        getSharedPreferences();

        editor.putString("zone",zone);
        applyShared();
        this.zone = zone;
    }

    public void setMobilenumber(String mobilenumber) {
        getSharedPreferences();

        editor.putString("mobile",mobilenumber);
        applyShared();

        this.mobilenumber = mobilenumber;
    }


    public void setStatus(String status) {
        getSharedPreferences();
        editor.putString("status",status);
        applyShared();
        this.status = status;
    }
}
