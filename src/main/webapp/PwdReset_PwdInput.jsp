<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Reset</title>

<link rel="stylesheet" href="css/PwdResetPwdInput_style.css" />
</head>
<body>

	<h1>Create new Password</h1>

<form action="PwdResetInput_type" method="post">
		<div class="container">
		
		
		
	<label for="email"><b>Email</b></label> 
	<input type="text" placeholder="Confirm Registered Email" name="email" required> 

	<label for="password"><b>Password</b></label> 
	<input type="password" placeholder="Enter NEW Password" name="password" required>
	
	<label for="password2"></label> 
	<input type="password" placeholder="Confirm Password" name="password2" id="pwd2" >

			
		
	<button type="submit">Save</button>

		</div>
	</form>

	<div class="container" style="background-color: #f1f1f1">
		<a href="index.html" class="button btn-white btn-animated">Home</a>
	</div>
	
</body>
</html>