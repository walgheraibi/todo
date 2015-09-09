<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Completed</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="NavBar.jsp"/>
<div class="container">
<table class="table table-bordered table-striped">
<thead>
<tr>
<th width="10%">NUMBER</th>
<th width="50%">DESCRIPTION</th>
<th width="20%">DUE DATE</th>
<th width="20%">DATE COMPLETED</th>
</tr>
</thead>
<tbody>
${todpListMsg}
</tbody>
</table>
</div>
</body>