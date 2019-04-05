package com.video.aashi.kmdk.Members.activities.classes;

import com.video.aashi.kmdk.menunames.arrays.ActivityText;

public class ActtypeList {
    String type;
    String id;

    public ActtypeList(String type,String id)
    {

        this.type = type;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
