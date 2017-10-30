package models;

public class Table {

	public int seatesPerTable;

	public int getSeatesPerTable() {
		return seatesPerTable;
	}

	public void setSeatesPerTable(int seatesPerTable) {
		this.seatesPerTable = seatesPerTable;
	}

	public Table(int seatesPerTable) {
		super();
		this.seatesPerTable = seatesPerTable;
	}
	
}
