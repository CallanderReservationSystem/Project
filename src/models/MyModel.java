package models;

public class MyModel {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String name;
	public String pass;
	public String status;
	public String upos;
	public Integer uid;

	public MyModel(String name, String pas, String status, String upos, int uid) {
		this.name = name;
		this.pass = pas;
		this.status = status;
		this.upos = upos;
		this.uid = uid;
	}

	

}
