<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String check = (String) session.getAttribute("Username");
	String name = "";
	String userposition = "";

	if (check != null) {
		name = (String) session.getAttribute("Username");
		userposition = (String) session.getAttribute("Userpos");
	} else {
		response.sendRedirect("../Index.jsp");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>New Calendar</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	
</head>
<body>

	<div>
		<div class="page-header">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<%
					if (userposition.equals("A")) {
				%>
				<%@include file="/inc/inc_admin_nav.jsp"%>
				<%
					} else {
				%>
				<%@include file="/inc/inc_user_nav.jsp"%>
				<%
					}
				%>
			</div>
			</nav>
		</div>
	</div>

	<div class="container">
		<form class="form-horizontal" method="post" action="../CreateCalander">
			<div class="form-group">
				<label class="col-sm-2 control-label">Calendar Name:</label>
				<c:if test="${not empty CalError }">
					<h9 style="color:RED;">*${CalError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="CalName" value="${param.CalName}">
				</div>	
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-2 control-label">Admin user:</label>
				<c:if test="${not empty EventError }">
					<h9 style="color:RED;">*${EventError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="AdminUser" value="${param.AdminUser}">
				</div>	
			</div>	
			<br>
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Create">
			</div>	
		</form>
	</div>
</body>
</html>