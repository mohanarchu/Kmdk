package com.video.aashi.kmdk.Members.advertisements.types;

public class TypeResponse {
    private String IfDeleted;

    private String CreatedAt;

    private String __v;

    private String AdvertisementType;

    private String _id;

    private String UpdatedAt;

    public String getIfDeleted ()
    {
        return IfDeleted;
    }

    public void setIfDeleted (String IfDeleted)
    {
        this.IfDeleted = IfDeleted;
    }

    public String getCreatedAt ()
    {
        return CreatedAt;
    }

    public void setCreatedAt (String CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getAdvertisementType ()
    {
        return AdvertisementType;
    }

    public void setAdvertisementType (String AdvertisementType)
    {
        this.AdvertisementType = AdvertisementType;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getUpdatedAt ()
    {
        return UpdatedAt;
    }

    public void setUpdatedAt (String UpdatedAt)
    {
        this.UpdatedAt = UpdatedAt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IfDeleted = "+IfDeleted+", CreatedAt = "+CreatedAt+", __v = "+__v+", AdvertisementType = "+AdvertisementType+", _id = "+_id+", UpdatedAt = "+UpdatedAt+"]";
    }
}
