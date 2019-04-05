package com.video.aashi.kmdk.Locations.district;

public class DistrictList {

    String name,Id;
    public DistrictList (String name,String id)
    {
        this.name = name;
        this.Id =  id;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
