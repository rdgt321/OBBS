package RMI;

import java.rmi.Remote;

public interface Pay extends Remote {
	public ResultMessage pay(int amount) throws java.rmi.RemoteException;
}
