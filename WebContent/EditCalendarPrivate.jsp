<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit a Private Calendar</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<h1>Edit a Private Calendar</h1>
	<form method="get" action="EditCalendarPrivate" id="page">
		New name of Calendar: <input type="text" value="${cal_name}" name="newPrivateCalendar"> <input type="hidden" name="calendarID" value="${calID}"> </br>
		New access restriction of Calendar:
		<select id="accessTypes">
			<option value="normalAdmin">A</option>
			<option value="normalUser">U</option>
		</select> </br>
		<button type="submit">Submit to calendar ${calID}</button>
	</form>
</body>
</html>