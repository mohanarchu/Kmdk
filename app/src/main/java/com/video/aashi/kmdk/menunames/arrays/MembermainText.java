package com.video.aashi.kmdk.menunames.arrays;

public class MembermainText {

    String newComplaints,seeAll, newMembers,gallery,events,advertisements,members,partyAffialation,homepage,complaints,activities,seeAllmem;


    public MembermainText(String newComplaints,String seeAll,String newMembers,String gallery,String events,String advertisements,
                          String members,String seeAllmem,String partyAffialation,String homepage,String complaints,String activities )
    {
        this.newComplaints= newComplaints;
        this.seeAll =seeAll;
        this.newMembers = newMembers;
        this.gallery = gallery;
        this.events =events;
        this.advertisements = advertisements;
        this.members = members;
        this.partyAffialation =partyAffialation;
        this.homepage = homepage;
        this.complaints = complaints;
        this.activities =activities;
        this.seeAllmem = seeAllmem;
    }

    public String getNewComplaints() {
        return newComplaints;
    }

    public String getGallery() {
        return gallery;
    }

    public String getComplaints() {
        return complaints;
    }

    public String getEvents() {
        return events;
    }

    public String getActivities() {
        return activities;
    }

    public String getAdvertisements() {
        return advertisements;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getMembers() {
        return members;
    }

    public String getNewMembers() {
        return newMembers;
    }

    public String getPartyAffialation() {
        return partyAffialation;
    }

    public String getSeeAll() {
        return seeAll;
    }

    public String getSeeAllmem() {
        return seeAllmem;
    }
}
