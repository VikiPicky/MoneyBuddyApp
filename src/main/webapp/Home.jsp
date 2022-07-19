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
<title>MoneyBuddy Home</title>

<link rel="stylesheet" href="css/style.css" />
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
			<a href="ProfileServlet">Profile</a> 
			<a href="/TestMaven/Record/listRecord">Records</a>
			
			<a href="Statistics.jsp">Statistics</a> 
			<a href="Logout.html">Logout</a>
		</div>
	</header>


	<div class="main">

	<br>
	<h1 style="width: 70%; margin: auto">Make an Expense Record</h1>

	<form action="Record/insertRecord" method="post">
	
		<div class="container" class="RecordForm" style="width: 70%; margin: auto">		
			
	<label for="category">Choose a category</label>		
	<select name="category" id="categories" name="category">
    <option value="utilities">Utilities</option>
    <option value="housing">Housing</option>
    <option value="transportation">Transportation</option>
    <option value="food">Food</option>
    <option value="clothing">Clothing</option>
    <option value="medical">Medical</option>
    <option value="insurance">Insurance</option>
    <option value="household">Household Items</option>
    <option value="personal">Personal</option>
    <option value="entertainment">Entertainment </option>
    <option value="other">Other </option>
  </select>
  <br><br>
  
	<label for="record"><b>Record</b></label> 
	<input type="text" placeholder="Enter Name" name="record" required> 
	
	<label for="amount"><b>Amount</b></label> 
	<input type="number" placeholder="Enter Amount" name="amount" required min="0" step=".01"> 
	
	<label for="date">Date</label> 
	<input type="date" id="date" name="date">
	
	<label for="taxAmount"><b>Tax Amount</b></label> 
	<input type="number" placeholder="Enter Tax Amount" name="taxAmount" min="0" step=".01" required> 
	
	<label for="comment">Comment</label> 
	<input type="text" id="comment" name="comment">

	<input type="submit" value="Save">
	</div>
	</form>
	
	<br>
	<br>      
       
	</div>
</body>
</html>

