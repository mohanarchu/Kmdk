package com.video.aashi.kmdk.menunames.arrays;

public class MainTExt {

    String dashboard,events,newMember,gallery,newComplaints,Complaints,downloads,contactAdmin,others,
            settings,login,visitWebsite,changeLan;

    public MainTExt(String dashboard,String events,String newMember,String gallery,String newComplaint,String complaints,
                    String downloads,String contactAdmin,String others,String settings,String login,String visitWebsite,String changeLan )
    {
        this.dashboard = dashboard;
        this.events =events;
        this.newMember= newMember;
        this.gallery = gallery;
        this.newComplaints = newComplaint;
        this.Complaints = complaints;
        this.downloads = downloads;
        this.contactAdmin = contactAdmin;
        this.others = others;
        this.settings = settings;
        this.login = login;
        this.visitWebsite = visitWebsite;
        this.changeLan = changeLan;
    }

    public String getEvents() {
        return events;
    }

    public String getComplaints() {
        return Complaints;
    }

    public String getContactAdmin() {
        return contactAdmin;
    }

    public String getDashboard() {
        return dashboard;
    }

    public String getDownloads() {
        return downloads;
    }

    public String getGallery() {
        return gallery;
    }

    public String getNewComplaints() {
        return newComplaints;
    }

    public String getNewMember() {
        return newMember;
    }

    public String getOthers() {
        return others;
    }

    public String getLogin() {
        return login;
    }

    public String getSettings() {
        return settings;
    }

    public String getVisitWebsite() {
        return visitWebsite;
    }
    public String getChangeLan() {
        return changeLan;
    }

}
