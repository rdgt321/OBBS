package RMI;

import java.rmi.Remote;

import Sale.OrderPO;

public interface modifyOrder extends Remote {
	public ResultMessage orderModify(OrderPO orderPO)
			throws java.rmi.RemoteException;
}
