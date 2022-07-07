<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MoneyBuddy User Management</title>

 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					<form action="/TestMaven/update" method="post">

				<h2>Update User Record</h2>
	

				<c:if test="${user != null}">
					<input type="hidden" name="userID" value="<c:out value='${user.userID}' />" />
				</c:if>
				
					<fieldset class="form-group">
					<label>First Name</label> 
					<input type="text" value="<c:out value='${user.firstName}' />" class="form-control" name="firstName" required="required">
					</fieldset>

					<fieldset class="form-group">
					<label>Last Name</label> 
					<input type="text" value="<c:out value='${user.lastName}' />" class="form-control" name="lastName" required="required">
					</fieldset>

					<fieldset class="form-group">
					<label>User Name</label> 
					<input type="text" value="<c:out value='${user.userName}' />" class="form-control" name="userName" required="required">
					</fieldset>
				
					<fieldset class="form-group">
					<label>Email</label> 
					<input type="text" value="<c:out value='${user.email}' />" class="form-control" name="email" required="required" >
					</fieldset>
				
					<fieldset class="form-group">
					<label>Telephone</label> 
					<input type="text" value="<c:out value='${user.telephone}' />" class="form-control" name="telephone" required="required">
					</fieldset>
					
		
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>


</body>
</html>