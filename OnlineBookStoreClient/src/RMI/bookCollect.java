package RMI;

import java.rmi.Remote;


public interface bookCollect extends Remote {
	public ResultMessage bookCollect(String bookISBN,int memberID) throws java.rmi.RemoteException;
}
