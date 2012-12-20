package RMI;

import java.rmi.Remote;

public interface useIntegral extends Remote {
	public ResultMessage useIntegral(int memberID, int integral)
			throws java.rmi.RemoteException;
}
