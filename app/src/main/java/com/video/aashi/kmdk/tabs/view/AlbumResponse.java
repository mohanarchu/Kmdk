package com.video.aashi.kmdk.tabs.view;

import java.util.ArrayList;

public class AlbumResponse {


    private String IfDeleted;


    private String Gallery_Name;

    private ArrayList<GalleryImage> Gallery_Image;

    private String Album_Id;

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



    public String getGallery_Name ()
    {
        return Gallery_Name;
    }

    public void setGallery_Name (String Gallery_Name)
    {
        this.Gallery_Name = Gallery_Name;
    }

    public ArrayList<GalleryImage> getGallery_Image ()
    {
        return Gallery_Image;
    }


    public String getAlbum_Id ()
    {
        return Album_Id;
    }

    public void setAlbum_Id (String Album_Id)
    {
        this.Album_Id = Album_Id;
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


}
