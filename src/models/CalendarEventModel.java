package models;

import java.util.Date;

public class CalendarEventModel {

	public int id;
	public Integer uid;
	public String cid; // need to change back to Integer
	public String title;
	public Date start_date;
	public Date end_date;
	public String start;
	public String end;
	public String color;
	public Integer tableCount;
	public Integer seatsPerTable;

	public CalendarEventModel(int id, Integer uid, String cid, String title, Date start_date, Date end_date, String start, String end, String color,
			Integer tableCount, Integer seatsPerTable) {
		super();
		this.id = id;
		this.uid = uid;
		this.cid = cid;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start = start;
		this.end = end;
		this.color = color;
		this.tableCount = tableCount;
		this.seatsPerTable = seatsPerTable;
	}

	

	public CalendarEventModel() {
		// TODO Auto-generated constructor stub
	}

	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}