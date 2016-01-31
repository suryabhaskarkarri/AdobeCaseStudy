package com.adobe.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.adobe.web.bean.GoogleGeocodingResponse;
import com.adobe.web.constants.ServiceConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class GeoCodingService {

    public GoogleGeocodingResponse getGeoAddress(String address) throws IOException
    {
        Gson gson = new Gson();
        String jsonResult = getGoogleVerifiedAddress(URLEncoder.encode(address, "UTF-8"));
        GoogleGeocodingResponse result = gson.fromJson(jsonResult, GoogleGeocodingResponse.class);
        return result;
    }

    private String getGoogleVerifiedAddress(String address) throws MalformedURLException, IOException 
    {
        String jsonResult = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(ServiceConstants.GEO_CODING_URL + address);
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                jsonResult += inputLine;
            } 
        }
        finally {
            if(null != reader) 
                reader.close();
        }
        return jsonResult; 
    }

}
