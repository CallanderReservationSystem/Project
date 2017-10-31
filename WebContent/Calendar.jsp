<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String check = (String) session.getAttribute("Username");
	String name = "";
	String userposition = "";
	
	if (check != null) {
		name = (String) session.getAttribute("Username");
		userposition = (String) session.getAttribute("Userpos");
	} else {
		response.sendRedirect("Index.jsp");
	}
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 		<link rel="stylesheet" href="css/fullcalendar.min.css">
 		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 		<script src='lib/moment.min.js'></script>
		<script src='lib/jquery.min.js'></script>
		<script src='js/fullcalendar.min.js'></script>
 		
 		<sql:setDataSource var = "db" driver="com.mysql.jdbc.Driver"
 			url="cs3.calstatela.edu/localhost:3306"
 			/>
 		
 		<!-- will eventually need to link this to another page so that we can create individual calendars  -->
		<script>		
			$(document).ready(function() {
				
				$('#calendar').fullCalendar({
					
				
					 	navLinks: true,
					 
					    
					    header : {
					    	 			left : 'prev,next today',
					    	 			center : 'title',
					    	 			right : 'month,agendaWeek,agendaDay,agenda5Day'
					    	},
					    	events: "/calanderProject/CalendarEventFiller.java",
					    	
				    	  	views: {
					    		agenda5Day:{
					    			type: "agenda",
					    			duration: { days: 5,},
					    			hiddenDays: [ 6, 0],
					    			buttonText: "weekdays"
					    		}
					    	},
					    
					    	
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
				 		
					    
					    
        			});
 
   			 });
		</script>

		
<title>${Username} Home ( ${Calendarname} )</title>
</head>
<body>
<a href=CreateEvent?cid=${cid}&name=${Calendarname} >hhhh</a>

		<div>
			<h1>${Username} Calendar: ( ${Calendarname} )  </h1>
			<div class="page-header">
				<nav class="navbar navbar-default">
				<div class="container-fluid">
					<%
						if (userposition.equals("A")) {
					%>
					<%@include file="inc/inc_admin_nav.jsp"%>
					<%
						} else {
					%>
					<%@include file="inc/inc_user_nav.jsp"%>
					<%
						}
					%>
				</div>
				</nav>
			</div>
		</div>
	<h4>Cal Name: ${Calendarname} </h4>
	<div id='calendar'></div>


</body>
</html>