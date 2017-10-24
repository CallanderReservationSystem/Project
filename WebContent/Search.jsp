<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1> Search for user</h1>
		</div>
		<div>
			<form action="Search" method="post">
				<input type="text" name="search" value="${param.search}" placeholder="Search">
				<input type="submit" class="btn btn-success" value="Search">
			</form>
		</div>
		<div>
			<c:if test="${not empty notFound }">
				<p style="color: RED;">*${notFound }</p>
			</c:if>
			<c:if test="${not empty users }">
				
				<c:forEach var="user" items="${requestScope.users }">
					<div >
					<c:out value="${user }"/> 
					<button class="btn btn-link">Follow</button>
					</div>
				</c:forEach>
				
			</c:if>
		</div>
	</div>
</body>
</html>