package com.video.aashi.kmdk.Members.viewMem.memView;

public class ViewPojo {
    private String Status;

    private ViewResponse Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ViewResponse getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
