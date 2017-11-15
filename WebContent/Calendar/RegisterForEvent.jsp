<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Event Registration</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<meta charset="UTF-8">
	<script src='lib/jquery.min.js'></script>
	
	<script>

	
	</script>
	
</head>
<body>
	<h1>Registration Confirmation for: ${eventName}</h1>
	<form method="post" action="RegisterForEvent" id="page">
		<div class="form-group row align-items-center">
			<div class="form-group col-sm-3">
				<label for="name">Name</label>
				<input type="text" class="form-control" id="name" placeholder="Name for Reservation">
			</div>
		</div>
		<div class="form-group row ">
			<div class="form-group col-sm-3">
				<label>Time of Reservation</label><br />
				Begin: <input type="time" class="form-control" id="startTimepicker" name="startTime">
				End: <input type="time" class="form-control" id="endtTimepicker" name="endTime">
			</div>
			<div class="form-group col-sm-3"> 
				<label>Registration Date</label><br />
				Begin: <input type="date" class="form-control" name="startDate" id="startDatePick">
				End: <input type="date" class="form-control" name="endDate" id="endDatePick">
			</div>		
		</div>
		<div class="form-group row ">
			<div class="form-group col-sm-3">
			<label>Number of People in Party</label>
				<input type="number" class="form-control" name="numOfPeople" id="numOfPeople" min="1" max="10">
			</div>
		</div>
		<div class="form-group row">
			<div class="form-group col-sm-8">
			<label for="details">Additional Information</label>
			<textarea rows="5"class=" form-control" id="details" name="details" placeholder="Anything Special?"></textarea>
			</div>
		</div>
		<button type="reset" class=" btn btn-danger">Clear</button>
		<button type="submit" class="btn btn-success">Register</button>

		
			
			<%-- Location: <input type="text" value="${location}" readonly>  <br> --%>
			
		
	</form>

</body>
</html>