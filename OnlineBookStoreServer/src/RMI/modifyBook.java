package RMI;

import java.rmi.Remote;
import java.util.Calendar;


public interface modifyBook extends Remote {
	public ResultMessage modifyBook(String name, String ISBN, String author,
			String press, String description, String directoryID,
			Calendar date, double price, double specialprice)
			throws java.rmi.RemoteException;
}
