package User;

public abstract class User {
	String ID;
	String name;
	String password;

	public User(String ID, String name, String password) {
		super();
		ID = ID;
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

	public String getID() {
		return ID;
	}
}
