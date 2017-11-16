<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
		crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		<link rel="stylesheet" href=css/Sidebar.css>
		
	<script>
		$(function() {
			$("#tabs").tabs();
		});
	</script>
	
	<script>
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
			});
		});
	</script>	
	<title>Admin Page</title>
</head>
<body>
	<div class="container">
		<div>
			<h1>${username} Admin page</h1>
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
				<h1>Welcome to the admin page, ${username}.</h1>
				<h1>user position: <%= userposition%></h1>
			</div>
		</div>
	</div>
</body>
</html>