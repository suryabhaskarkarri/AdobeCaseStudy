<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adobe Case Study</title>
    </head>
 
    <body> 
        <h1>Adobe Case Study</h1>
        <div>
            <h3> Choose File to Upload in Server </h3>
            <form action="uploadFile" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>          
        </div>
    </body>
</html>