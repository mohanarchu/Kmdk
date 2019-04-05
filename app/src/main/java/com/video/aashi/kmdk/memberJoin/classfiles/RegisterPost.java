package com.video.aashi.kmdk.memberJoin.classfiles;

public class RegisterPost {
    String	Name;
     String Education;
      String Address;
      String City;
         String MobileNumber;
         String State_Id;
        String District_Id;
         String Zone_Id;
        String Branch_Id;
         String User_Id;
         String  Volunteer;
         String If_Committee;
         String SubCaste;

         public RegisterPost(String name,String education,String address,String city,String mobileNumber,String state,String district
                              ,String zone,String branch,String user_Id,String volunteer,String if_Committee,String subCaste  )
         {
             this.Name = name;
             this.Education     = education;
             this.Address = address;
             this.City = city;
             this.MobileNumber =mobileNumber;
             this.State_Id = state;
             this.District_Id = district;
             this.Zone_Id = zone;
             this.Branch_Id = branch;
             this.User_Id = user_Id;
             this.Volunteer  = volunteer;
             this.If_Committee = if_Committee;
             this.SubCaste = subCaste;
         }
}
