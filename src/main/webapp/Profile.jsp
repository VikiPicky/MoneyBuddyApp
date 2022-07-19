<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>

<link rel="stylesheet" href="css/Profile_style.css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	
</head>
<body>
	<header>
		<div class="navbar">
			<a href="Home.jsp">Home</a> 
			<a href="<%=response.encodeURL("ProfileServlet")%>">Profile</a> 
			<a href="/TestMaven/Record/listRecord">Records</a>
			
			<a href="Statistics.jsp">Statistics</a> 
			<a href="Logout.html">Logout</a>
		</div>
	</header>
	<div class="main">
		<p>Some text some text some text some text..</p>
	</div>


	<jsp:useBean id="user" scope="request"
		type="com.registration.model.UserBean"></jsp:useBean>
	<section id="profile" class="section">
		<div class="container">
			<h2 class="headline">My Profile</h2>
			<table id="profiletb">
				<tr>
					<th>Position</th>
					<th>Value</th>
				</tr>
				<tr>
					<td>User Name</td>
					<td><jsp:getProperty property="userName" name="user" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><jsp:getProperty property="firstName" name="user" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><jsp:getProperty property="lastName" name="user" /></td>
				</tr>
				<tr>
					<td>Telephone</td>
					<td><jsp:getProperty property="telephone" name="user" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><jsp:getProperty property="email" name="user" /></td>
				</tr>

			</table>
		</div>
	</section>
<span class="psw">Reset <a href="PwdReset_Email.html">password?</a></span>

</body>
</html>