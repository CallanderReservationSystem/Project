<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Registration Form</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <div class="container">
    	<c:if test="${not empty message }">
    		<p style="color:RED;">Message: ${message}</p>
    	</c:if>
        <div>
            <h1> Please Enter your info below: </h1>
            <!-- this is dividing blocks -->
            <!-- hyperLinks to other pages -->
            <div class="page-header">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <ul class="nav nav-pills">
                            <!-- target="_blank" -->
                            <li><a class="btn btn-primary" href="Main">Home</a></li>
                            <li><a class="btn btn-primary" href="About">About</a></li>
                            <li><a class="btn btn-success" href="Signup">Sign up</a></li>
                            <li><a class="btn btn-primary" href="Main">Login</a></li>
                            <li><a class="btn btn-primary" href="Contact">Contact Us</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <div>
            <!-- The .. before the path to go back one dirctory -->
            <img src="../Images/homepod-white-shelf.jpg">
        </div>
		<form class="form-horizontal" method="post" action="Register">
			<div class="form-group">
				<label class="col-sm-2 control-label">First Name</label> 
				<c:if test="${not empty firstError }">
					<h9 style="color:RED;">*${firstError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="first" value="${param.first}" placeholder="Bob"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Last Name</label>
				<c:if test="${not empty lastError }">
					<h9 style="color:RED;">*${lastError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="last" value="${param.last}" placeholder="Smith"/> <br>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Username</label>
				<c:if test="${not empty nameError }">
					<h9 style="color:RED;">*${nameError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control"type="text" name="username" value="${param.username}" placeholder="BobSmith123"/>
				</div>
			</div>	
			<div class="form-group">
				<label class="col-sm-2 control-label">Password</label>
				<c:if test="${not empty passError }">
					<h9 style="color:RED;">*${passError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="password" id="password" name="password" value="${param.password}" placeholder="Password" onkeyup='check();'/>
					<p class="help-block" id='message'></p><!-- <span ></span> -->
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">confirm-password</label>
				<div class="col-sm-10">
					<input class="form-control"type="password" id="confirm_password" name="password1" value="${param.password1}" placeholder="Retype-Password" onkeyup='check();'/> <br>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Email</label>
				<c:if test="${not empty emailError }">
					<h9 style="color:RED;">*${emailError}</h9>
				</c:if>
				<div class="col-sm-10">
					<input class="form-control" type="email" name="email" value="${param.email}" placeholder="bobsmith123@company.com"/> <br>
				</div>
			</div>	
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-success" type="submit">sign up</button>
			</div>
		</form>
    </div>
    <script type="text/javascript">
    var check = function() {
    	  if (document.getElementById('password').value ==
    	    document.getElementById('confirm_password').value) {
    	    document.getElementById('message').style.color = 'green';
    	    document.getElementById('message').innerHTML = 'matching';
    	  } else {
    	    document.getElementById('message').style.color = 'red';
    	    document.getElementById('message').innerHTML = 'not matching';
    	  }
    	}
    </script>
</body>

</html>