package com.video.aashi.kmdk.menunames.arrays;

public class Enquiry {
    String hedings,name,district,city,mobilenumber,description,submit;


    public Enquiry(String hedings,String name, String district,String city,String mobilenumber,String description,String submit)
    {
        this.hedings = hedings;
        this.name = name;
        this.district =district;
        this.city = city;
        this.mobilenumber = mobilenumber;
        this.description = description;
        this.submit = submit;
    }
    public String getDistrict() {
        return district;
    }
    public String getSubmit() {
        return submit;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getDescription() {
        return description;
    }
    public String getHedings() {
        return hedings;
    }
    public String getMobilenumber() {
        return mobilenumber;
    }

}
