<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	 <%@ page import="java.sql.*" %>
	 <%@ page import="com.registration.model.UserBean" %>
	 <%@ page import="com.core.model.RecordBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MoneyBuddy Record Update</title>



<style type="text/css">

body {
margin:0;

}

input[type=text], [type=date], [type=number],select {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-style: solid;
	margin-top: 30px;
	
}

.main {
  margin-top: 35px; /* Add a top margin to avoid content overlay */
}
/* The navigation bar */
.navbar {
  overflow: hidden;
  background-color: #333;
  position: fixed; /* Set the navbar to fixed position */
  top: 0; /* Position the navbar at the top of the page */
  width: 100%; /* Full width */
}

/* Links inside the navbar */
.navbar a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change background on mouse-over */
.navbar a:hover {
  background: #ddd;
  color: black;
}

h2 {
margin-top: 50px;
}

</style>

</head>
<body>

	<header>
		<div class="navbar">
			<a href="/TestMaven/Home.jsp">Home</a> 
			<a href="/TestMaven/ProfileServlet">Profile</a>
			<a href="/TestMaven/Record/listRecord">Records</a>
			 <a href="/TestMaven/Statistics.jsp">Statistics</a> 
			<a href="/TestMaven/Logout.html">Logout</a>
		</div>
	</header>

<form action="updateRecord" method="post">

				<h2>Update Record</h2>
	

				<c:if test="${record != null}">
					<input type="hidden" name="recordId" value="<c:out value='${record.recordId}' />" />
				</c:if>
				
					<fieldset class="form-group">
					<label>Record</label> 
					<input type="text" value="<c:out value='${record.record}' />" class="form-control" name="record" required="required">
					</fieldset>

					<fieldset class="form-group">
					<label>Amount</label> 
					<input type="text" value="<c:out value='${record.amount}' />" class="form-control" name="amount" required="required">
					</fieldset>
				
		
					<fieldset class="form-group">
					<label>Comment</label> 
					<input type="text" value="<c:out value='${record.comment}' />" class="form-control" name="comment">
					</fieldset>
					
					<fieldset class="form-group">
					<label>Tax Amount</label> 
					<input type="text" value="<c:out value='${record.taxAmount}' />" class="form-control" name="taxAmount">
					</fieldset>
					
		
				<button type="submit" class="btn btn-success">Save</button>
				</form>
</body>
</html>