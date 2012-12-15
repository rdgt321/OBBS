package RMI;

import java.rmi.Remote;

public interface getMembers extends Remote {
	public ResultMessage getMembers() throws java.rmi.RemoteException;
}
