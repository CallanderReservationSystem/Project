<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${user}Home</title>

<link href='css/fullcalendar.min.css' rel='stylesheet' />
<link href='css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='lib/moment.min.js'></script>
<script src='lib/jquery.min.js'></script>
<script src='js/fullcalendar.min.js'></script>


<script>
	$(document).ready(function() {

		$('#calendar').fullCalendar({

			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			defaultDate : '2017-09-12',

			navLinks: true, // can click day/week names to navigate views
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				var title = prompt('Event Title:');
				var eventData;
				if (title) {
					eventData = {
						title: title,
						start: start,
						end: end
					};
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
				}
				$('#calendar').fullCalendar('unselect');
			},
			
			/* dayClick : function() {
				alert('a day has been clicked!');
				window.location.href = "Member.jsp";
			}, */ 

			defaultDate : '2017-09-12',
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			events : [ ]

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
	
	<h4>List of events with this calendar<br></h4>
	<c:if test="${not empty calendar}">
		<c:forEach items="${calendar}" var="c">
			<br>
			<b style="color:RED;">${c.getId()}. </b>
			<b style="color:RED;">${c.getStart()} / </b>
			<b style="color:RED;">${c.getEnd()}</b>
			<b style="color:RED;">${c.getTitle()}</b>
		</c:forEach>
		
	</c:if>
</body>
</html>