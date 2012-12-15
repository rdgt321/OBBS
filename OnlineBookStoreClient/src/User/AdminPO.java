package User;

import java.io.Serializable;

import ClientRunner.Const;


public class AdminPO extends UserPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4287744517919904190L;

	public AdminPO(int ID, String name, String password) {
		super(ID, name, password);
		this.type = Const.ADMIN;
	}

}
