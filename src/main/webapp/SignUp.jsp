<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp form MoneyBuddy</title>

<link rel="stylesheet" href="css/SignUp_style.css" />

</head>
<body>

	<form action="SignUpServlet" method="post">

		<div class="container">
			<h1>Sign up to start using MoneyBuddy today!</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>
		
			<label for="firstName"><b>First Name</b></label> 
			<input type="text" placeholder="Enter First Name" name="firstName" id="firstName" > 
				
			<label for="lastName"><b>Last Name</b></label> 
			<input type="text" placeholder="Enter Last Name" name="lastName" id="fn" > 
				
			<label for="userName"><b>UserName</b></label> 
			<input type="text" placeholder="Enter UsereName" name="userName" id="un" > 
				
			<label for="password"><b>Password</b></label> 
			<input type="password" placeholder="Enter Password" name="password" id="pwd" >
			
			<label for="password2"></label> 
			<input type="password" placeholder="Confirm Password" name="password2" id="pwd2" >
			<p>Tip: Password must contain 1 UpperCase Letters, 1 LowerCase Letters and 1 digit</p>
			
			<span style="color:red;">	 <c:out value='${errorNoMatch}' ></c:out>		</span><br>
			
			<span style="color:red;">	 <c:out value='${error}' ></c:out>		</span><br>

			
			<label for="email"><b>Email</b></label> 
			<input type="text" placeholder="Enter Email" name="email" id="em" > 
			
			<label for="telephone"><b>Phone No</b></label> 
			<input type="text" placeholder="Enter Contact No" name="telephone" id="tel" > 
			
			<input type="checkbox" id="admin" name="admin" value="ON">
  			<label for="admin"> Are you Administrator?</label><br>
			
			<hr>
			
			<input type="submit" value="Submit" class="registerbtn" id = "submit">

			<div class="container signin">
				<p>
					Already have an account? <a href="SignIn.html">Sign in</a>.
				</p>
			</div>
		</div>
	</form>
	
</body>

</html>