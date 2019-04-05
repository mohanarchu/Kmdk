package com.video.aashi.kmdk.Members.viewMem.classes.locations;

public class Zones {
    private String ZoneName;

    private String _id;

    public String getZoneName ()
    {
        return ZoneName;
    }

    public void setZoneName (String ZoneName)
    {
        this.ZoneName = ZoneName;
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
        return "ClassPojo [ZoneName = "+ZoneName+", _id = "+_id+"]";
    }
}
