package com.video.aashi.kmdk.Locations.branch;

import java.util.ArrayList;

public class BranchPojo {
    private String Status;

    private ArrayList<BranchResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<BranchResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
