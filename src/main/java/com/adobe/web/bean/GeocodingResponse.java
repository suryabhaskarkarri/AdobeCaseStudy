package com.adobe.web.bean;

public class GeocodingResponse
{
    private Results[] results;

    private String status;
    
    private String error_message;
   

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
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

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", status = "+status+"]";
    }
    
    public class Results
    {
        private String place_id;

        private Address_Components[] address_components;

        private String formatted_address;

        private String[] types;

        private Geometry geometry;

        public String getPlace_id ()
        {
            return place_id;
        }

        public void setPlace_id (String place_id)
        {
            this.place_id = place_id;
        }

        public Address_Components[] getAddress_components ()
        {
            return address_components;
        }

        public void setAddress_components (Address_Components[] address_components)
        {
            this.address_components = address_components;
        }

        public String getFormatted_address ()
        {
            return formatted_address;
        }

        public void setFormatted_address (String formatted_address)
        {
            this.formatted_address = formatted_address;
        }

        public String[] getTypes ()
        {
            return types;
        }

        public void setTypes (String[] types)
        {
            this.types = types;
        }

        public Geometry getGeometry ()
        {
            return geometry;
        }

        public void setGeometry (Geometry geometry)
        {
            this.geometry = geometry;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [place_id = "+place_id+", address_components = "+address_components+", formatted_address = "+formatted_address+", types = "+types+", geometry = "+geometry+"]";
        }
        
        public class Address_Components
        {
            private String long_name;

            private String[] types;

            private String short_name;

            public String getLong_name ()
            {
                return long_name;
            }

            public void setLong_name (String long_name)
            {
                this.long_name = long_name;
            }

            public String[] getTypes ()
            {
                return types;
            }

            public void setTypes (String[] types)
            {
                this.types = types;
            }

            public String getShort_name ()
            {
                return short_name;
            }

            public void setShort_name (String short_name)
            {
                this.short_name = short_name;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [long_name = "+long_name+", types = "+types+", short_name = "+short_name+"]";
            }
        }
        
        public class Geometry
        {
            private Viewport viewport;

            private String location_type;

            private Location location;

            public Viewport getViewport ()
            {
                return viewport;
            }

            public void setViewport (Viewport viewport)
            {
                this.viewport = viewport;
            }

            public String getLocation_type ()
            {
                return location_type;
            }

            public void setLocation_type (String location_type)
            {
                this.location_type = location_type;
            }

            public Location getLocation ()
            {
                return location;
            }

            public void setLocation (Location location)
            {
                this.location = location;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [viewport = "+viewport+", location_type = "+location_type+", location = "+location+"]";
            }
            
            public class Location
            {
                private String lng;

                private String lat;

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

                @Override
                public String toString()
                {
                    return "ClassPojo [lng = "+lng+", lat = "+lat+"]";
                }
            }
            
            public class Viewport
            {
                private Southwest southwest;

                private Northeast northeast;

                public Southwest getSouthwest ()
                {
                    return southwest;
                }

                public void setSouthwest (Southwest southwest)
                {
                    this.southwest = southwest;
                }

                public Northeast getNortheast ()
                {
                    return northeast;
                }

                public void setNortheast (Northeast northeast)
                {
                    this.northeast = northeast;
                }

                @Override
                public String toString()
                {
                    return "ClassPojo [southwest = "+southwest+", northeast = "+northeast+"]";
                }
                
                public class Southwest
                {
                    private String lng;

                    private String lat;

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

                    @Override
                    public String toString()
                    {
                        return "ClassPojo [lng = "+lng+", lat = "+lat+"]";
                    }
                }
                
                public class Northeast
                {
                    private String lng;

                    private String lat;

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

                    @Override
                    public String toString()
                    {
                        return "ClassPojo [lng = "+lng+", lat = "+lat+"]";
                    }
                }
            }
        }
    }
}
