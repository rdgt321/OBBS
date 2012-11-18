package RMI;

import java.rmi.Remote;
import java.util.Calendar;

public interface modifyMember extends Remote {
	public ResultMessage modifyMember(String memberID, String name,
			String password, String phone, Calendar birth)
			throws java.rmi.RemoteException;
}
