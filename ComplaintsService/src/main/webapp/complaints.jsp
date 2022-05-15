<%@page import="com.Complaint"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaints Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/complaints.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h2>ElectroGrid Company (pvt).Ltd</h2>
	<h1>Complaints..</h1><br>
<h4>New Complaint</h4>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<form id="formComplaint" name="formComplaint">
 		Electricity Account Number:
 		<input placeholder= "10 digits (eg: xxxxxxxxxx)" id="eNumber" name="eNumber" type="text" class="form-control form-control-sm">
 		<br> 
 		Complaint type:
		<input placeholder= "(eg: Supply failure, Area-Kandy)" id="cType" name="cType" type="text" class="form-control form-control-sm">
 		<br> 
 		Contact Details:
 		<input placeholder= "Mobile Phone Number (eg: 07XXXXXXXX)" id="cDetails" name="cDetails" 
 		type="text" class="form-control form-control-sm">
 		<br> 
 		Attachments:
		<input placeholder= "Anything More to tell?" id="attachments" name="attachments" type="text" class="form-control form-control-sm">
 		<br>
 		
 		<input style="background-color:'red'" id="btnSave" name="btnSave" type="button" value="Lodge Complaint" class="btn btn-primary">
 		<input type="hidden" id="hidComplaintIDSave" name="hidComplaintIDSave" value="">
	</form>
	

	<br>
	
</div> </div> </div><div style=" margin-top:-420px; float:right">
	<h4>Previous Complaints</h4>
	<div id="divComplaintsGrid">
 		<%
 		Complaint complaintObj = new Complaint();
 		 			out.print(complaintObj.readComplaints());
 		%>
	</div></div>
</body>
</html>