package com.video.aashi.kmdk.Login.otp.classfiles;

public class CreatedBy
{
    private String _id;

    private String Name;

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_id = "+_id+", Name = "+Name+"]";
    }
}
