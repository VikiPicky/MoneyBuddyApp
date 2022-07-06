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
<%-- 				<c:if test="${userBean != null}"> --%>
<!-- 					<form action="/update" method="post"> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${userBean == null}"> --%>
					<form action="/TestMaven/insert" method="post">
<%-- 				</c:if> --%>

				<caption>
					<h2>
<%-- 						<c:if test="${userBean != null}">Edit User</c:if> --%>
						<c:if test="${userBean == null}">Add New User</c:if>
					</h2>
				</caption>

				<c:if test="${userBean != null}">
					<input type="hidden" name="userid" value="<c:out value='${userBean.userid}' />" />
				</c:if>

					<fieldset class="form-group">
					<label>First Name</label> 
					<input type="text" value="<c:out value='${userBean.firstName}' />" class="form-control" name="firstName" required="required">
					</fieldset>

					<fieldset class="form-group">
					<label>Last Name</label> 
					<input type="text" value="<c:out value='${userBean.lastName}' />" class="form-control" name="lastName" required="required">
					</fieldset>

					<fieldset class="form-group">
					<label>User Name</label> 
					<input type="text" value="<c:out value='${userBean.userName}' />" class="form-control" name="userName" required="required">
					</fieldset>
				
					<fieldset class="form-group">
					<label>Email</label> 
					<input type="text" value="<c:out value='${userBean.email}' />" class="form-control" name="email" required="required">
					</fieldset>
				
					<fieldset class="form-group">
					<label>Telephone</label> 
					<input type="text" value="<c:out value='${userBean.telephone}' />" class="form-control" name="telephone" required="required">
					</fieldset>
					
		
				<button type="submit" class="btn btn-success">Save and Email</button>
				</form>
			</div>
		</div>
	</div>


</body>
</html>