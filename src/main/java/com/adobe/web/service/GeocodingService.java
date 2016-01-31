package com.adobe.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.adobe.web.bean.GoogleGeocodingResponse;
import com.adobe.web.constants.ServiceConstants;
import com.google.gson.Gson;

public class GeocodingService {
    
    private static volatile int requestCount = 0;
    
    public synchronized GoogleGeocodingResponse getGeoAddress(String address) throws IOException, InterruptedException, SocketTimeoutException
    {
        // Pausing the service call for every 10 api calls.
        if(requestCount > 9) {
            Thread.sleep(ServiceConstants.GEOCODING_REQUEST_PAUSE_TIME);
            requestCount = 0;
        }
        Gson gson = new Gson();
        String jsonResult = getGoogleVerifiedAddress(URLEncoder.encode(address, "UTF-8"));
        requestCount ++;
        GoogleGeocodingResponse result = gson.fromJson(jsonResult, GoogleGeocodingResponse.class);
        return result;
    }

    private synchronized String getGoogleVerifiedAddress(String address) throws MalformedURLException, IOException, SocketTimeoutException 
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
