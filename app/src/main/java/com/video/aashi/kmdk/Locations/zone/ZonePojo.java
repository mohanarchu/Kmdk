package com.video.aashi.kmdk.Locations.zone;

import java.util.ArrayList;

public class ZonePojo {
    private String Status;

    private ArrayList<ZoneResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<ZoneResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
