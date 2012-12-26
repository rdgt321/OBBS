package RMI;

import java.rmi.Remote;

public interface queryMember extends Remote {
	public ResultMessage queryMember(int memberID)
			throws java.rmi.RemoteException;
}
