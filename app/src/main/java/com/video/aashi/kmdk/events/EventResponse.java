package com.video.aashi.kmdk.events;

public class EventResponse {

    private String IfDeleted;

    private CreatedBy CreatedBy;

    private EventImage EventImage;

    private Zone Zone;

    private String CreatedAt;

    private String EventName;

    private String UpdatedAt;

    private String Date;

    private UpdatedBy UpdatedBy;

    private State State;

    private Branch Branch;
    public String ChiefGuest;
    private String __v;

    private String ContactNumber;

    private String _id;

    private District District;

    private String ContactName;

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

    public EventImage getEventImage ()
    {
        return EventImage;
    }

    public void setEventImage (EventImage EventImage)
    {
        this.EventImage = EventImage;
    }

    public Zone getZone ()
    {
        return Zone;
    }

    public void setZone (Zone Zone)
    {
        this.Zone = Zone;
    }

    public String getCreatedAt ()
    {
        return CreatedAt;
    }

    public void setCreatedAt (String CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }

    public String getEventName ()
    {
        return EventName;
    }

    public void setEventName (String EventName)
    {
        this.EventName = EventName;
    }

    public String getUpdatedAt ()
    {
        return UpdatedAt;
    }

    public void setUpdatedAt (String UpdatedAt)
    {
        this.UpdatedAt = UpdatedAt;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public UpdatedBy getUpdatedBy ()
    {
        return UpdatedBy;
    }



    public void setUpdatedBy (UpdatedBy UpdatedBy)
    {
        this.UpdatedBy = UpdatedBy;
    }

    public State getState ()
    {
        return State;
    }

    public void setState (State State)
    {
        this.State = State;
    }

    public Branch getBranch ()
    {
        return Branch;
    }

    public void setBranch (Branch Branch)
    {
        this.Branch = Branch;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getContactNumber ()
    {
        return ContactNumber;
    }

    public String getChiefGuest() {
        return ChiefGuest;
    }

    public void setContactNumber (String ContactNumber)
    {
        this.ContactNumber = ContactNumber;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public District getDistrict ()
    {
        return District;
    }

    public void setDistrict (District District)
    {
        this.District = District;
    }

    public String getContactName ()
    {
        return ContactName;
    }

    public void setContactName (String ContactName)
    {
        this.ContactName = ContactName;
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
        return "ClassPojo [IfDeleted = "+IfDeleted+", CreatedBy = "+CreatedBy+", EventImage = "+EventImage+", Zone = "+Zone+", CreatedAt = "+CreatedAt+", EventName = "+EventName+", UpdatedAt = "+UpdatedAt+", Date = "+Date+", UpdatedBy = "+UpdatedBy+", State = "+State+", Branch = "+Branch+", __v = "+__v+", ContactNumber = "+ContactNumber+", _id = "+_id+", District = "+District+", ContactName = "+ContactName+", Place = "+Place+"]";
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
    public class Branch
    {
        private String _id;

        private String BranchName;

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
            return "ClassPojo [_id = "+_id+", BranchName = "+BranchName+"]";
        }
    }

    public class Zone
    {
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
    public class District
    {
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

    public class State
    {
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
    public class EventImage
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
