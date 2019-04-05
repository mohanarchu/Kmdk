package com.video.aashi.kmdk.Members.activities.activitylist;

import java.util.ArrayList;

public class ActPojo {
    private String Status;

    private ArrayList<ActResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<ActResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
