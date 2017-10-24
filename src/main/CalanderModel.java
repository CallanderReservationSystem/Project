package main;

public class CalanderModel {

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCalName() {
		return calName;
	}

	public void setCalName(String calName) {
		this.calName = calName;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public Integer uid;
	public String calName;
	public String events;
	
	public CalanderModel(Integer uid, String calName, String events) {
		this.uid = uid;
		this.calName = calName;
		this.events = events;
	}

}
