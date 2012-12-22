package RMI;

import java.rmi.Remote;

public interface readMessage extends Remote {
	public ResultMessage readMessage(int messageID)
			throws java.rmi.RemoteException;
}
