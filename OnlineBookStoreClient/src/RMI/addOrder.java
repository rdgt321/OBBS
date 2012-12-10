package RMI;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Calendar;

import Sale.OrderPO;


public interface addOrder extends Remote {
	public ResultMessage addOrder(OrderPO orderPO) throws java.rmi.RemoteException;
}
