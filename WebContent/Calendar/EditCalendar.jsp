<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Calendar</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form class="form-horizontal" method="get" action="EditCalendar">
			<div class="form-group">
				<label class="col-sm-2 control-label">Calendar Id:</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="Cal-id" value="${cid}">
				</div>	
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Calendar Name:</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="CalName" value="${name}">
				</div>	
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-2 control-label">Admin users:</label>
				<c:if test="${not empty EventError }">
					<h9 style="color:RED;">*${EventError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="AdminUser" value="">
					<c:forEach items="${admins}" var="admin">
						<input class="form-control" type="text" name="AdminUser" value="${admin}">
					</c:forEach>
					
				</div>	
			</div>	
			<br>
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Edit">
			</div>	
		</form>
	</div>
</body>
</html>