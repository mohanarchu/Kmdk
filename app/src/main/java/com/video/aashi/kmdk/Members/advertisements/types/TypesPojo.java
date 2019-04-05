package com.video.aashi.kmdk.Members.advertisements.types;

import java.util.ArrayList;

public class TypesPojo {
    private String Status;

    private ArrayList<TypeResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<TypeResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
