package RMI;

import java.rmi.Remote;

public interface getPromotionList extends Remote {
	public ResultMessage getPormotionList() throws java.rmi.RemoteException;
}
