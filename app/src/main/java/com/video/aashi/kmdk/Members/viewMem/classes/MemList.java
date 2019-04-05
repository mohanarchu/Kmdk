package com.video.aashi.kmdk.Members.viewMem.classes;

public class MemList {
    String name,createAt,id,phone,education,city,photo,status;
    String state,district,zone,branch,memId;


    public MemList(String name,String createAt,String id,String phone,String education,String photo,String city,String status
    ,String state,String district,String zone,String branch,String memId)
    {
        this.name = name;
        this.createAt =createAt;
        this.id = id;
        this.phone = phone;
        this.education = education;
        this.photo = photo;
        this.city = city;
        this.status = status;
        this.state = state;
        this.district = district;
        this.zone = zone;
        this.branch = branch;
        this.memId = memId;

    }

    public String getMemId() {
        return memId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEducation() {
        return education;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public String getBranch() {
        return branch;
    }

    public String getDistrict() {
        return district;
    }

    public String getZone() {
        return zone;
    }

    public String getState() {
        return state;
    }
}
