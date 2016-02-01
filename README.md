# Adobe Case Study


<b>View the application online:</b>

http://adobecasestudy.appspot.com/

You can access the application via google cloud store from the above link. Hopefully the application should be up and running. The app engine quotas will reset sometimes since it is a free app engine space. So if the application is not running, you can copy the code and run it in your own server.

<b>Note:</b> <u style="text-decoration: underline;">Please upload excel sheet with 10 or fewer records for successful response, because the free version of Geocoding API accepts only 10 requests per second</u>. Otherwise the application still works but only some records will be updated with the google verified address.<br>
Since the application is deployed in free google app engine, it results in poor performance because of the file upload process and wait time issues of Geocoding API call from this free app engine.<br>
However you can upload larger excel sheet and the application works perfectly if you deploy the code in your local server
<br><br>

-------------------

<b>Google Cloud Storage Location:</b>

Files will be stored in my public cloud

https://console.cloud.google.com/m/cloudstorage/b/adobecasestudy1/o/YOUR_FILE_NAME

Below are the files in cloud which i used for testing and have the Google Verified Address

https://console.cloud.google.com/m/cloudstorage/b/adobecasestudy1/o/Small_Excel_File.xlsx
https://console.cloud.google.com/m/cloudstorage/b/adobecasestudy1/o/Large_Excel_File.xlsx

<br><br>

-------------------


<b>Restrictions:</b>

1) Google Geocoding API is subject to a query limit of 2,500 geolocation requests per day and 10 requests per second. So It would be better to test the application with fewer records in excel sheet. Otherwise the Geocode API returns limit exceeded error. Also because of the response time of Geocoding API, the application takes time to update the excel sheet with latitude and longitude

2) The application only accepts xlsx files

<br><br>

-------------------

<b>Technology Used:</b>

1. Java & J2EE (Servlets & JSP)
2. Maven - for build
3. OAuth2 for Google Cloud Authentication
4. Google Cloud Storage API
5. Google Geocoding API
6. Apache POI for reading and writing excel documents.



<br><br>


-------------------


<b>Assumptions:</b>

1) The columns in the excel sheet and their positions are fixed and will not change.














