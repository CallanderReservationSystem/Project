package main;

public class CalendarEventModel {
<<<<<<< HEAD
	public int id;
    public String title;
    public String start;
    public String end;
    public String color;
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
=======

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

>>>>>>> f18269aec0d03a34e7a8bcd3d4ab6c69e68d8d40
}
