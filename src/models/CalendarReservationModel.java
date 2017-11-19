package models;

public class CalendarReservationModel {

	String start_time;
	String end_time;
	String start_date;
	String end_date;
	String user_name;
	String details;

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	Integer calId;
	Integer eventId;
	Integer userId;
	Integer tableId;

	public CalendarReservationModel(String start_time, String end_time, String start_date, String end_date,
			String user_name, String details, Integer calId, Integer eventId, Integer userId, Integer tableId) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_name = user_name;
		this.details = details;
		this.calId = calId;
		this.eventId = eventId;
		this.userId = userId;
		this.tableId = tableId;
	}

	public CalendarReservationModel() {

	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getCalId() {
		return calId;
	}

	public void setCalId(Integer calId) {
		this.calId = calId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}