package com.adobe.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.adobe.web.bean.Address;
import com.adobe.web.constants.ErrorMessages;
import com.adobe.web.constants.ServiceConstants;
import com.adobe.web.handler.FileUploadDownloadHandler;

public class FileUploadServlet extends HttpServlet {

    private FileUploadDownloadHandler handler = null;
    private List<String> errorMessages = null;
    
    public FileUploadServlet() {
        handler = new FileUploadDownloadHandler();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        errorMessages = new ArrayList<String>();
        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iterator = upload.getItemIterator(request);
                if(iterator.hasNext()) {
                    FileItemStream item = iterator.next();
                    if(validateFile(item)) {
                        InputStream stream = item.openStream();
                        uploadDocumentStream(getBytes(stream), item.getName());
                        downloadDocumentStream(request, item.getName());
                        request.setAttribute("message", "File Uploaded Successfully"); 
                        request.setAttribute("filePath", ServiceConstants.CLOUD_STORAGE_PATH+item.getName());
                    }
                }
                else 
                    errorMessages.add(ErrorMessages.INVALID_FILE);
            }
            catch(IOException e) {
                errorMessages.add(ErrorMessages.FILE_UPLOAD_EXCEPTION_MSG);
            }
            catch(FileUploadException fue) {
                errorMessages.add(ErrorMessages.FILE_UPLOAD_EXCEPTION_MSG);
            }
            catch(Exception e) {
                //Do nothing. Already handled the error messages in the handler
            }
        }
        else 
            errorMessages.add(ErrorMessages.INVALID_FILE);
        
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
    
    private boolean validateFile(FileItemStream item) 
    {
        if(item.getName().indexOf(ServiceConstants.EXCEL_EXTENSION) < 0) {
            errorMessages.add(ErrorMessages.INVALID_FILE);
            return false;
        }
        if(!item.getContentType().equals(ServiceConstants.CONTENT_TYPE)) {
            errorMessages.add(ErrorMessages.INVALID_CONTENT_TYPE);
            return false;
        }
        return true;
    }
    
    
    public void uploadDocumentStream(byte[] content, String fileName) throws Exception
    {
        Map<String, String> uploadErrorCodesMap = new HashMap<String, String>();
        handler.modifyAndUploadExcelToCloud(content, fileName, uploadErrorCodesMap);
        addServiceCodeErrorMessages(uploadErrorCodesMap);
    }
    
    public void downloadDocumentStream(HttpServletRequest request, String fileName) throws Exception
    {
        Map<String, String> downloadErrorCodesMap = new HashMap<String, String>();
        List<Address> addressList = handler.downloadExcelFromCloud(fileName, downloadErrorCodesMap);
        addServiceCodeErrorMessages(downloadErrorCodesMap);
        request.setAttribute("addressList", addressList);
    }
    
    public void addServiceCodeErrorMessages(Map<String, String> errorCodesMap) {
        if(errorCodesMap.size() > 0) {
            for (Map.Entry<String,String> entry : errorCodesMap.entrySet()) {
                errorMessages.add(entry.getKey()+" - "+entry.getValue());
            }
        }
    }

}
