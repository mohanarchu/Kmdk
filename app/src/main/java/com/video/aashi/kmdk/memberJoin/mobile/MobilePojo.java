package com.video.aashi.kmdk.memberJoin.mobile;

public class MobilePojo {


    private String Status;

    private String CanSave;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getCanSave ()
    {
        return CanSave;
    }

    public void setCanSave (String CanSave)
    {
        this.CanSave = CanSave;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", CanSave = "+CanSave+"]";
    }
}
