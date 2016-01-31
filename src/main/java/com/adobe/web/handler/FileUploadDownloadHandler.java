package com.adobe.web.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.adobe.web.bean.Address;
import com.adobe.web.bean.GoogleGeocodingResponse;
import com.adobe.web.constants.ErrorMessages;
import com.adobe.web.constants.ServiceConstants;
import com.adobe.web.service.GeoCodingService;
import com.adobe.web.service.GoogleCloudStorageService;

public class FileUploadDownloadHandler {
    
    private GoogleCloudStorageService storageService;
    private GeoCodingService geoService;
    
    public FileUploadDownloadHandler() {
        storageService = new GoogleCloudStorageService();
        geoService = new GeoCodingService();
    }
    
    public void modifyAndUploadExcelToCloud(byte[] content, String fileName, Map<String, String> serviceErrorCodesMap) throws URISyntaxException, IOException, GeneralSecurityException, SocketTimeoutException
    {
        GeoCodingService geoService = new GeoCodingService();
        ByteArrayInputStream bInput = null;
        ByteArrayOutputStream bOut = null;
        XSSFWorkbook workbook = null;
        try {
            bInput = new ByteArrayInputStream(content);
            workbook = new XSSFWorkbook(bInput);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if(nextRow.getRowNum() == 0) continue;    //Ignore first row as it is a header
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                Cell[] cells = new Cell[9];     // Since there are 9 headers
                int cellCount = 0; 
                while (cellIterator.hasNext()) {
                    cells[cellCount++] = cellIterator.next();
                }
                String address = "";
                for(int i=0; i<6; i++) {
                    switch (cells[i].getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        address = address + cells[i].getStringCellValue() +", ";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        address = address + cells[i].getNumericCellValue() + ", ";
                        break;
                    }
                }
                GoogleGeocodingResponse result = null;
                try {
                    result = geoService.getGeoAddress(address);
                }
                catch(SocketTimeoutException ste) {
                    if(!serviceErrorCodesMap.containsKey(ErrorMessages.TIME_OUT_MSG_KEY))
                        serviceErrorCodesMap.put(ErrorMessages.TIME_OUT_MSG_KEY, ErrorMessages.TIME_OUT_MSG_VALUE);
                }
                catch(Exception e) {
                    if(!serviceErrorCodesMap.containsKey(ErrorMessages.ERROR_MSG_KEY))
                        serviceErrorCodesMap.put(ErrorMessages.ERROR_MSG_KEY, ErrorMessages.ERROR_MSG_VALUE);
                }
                if(result != null && result.getStatus().equals(ServiceConstants.HTTP_STATUS_OK) && result.getResults() != null && result.getResults().length > 0) {
                    Cell cell = nextRow.createCell(6);
                    cell.setCellValue(result.getResults()[0].getFormatted_address());
                    cells[6] = cell;

                    cell = nextRow.createCell(7);
                    cell.setCellValue(result.getResults()[0].getGeometry().getLocation().getLat());
                    cells[7] = cell;

                    cell = nextRow.createCell(8);
                    cell.setCellValue(result.getResults()[0].getGeometry().getLocation().getLng());
                    cells[8] = cell;
                }
                else {
                    if(result.getStatus().equals(ServiceConstants.NON_EXISTENT_ADDRESS_STATUS)) {
                        
                        String val = ServiceConstants.NON_EXISTENT_ADDRESS;
                        
                        Cell cell = nextRow.createCell(6);
                        cell.setCellValue(val);
                        cells[6] = cell;

                        cell = nextRow.createCell(7);
                        cell.setCellValue(val);
                        cells[7] = cell;

                        cell = nextRow.createCell(8);
                        cell.setCellValue(val);
                        cells[8] = cell;
                    }
                    else {
                        String errorCode = result.getStatus();
                        String errorMessage = result.getError_message();
                        if(!serviceErrorCodesMap.containsKey(errorCode))
                            serviceErrorCodesMap.put(errorCode, errorMessage);
                    }
                }
            }
            bOut = new ByteArrayOutputStream();
            workbook.write(bOut);
            storageService.uploadDocumentStream(bOut.toByteArray(), fileName);
        }
        finally {
            if(null != bInput)
                bInput.close();
            if(null != workbook)
                workbook.close();
            if(null != bOut)
                bOut.close();
        }
    }
    
    public List<Address> downloadExcelFromCloud(String fileName) throws URISyntaxException, IOException, GeneralSecurityException
    {
        XSSFWorkbook workbook = null;
        List<Address> addressList = null;
        try {
            byte[] content = storageService.getFileFromGoogleCloud(fileName);
            workbook = new XSSFWorkbook(new ByteArrayInputStream(content));
            
            addressList = new ArrayList<Address>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if(nextRow.getRowNum() == 0) continue;    //Ignore first row as it is a header
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                Cell[] cells = new Cell[9];
                int cellCount = 0; 
                while (cellIterator.hasNext()) {
                    cells[cellCount++] = cellIterator.next();
                }
                Address address = new Address();
                for(int i=0; i<9; i++) {
                    if(cells[i] != null) {
                        switch (cells[i].getColumnIndex()) {
                            case 0: address.setAddress(getCellValue(cells[i])); break;
                            case 1: address.setCity(getCellValue(cells[i])); break;
                            case 2: address.setCounty(getCellValue(cells[i])); break;
                            case 3: address.setPostalCode(getCellValue(cells[i])); break;
                            case 4: address.setStateProvince(getCellValue(cells[i])); break;
                            case 5: address.setCountry(getCellValue(cells[i])); break; 
                            case 6: address.setGoogleVerifiedAddress(getCellValue(cells[i])); break;
                            case 7: address.setLatitude(getCellValue(cells[i])); break;
                            case 8: address.setLongitude(getCellValue(cells[i])); break;
                        }
                    }
                }
                addressList.add(address);
            }
        }
        finally {
            if(null != workbook)
                workbook.close();
        }
        
        return addressList;
    }
    
    
    private String getCellValue(Cell cell) 
    {
        if(cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC: return String.valueOf(cell.getNumericCellValue());
            }
        }
        return null;
    }

}
