package User;

import java.io.Serializable;

import ClientRunner.Const;


public class SalesManagerPO extends UserPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414183683478028408L;

	public SalesManagerPO(int ID, String name, String password) {
		super(ID, name, password);
		this.type = Const.SALESMANAGER;
	}

}
