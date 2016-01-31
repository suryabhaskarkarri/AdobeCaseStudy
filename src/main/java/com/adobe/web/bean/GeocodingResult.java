package com.adobe.web.bean;

public class GeocodingResult {
    
    private String formatted_address;
    
    private GeocodingGeometry geometry;
    
    public GeocodingResult() {
        
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public GeocodingGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeocodingGeometry geometry) {
        this.geometry = geometry;
    }

}
