package User;

import java.io.Serializable;

import Server.Const;

public class GeneralManagerPO extends UserPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197466430436191951L;

	public GeneralManagerPO(int ID, String name, String password) {
		super(ID, name, password);
		this.type = Const.GENERALMANAGER;
	}

}
