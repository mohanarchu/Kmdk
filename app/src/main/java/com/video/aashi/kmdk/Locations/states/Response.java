package com.video.aashi.kmdk.Locations.states;

public class Response
{
    private String UpdatedBy;

    private String IfDeleted;

    private String CreatedBy;

    private String StateName;

    private String __v;

    private String _id;

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

    public String getStateName ()
    {
        return StateName;
    }

    public void setStateName (String StateName)
    {
        this.StateName = StateName;
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

    @Override
    public String toString()
    {
        return "ClassPojo [UpdatedBy = "+UpdatedBy+", IfDeleted = "+IfDeleted+", CreatedBy = "+CreatedBy+", StateName = "+StateName+", __v = "+__v+", _id = "+_id+"]";
    }
}