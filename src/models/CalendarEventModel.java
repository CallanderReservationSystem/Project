package models;


public class CalendarEventModel {

	public Integer getUid() {
		return uid;
	}



	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public Integer getCid() {
		return cid;
	}



	public void setCid(Integer cid) {
		this.cid = cid;
	}



	public String getEnd_time() {
		return end_time;
	}



	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}



	public Integer getTableCount() {
		return tableCount;
	}



	public void setTableCount(Integer tableCount) {
		this.tableCount = tableCount;
	}



	public Integer getSeatsPerTable() {
		return seatsPerTable;
	}



	public void setSeatsPerTable(Integer seatsPerTable) {
		this.seatsPerTable = seatsPerTable;
	}

	public int id;
	public Integer uid;
	public Integer cid; // need to change back to Integer
	public String title;
	public String start;
	public String end;
	public String start_time;
	public String end_time;
	public String url;
	public String color;
	public Integer tableCount;
	public Integer seatsPerTable;

	public CalendarEventModel(int id, Integer uid, Integer cid, String title, String start, String end, String start_time, String end_time, String url, String color,
			Integer tableCount, Integer seatsPerTable) {
		super();
		this.id = id;
		this.uid = uid;
		this.cid = cid;
		this.title = title;
		this.start = start;
		this.end = end;
		this.start_time = start_time;
		this.end_time = end_time;
		this.url = url;
		this.color = color;
		this.tableCount = tableCount;
		this.seatsPerTable = seatsPerTable;
	}

	

	public CalendarEventModel() {
		// TODO Auto-generated constructor stub
	}

	public CalendarEventModel(int eid, int uid, int cid, String title, String start, String end,
			String startTime, String endTime, String detail, String color, int tableCount, int seatsPerTable,
			String location) {
		super();
		this.id = eid;
		this.uid = uid;
		this.cid = cid;
		this.title = title;
		this.start = start;
		this.end = end;
		this.start_time = startTime;
		this.end_time = endTime;
		this.color = color;
		this.tableCount = tableCount;
		this.seatsPerTable = seatsPerTable;
	}



	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
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

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}