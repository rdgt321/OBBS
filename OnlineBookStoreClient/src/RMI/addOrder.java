package RMI;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Calendar;


public interface addOrder extends Remote {
	public ResultMessage addOrder(String memberID, ArrayList<String> bookISBNs,
			double totalprice, Calendar date) throws java.rmi.RemoteException;
}
