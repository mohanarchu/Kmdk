package com.video.aashi.kmdk.Members.viewMem.memView;

import com.video.aashi.kmdk.Login.otp.classfiles.CreatedBy;
import com.video.aashi.kmdk.Login.otp.classfiles.Designations;
import com.video.aashi.kmdk.Login.otp.classfiles.OtpRespons;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.Branches;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.Districts;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.States;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.Zones;

public class ViewResponse {
    private String If_Committee;

    private String IfDeleted;

    private String Address;

    private String Volunteer;

    public MemberImage MemberImage;

    private String CreatedAt;



    private String Name;

    private String ActiveStatus;

    private String If_Official;

    private String Member_Status;

    private String Education;

    private Branches Branch;

    private String __v;

    private String MemberIdLength;

    private String MemberReferenceId;

    private String Status;

    private Designations Designation;



    private Zones Zone;

    private String City;

    private String UpdatedAt;

    private String MobileNumber;

    private States State;

    private String _id;

    private Districts District;

    public String getIf_Committee ()
    {
        return If_Committee;
    }

    public void setIf_Committee (String If_Committee)
    {
        this.If_Committee = If_Committee;
    }

    public String getIfDeleted ()
    {
        return IfDeleted;
    }

    public void setIfDeleted (String IfDeleted)
    {
        this.IfDeleted = IfDeleted;
    }

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
    }

    public String getVolunteer ()
    {
        return Volunteer;
    }

    public void setVolunteer (String Volunteer)
    {
        this.Volunteer = Volunteer;
    }

    public String getCreatedAt ()
    {
        return CreatedAt;
    }

    public void setCreatedAt (String CreatedAt)
    {
        this.CreatedAt = CreatedAt;
    }

    public ViewResponse.MemberImage getMemberImage() {
        return MemberImage;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getActiveStatus ()
    {
        return ActiveStatus;
    }

    public void setActiveStatus (String ActiveStatus)
    {
        this.ActiveStatus = ActiveStatus;
    }

    public String getIf_Official ()
    {
        return If_Official;
    }

    public void setIf_Official (String If_Official)
    {
        this.If_Official = If_Official;
    }


    public String getMember_Status ()
    {
        return Member_Status;
    }

    public void setMember_Status (String Member_Status)
    {
        this.Member_Status = Member_Status;
    }

    public String getEducation ()
    {
        return Education;
    }

    public void setEducation (String Education)
    {
        this.Education = Education;
    }

    public Branches getBranch ()
    {
        return Branch;
    }

    public void setBranch (Branches Branch)
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

    public String getMemberIdLength ()
    {
        return MemberIdLength;
    }

    public void setMemberIdLength (String MemberIdLength)
    {
        this.MemberIdLength = MemberIdLength;
    }

    public String getMemberReferenceId ()
    {
        return MemberReferenceId;
    }

    public void setMemberReferenceId (String MemberReferenceId)
    {
        this.MemberReferenceId = MemberReferenceId;
    }

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }


    public Designations getDesignation() {
        return Designation;
    }

    public Zones getZone ()
    {
        return Zone;
    }

    public void setZone (Zones Zone)
    {
        this.Zone = Zone;
    }

    public String getCity ()
    {
        return City;
    }

    public void setCity (String City)
    {
        this.City = City;
    }

    public String getUpdatedAt ()
    {
        return UpdatedAt;
    }

    public void setUpdatedAt (String UpdatedAt)
    {
        this.UpdatedAt = UpdatedAt;
    }

    public String getMobileNumber ()
    {
        return MobileNumber;
    }

    public void setMobileNumber (String MobileNumber)
    {
        this.MobileNumber = MobileNumber;
    }

    public States getState ()
    {
        return State;
    }

    public void setState (States State)
    {
        this.State = State;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public Districts getDistrict ()
    {
        return District;
    }

    public void setDistrict (Districts District)
    {
        this.District = District;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [If_Committee = "+If_Committee+", IfDeleted = "+IfDeleted+", Address = "+Address+", Volunteer = "+Volunteer+", " +
                "CreatedAt = "+CreatedAt+", Name = "+Name+", ActiveStatus = "+ActiveStatus+", If_Official = "+If_Official+"," +
                ", Member_Status = "+Member_Status+", Education = "+Education+", Branch = "+Branch+", __v = "+__v+", MemberIdLength = " +
                ""+MemberIdLength+", MemberReferenceId = "+MemberReferenceId+", Status = "+Status+", Designation = "+Designation+"," +
               ", Zone = "+Zone+", City = "+City+", UpdatedAt = "+UpdatedAt+", MobileNumber = "+MobileNumber+", " +
                "State = "+State+", _id = "+_id+", District = "+District+"]";
    }
    public class MemberImage
    {
        private String filename;

        private String size;

        private String mimetype;

        public String getFilename ()
        {
            return filename;
        }


    }
}
