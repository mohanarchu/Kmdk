package com.video.aashi.kmdk.Members.activities.activitylist;

public class ActResponse {
    private String IfDeleted;

    private String Description;

    private CreatedBy CreatedBy;

    private String ActivityName;

    private String ActivityDate;

    private String CreatedAt;

    private ActivityType ActivityType;

    private String Latitude;

    private String Longitude;

    private String UpdatedAt;

    private ActivityImage ActivityImage;

    private UpdatedBy UpdatedBy;

    private String __v;

    private String _id;

    private String Place;

     private   ActivityVideo ActivityVideo;

    public String getIfDeleted ()
    {
        return IfDeleted;
    }

    public void setIfDeleted (String IfDeleted)
    {
        this.IfDeleted = IfDeleted;
    }

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public CreatedBy getCreatedBy ()
    {
        return CreatedBy;
    }

    public void setCreatedBy (CreatedBy CreatedBy)
    {
        this.CreatedBy = CreatedBy;
    }

    public String getActivityName ()
    {
        return ActivityName;
    }

    public void setActivityName (String ActivityName)
    {
        this.ActivityName = ActivityName;
    }

    public String getActivityDate ()
    {
        return ActivityDate;
    }

    public void setActivityDate (String ActivityDate)
    {
        this.ActivityDate = ActivityDate;
    }

    public String getCreatedAt ()
    {
        return CreatedAt;
    }

    public void setCreatedAt (String CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }

    public ActivityType getActivityType ()
    {
        return ActivityType;
    }

    public void setActivityType (ActivityType ActivityType)
    {
        this.ActivityType = ActivityType;
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

    public ActivityImage getActivityImage ()
    {
        return ActivityImage;
    }

    public void setActivityImage (ActivityImage ActivityImage)
    {
        this.ActivityImage = ActivityImage;
    }

    public ActResponse.ActivityVideo getActivityVideo() {
        return ActivityVideo;
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
        return "ClassPojo [IfDeleted = "+IfDeleted+", Description = "+Description+", CreatedBy = "+CreatedBy+", ActivityName = "+ActivityName+", ActivityDate = "+ActivityDate+", CreatedAt = "+CreatedAt+", ActivityType = "+ActivityType+", Latitude = "+Latitude+", Longitude = "+Longitude+", UpdatedAt = "+UpdatedAt+", ActivityImage = "+ActivityImage+", UpdatedBy = "+UpdatedBy+", __v = "+__v+", _id = "+_id+", Place = "+Place+"]";
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

    public class ActivityImage
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

    public class ActivityType
    {
        private String ActivityType;

        private String _id;

        public String getActivityType ()
        {
            return ActivityType;
        }

        public void setActivityType (String ActivityType)
        {
            this.ActivityType = ActivityType;
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
            return "ClassPojo [ActivityType = "+ActivityType+", _id = "+_id+"]";
        }
    }
    public class ActivityVideo
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



}
