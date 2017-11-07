package models;

public class Table {

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Integer id;
	public Integer eventId;
	public String eventName;
	public Integer tableAmount;
	public Integer seatesPerTable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getTableAmount() {
		return tableAmount;
	}

	public void setTableAmount(Integer tableAmount) {
		this.tableAmount = tableAmount;
	}

	public Integer getSeatesPerTable() {
		return seatesPerTable;
	}

	public void setSeatesPerTable(Integer seatesPerTable) {
		this.seatesPerTable = seatesPerTable;
	}

	public Table(Integer id, Integer eventId, String eventName, Integer tableAmount, Integer seatesPerTable) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.eventName = eventName;
		this.tableAmount = tableAmount;
		this.seatesPerTable = seatesPerTable;
	}

}