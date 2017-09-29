package main;

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

	public MyModel(String name, String pas) {
		this.name = name;
		this.pass = pas;
	}

}
