<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Excel Upload</title>
    </head>
 
    <body> 
        <div id="header"><div id="title"><b>Adobe</b> Case Study</div></div>
        <div id="upload">
            <div id="form">
	            <h3 id="text"> Choose Excel File (xlsx) to Upload </h3>
	            <form action="uploadFile" method="post" enctype="multipart/form-data">
	               <!--  <label  class="custom-file-input">
	                   <input type="file" id="fileInput"/>
	                </label><label id="filename">No file selected</label><br> -->
	                 <input type="file" name="file"/><br>
	                <input type="submit" class="button" value="upload" />
	            </form>
            </div>          
        </div>
    </body>
</html>