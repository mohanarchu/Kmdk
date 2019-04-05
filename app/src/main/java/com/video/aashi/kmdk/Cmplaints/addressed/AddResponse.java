package com.video.aashi.kmdk.Cmplaints.addressed;

public class AddResponse {
    private String IfDeleted;

    private CreatedBy CreatedBy;


    private ComplaintVideo ComplaintVideo;
    private ComplaintImage ComplaintImage;
    private String Message;

    private String CreatedAt;

    private String UpdatedAt;

    private String Name;

    private UpdatedBy UpdatedBy;

    private String MobileNumber;

    private ComplaintType ComplaintType;

    private String __v;

    private String Complaint;

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

    public String getUpdatedAt ()
    {
        return UpdatedAt;
    }

    public void setUpdatedAt (String UpdatedAt)
    {
        this.UpdatedAt = UpdatedAt;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public UpdatedBy getUpdatedBy ()
    {
        return UpdatedBy;
    }

    public void setUpdatedBy (UpdatedBy UpdatedBy)
    {
        this.UpdatedBy = UpdatedBy;
    }

    public String getMobileNumber ()
    {
        return MobileNumber;
    }

    public void setMobileNumber (String MobileNumber)
    {
        this.MobileNumber = MobileNumber;
    }

    public ComplaintType getComplaintType ()
    {
        return ComplaintType;
    }

    public void setComplaintType (ComplaintType ComplaintType)
    {
        this.ComplaintType = ComplaintType;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getComplaint ()
    {
        return Complaint;
    }

    public void setComplaint (String Complaint)
    {
        this.Complaint = Complaint;
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

    public AddResponse.ComplaintImage getComplaintImage() {
        return ComplaintImage;
    }

    public AddResponse.ComplaintVideo getComplaintVideo() {
        return ComplaintVideo;
    }

    public void setPlace (String Place)
    {
        this.Place = Place;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IfDeleted = "+IfDeleted+", CreatedBy = "+CreatedBy+", Message = "+Message+", CreatedAt = "+CreatedAt+", UpdatedAt = "+UpdatedAt+", Name = "+Name+", UpdatedBy = "+UpdatedBy+", MobileNumber = "+MobileNumber+", ComplaintType = "+ComplaintType+", __v = "+__v+", Complaint = "+Complaint+", _id = "+_id+", Place = "+Place+"]";
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

    public class ComplaintType
    {
        private String ComplaintCategory;

        private String _id;

        public String getComplaintCategory ()
        {
            return ComplaintCategory;
        }

        public void setComplaintCategory (String ComplaintCategory)
        {
            this.ComplaintCategory = ComplaintCategory;
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
            return "ClassPojo [ComplaintCategory = "+ComplaintCategory+", _id = "+_id+"]";
        }
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

    public class ComplaintVideo
    {
        private String filename;

        private String  size;

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

    }
    public class ComplaintImage
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


    }



}
