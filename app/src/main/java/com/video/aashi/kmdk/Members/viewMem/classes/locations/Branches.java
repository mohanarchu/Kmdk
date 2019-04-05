package com.video.aashi.kmdk.Members.viewMem.classes.locations;

public class Branches {
    private String _id;

    private String BranchName;

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getBranchName ()
    {
        return BranchName;
    }

    public void setBranchName (String BranchName)
    {
        this.BranchName = BranchName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_id = "+_id+", BranchName = "+BranchName+"]";
    }
}
