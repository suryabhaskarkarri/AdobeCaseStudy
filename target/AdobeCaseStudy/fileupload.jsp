<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Excel Upload Result</title>
	</head>
	 
	<body>
	   <div id="header"><div id="title"><b>Adobe&nbsp;</b>Case Study</div></div><br>
	    <center>
	        <br/>
	        <h2 style="color:green"> ${requestScope.message}</h2> 
	        <br> 
	        <c:if test="${requestScope.filePath != null}">
	           <a href="${requestScope.filePath}">Download from cloud</a>
	        </c:if>
	    </center>
	    <c:if test="${requestScope.errorMessages != null && not empty requestScope.errorMessages}">
	            <br><div style="color: red"><h3>Error Messages</h3>
	            <ul>
	                <c:forEach items="${requestScope.errorMessages}" var="element"> 
	                      <li>${element}</li>
	                </c:forEach>
	            </ul></div>
	    </c:if>
	    <c:if test="${requestScope.addressList != null}">
	        <br>
		    <table id="geoLocatedAddresses">
			    <tr>
			        <th>Address</th>
			        <th>City</th>
			        <th>County</th>
			        <th>Postal Code</th>
			        <th>State Province</th>
			        <th>Country</th>
			        <th>Google Verified Address</th>
			        <th>Latitude</th>
			        <th>Longitude</th>
			    </tr>
			    <c:forEach items="${requestScope.addressList}" var="element"> 
				  <tr>
				    <td>${element.address}</td>
				    <td>${element.city}</td>
				    <td>${element.county}</td>
				    <td>${element.postalCode}</td>
				    <td>${element.stateProvince}</td>
				    <td>${element.country}</td>
				    <td>${element.googleVerifiedAddress}</td>
				    <td>${element.latitude}</td>
				    <td>${element.longitude}</td>
				  </tr>
				</c:forEach>
		    </table>
	    </c:if>
	</body>
</html>