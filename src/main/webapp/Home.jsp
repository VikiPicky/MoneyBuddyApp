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

<link rel="stylesheet" href="css/Home_style.css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
input[type=text], [type=date], [type=number],select {
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

body {
margin:0;

}

#RecordTable {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
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


</style>

</head>
<body>
	<header>
		<div class="navbar">
			<a href="Home.jsp">Home</a> 
			<a href="ProfileServlet">Profile</a> 
			<a href="Statistics.jsp">Statistics</a> 
			<a href="Logout.html">Logout</a>
		</div>
	</header>
	<div class="main">

	<br>
	<h1 style="width: 70%; margin: auto">Make an Expense Record</h1>


	<form action="RecordServlet" method="post" >
	
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
	<input type="number" placeholder="Enter Tax Amount" name="taxAmount" min="0" step=".01"> 
	
	<label for="comment">Comment</label> 
	<input type="text" id="comment" name="comment">

	<input type="submit" value="Save">
	</div>
	</form>
	
	<br>
	<br>
	
	<%
try
{       
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/moneybuddy","root","");
        String sql = "SELECT Category, Record, Amount, Date, Comment, TaxAmount from RECORD where userid=?";
        
        PreparedStatement preparedStatement = con.prepareStatement(sql);
		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
        preparedStatement.setInt(1, userBean.getUserID());
	
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()==false)
        {
            out.println("No Records in the table");
        }
        else
        {%>
       
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
        <%
            do
            {%>
           
            <tr>
            <td><%= rs.getString(1)%></td>
            <td><%= rs.getString(2)%></td>
            <td><%= rs.getDouble(3)%></td>
            <td><%= rs.getString(4)%></td>
            <td><%= rs.getString(5)%></td>
            <td><%= rs.getDouble(6)%></td>
            <td>Edit</td>
            <td>Delete</td></tr>
           
            <%}while(rs.next());
        }       
}
catch(Exception e)
{
    System.out.println(e.getMessage());
    e.getStackTrace();
}
%>
</table>
	</div>
</body>
</html>
