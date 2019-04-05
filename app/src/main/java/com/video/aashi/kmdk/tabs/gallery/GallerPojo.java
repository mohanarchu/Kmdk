package com.video.aashi.kmdk.tabs.gallery;

import java.util.ArrayList;

public class GallerPojo {
    private String Status;

    private ArrayList<GalleryResponse> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<GalleryResponse> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }

}
