package com.video.aashi.kmdk.events;

public class EventList {

    String eventname,eventid,eventimage,place,date,contactname,number,created,crId,chief;

    public EventList(String eventname,String eventid,String eventimage,String place,String date,String contactname,
                     String number,String created,String createdID,String chief )
    {

        this.eventname = eventname;
        this.eventid = eventid;
        this.eventimage = eventimage;
        this.place = place;
        this.date = date;
        this.contactname =contactname;
        this.number = number;
        this.created = created;

        this.crId = createdID;
        this.chief = chief;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getContactname() {
        return contactname;
    }

    public String getCreated() {
        return created;
    }

    public String getEventid() {
        return eventid;
    }

    public String getEventimage() {
        return eventimage;
    }

    public String getEventname() {
        return eventname;
    }

    public String getNumber() {
        return number;
    }

    public String getCrId() {
        return crId;
    }

    public String getChief() {
        return chief;
    }
}
