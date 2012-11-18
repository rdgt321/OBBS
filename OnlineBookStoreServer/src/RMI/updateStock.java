package RMI;

import java.rmi.Remote;

public interface updateStock extends Remote {
	public ResultMessage updateStock() throws java.rmi.RemoteException;
}
