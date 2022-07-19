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

<style type="text/css">

body {
margin:0;

}

#RecordTable {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 70%;
  margin: auto;
  margin-top: 50px;
}

#RecordTable td, #RecordTable th {
  border: 1px solid #ddd;
  padding: 8px;
}
#RecordTable tr:hover {background-color: #ddd;}

#RecordTable th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
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


</style>
<title>MoneyBuddy Record Update</title>

<link rel="stylesheet" href="css/style.css" />
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

 <table id=RecordTable border="1">
        <tr>
        <th>Category</th>
        <th>Record</th>
        <th>Amount</th>
        <th>Date</th>
        <th>Comment</th>
        <th>TaxAmount</th>
        <th>Edit</th>
        <th>Delete</th>
        </tr>
        <tbody>
        <c:forEach var="recordBean" items="${listRecord}">

						<tr>
							<td><c:out value="${recordBean.category}" /></td>
							<td><c:out value="${recordBean.record}" /></td>
							<td><c:out value="${recordBean.amount}" /></td>
							<td><c:out value="${recordBean.date}" /></td>
							<td><c:out value="${recordBean.comment}" /></td>
							<td><c:out value="${recordBean.taxAmount}" /></td>
							<td><a href="editRecord?recordId=<c:out value='${recordBean.recordId}' />">Edit</a></td>
							<td><a href="deleteRecord?recordId=<c:out value='${recordBean.recordId}' />">Delete</a></td>							
						</tr>
					</c:forEach>
        </tbody>

</table>

</body>
</html>