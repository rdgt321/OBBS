package Sale;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.Calendar;

import com.sun.media.rtp.RTCPAPPPacket;

import Book.BookPO;
import DBC.CartItemDAO;
import DBC.DAOFactory;
import DBC.OrderDAO;
import DBC.OrderItemDAO;
import RMI.ResultMessage;

public class SaleServiceImpl extends UnicastRemoteObject implements SaleService {
	private OrderDAO orderDAO = null;
	private OrderItemDAO orderItemDAO = null;
	private CartItemDAO cartItemDAO = null;

	public SaleServiceImpl() throws RemoteException {
		super();
		try {
			Naming.bind("rmi://localhost:1099/SaleService", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		orderDAO = DAOFactory.getOrderDAO();
		orderItemDAO = DAOFactory.getOrderItemDAO();
		cartItemDAO = DAOFactory.getCartItemDAO();
	}

	@Override
	public ResultMessage addOrder(OrderPO orderPO) throws RemoteException {
		ArrayList<ItemPO> items = orderPO.getBooks();
		boolean success = true;
		ResultMessage orderMessage = orderDAO.addOrder(orderPO);
		success &= orderMessage.isInvokeSuccess();
		if (!success) {
			return new ResultMessage(false, null, "add order failed");
		}
		ResultMessage orderItemMessage;
		for (ItemPO item : items) {
			orderItemMessage = orderItemDAO.addOrderItem(orderPO.getOrderID(),
					item);
			success &= orderItemMessage.isInvokeSuccess();
		}
		if (!success) {
			return new ResultMessage(false, null, "add orderitem failed");
		}
		return new ResultMessage(true, null, "add order success");

	}

	@Override
	public ResultMessage addToCart(ItemPO itemPO, int memberID)
			throws RemoteException {
		return cartItemDAO.addCartItem(memberID, itemPO);
	}

	@Override
	public ResultMessage deleteFromCart(String bookISBN, int memberID)
			throws RemoteException {
		return cartItemDAO.deleteCartItem(memberID, bookISBN);
	}

	@Override
	public ResultMessage getBooksInCart(int memberID) throws RemoteException {
		ResultMessage cartitemMessage = cartItemDAO.getBooksInCart(memberID);
		ArrayList<CartPO> po = new ArrayList<CartPO>();
		if (cartitemMessage.isInvokeSuccess()) {
			po.add(new CartPO(memberID, cartitemMessage.getResultSet()));
			return new ResultMessage(true, po, "return books in cart");
		}
		return cartitemMessage;
	}

	@Override
	public ResultMessage updateCart(CartPO cartPO) throws RemoteException {
		ArrayList<ItemPO> items = cartPO.getItems();
		ResultMessage updateItemMessage = null;
		boolean success = true;
		for (ItemPO item : items) {
			updateItemMessage = cartItemDAO.updateCartItem(
					cartPO.getMemberID(), item);
			success &= updateItemMessage.isInvokeSuccess();
		}
		if (success) {
			return new ResultMessage(true, null, "update cart success");
		}
		return new ResultMessage(false, null, "update cart failed");
	}
}
