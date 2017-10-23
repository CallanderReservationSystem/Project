<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
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
 		
 		<!-- will eventually need to link this to another page so that we can create individual calendars  -->
		<script>		
			$(document).ready(function() {
				
				$('#calendar').fullCalendar({
				
					  navLinks: false,
					  /*,dayClick: function() { 
						  window.location.href= "Member.jsp";
						  
					    },*/
					    header : {
					    	 			left : 'prev,next today',
					    	 			center : 'title',
					    	 			right : 'month,agendaWeek,agendaDay'
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

		
<title>${user} Home</title>
</head>
<body>
<div id='calendar'></div>

</body>
</html>