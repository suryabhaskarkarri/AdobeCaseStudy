package com.adobe.web.bean;

import java.io.Serializable;

import com.google.maps.model.GeocodingResult;

public class GoogleGeocodingResponse {
    
    private String status;
    private GeocodingResult[] results;
    private String error_message;
    
    public GoogleGeocodingResponse() {
        
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public GeocodingResult[] getResults() {
        return results;
    }
    public void setResults(GeocodingResult[] results) {
        this.results = results;
    }
    public String getError_message() {
        return error_message;
    }
    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
