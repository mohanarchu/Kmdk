package com.video.aashi.kmdk.Members.activities.activitylist;
public class ActList {


    String id,name,date,type,image,des,createdBy,place,video,userId;

    public ActList(String id,String name,String date,String type,String image,String des,String createdBy,String createdById, String place,String video)
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.image = image;
        this.des = des;
        this.createdBy = createdBy;
        this.video = video;

        this.place = place;
        this.userId = createdById;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDes() {
        return des;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getPlace() {
        return place;
    }

    public String getVideo() {
        return video;
    }

    public String getUserId() {
        return userId;
    }
}



