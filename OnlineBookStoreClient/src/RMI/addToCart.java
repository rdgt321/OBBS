package RMI;

import java.rmi.Remote;

import Sale.CartPO;
import Sale.ItemPO;

import Book.BookPO;


public interface addToCart extends Remote {
	public ResultMessage addToCart(ItemPO itemPO,int memberID) throws java.rmi.RemoteException;
}
