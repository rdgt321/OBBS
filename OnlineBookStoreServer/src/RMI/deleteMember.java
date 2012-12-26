package RMI;

import java.rmi.Remote;

public interface deleteMember extends Remote {
	public ResultMessage deleteMember(int memberID)
			throws java.rmi.RemoteException;
}
