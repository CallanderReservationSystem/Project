package models;

public class CalendarModel {

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

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

	public Integer getEvents() {
		return eventCount;
	}

	public void setEvents(Integer events) {
		this.eventCount = events;
	}

	public Integer cid;
	public Integer uid;
	public String calName;
	public Integer eventCount;

	public CalendarModel(Integer cid, Integer uid, String calName, Integer eventCount) {
		this.cid = cid;
		this.uid = uid;
		this.calName = calName;
		this.eventCount = eventCount;
	}

	public CalendarModel(Integer id, Integer uid0, String name) {
		this.cid = id;
		this.uid = uid0;
		this.calName = name;
	}

}
