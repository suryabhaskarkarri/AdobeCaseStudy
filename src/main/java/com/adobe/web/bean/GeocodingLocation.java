package com.adobe.web.bean;

public class GeocodingLocation {
    
    private String lng;

    private String lat;
    
    public GeocodingLocation() {
        
    }

    public String getLng ()
    {
        return lng;
    }

    public void setLng (String lng)
    {
        this.lng = lng;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }
}
