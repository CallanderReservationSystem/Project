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
	<div class="form-group col-sm-3">
		Change Title: <input type="text" value="${title}" name="newTitle" class="form-control"> <input type="hidden" name="theEventID" value="${eventID}"> </br> 
		Change Location: <input type="text" value="${location}" name="newLocation" class="form-control"> </br>
		Change Duration: </br> 
		from <input type="date" value="${start_date}" name="newStart_date" class="form-control"> 
		at <input type="time" value="${start}" name="newStart" class="form-control"> 
		to <input type="date" value="${end_date}" name="newEnd_date" class="form-control"> 
		at <input type="time" value="${end}" name="newEnd" class="form-control"> </br>
		<button type="reset" class="btn btn-primary">Clear</button>
		<button type="submit" class="btn btn-success">Submit</button>
	</div>
	</form>
</body>
</html>