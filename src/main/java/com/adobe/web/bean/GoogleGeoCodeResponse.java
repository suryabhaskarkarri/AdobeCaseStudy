package com.adobe.web.bean;

public class GoogleGeoCodeResponse {
    private String status;
    private results[] results;
    private String error_message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public results[] getResults() {
        return results;
    }

    public void setResults(results[] results) {
        this.results = results;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public GoogleGeoCodeResponse() {
    }

    public class results {
        public String formatted_address;
        public geometry geometry;
        public String[] types;
        public address_component[] address_components;
    }

    public class geometry {
        public bounds bounds;
        public String location_type;
        public location location;
        public bounds viewport;
    }

    public class bounds {

        public location northeast;
        public location southwest;
    }

    public class location {
        public String lat;
        public String lng;
    }

    public class address_component {
        public String long_name;
        public String short_name;
        public String[] types;
    }
}
