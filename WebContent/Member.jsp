<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<title>${user} Home (Members Only)</title>
	</head>
	<body>
		<div class="container">
			<div>
        		<h1>${user} Home</h1>
        		<div class="page-header">
					<nav class="navbar navbar-default">
			        	<div class="container-fluid">
			            	<ul class="nav nav-pills">
			            	    <li><a class="btn btn-success" href="Member">Home</a></li>
			                    <li><a class="btn btn-primary" href="playlist">PlayList</a></li>
			                    <li><a class="btn btn-primary" href="membersList">Members</a></li>
	                            <li><a class="btn btn-danger" href="Signout">Logout</a></li>
			                </ul>
			             </div>
			        </nav>
					<h1>Welcome home, ${user}.</h1>
				</div>
			</div>
		</div>
	</body>
</html>