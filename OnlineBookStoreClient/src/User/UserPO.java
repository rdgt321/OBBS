package User;

import java.io.Serializable;

public abstract class UserPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3367716863127718225L;
	int ID;
	String name;
	String password;
	int type = -1;

	public UserPO(int ID, String name, String password) {
		super();
		this.ID = ID;
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public int getType() {
		return type;
	}
}
