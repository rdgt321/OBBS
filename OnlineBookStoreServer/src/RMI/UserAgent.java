package RMI;

import java.io.Serializable;

import Server.Const;

public class UserAgent implements Serializable, Comparable<UserAgent> {
	private int id;
	private String name;
	private String password;
	private int userType;
	public String ip;
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

	public String toString() {
		String id = "userID:" + this.id;
		String type = null;
		if (userType == Const.ADMIN) {
			type = "admin";
		} else if (userType == Const.GENERALMANAGER) {
			type = "general maanger";
		} else if (userType == Const.SALESMANAGER) {
			type = "sales manager";
		} else if (userType == Const.MEMBER) {
			id = "memberID:" + this.id;
			type = "member";
		}
		return id + " name:" + name + " type:" + type + " ip:" + ip;
	}

	public int getKey() {
		return Integer.parseInt(String.valueOf(userType) + String.valueOf(id));
	}

	@Override
	public int compareTo(UserAgent o) {
		return this.getKey() - o.getKey();
	}
}
