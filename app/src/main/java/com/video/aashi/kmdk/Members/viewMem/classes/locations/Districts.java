package com.video.aashi.kmdk.Members.viewMem.classes.locations;

public class Districts {
    private String DistrictName;

    private String _id;

    public String getDistrictName ()
    {
        return DistrictName;
    }

    public void setDistrictName (String DistrictName)
    {
        this.DistrictName = DistrictName;
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
        return "ClassPojo [DistrictName = "+DistrictName+", _id = "+_id+"]";
    }
}
