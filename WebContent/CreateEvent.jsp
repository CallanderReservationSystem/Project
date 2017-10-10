<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
	<title>Create Event</title>
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js">	</script>

	<script>
	  $( function() {
	    $( "#datepicker" ).datepicker();
	  } );
	  </script>

	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#tables').change(function(){
				if(this.checked){
					$('#addTables').fadeOut('slow');
				}
				else{
					$('#addTables').fadeIn('slow');
				}

			});
			$('#private').change(function(){
				if(this.checked){
					$('#options').fadeOut('slow');
				}
				else{
					$('#options').fadeIn('slow');
				}

			});
		});

	</script>
</head>

<body>
	<h2>Create Event</h2>
	<form  method="post" action="CreateEvent">
		Event Name <input  type="text" name="eventname" size="45" placeholder="Event name"><br>
		Select date<input type="text" id="datepicker"><br>
		Event time<input type="text" ><br>
		<input type="checkbox" name="private" id="private">Make event private<br>
		<div id="options">
		Location <input type="text" name="location" size="45" placeholder="location"><br>
		Description <br><textarea rows="5" cols="35" placeholder="Optional"></textarea><br>
		Amount of seats<input type="text" placeholder="1" ><br>
		<div id = "addTables" >
			Amount of tables<input type="text" name="ammount" placeholder="1"><br>
		</div>
		<input type="checkbox" name="tables" id="tables"> Remove Tables<br>
		<br>
		</div>
		
		<input type="submit" value="Create"><input type="submit" value="Discard">
	


	</form>

</body>
</html>