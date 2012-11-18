package Sale;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

import RMI.ResultMessage;

public class SaleServiceController extends UnicastRemoteObject implements
		SaleService {

	protected SaleServiceController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage addToCast(String bookISBN) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteFromCast(String BookISBN) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addOrder(String memberID, ArrayList<String> bookISBNs,
			double totalprice, Calendar date) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage pay(int amount) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateStock() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
