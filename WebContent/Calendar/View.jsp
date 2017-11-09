<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>View</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
          	<%--Set proper links to options --%>
            <li><a><strong>${sUser }</strong></a></li>
            <li><a href="Memeber">Home</a></li>
            <li><a href="Search">Back to Search</a></li>
          </ul>
        </div>
      </div>
    </nav>


	<div class="container" style="margin-top: 50px;">
		<div>
			<c:if test="${not empty noUser }">
				<h1 style="color: RED;">*${noUser}</h1>
				<a href="Search">Go to search</a>
			</c:if>
			<c:if test="${empty noUser }">
				<c:if test="${not empty sameUser }">
					<h1>${sameUser }</h1>
				</c:if>
				<c:if test="${empty sameUser }">
					<h1>These are ${param.name }'s calendar(s)</h1>
				</c:if>
				
				<c:if test="${not empty noCal }">
					<h3>${noCal}</h3>
				</c:if>	
				<c:if test="${empty noCal }">
				<div class="row">
				<c:forEach var="cal" items="${requestScope.calendars }">
					
						<div class="col-xs-6 col-lg-4">
						<c:set var="eventsFound" value="false"/>
						<h2><c:out value="${cal.calName } "/></h2>
							<h4>Events on calendar</h4>
							<c:forEach var="event" items="${requestScope.events }">
								<c:if test="${cal.cid eq event.cid }">
									<li><c:out value="${event.title }"></c:out></li>
									<c:set var="eventsFound" value="true"/>
								</c:if>
							</c:forEach>
						<c:if test="${eventsFound eq false}">
							<p>No events for this calendar</p>
						</c:if>	
						</div>
					
				</c:forEach>
				</div>
				</c:if>
			</c:if>
		</div>
		
	
	</div>

</body>
</html>