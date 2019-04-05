package com.video.aashi.kmdk.Cmplaints.addressed;

public class ComplaiArray {

    String name,mobilenumber,addtype,message,complaint,time,place,video,image;

    public ComplaiArray(String name,String mobilenumber,String addtype,String message,String complaint,String time,String place,
                         String video,String image )
    {
        this.name= name;
        this.mobilenumber= mobilenumber;
        this.addtype = addtype;
        this.message= message;
        this.complaint= complaint;
        this.time= time;
        this.place = place;
        this.video = video;
        this.image = image;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getName() {
        return name;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getAddtype() {
        return addtype;
    }

    public String getPlace() {
        return place;
    }


    public String getVideo() {
        return video;
    }

    public String getImage() {
        return image;
    }
}
