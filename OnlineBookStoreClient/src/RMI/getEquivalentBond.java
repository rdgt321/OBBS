package RMI;

import java.rmi.Remote;

public interface getEquivalentBond extends Remote {
	public ResultMessage getEquivalentBond(int memberID) throws java.rmi.RemoteException;
}
