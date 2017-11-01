package models;

public class MyModel {

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpos() {
		return upos;
	}

	public void setUpos(String upos) {
		this.upos = upos;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

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

	public MyModel(String name, String pas, String status, String upos, Integer uid) {
		this.name = name;
		this.pass = pas;
		this.status = status;
		this.upos = upos;
		this.uid = uid;
	}

	

}
