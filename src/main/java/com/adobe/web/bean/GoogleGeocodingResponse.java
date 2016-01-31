package com.adobe.web.bean;

public class GoogleGeocodingResponse 
{
    private GeocodingResult[] results;

    private String status;
    
    private String error_message;
    
    public GoogleGeocodingResponse() {
        
    }
   

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public GeocodingResult[] getResults ()
    {
        return results;
    }

    public void setResults (GeocodingResult[] results)
    {
        this.results = results;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
