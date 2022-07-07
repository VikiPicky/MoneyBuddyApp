<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Money Buddy List of Users</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<header>

	<div class="navbar">
	<h2> MoneyBuddy User Management App </a>
	<a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a>
	</div>
	</header>
            <br>

            
            <div class="row">
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>User Name</th>
						<th>Password</th>
						<th>Email</th>
						<th>Telephone</th>
						<th>Admin</th>
						<th>Active</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="userBean" items="${listUser}">

						<tr>
							<td><c:out value="${userBean.userID}" /></td>
							<td><c:out value="${userBean.firstName}" /></td>
							<td><c:out value="${userBean.lastName}" /></td>
							<td><c:out value="${userBean.userName}" /></td>
							<td><c:out value="${userBean.password}" /></td>
							<td><c:out value="${userBean.email}" /></td>
							<td><c:out value="${userBean.telephone}" /></td>
							<td><c:out value="${userBean.admin}" /></td>
							<td><c:out value="${userBean.active}" /></td>
							<td><a href="edit?userid=<c:out value='${userBean.userID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="delete?userid=<c:out value='${userBean.userID}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
            
</body>
</html>