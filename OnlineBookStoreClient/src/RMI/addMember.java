package RMI;

import java.rmi.Remote;
import java.util.Calendar;


public interface addMember extends Remote {
	public ResultMessage addMember(String name, String password, String phone,
			Calendar birth) throws java.rmi.RemoteException;
}
