<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${username}Home</title>
	<link href='fullcalendar.min.css' rel='stylesheet' />
	<link href='fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='lib/moment.min.js'></script>
	<script src='lib/jquery.min.js'></script>
	<script src='fullcalendar.min.js'></script>

	<script>
		$(document).ready(function() {
	
			$('#calendar').fullCalendar({
				defaultDate : '2017-09-12',
				editable : true,
				eventLimit : true, // allow "more" link when too many events
				events : [ {
					title : 'All Day Event',
					start : '2017-09-01'
				}, {
					title : 'Long Event',
					start : '2017-09-07',
					end : '2017-09-10'
				}, {
					id : 999,
					title : 'Repeating Event',
					start : '2017-09-09T16:00:00'
				}, {
					id : 999,
					title : 'Repeating Event',
					start : '2017-09-16T16:00:00'
				}, {
					title : 'Conference',
					start : '2017-09-11',
					end : '2017-09-13'
				}, {
					title : 'Meeting',
					start : '2017-09-12T10:30:00',
					end : '2017-09-12T12:30:00'
				}, {
					title : 'Lunch',
					start : '2017-09-12T12:00:00'
				}, {
					title : 'Meeting',
					start : '2017-09-12T14:30:00'
				}, {
					title : 'Happy Hour',
					start : '2017-09-12T17:30:00'
				}, {
					title : 'Dinner',
					start : '2017-09-12T20:00:00'
				}, {
					title : 'Birthday Party',
					start : '2017-09-13T07:00:00'
				}, {
					title : 'Click for Google',
					url : 'http://google.com/',
					start : '2017-09-28'
				} ]
	
			});
	
			$('#calendar').fullCalendar({
				dayClick : function() {
					alert('a day has been clicked!');
				}
			});
	
		});
	</script>
	<style>
		body {
			margin: 40px 10px;
			padding: 0;
			font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
			font-size: 14px;
		}
	
		#calendar {
			max-width: 900px;
			margin: 0 auto;
		}
	</style>
</head>
<body>

	<div id="calendar" class="fc fc-unthemed fc-ltr"></div>
	
	<h2>${Calendarname}:Create Event</h2>

	<form method="post" action="CreateEvent">

		<c:if test="${not empty eventError }">
			<p style="color: RED;">*${eventError}</p>
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
		
		<p>
			<strong>Begin: </strong> <input type="text" name="startTime" id="startTimepicker" value="${param.startTime}">
		</p>
		
		<c:if test="${not empty timeEError }">
			<p style="color: RED;">*${timeEError}</p>
		</c:if>
		
		<p>
			<strong>End: </strong> <input type="text" name="endTime" id="endTimepicker" value="${param.endTime}">
		</p>
		
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
				<p style="color: RED;">*${locaError}</p>
		</c:if>
		
		<p>
			Location <input type="text" name="location" size="45" value="${param.location }">
		</p>
		
		<!--  	<p>Location <input type="text" name="location" size="45" value="${param.location }"></p>
 -				 +		-->
 
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