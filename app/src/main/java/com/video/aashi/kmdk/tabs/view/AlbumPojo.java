package com.video.aashi.kmdk.tabs.view;

import java.util.ArrayList;

public class AlbumPojo {
    private String Status;

    private ArrayList<AlbumResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<AlbumResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
