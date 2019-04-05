package com.video.aashi.kmdk.Members.advertisements.list;

public class AdListResponse {
    private String IfDeleted;

    private CreatedBy CreatedBy;

    private String Message;

    private String CreatedAt;

    private String AdvertisementType;

    private String Latitude;

    private String Longitude;

    private String UpdatedAt;

    private AdvertisementImage AdvertisementImage;

    private UpdatedBy UpdatedBy;

    private String __v;

    private String Advertisement;

    private String _id;

    private String Place;

    public String getIfDeleted ()
    {
        return IfDeleted;
    }

    public void setIfDeleted (String IfDeleted)
    {
        this.IfDeleted = IfDeleted;
    }

    public CreatedBy getCreatedBy ()
    {
        return CreatedBy;
    }

    public void setCreatedBy (CreatedBy CreatedBy)
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

    public String getCreatedAt ()
    {
        return CreatedAt;
    }

    public void setCreatedAt (String CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }

    public String getAdvertisementType ()
    {
        return AdvertisementType;
    }

    public void setAdvertisementType (String AdvertisementType)
    {
        this.AdvertisementType = AdvertisementType;
    }

    public String getLatitude ()
    {
        return Latitude;
    }

    public void setLatitude (String Latitude)
    {
        this.Latitude = Latitude;
    }

    public String getLongitude ()
    {
        return Longitude;
    }

    public void setLongitude (String Longitude)
    {
        this.Longitude = Longitude;
    }

    public String getUpdatedAt ()
    {
        return UpdatedAt;
    }

    public void setUpdatedAt (String UpdatedAt)
    {
        this.UpdatedAt = UpdatedAt;
    }

    public AdvertisementImage getAdvertisementImage ()
    {
        return AdvertisementImage;
    }

    public void setAdvertisementImage (AdvertisementImage AdvertisementImage)
    {
        this.AdvertisementImage = AdvertisementImage;
    }

    public UpdatedBy getUpdatedBy ()
    {
        return UpdatedBy;
    }

    public void setUpdatedBy (UpdatedBy UpdatedBy)
    {
        this.UpdatedBy = UpdatedBy;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getAdvertisement ()
    {
        return Advertisement;
    }

    public void setAdvertisement (String Advertisement)
    {
        this.Advertisement = Advertisement;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getPlace ()
    {
        return Place;
    }

    public void setPlace (String Place)
    {
        this.Place = Place;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IfDeleted = "+IfDeleted+", CreatedBy = "+CreatedBy+", Message = "+Message+", CreatedAt = "+CreatedAt+", AdvertisementType = "+AdvertisementType+", Latitude = "+Latitude+", Longitude = "+Longitude+", UpdatedAt = "+UpdatedAt+", AdvertisementImage = "+AdvertisementImage+", UpdatedBy = "+UpdatedBy+", __v = "+__v+", Advertisement = "+Advertisement+", _id = "+_id+", Place = "+Place+"]";
    }

    public class UpdatedBy
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

    public class AdvertisementImage
    {
        private String filename;

        private String size;

        private String mimetype;

        public String getFilename ()
        {
            return filename;
        }

        public void setFilename (String filename)
        {
            this.filename = filename;
        }

        public String getSize ()
        {
            return size;
        }

        public void setSize (String size)
        {
            this.size = size;
        }

        public String getMimetype ()
        {
            return mimetype;
        }

        public void setMimetype (String mimetype)
        {
            this.mimetype = mimetype;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [filename = "+filename+", size = "+size+", mimetype = "+mimetype+"]";
        }
    }
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




}
