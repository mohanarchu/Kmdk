package com.video.aashi.kmdk.Locations.states;

import java.util.ArrayList;

public class StatePojo {
    private String Status;

    private ArrayList<Response> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<com.video.aashi.kmdk.Locations.states.Response> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
