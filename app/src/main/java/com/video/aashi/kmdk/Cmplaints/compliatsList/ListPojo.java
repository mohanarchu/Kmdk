package com.video.aashi.kmdk.Cmplaints.compliatsList;

import java.util.ArrayList;

public class ListPojo {
    private String Status;

    private ArrayList<Comp> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<Comp> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }

    public class Comp
    {
        private String IfDeleted;

        private String ComplaintCategory;

        private String CreatedAt;

        private String __v;

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

        public String getComplaintCategory ()
        {
            return ComplaintCategory;
        }

        public void setComplaintCategory (String ComplaintCategory)
        {
            this.ComplaintCategory = ComplaintCategory;
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
            return "ClassPojo [IfDeleted = "+IfDeleted+", ComplaintCategory = "+ComplaintCategory+", CreatedAt = "+CreatedAt+", __v = "+__v+", _id = "+_id+", UpdatedAt = "+UpdatedAt+"]";
        }
    }
}
