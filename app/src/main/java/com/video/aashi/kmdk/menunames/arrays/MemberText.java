package com.video.aashi.kmdk.menunames.arrays;

public class MemberText {

    String  header,name,education,address,place,mobNumber,commity,caste,job,submit;
    String state,district, zone,branch;

    public MemberText(String header,String name,String education,String address,String place,String mobNumber,String commity,
                      String caste,String state,String district,String zone,String branch, String job,String submit )
    {
        this.header= header;
        this.name = name;
        this.education = education;
        this.address = address;
        this.place = place;
        this.mobNumber = mobNumber;
        this.commity =commity;
        this.caste = caste;
        this.job = job;
        this.submit =submit;
        this.state = state;
        this.district = district;
        this.zone = zone;
        this.branch = branch;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCaste() {
        return caste;
    }

    public String getCommity() {
        return commity;
    }

    public String getEducation() {
        return education;
    }

    public String getHeader() {
        return header;
    }

    public String getJob() {
        return job;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public String getPlace() {
        return place;
    }

    public String getSubmit() {
        return submit;
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
