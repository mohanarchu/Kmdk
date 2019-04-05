package com.video.aashi.kmdk.tabs.view;

public class GalleryImage {
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
