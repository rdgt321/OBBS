package RMI;

import java.rmi.Remote;

public interface useEquivalentBond extends Remote {
	public ResultMessage useEquivalentBond(int equivalentbondID) throws java.rmi.RemoteException;
}
