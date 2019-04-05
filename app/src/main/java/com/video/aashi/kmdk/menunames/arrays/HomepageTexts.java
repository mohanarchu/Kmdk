package com.video.aashi.kmdk.menunames.arrays;

public class HomepageTexts {
    String member,complaints,gallary,events;
    public HomepageTexts(String member,String complaints,String gallary,String events)
    {
        this.member = member;
        this.complaints =complaints;
        this.gallary = gallary;
        this.events = events;
    }
    public String getComplaints() {
        return complaints;
    }
    public String getMember() {
        return member;
    }
    public String getEvents() {
        return events;
    }
    public String getGallary() {
        return gallary;
    }

}
