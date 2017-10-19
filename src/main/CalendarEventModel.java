package main;

public class CalendarEventModel {

	Integer id;
	String name, start, end, title;

	public CalendarEventModel(Integer id, String name, String start, String end, String title) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.title = title;
	}

	public CalendarEventModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
