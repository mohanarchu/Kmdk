package com.video.aashi.kmdk.menunames.arrays;

public class ActivityText {

    String  header,date,district,zone,branch,division,heading,description,submit;

    public ActivityText(String header,String date,String district, String zone,String branch,String division,
                        String heading,String   description,String submit )
    {
        this.header =header;
        this.date =date;
        this.district = district;
        this.zone= zone;
        this.branch = branch;
        this.division =division;
        this.heading = heading;
        this.description= description;
        this.submit = submit;
    }

    public String getSubmit() {
        return submit;
    }

    public String getDescription() {
        return description;
    }

    public String getDistrict() {
        return district;
    }

    public String getZone() {
        return zone;
    }

    public String getBranch() {
        return branch;
    }

    public String getHeader() {
        return header;
    }

    public String getDate() {
        return date;
    }

    public String getDivision() {
        return division;
    }

    public String getHeading() {
        return heading;
    }

}
