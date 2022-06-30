<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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

<style>
input[type=text], [type=date], select {
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
</style>

</head>
<body>
	<header>
		<div class="navbar">
			<a href="Home.jsp">Home</a> <a href="ProfileServlet">Profile</a> <a
				href="Statistics.html">Statistics</a> <a href="Logout.html">Logout</a>
		</div>
	</header>
	<div class="main">
		<p>Some text some text some text some text..</p>
	</div>

	<h1>Welcome Home</h1>

	<form action="CategorySaveServlet" method="post">
		<div class="container">
			<h2>Create Expense Category</h2>
			<label for="category"><b>Category</b></label> <input type="text"
				placeholder="Enter Category" name="category" required> <input
				type="submit" value="Save">

		</div>
	</form>

	<form action="CategoryServlet" method="get">
	<div class="container">
    Select a Category:&nbsp;
    <select name="category">
        <c:forEach items="${listCategory}" var="category">
            <option value="${category.categoryId}">${category.categoryName}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Get My Categories" />
    </div>
	</form>

	<form action="RecordServlet" method="post">
		<div class="container">
			<h2>Make an Expense Record</h2>
			<label for="record"><b>Record</b></label> <input type="text"
				placeholder="Enter Expense Record" name="income" required> <label
				for="birthday">Date</label> <input type="date" id="date" name="date">

			<input type="submit" value="Submit">
		</div>
	</form>
</body>
</html>

