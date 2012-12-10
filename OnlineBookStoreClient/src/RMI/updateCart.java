package RMI;

import java.rmi.Remote;

import Sale.CartPO;

public interface updateCart extends Remote {
	public ResultMessage updateCart(CartPO cartPO)
			throws java.rmi.RemoteException;
}
