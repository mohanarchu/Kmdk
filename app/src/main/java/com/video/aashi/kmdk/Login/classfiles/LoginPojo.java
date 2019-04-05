package com.video.aashi.kmdk.Login.classfiles;

public class LoginPojo {
    private String Status;

    private String Response;

    private String Message;

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getResponse ()
    {
        return Response;
    }

    public void setResponse (String Response)
    {
        this.Response = Response;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", Response = "+Response+", Message = "+Message+"]";
    }

}
