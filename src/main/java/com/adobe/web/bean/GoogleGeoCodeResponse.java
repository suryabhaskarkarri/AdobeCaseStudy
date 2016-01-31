package com.adobe.web.bean;

import java.io.Serializable;

public class GoogleGeoCodeResponse implements Serializable {
    
    private String status;
    private results[] results;
    private String error_message;

    public GoogleGeoCodeResponse() {
        super();
    }

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

    public class results {
        private String formatted_address;
        private geometry geometry;
        private String[] types;
        private address_component[] address_components;
        public results() {
            
        }
        
        public String getFormatted_address() {
            return formatted_address;
        }
        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }
        public geometry getGeometry() {
            return geometry;
        }
        public void setGeometry(geometry geometry) {
            this.geometry = geometry;
        }
        public String[] getTypes() {
            return types;
        }
        public void setTypes(String[] types) {
            this.types = types;
        }
        public address_component[] getAddress_components() {
            return address_components;
        }
        public void setAddress_components(address_component[] address_components) {
            this.address_components = address_components;
        }
    }

    public class geometry {
        private bounds bounds;
        private String location_type;
        private location location;
        private bounds viewport;
        private geometry() {
            
        }
        public bounds getBounds() {
            return bounds;
        }
        public void setBounds(bounds bounds) {
            this.bounds = bounds;
        }
        public String getLocation_type() {
            return location_type;
        }
        public void setLocation_type(String location_type) {
            this.location_type = location_type;
        }
        public location getLocation() {
            return location;
        }
        public void setLocation(location location) {
            this.location = location;
        }
        public bounds getViewport() {
            return viewport;
        }
        public void setViewport(bounds viewport) {
            this.viewport = viewport;
        }
    }

    public class bounds {
        private location northeast;
        private location southwest;
        public bounds() {
            
        }
        public location getNortheast() {
            return northeast;
        }
        public void setNortheast(location northeast) {
            this.northeast = northeast;
        }
        public location getSouthwest() {
            return southwest;
        }
        public void setSouthwest(location southwest) {
            this.southwest = southwest;
        }
    }

    public class location {
        private String lat;
        private String lng;
        private location() {
            
        }
        public String getLat() {
            return lat;
        }
        public void setLat(String lat) {
            this.lat = lat;
        }
        public String getLng() {
            return lng;
        }
        public void setLng(String lng) {
            this.lng = lng;
        }
    }

    public class address_component {
        private String long_name;
        private String short_name;
        private String[] types;
        private address_component() {
            
        }
        public String getLong_name() {
            return long_name;
        }
        public void setLong_name(String long_name) {
            this.long_name = long_name;
        }
        public String getShort_name() {
            return short_name;
        }
        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }
        public String[] getTypes() {
            return types;
        }
        public void setTypes(String[] types) {
            this.types = types;
        }
    }
}
