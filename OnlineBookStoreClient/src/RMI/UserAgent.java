package RMI;

import java.util.Calendar;

public class UserAgent {
	private int id;
	private String name;
	private String password;
	private int userType;
	private boolean online;
	public long lastRequest;

	public UserAgent(int id, String name, String password, int userType) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getUserType() {
		return userType;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
