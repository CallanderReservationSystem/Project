<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Event Registration</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<meta charset="UTF-8">
</head>
<body>
	<h1>Registration Confirmation</h1>
	<p>Location:</p>
	<p>Time:</p>
	<form method="post" action="RegisterForEvent" id="page">
		Time to be at the event: <input type="time" name="fromTime"> to <input type="time" name="toTime"> </br>
		Number of people: <input type="text" name="party#" id="party#"> </br>
		Table to reserve: <input type="text" name="table" id="table"> </br>
		<button type="reset">Clear</button>
		<button type="submit">Register</button>
	</form>
</body>
</html>