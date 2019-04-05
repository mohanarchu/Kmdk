package com.video.aashi.kmdk.Members.viewMem.classes;

import com.video.aashi.kmdk.Login.otp.classfiles.OtpRespons;

import java.util.ArrayList;

public class MemPojo {

    private String Status;

    private ArrayList<OtpRespons> Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public ArrayList<OtpRespons> getResponse() {
        return Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
