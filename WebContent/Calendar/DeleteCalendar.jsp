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
<link rel="shortcut icon" href="favicon.ico"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Remove a Calendar</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<div class="container">
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
		<div>
			<table class="table table-striped table-bordered table-hover table-condensed" border="1">
				<tr>
					<td>Calendar ID</td>
					<td>Calendar Name</td>
					<td>Action</td>
				</tr>
					<c:forEach items="${myCalanders}" var="cal">
						<tr>
							<td>${cal.cid}</td>
							<td><a href=Calander?cid=${cal.cid}>${cal.calName}</a></td>
							<td><a href="DeleteCalendar?id=${cal.cid}">Delete</a></td>
						</tr>			
					</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>