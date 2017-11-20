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
	public Integer cid;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String eventName;
	public Integer tableAmount;
	public Integer seatsPerTable;

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

	public Integer getSeatsPerTable() {
		return seatsPerTable;
	}

	public void setSeatsPerTable(Integer seatsPerTable) {
		this.seatsPerTable = seatsPerTable;
	}

	public Table(Integer id, Integer eventId, Integer cid, String eventName, Integer tableAmount,
			Integer seatsPerTable) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.cid = cid;
		this.eventName = eventName;
		this.tableAmount = tableAmount;
		this.seatsPerTable = seatsPerTable;
	}

}