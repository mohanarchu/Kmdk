package com.video.aashi.kmdk.Locations.branch;

public class BranchResponse {
    private String UpdatedBy;

    private String IfDeleted;

    private String CreatedBy;


    private String __v;

    private String _id;

    private String BranchName;


    public String getUpdatedBy ()
    {
        return UpdatedBy;
    }

    public void setUpdatedBy (String UpdatedBy)
    {
        this.UpdatedBy = UpdatedBy;
    }

    public String getIfDeleted ()
    {
        return IfDeleted;
    }

    public void setIfDeleted (String IfDeleted)
    {
        this.IfDeleted = IfDeleted;
    }

    public String getCreatedBy ()
    {
        return CreatedBy;
    }

    public void setCreatedBy (String CreatedBy)
    {
        this.CreatedBy = CreatedBy;
    }



    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

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
        return "ClassPojo [UpdatedBy = "+UpdatedBy+", IfDeleted = "+IfDeleted+", CreatedBy = "+CreatedBy+", __v = "+__v+", _id = "+_id+", BranchName = "+BranchName+"]";
    }
}
