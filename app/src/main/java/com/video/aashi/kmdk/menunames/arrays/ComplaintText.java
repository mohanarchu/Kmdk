package com.video.aashi.kmdk.menunames.arrays;

public class ComplaintText {

    String header,name,district,zone,branch,place,mobile,comType,complaint,photo,video,audio,discription,submit;
    public ComplaintText(String header,String name,String district,String zone,String branch,String place,String  mobile,String comType
            ,String complaint,String photo,String video,String audio,String discription,String submit)
    {
        this.header = header ;
        this.name = name;
        this.district = district;
        this.zone= zone;
        this.branch = branch;
        this.place = place;
        this.mobile = mobile;
        this.comType = comType;
        this.complaint = complaint;
        this.photo = photo;
        this.video = video;
        this.audio = audio;
        this.discription = discription;
        this.submit = submit;
    }

    public String getPlace() {
        return place;
    }

    public String getHeader() {
        return header;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBranch() {
        return branch;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getComType() {
        return comType;
    }

    public String getDistrict() {
        return district;
    }

    public String getPhoto() {
        return photo;
    }

    public String getVideo() {
        return video;
    }

    public String getZone() {
        return zone;
    }

    public String getAudio() {
        return audio;
    }

    public String getSubmit() {
        return submit;
    }

    public String getDiscription() {
        return discription;
    }

}
