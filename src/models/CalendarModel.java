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

	public CalendarModel(Integer cid, Integer uid, String calName, String events) {
		this.cid = cid;
		this.uid = uid;
		this.calName = calName;
		this.events = events;
	}

	public CalendarModel(Integer ownerId, Integer followerId, Integer calanderId, String title) {
		this.uid = ownerId;
		this.cid = calanderId;
		this.calName = title;

	}

	public CalendarModel(Integer id, Integer uid0, String name) {
		this.cid = id;
		this.uid = uid0;
		this.calName = name;
	}

	public CalendarModel(Integer cid2, Integer uid2) {
		this.cid = cid2;
		this.uid = uid2;
	}

}
