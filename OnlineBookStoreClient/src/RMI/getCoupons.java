package RMI;

import java.rmi.Remote;

public interface getCoupons extends Remote {
	public ResultMessage getCoupons(int memberID) throws java.rmi.RemoteException;
}
