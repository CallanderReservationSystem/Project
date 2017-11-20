<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>

<body>
	
	<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
          	<%--Set proper links to options --%>
            <li><a href="Member"><strong>${user}'s Home</strong></a></li>
          </ul>
        </div>
      </div>
    </nav>
	
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
					<a href="View?name=${user}">
					<c:out value="${user }"/>
					</a>
					</div>
				</c:forEach>
				
			</c:if>
		</div>
	</div>
</body>
</html>