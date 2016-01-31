package com.adobe.web.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.adobe.web.bean.GoogleGeocodingResponse;
import com.adobe.web.constants.ServiceConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;

public class GoogleCloudStorageService {
    
    private static final List<String> SCOPES = Arrays.asList(ServiceConstants.STORAGE_SCOPE);    
    private static Storage storageService;
    
    public byte[] getFileFromGoogleCloud(String fileName) throws URISyntaxException, IOException, GeneralSecurityException
    {
        InputStream in = null;
        ByteArrayOutputStream bOut = null;
        byte[] content = null;
        try {
            Storage client = getService();
            Storage.Objects.Get file = client.objects().get(ServiceConstants.BUCKET_NAME, fileName);
            in = file.executeMediaAsInputStream();
            bOut = new ByteArrayOutputStream();
            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                bOut.write(data, 0, count);
            }
            bOut.flush();
            content = bOut.toByteArray();
        }
        finally {
            if(null != bOut)
                bOut.close();
            if(null != in)
                in.close();
        }
        return content;
    }

    public void uploadDocumentStream(byte[] content, String fileName) throws URISyntaxException, IOException, GeneralSecurityException
    {
        InputStreamContent contentStream = new InputStreamContent(ServiceConstants.CONTENT_TYPE, new ByteArrayInputStream(content));
        StorageObject objectMetadata = new StorageObject().setName(fileName).setAcl(Arrays.asList(new ObjectAccessControl().setEntity("allUsers").setRole("READER")));
        Storage client = getService();
        Storage.Objects.Insert insertRequest = client.objects().insert(ServiceConstants.BUCKET_NAME, objectMetadata, contentStream);
        insertRequest.execute();
    }


    private  Storage getService() throws URISyntaxException, IOException, GeneralSecurityException 
    {
        if (null == storageService) {
            GoogleCredential credential = getCredentials();
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
            storageService = new Storage.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(ServiceConstants.GOOGLE_CLOUD_APP_NAME).build();
        }
        return storageService;
    }


    private GoogleCredential getCredentials() throws GeneralSecurityException, IOException, URISyntaxException 
    {
        JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        GoogleCredential credential = new GoogleCredential.Builder()
                                        .setTransport(httpTransport)
                                        .setJsonFactory(JSON_FACTORY)
                                        .setServiceAccountId(ServiceConstants.CLIENT_ID)
                                        .setServiceAccountPrivateKeyFromP12File(new File(getClass().getClassLoader().getResource(ServiceConstants.P12FILE).getFile()))
                                        .setServiceAccountScopes(SCOPES).build();
        return credential;
    }

}
