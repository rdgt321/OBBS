package RMI;

import java.rmi.Remote;

public interface useCoupons extends Remote {
	public ResultMessage userCoupons(int couponsID) throws java.rmi.RemoteException;
}
