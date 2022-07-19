<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	 <%@ page import="java.sql.*" %>
	 <%@ page import="com.registration.model.UserBean" %>
	 <%@ page import="com.core.model.StatisticsBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistics</title>

<link rel="stylesheet" href="css/Statistics_style.css" />
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900"
	rel="stylesheet" />
	
	
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

</div>
<br>
<br>

<h2>Total by Category</h2>
<%
try
{       
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/moneybuddy","root","");
        String sql = "Select category, sum(amount), sum(taxamount) from RECORD  where userid=? group by category;";
        
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
       
        <table id=TotalByCategoryTable border="1">
        <tr>
        <th>Category</th>
        <th>Total Expense</th>
        <th>Total Tax</th>
        </tr>
        <%
            do
            {%>           
            <tr>
            <td><%= rs.getString(1)%></td>
            <td><%= rs.getDouble(2)%></td>
            <td><%= rs.getDouble(3)%></td>
          
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
</body>
</html>