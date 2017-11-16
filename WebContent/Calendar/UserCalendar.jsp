<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String check = (String) session.getAttribute("Username");
	String name = "";
	String userposition = "";

	if (check != null) {
		name = (String) session.getAttribute("Username");
		userposition = (String) session.getAttribute("Userpos");
	} else {
		response.sendRedirect("../Index.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>"src/Calander/Calander.java"
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>${username}Home ( ${cName} )</title>

<link href='css/fullcalendar.min.css' rel='stylesheet' />
<link href='css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='lib/moment.min.js'></script>
<script src='lib/jquery.min.js'></script>
<script src='js/fullcalendar.min.js'></script>




<script>
var date = moment().subtract( 1, "day");


	$(document).ready(
			function() {

				$('#calendar').fullCalendar(
						{
							

							header : {
								left : 'prev,next,dayForward today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay,agendaCustom'
							},
							
							/* customButtons: {
								dayForward: {
									text: '+',
									click: function() {
										incrementDate:
									}
								}
							}, */
							
							
						 	views: {
							  agendaCustom: {
								  type: 'agenda',
								  duration: { days : 7 },
								  firstDay: date,
								  buttonText: 'Forcast'
								  
							  }	
							}, 
							
							

							navLinks : true, // can click day/week names to navigate views
							selectable : true,
							selectHelper : true,
							select : function(start, end) {
								var title = prompt('Event Title:');
								var eventData;
								if (title) {
									eventData = {
										title : title,
										start : start,
										end : end
									};
									$('#calendar').fullCalendar('renderEvent',
											eventData, true); // stick? = true
								}
								$('#calendar').fullCalendar('unselect');
							},
							editable : true,
							eventLimit : true, // allow "more" link when too many events
							events : "/calanderProject/CalanderEventFillerRegistration"
								
							
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


	<div>
		<h1>${username}Calendar ( ${cName} )</h1>
		<div class="page-header">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<%
					if (userposition.equals("A")) {
				%>
				<%@include file="/inc/inc_admin_nav.jsp"%>
				<%
					} else {
				%>
				<%@include file="/inc/inc_user_nav.jsp"%>
				<%
					}
				%>
			</div>
			</nav>
		</div>
	</div>
	<p>
		<a href="CreateEvent?id=${cid}&calName=${cName}">create event.</a>
	</p>
	<h4>Cal Name: ${cName}</h4>
	<div id="calendar" class="fc fc-unthemed fc-ltr"></div>

	<h4>
		List of events with this calendar<br>
	</h4>
	<c:if test="${not empty events}">
		<c:forEach items="${events}" var="e">
			<br>
			<!-- <b>list of Events: </b> -->
			<b style="color: RED;">${e.getId()}. </b>
			<b style="color: RED;">${e.getStart()} / </b>
			<b style="color: RED;">${e.getEnd()}</b>
			<b style="color: RED;">${e.getTitle()}</b>
		</c:forEach>

	</c:if>
</body>
</html>