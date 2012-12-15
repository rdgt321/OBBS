package Book;

import java.io.Serializable;

public class DirectoryPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2409803346848357208L;
	private int ID;
	private String name;
	
	public DirectoryPO(int ID,String name) {
		super();
		this.ID = ID;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}
	
}
