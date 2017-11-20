<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${username}Home</title>
<link href='fullcalendar.min.css' rel='stylesheet' />
<link href='fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src='lib/moment.min.js'></script>
<script src='lib/jquery.min.js'></script>
<script src='fullcalendar.min.js'></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		var dateFormat = "mm/dd/yy", from = $("#startdate").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 3
		}).on("change", function() {
			to.datepicker("option", "minDate", getDate(this));
		}), to = $("#enddate").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 3
		}).on("change", function() {
			from.datepicker("option", "maxDate", getDate(this));
		});

		function getDate(element) {
			var date;
			try {
				date = $.datepicker.parseDate(dateFormat, element.value);
			} catch (error) {
				date = null;
			}

			return date;
		}
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tablesCheck').change(function() {
			if (this.checked) {
				$('#addTables').fadeOut('fast');
			} else {
				$('#addTables').fadeIn('fast');
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
	<div class="container">
		<div id="calendar" class="fc fc-unthemed fc-ltr page-header">
			<h1>${Calendarname}:CreateEvent</h1>
		</div>
		<a href="Member">
			<button class="btn btn-primary">Home</button>
		</a>
		<form name="filler" method="post" action="CreateEvent">
			<div class="form-group">
				<c:if test="${not empty eventError }">
					<p style="color: RED;">*${eventError}</p>
				</c:if>
				<label class="col-sm-2 control-label">Event Name</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" name="eventName" size="45"
						value="${param.eventName}">
				</div>
			</div>
			<br>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="fromTo">Select
					time</label>
					<div class="col-sm-10" id="fromTo">
					<input class="form-control" size="45 type="text" id="startdate" name="startdate"> 
					<input class="form-control" size="45 type="text" id="enddate" name="enddate">
				</div>
				</div>
				<div>
					<h4>Event Time</h4>
				</div>

				<div class="form-group">
					<c:if test="${not empty timeSError }">
						<p style="color: RED;">*${timeSError}</p>
					</c:if>

					<label class="col-sm-2 control-label">Begin: </label>
					<div class="col-sm-10">
						<input class="form-control" type="time" name="startTime"
							id="startTimepicker" value="${param.startTime}">
					</div>
				</div>
				<div class="form-group">
					<c:if test="${not empty timeEError }">
						<p style="color: RED;">*${timeEError}</p>
					</c:if>
					<label class="col-sm-2 control-label">End: </label>
					<div class="col-sm-10">
						<input class="form-control" type="time" name="endTime"
							id="endTimepicker" value="${param.endTime}">
					</div>
				</div>
				<br>
				<h4>Type of event?</h4>

				<div class="form-group">
					<c:if test="${not empty pri }">
						<p style="color: RED;">*${pri}</p>
					</c:if>

					<p>
						<label>Public</label> <input type="radio" name="pr" value="public">
					</p>
					<p>
						<label>Private</label> <input type="radio" name="pr"
							value="private">
					</P>
				</div>
				<hr>

				<div class="form-group">
					<c:if test="${not empty locaError }">
						<p style="color: RED;">*${locaError}</p>
					</c:if>

					<label class="col-sm-2 control-label">Location</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" name="location" size="45"
							value="${param.location }">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Description</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="5" cols="35"
							name="description" style="resize: none;" placeholder="Optional">${param.description}</textarea>
					</div>
				</div>

				<%-- <div class="form-group">
		<c:if test="${not empty seatsError }">
			<p style="color:RED;">*${seatsError}</p>
		</c:if>
		<label class="col-sm-2 control-label">Amount of Seats</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" name="seats" value="${param.seats }" placeholder="1" >
		</div>
	</div>
	 --%>
				<%-- 		<div class="form-group" id="addTables" >
		
			<c:if test="${not empty tableError }">
				<p style="color:RED;">*${tableError}</p>
			</c:if>
			<label class="col-sm-2 control-label">Amount of tables</label>
			<div class="col-sm-10">
				<input class="form-control" type="text" name="tables" value="${param.tables}" placeholder="1">
			</div>
		</div>
		<div class="col-sm-offset-2 col-sm-10"> --%>
<!-- 				<input type="checkbox" name="tablesCheck" id="tablesCheck">
				Remove Tables<br>
			</div> -->

			<br> <input type="hidden" name="cid" value="${cid}">
			<div class="col-sm-offset-2 col-sm-10" style="margin-top: 10px">
				<button class="btn btn-success" type="submit" value="Create"
					onclick="{document.filler.cid.value=${id}; document.filler.submit();}">Create</button>
				<button class="btn btn-danger" type="reset">Reset</button>
			</div>

		</form>
	</div>
</body>
</html>