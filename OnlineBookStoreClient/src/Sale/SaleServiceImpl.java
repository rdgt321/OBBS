package Sale;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ClientRunner.Const;
import RMI.ResultMessage;

public class SaleServiceImpl implements SaleService {
	private SaleService service_stub = null;

	public SaleServiceImpl() {
		try {
			service_stub = (SaleService) Naming.lookup(Const.SERVER
					+ "SaleService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultMessage addToCart(ItemPO itemPO, int memberID)
			throws RemoteException {
		return service_stub.addToCart(itemPO, memberID);
	}

	@Override
	public ResultMessage deleteFromCart(String bookISBN, int memberID)
			throws RemoteException {
		return service_stub.deleteFromCart(bookISBN, memberID);
	}

	@Override
	public ResultMessage addOrder(OrderPO orderPO) throws RemoteException {
		return service_stub.addOrder(orderPO);
	}

	@Override
	public ResultMessage getBooksInCart(int memberID) throws RemoteException {
		return service_stub.getBooksInCart(memberID);
	}

	@Override
	public ResultMessage updateCart(CartPO cartPO) throws RemoteException {
		return service_stub.updateCart(cartPO);
	}

}
