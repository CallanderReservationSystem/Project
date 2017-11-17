<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Registration</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta charset="UTF-8">
<script src='lib/jquery.min.js'></script>

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

</head>
<body>

	<div>
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

	<h1>Registration Confirmation for: ${eventName}</h1>
	<form method="post" action="RegisterForEvent" id="page">
		<div class="form-group row align-items-center">
			<div class="form-group col-sm-3">
				<label for="name">Name</label>
				<c:if test="${ not empty nameError}">
					<p style="color: RED;">*${nameError}</p>
				</c:if>
				<input type="text" class="form-control" id="name" name='name'
					placeholder="Name for Reservation">
			</div>

			<div class="form-group col-sm-3"></div>
			<div class="form-group row align-items-center">
				<div class="form-group col-sm-3">
					<label for="list">Table List</label><br />
					<div id="table">
						<table class="table table-bordered table-hover table-condensed"
							border="1">
							<tr>
								<th>Tables </th>
								<th>Seats Per Table</th>
							</tr>
							<c:forEach items="${eventTable}" var="tab">
								<tr>
									<td>${tab.tableAmount}</td>
									<td>${tab.seatsPerTable}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group row ">
			<div class="form-group col-sm-3">
				<label>Time of Reservation</label><br />
				<c:if test="${ not empty timeError}">
					<p style="color: RED;">*${timeError}</p>
				</c:if>
				Begin: <input type="time" class="form-control" id="startTimepicker"
					name="startTime"> End: <input type="time"
					class="form-control" id="endtTimepicker" name="endTime">
			</div>
			<div class="form-group col-sm-3">
				<label>Registration Date</label><br />
				<c:if test="${ not empty dateError}">
					<p style="color: RED;">*${dateError}</p>
				</c:if>
				Begin: <input type="date" class="form-control" name="startDate"
					id="startDatePick"> End: <input type="date"
					class="form-control" name="endDate" id="endDatePick">
			</div>
		</div>
		<div class="form-group row ">
			<div class="form-group col-sm-3">
				<label>Number of People in Party</label>
				<c:if test="${ not empty numOfPeopleError}">
					<p style="color: RED;">*${numOfPeopleError}</p>
				</c:if>
				<input type="number" class="form-control" name="numOfPeople"
					id="numOfPeople" min="1" max="10">
			</div>
		</div>
		<div class="form-group row">
			<div class="form-group col-sm-8">
				<label for="details">Additional Information</label>
				<textarea rows="5" class=" form-control" id="details" name="details"
					placeholder="Anything Special?"></textarea>
			</div>
		</div>
		<input type="hidden" name="user_name" value="${user_name}"> 
		<input type="hidden" name="id" value="${id}">
		<button type="reset" class=" btn btn-danger">Clear</button>
		<button type="submit" class="btn btn-success">Register</button>



		<%-- Location: <input type="text" value="${location}" readonly>  <br> --%>
	</form>

</body>
</html>