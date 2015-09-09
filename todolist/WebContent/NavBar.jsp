<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>To Do</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-brand">ToDo!</div>
	<div>
<ul class="nav navbar-nav navbar-right">

<% if (session.getAttribute("user") == null) { %>
<li><a href="signup.jsp">Signup</a></li>
<li><a href="Login.jsp">Login</a></li>
<% } else {%>
<li><a href="Additem.jsp">Add To Do Item</a>
<li><a href="ServletToDoList">View My To Do List</a>
<li><a href="ServeletCompleted">Completed Items</a></li>
<li><a href="ServletLogin?logout=true">Logout</a></li>
<% } %>
</ul>
	</div>
</div>
</nav>
</body>
</html>