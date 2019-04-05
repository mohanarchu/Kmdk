package com.video.aashi.kmdk.Members.viewMem.classes.locations;

public class States {
    private String StateName;

    private String _id;

    public String getStateName ()
    {
        return StateName;
    }

    public void setStateName (String StateName)
    {
        this.StateName = StateName;
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
        return "ClassPojo [StateName = "+StateName+", _id = "+_id+"]";
    }
}
