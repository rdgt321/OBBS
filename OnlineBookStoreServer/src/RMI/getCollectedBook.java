package RMI;

import java.rmi.Remote;

public interface getCollectedBook extends Remote {
	public ResultMessage getCollectedBook(int memebrID) throws java.rmi.RemoteException;
}
