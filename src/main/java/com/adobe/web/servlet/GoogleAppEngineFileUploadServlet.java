package com.adobe.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.adobe.web.bean.Address;
import com.adobe.web.constants.ErrorMessages;
import com.adobe.web.constants.ServiceConstants;
import com.adobe.web.handler.FileUploadDownloadHandler;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class GoogleAppEngineFileUploadServlet extends HttpServlet {
    
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    private FileUploadDownloadHandler handler = null;
    private List<String> errorMessages = null;
    
    public GoogleAppEngineFileUploadServlet() {
        handler = new FileUploadDownloadHandler();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
        List<BlobKey> blobKeys = blobs.get("myFile");
        
        if (blobKeys != null && !blobKeys.isEmpty()) {
            BlobKey blobKey = new BlobKey(blobKeys.get(0).getKeyString());
            BlobstoreInputStream bis = new BlobstoreInputStream(blobKey);
            String filename = "myFile.xlsx";
            try {
                uploadDocumentStream(getBytes(bis), filename);
                downloadDocumentStream(request, filename);
                request.setAttribute("message", "File Uploaded Successfully");
            }
            catch(IOException e) {
                    errorMessages.add(ErrorMessages.FILE_UPLOAD_EXCEPTION_MSG);
            }
            catch(Exception e) {
                errorMessages.add(ErrorMessages.FILE_UPLOAD_EXCEPTION_MSG);
            }
        }
        else 
            errorMessages.add(ErrorMessages.INVALID_CONTENT_TYPE);
        
        request.setAttribute("errorMessages", errorMessages);
        request.getRequestDispatcher("/fileupload.jsp").forward(request, response);
    }
    
    public static byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int len;
        byte[] data = new byte[1024];
        while ((len = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, len);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
    

    
    public void uploadDocumentStream(byte[] content, String fileName) throws URISyntaxException, IOException, GeneralSecurityException
    {
        Map<String, String> serviceErrorCodesMap = new HashMap<String, String>();
        handler.modifyAndUploadExcelToCloud(content, fileName, serviceErrorCodesMap);
        if(serviceErrorCodesMap.size() > 0) {
            for (Map.Entry<String,String> entry : serviceErrorCodesMap.entrySet()) {
                errorMessages.add(entry.getKey()+" - "+entry.getValue());
            }
        }
    }
    
    public void downloadDocumentStream(HttpServletRequest request, String fileName) throws URISyntaxException, IOException, GeneralSecurityException
    {
        List<Address> addressList = handler.downloadExcelFromCloud(fileName);
        request.setAttribute("addressList", addressList);
    }

}
