package com.video.aashi.kmdk.contact;

public class ContactPost {

    String  User_Id;
     String Name;
      String Place;
      String Message;
      String MobileNumber;

      public ContactPost(String user_Id,String name,String place,String message,String mobileNumber)
      {
          this.User_Id = user_Id;
          this.Name = name;
          this.Place = place;
          this.Message = message;
          this.MobileNumber = mobileNumber;
      }
}
