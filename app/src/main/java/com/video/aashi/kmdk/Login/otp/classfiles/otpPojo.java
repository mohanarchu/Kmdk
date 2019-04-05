package com.video.aashi.kmdk.Login.otp.classfiles;

public class otpPojo {
    private String Status;

    private OtpRespons Response;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public OtpRespons getResponse ()
    {
        return Response;
    }

    public void setResponse (OtpRespons Response)
    {
        this.Response = Response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+"]";
    }
}
