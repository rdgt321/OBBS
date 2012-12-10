package RMI;

import java.rmi.Remote;

public interface getTicket extends Remote {
	public ResultMessage getTicket() throws java.rmi.RemoteException;

}
