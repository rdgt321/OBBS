package User;

import java.io.Serializable;

import Server.Const;

public class AdminPO extends UserPO implements Serializable{

	public AdminPO(int ID, String name, String password) {
		super(ID, name, password);
		this.type = Const.ADMIN;
	}

}
