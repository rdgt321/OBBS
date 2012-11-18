package RMI;

import java.rmi.Remote;

public interface queryMember extends Remote {
	public ResultMessage queryMember(String memberID)
			throws java.rmi.RemoteException;
}
