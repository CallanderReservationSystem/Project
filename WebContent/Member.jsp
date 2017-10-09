<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
	
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<!-- These are the style sheets that come with jQuery as well as the Legacy jQuery  && 
			 the jQuery-UI Content Delivery Networks (CDN) respectivly-->
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 		<link rel="stylesheet" href="/resources/demos/style.css">
 		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		
 		<!-- This is a style sheet for the sidebar on page -->
		<link rel="stylesheet" href=Sidebar.css>
 		
 	
 		 <script>
  			$( function() {
  			  $( "#tabs" ).tabs();
			  });
  		</script>
  		
  		<script>
  		$(document).ready(function () {
  		    $('#sidebarCollapse').on('click', function () {
  		        $('#sidebar').toggleClass('active');
  		    });
  		});
  		</script>
  		
		<title>${user} Home (Members Only)</title>
	</head>
	<body>
		<div class="container">
			<div>
        		<h1>${user} Home</h1>
        		<div class="page-header">
					<nav class="navbar navbar-default">
			        	<div class="container-fluid">
			            	<ul class="nav nav-pills">
			            	    	<li><a class="btn btn-success" href="Member">Home</a></li>
			                    <li><a class="btn btn-primary" href="playlist">PlayList</a></li>
			                    <li><a class="btn btn-primary" href="membersList">Members</a></li>
	                            <li><a class="btn btn-danger" href="Signout">Logout</a></li>
			                </ul>
			             </div>
			        </nav>
					<h1>Welcome home, ${user}.</h1>					
			</div>
			
		</div>
			
			<!--  -->
			
			<div class=centered id="tabs">
  				<ul>
    					<li><a href="#tabs-1">My Calendars</a></li>
    					<li><a href="#tabs-2">Public Calendars</a></li>
 
  				</ul>
  					<div id="tabs-1">
   					 	<div class="wrapper">
							<nav id ="sidebar">
								<div class="sidebar-header">
									<h3>Options</h3>
								</div>
								<ul class="options">
									<li class="active"><a href="Calander.jsp">Create a Calendar</a></li>
									<li> 
										<a href="#deleteCalendar" data-toggle="collapse" aria-expanded="false">Delete a Calendar</a>
										<ul class="collapse list-unstyled" id="deleteCalendar">
											<li>hello</li>
											<li>this</li>
											<li>will be replaced soon</li>
										</ul> 
									</li>
									<li>
										<a href="#editCalendar" data-toggle="collapse" aria-expanded=false">Edit a Calendar</a>
										<ul class="collapse list" id ="editCalendar" >
											<li>test</li>
										</ul>
									</li>
								</ul>
							</nav>
						</div>
  					</div>
  						
  					<div id="tabs-2">
  								<div class="wrapper">
							<nav id ="sidebar">
								<div class="sidebar-header">
									<h3>Options</h3>
								</div>
								<ul class="options">
									<li class="active"><a href="#findCalendar">Find a Calendar</a>
									<li> 
										<a href="#unfollowCalendar" data-toggle="collapse" aria-expanded="false">Unfollow a Calendar</a>
										<ul class="collapse list-unstyled" id="unfollowCalendar">
											<li>hello</li>
											<li>this</li>
											<li>will be replaced soon</li>
										</ul> 
											
									</li>
									<li>
										<a href="#editCalendar" data-toggle="collapse" aria-expanded=false">Edit a Calendar</a>
										<ul class="collapse list" id ="editCalendar" ></ul>
									</li>
								</ul>
							</nav>
						</div>
  					</div>
			</div>
			
			  

			
			
	
			
 	
			
			
			
			
		</div>
	</body>
</html>