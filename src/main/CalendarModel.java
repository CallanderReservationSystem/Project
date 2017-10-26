package main;

public class CalendarModel {
	
	public Integer id;
	public Integer uid;
	public String calName;
	public Integer eventCount;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getuid() {
		return uid;
	}
	
	public void setuid(Integer uid) {
		this.uid = uid;
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

	public Integer getEvents() {
		return eventCount;
	}

	public void setEvents(Integer eventCount) {
		this.eventCount = eventCount;
	}

	public CalendarModel(Integer id, Integer uid, String calName, Integer eventCount) {
		super();
		this.id = id;
		this.uid = uid;
		this.calName = calName;
		this.eventCount = eventCount;
	}
	
	
	

}
