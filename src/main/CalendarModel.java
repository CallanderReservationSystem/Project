package main;

public class CalendarModel {
	
	public int id;
	public int userId;
	public String calName;
	public int eventCount;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getcalName(String calName) {
		return calName;
	}

	public String getCalName() {
		return calName;
	}

	public void setCalName(String calName) {
		this.calName = calName;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public CalendarModel(int id, int userId, String calName, int eventCount) {
		super();
		this.id = id;
		this.userId = userId;
		this.calName = calName;
		this.eventCount = eventCount;
	}
	
	
	

}
