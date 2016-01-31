package com.adobe.web.constants;

public class ErrorMessages {
    
    public static final String INVALID_CONTENT_TYPE = "The file is invalid or corrupted. Only xlsx file can be uploaded";
    
    public static final String INVALID_FILE = "Invalid file. Please choose a valid xlsx file to upload";
    
    public static final String FILE_UPLOAD_EXCEPTION_MSG = "File Upload failed. Please upload a valid xlsx file";
    
    public static final String GEO_CODE_TIME_OUT_MSG_KEY = "GEOCODING_TIMEOUT_ERROR";
    
    public static final String GEO_CODE_TIME_OUT_MSG_VALUE = "Socket time out while querying Geocode API";
    
    public static final String GEO_CODE_ERROR_MSG_KEY = "GEOCODING_ERROR";
    
    public static final String GEO_CODE_ERROR_MSG_VALUE = "Error querying the Geocode API";
    
    public static final String EXCEL_ERROR_MSG_KEY = "EXCEL_ERROR";
    
    public static final String EXCEL_ERROR_MSG_VALUE = "The file is invalid or corrupted. Error creating excel workbook from the file.";
    
    public static final String EXCEL_UPLOAD_ERROR_MSG_KEY = "EXCEL_UPLOAD_ERROR";
    
    public static final String EXCEL_UPLOAD_ERROR_MSG_VALUE = "Error uploading the excel sheet to public google cloud";
    
    public static final String EXCEL_DOWNLOAD_ERROR_MSG_KEY = "EXCEL_DOWNLOAD_ERROR";
    
    public static final String EXCEL_DOWNLOAD_ERROR_MSG_VALUE = "Error reading the modified excel sheet from public google cloud";

}
