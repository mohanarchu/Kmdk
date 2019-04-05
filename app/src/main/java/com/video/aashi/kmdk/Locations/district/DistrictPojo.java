package com.video.aashi.kmdk.Locations.district;

import java.util.ArrayList;

public class DistrictPojo { private String Status;

    private ArrayList<DistrictResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<DistrictResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }

}
