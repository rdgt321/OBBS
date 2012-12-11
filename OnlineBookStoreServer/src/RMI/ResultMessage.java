package RMI;

import java.io.Serializable;
import java.util.ArrayList;


public class ResultMessage implements Serializable{
	private boolean invokeSuccess = false;
	private ArrayList resultSet = null;
	private String postScript = "";

	public ResultMessage(boolean invokeSuccess, ArrayList resultSet,
			String postScript) {
		super();
		this.invokeSuccess = invokeSuccess;
		this.resultSet = resultSet;
		this.postScript = postScript;
	}

	public boolean isInvokeSuccess() {
		return invokeSuccess;
	}

	public ArrayList getResultSet() {
		return resultSet;
	}

	public String getPostScript() {
		return postScript;
	}
}
