<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit/Update Event Information</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<meta charset="UTF-8">
</head>
<body>
	<h1>Edit Event Info</h1>
	<form method="post" action="EditEventInfo" id="page">
		Change Title: <input type="text" id="newTitle"> </br>
		Change Location: <input type="text" id="newLocation"> </br>
		Change Duration: from <input type="date" id="newDurationFromDate"> at <input type="time" id="newDurationFromTime"> to <input type="date" id="newDurationToDate"> at <input type="time" id="newDurationToTime"> </br>
		Change Max Occupancy: <input type="number" id="newMaxOccupancy"> </br>
		Change Table Count: <input type="number" id="newTableCount"> </br>
		Change Seats Per Table: <input type="number" id="newSeatsPerTable"> </br>
		<button type="reset">Clear</button>
		<button type="submit">Submit</button>
	</form>
</body>
</html>