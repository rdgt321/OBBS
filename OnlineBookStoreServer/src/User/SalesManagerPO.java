package User;

import java.io.Serializable;

import Server.Const;

public class SalesManagerPO extends UserPO implements Serializable{

	public SalesManagerPO(int ID, String name, String password) {
		super(ID, name, password);
		this.type = Const.SALESMANAGER;
	}

}
