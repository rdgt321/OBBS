package RMI;

import java.sql.ResultSet;

public class ResultMessage {
	private boolean invokeSuccess = false;
	private ResultSet resultSet = null;
	private String postScript = "";

	public ResultMessage(boolean invokeSuccess, ResultSet resultSet,
			String postScript) {
		super();
		this.invokeSuccess = invokeSuccess;
		this.resultSet = resultSet;
		this.postScript = postScript;
	}

	public boolean isInvokeSuccess() {
		return invokeSuccess;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public String getPostScript() {
		return postScript;
	}
}
