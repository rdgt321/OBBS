package RMI;

import java.rmi.Remote;

public interface cancelCollect extends Remote {
	public ResultMessage cancelCollect(String bookISBN,int memberID) throws java.rmi.RemoteException;
}
