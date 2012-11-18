package RMI;

import java.rmi.Remote;


public interface queryRecord extends Remote{
	public ResultMessage purchaseQuery(String memberID) throws java.rmi.RemoteException;
}
