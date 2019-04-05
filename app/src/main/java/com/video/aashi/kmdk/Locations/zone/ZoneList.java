package com.video.aashi.kmdk.Locations.zone;

public class ZoneList {

    String zoneName,zoneId;
    public ZoneList(String zoneId,String zoneName)
    {
        this.zoneName = zoneName;
        this.zoneId = zoneId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }
}
