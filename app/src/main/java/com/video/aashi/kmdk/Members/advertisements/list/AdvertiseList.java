package com.video.aashi.kmdk.Members.advertisements.list;

public class AdvertiseList {
    String id,type,advertise,message,image,place,createby,createdat;


    public  AdvertiseList(String id,String type,String advertise,String message,String image,String place,
                          String createby,String createdat)
    {
        this.id = id    ;
        this.type = type;

        this.advertise = advertise;
        this.message = message;
        this.image = image;
        this.place = place;
        this.createby = createby;
        this.createdat = createdat;
    }

    public String getPlace() {
        return place;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getAdvertise() {
        return advertise;
    }

    public String getCreateby() {
        return createby;
    }

    public String getCreatedat() {
        return createdat;
    }

    public String getType() {
        return type;
    }
}
