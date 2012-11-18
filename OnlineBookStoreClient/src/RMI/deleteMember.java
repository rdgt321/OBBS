package RMI;

import java.rmi.Remote;

public interface deleteMember extends Remote {
	public ResultMessage deleteMember(String memberID)
			throws java.rmi.RemoteException;
}
