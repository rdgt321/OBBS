package DBC;

import RMI.ResultMessage;

public interface MessageDAO {
	public ResultMessage addMessage(int memberID, String title, String msg);

	public ResultMessage updateMessage(int messageID, boolean sent);

	public ResultMessage getMessage(int memberID);
}
