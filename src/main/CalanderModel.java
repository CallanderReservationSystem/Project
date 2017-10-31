package main;

public class CalanderModel {

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

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public Integer cid;
	public Integer uid;
	public String calName;
	public String events;
	
	public CalanderModel(Integer cid, Integer uid, String calName, String events) {
		this.cid = cid;
		this.uid = uid;
		this.calName = calName;
		this.events = events;
	}

}