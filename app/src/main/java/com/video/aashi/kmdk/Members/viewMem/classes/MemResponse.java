package com.video.aashi.kmdk.Members.viewMem.classes;

import com.video.aashi.kmdk.Locations.branch.Branch;
import com.video.aashi.kmdk.Locations.district.DistrictList;
import com.video.aashi.kmdk.Locations.states.StateList;
import com.video.aashi.kmdk.Locations.zone.ZoneList;
import com.video.aashi.kmdk.Locations.zone.ZoneResponse;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.Branches;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.Districts;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.States;
import com.video.aashi.kmdk.Members.viewMem.classes.locations.Zones;

import java.util.ArrayList;

public class MemResponse {
    private String If_Committee;

    private String IfDeleted;

    private String Address;

    private String Volunteer;

    private String CreatedAt;

    private String District_Authority;

    private String OTP;
    public MemberImage memberImage;

    private String Can_Approval;

    private String Name;

    private  String MemberReferenceId;

    private States State;

    private String Zone_Authority;

    private String Branch_Authority;

    private String ActiveStatus;

    private String If_Official;

    private String UpdatedBy;

    private String Member_Status;

    private String Education;

    private String SubCaste;

  private  Branches branches;

    private String __v;

    private String Status;

    private String Can_Edit;

    private String CreatedBy;

    private Zones Zone;

    private String City;

    private String UpdatedAt;

    private String MobileNumber;

    private String Can_Add;

   // private States[] State;

    private String Can_View;

    private String _id;

    private Districts District;

    private String State_Authority;

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

    public MemberImage getMemberImage() {
        return memberImage;
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

    public String getDistrict_Authority ()
    {
        return District_Authority;
    }

    public void setDistrict_Authority (String District_Authority)
    {
        this.District_Authority = District_Authority;
    }

    public String getMemberReferenceId() {
        return MemberReferenceId;
    }

    public String getOTP ()
    {
        return OTP;
    }
    public String getCan_Approval ()
    {
        return Can_Approval;
    }

    public void setCan_Approval (String Can_Approval)
    {
        this.Can_Approval = Can_Approval;
    }

    public String getName ()
    {
        return Name;
    }

    public States getStates() {
        return State;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getZone_Authority ()
    {
        return Zone_Authority;
    }

    public void setZone_Authority (String Zone_Authority)
    {
        this.Zone_Authority = Zone_Authority;
    }

    public String getBranch_Authority ()
    {
        return Branch_Authority;
    }

    public void setBranch_Authority (String Branch_Authority)
    {
        this.Branch_Authority = Branch_Authority;
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

    public String getUpdatedBy ()
    {
        return UpdatedBy;
    }

    public void setUpdatedBy (String UpdatedBy)
    {
        this.UpdatedBy = UpdatedBy;
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

    public String getSubCaste ()
    {
        return SubCaste;
    }

    public void setSubCaste (String SubCaste)
    {
        this.SubCaste = SubCaste;
    }

    public Branches getBranches() {
        return branches;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getCan_Edit ()
    {
        return Can_Edit;
    }

    public void setCan_Edit (String Can_Edit)
    {
        this.Can_Edit = Can_Edit;
    }

    public String getCreatedBy ()
    {
        return CreatedBy;
    }

    public void setCreatedBy (String  CreatedBy)
    {
        this.CreatedBy = CreatedBy;
    }

    public Zones getZone() {
        return Zone;
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

    public String getCan_Add ()
    {
        return Can_Add;
    }

    public void setCan_Add (String Can_Add)
    {
        this.Can_Add = Can_Add;
    }



    public String getCan_View ()
    {
        return Can_View;
    }

    public void setCan_View (String Can_View)
    {
        this.Can_View = Can_View;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public Districts getDistrict() {
        return District;
    }

    public String getState_Authority ()
    {
        return State_Authority;
    }

    public void setState_Authority (String State_Authority)
    {
        this.State_Authority = State_Authority;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [If_Committee = "+If_Committee+", IfDeleted = "+IfDeleted+", Address = "+Address+", " +
                "Volunteer = "+Volunteer+", CreatedAt = "+CreatedAt+", District_Authority = "+District_Authority+"," +
                " OTP = "+OTP+", Can_Approval = "+Can_Approval+", Name = "+Name+", Zone_Authority = "+Zone_Authority+", " +
                "Branch_Authority = "+Branch_Authority+", ActiveStatus = "+ActiveStatus+", If_Official = "+If_Official+", " +
                "UpdatedBy = "+UpdatedBy+", Member_Status = "+Member_Status+", Education = "+Education+", SubCaste = "+
                SubCaste+"+, __v = "+__v+", Status = "+Status+", Can_Edit = "+Can_Edit+", CreatedBy = "
                +CreatedBy+", Zone = "+Zone+", City = "+City+", UpdatedAt = "+UpdatedAt+", MobileNumber = "+MobileNumber+"," +
                " Can_Add = "+Can_Add+", , Can_View = "+Can_View+", _id = "+_id+", District = "+District+"," +
                " State_Authority = "+State_Authority+"]";
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
