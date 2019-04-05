package com.video.aashi.kmdk.Locations.district;

public class DistrictResponse {
    private String DistrictName;

    private String UpdatedBy;

    private String IfDeleted;

    private String CreatedBy;

    private String Message;
    private String __v;

    private String _id;

    public String getDistrictName ()
    {
        return DistrictName;
    }

    public void setDistrictName (String DistrictName)
    {
        this.DistrictName = DistrictName;
    }

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

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
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
        return "ClassPojo [DistrictName = "+DistrictName+", UpdatedBy = "+UpdatedBy+", IfDeleted = "+IfDeleted+", " +
                "CreatedBy = "+CreatedBy+", Message = "+Message+", __v = "+__v+", _id = "+_id+"]";
    }
}
