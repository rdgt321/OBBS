package RMI;

import java.rmi.Remote;

import Sale.ItemPO;


public interface addToCart extends Remote {
	public ResultMessage addToCart(ItemPO itemPO,int memberID) throws java.rmi.RemoteException;
}
