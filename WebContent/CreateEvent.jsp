<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${Username} Home</title>
</head>
<body>



<link href='fullcalendar.min.css' rel='stylesheet' />
<link href='fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='lib/moment.min.js'></script>
<script src='lib/jquery.min.js'></script>
<script src='fullcalendar.min.js'></script>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
	<title> ${Calendarname} :Create Event</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="jquery.ui.timepicker.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js">	</script>
	<script src="jquery.ui.timepicker.js"></script>

	<script>
	  $( function() {
	    $( "#datepicker" ).datepicker({
	    	showButtonPanel: true,
	    	minDate: 0,
	    	maxDate: "+1M + 5D"
	    });
	    
	    <%-- https://fgelinas.com/code/timepicker/  --%>
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
	  
	  
	
	  
	  
	  
	  </script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#tablesCheck').change(function(){
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
	<h2>${Calendarname}: Create Event</h2>
	
	<form  method="post" action="CreateEvent">
	
		<c:if test="${not empty eventError }">
			<p style="color:RED;">*${eventError}</p>
		</c:if>
		<p>Event Name <input  type="text" name="eventName" size="45"  value="${param.eventName}" ></p>
		
		<c:if test="${not empty dateError }">
			<p style="color:RED;">*${dateError}</p>
		</c:if>
		<p>Select date<input type="text" name= "date" id="datepicker" value="${param.date}"></p>
		
		<p>Event time</p>
		
		<c:if test="${not empty timeSError }">
			<p style="color:RED;">*${timeSError}</p>
		</c:if>
		<p><strong>Begin: </strong> <input type=time name="startTime" id="startTimepicker" value="${param.startTime}"></p>
		
		<c:if test="${not empty timeEError }">
			<p style="color:RED;">*${timeEError}</p>
		</c:if>
		<p><strong>End: </strong>  <input type="time" name="endTime" id="endTimepicker" value="${param.endTime}"></p>
		
		<p>Type of event?</p>
		
		<c:if test="${not empty pri }">
			<p style="color:RED;">*${pri}</p>
		</c:if>
		<P>
			Public<input type="radio" name="pr" value="public">
			Private<input type="radio" name="pr" value="private">
		</P>
		
		<hr>
		
		<c:if test="${not empty locaError }">
			<p style="color:RED;">*${locaError}</p>
		</c:if>
	<!--  	<p>Location <input type="text" name="location" size="45" value="${param.location }"></p>
		-->
		<p>Description</p>
		<textarea rows="5" cols="35" name="description" placeholder="Optional">${param.description }</textarea><br>
		
		<c:if test="${not empty seatsError }">
			<p style="color:RED;">*${seatsError}</p>
		</c:if>
		<p>Amount of seats<input type="text" name="seats" value="${param.seats }" placeholder="1" ></p>
		
		<div id = "addTables" >
			<c:if test="${not empty tableError }">
				<p style="color:RED;">*${tableError}</p>
			</c:if>
			<p>Amount of tables<input type="text" name="tables" value="${param.tables}" placeholder="1"></p>
		</div>
		<input type="checkbox" name="tablesCheck" id="tablesCheck"> Remove Tables<br>
		<br>

		<input type="submit" value="Create"><input type="reset" value="Reset">
	
	</form>
	
	

</body>
</html>

