<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//session = request.getSession();
// String email ="";
// email= session.getAttribute("session_user").toString();
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MoneyBuddy Home</title>

<link rel="stylesheet" href="css/Home_style.css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<header>
		<div class="navbar">
			<a href="Home.html">Home</a> <a href="Profile.html">Profile</a> <a
				href="Statistics.html">Statistics</a> <a href="Logout.jsp">Logout</a>
		</div>
	</header>
	<div class="main">
		<p>Some text some text some text some text..</p>
	</div>

	<h1>Welcome Home</h1>

	<form action="IncomeServlet" method="post">
		<div class="container">


			<label for="income"><b>Income</b></label> <input type="text"
				placeholder="Enter Income" name="income" required>

			<button type="submit">Save</button>
		</div>
		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
		</div>
	</form>

	<form action="CategoryServlet" method="post">
		<div class="container">


			<label for="category"><b>Category</b></label> <input type="text"
				placeholder="Enter Category" name="category" required>

			<button type="submit">Save</button>
		</div>
		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
		</div>
	</form>

</body>
</html>