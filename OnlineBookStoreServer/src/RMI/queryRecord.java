package RMI;

import java.rmi.Remote;


public interface queryRecord extends Remote{
	public ResultMessage purchaseQuery(int memberID) throws java.rmi.RemoteException;
}
