package Book;

import java.io.Serializable;

public class DirectoryPO implements Serializable{
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
