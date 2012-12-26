package RMI;

import java.rmi.Remote;

import Sale.OrderPO;


public interface addOrder extends Remote {
	public ResultMessage addOrder(OrderPO orderPO) throws java.rmi.RemoteException;
}
