package Model;

import Helper.DBConnection;

public class User {
	private int id;
	String identitynum,name,type,password;
	DBConnection conn = new DBConnection();

	
	public User(int id, String identitynum, String name, String type, String password) {
		super();
		this.id = id;
		this.identitynum = identitynum;
		this.name = name;
		this.type = type;
		this.password = password;
	}
	public User() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentitynum() {
		return identitynum;
	}
	public void setIdentitynum(String identitynum) {
		this.identitynum = identitynum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
	

}
