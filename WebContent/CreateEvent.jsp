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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="jquery.ui.timepicker.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js">	</script>
	<script src="jquery.ui.timepicker.js"></script>

	<script>
	  $( function() {
	    $( "#datepicker" ).datepicker({
	    	showButtonPanel: true
	    });
	    
	    $('#startTimepicker').timepicker({
	        showPeriod: true,
	        showLeadingZero: false,
	        onSelect: tpStartSelect
	    });

	    $('#endTimepicker').timepicker({
	   		showPeriod: true,
	    		showLeadinZero: false,
	    		onSelect: tpEndSelect
	    });
	    
	    function tpStartSelect( time, endTimePickerInst ) {
		   $('#endTimepicker').timepicker('option', {
		       minTime: {
		           hour: endTimePickerInst.hours,
		           minute: endTimePickerInst.minutes
		       }
		   });
		}
	    
	    
	    function tpEndSelect( time, startTimePickerInst ) {
		   $('#startTimepicker').timepicker('option', {
		       maxTime: {
		           hour: startTimePickerInst.hours,
		           minute: startTimePickerInst.minutes
		       }
		   });
		}
	    
	  } );
	  
	  
	  <!-- 
	  $(document).ready(function() {
		   $('#timepicker_start').timepicker({
		       showLeadingZero: false,
		       onSelect: tpStartSelect,
		       maxTime: {
		           hour: 16, minute: 30
		       }
		   });
		   $('#timepicker_end').timepicker({
		       showLeadingZero: false,
		       onSelect: tpEndSelect,
		       minTime: {
		           hour: 9, minute: 15
		       }
		   });
		});

		// when start time change, update minimum for end timepicker
		function tpStartSelect( time, endTimePickerInst ) {
		   $('#timepicker_end').timepicker('option', {
		       minTime: {
		           hour: endTimePickerInst.hours,
		           minute: endTimePickerInst.minutes
		       }
		   });
		}

		// when end time change, update maximum for start timepicker
		function tpEndSelect( time, startTimePickerInst ) {
		   $('#timepicker_start').timepicker('option', {
		       maxTime: {
		           hour: startTimePickerInst.hours,
		           minute: startTimePickerInst.minutes
		       }
		   });
		}
	  -->
	  
	  
	  
	  </script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#tables').change(function(){
				if(this.checked){
					$('#addTables').fadeOut('fast');
				}
				else{
					$('#addTables').fadeIn('fast');
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
		Event time<br>
		<strong>Begin: </strong> <input type="text" name="startTimepicker" id="startTimepicker"><br>
		<strong>End: </strong>  <input type="text" name="endTimepicker" id="endTimepicker"><br>
		
		<p>Type of event?</p>
		Public<input type="radio" name="pr" value="public">
		Private<input type="radio" name="pr" values="private"><br>
		<hr>
		
		
		
		Location <input type="text" name="location" size="45" placeholder="location"><br>
		Description <br><textarea rows="5" cols="35" placeholder="Optional"></textarea><br>
		Amount of seats<input type="text" placeholder="1" ><br>
		<div id = "addTables" >
			Amount of tables<input type="text" name="ammount" placeholder="1"><br>
		</div>
		<input type="checkbox" name="tables" id="tables"> Remove Tables<br>
		<br>

		
		<input type="submit" value="Create"><input type="submit" value="Discard">
	


	</form>
	
	
	
	

</body>
</html>