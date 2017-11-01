<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Event Registration</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<meta charset="UTF-8">
</head>
<body>
	<h1>Registration Confirmation</h1>
	<form method="post" action="RegisterForEvent" id="page">
		Location: <input type="text" value="${location}" readonly>  <br>
		Time: from <input type="text" value="${start}" readonly> to <input type="text" value="${end}" readonly> <br>
		Time to be at the event: <input type="datetime-local" name="fromTime"> to <input type="datetime-local" name="toTime"> <br>
		Number of people: <input type="number" name="party#" min="1" max="10"> <br>
		Table to reserve: <input type="number" name="table" min="1" max="10"> <br>
		<button type="reset">Clear</button>
		<button type="submit">Register</button>
	</form>
</body>
</html>