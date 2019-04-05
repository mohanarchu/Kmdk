package com.video.aashi.kmdk.Members.advertisements.list;

import java.util.ArrayList;

public class AdListPojo {
    private String Status;

    private ArrayList<AdListResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<AdListResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
