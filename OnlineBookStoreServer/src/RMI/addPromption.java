package RMI;

import java.rmi.Remote;
import java.util.Calendar;


public interface addPromption extends Remote {
	public ResultMessage addPromotion(int leastIntegral, Calendar startDate,
			Calendar endDate, double discountRate, double EquivalentDenomination)
			throws java.rmi.RemoteException;
}
