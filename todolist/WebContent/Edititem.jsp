<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="NavBar.jsp"/>
<body>
	<div class="container">
		<h3>Edit item</h3>
		<br />
		<form class="form-horizontal" role="form" name="AdditemForm"
			id="AdditemForm" action="Updateitem" method="post">
			<input hidden action="isEdit" value="true"/> 
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Due date</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="duedate" id="duedate"
						placeholder="Enter due date">
				</div>
			</div>
				<div class = "form-group">
				<label class="control-label col-sm-2" for="email">Status</label> 
				<div class="col-sm-10">
				<select
					name="status">
					<option name="status" value="1">Not Started</option>
					<option name="status" value="2">In Progress</option>
					<option name="status" value="3">Completed</option>
				</select>
			</div>
			</div>
			
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default" name="SignupSub"
				id="SignupSub">Edit</button>
		</div>
	</div>
	</form>
	</div>
	
	${temp}
</body>
</html>