package RMI;

import java.io.File;
import java.rmi.Remote;

public interface getConfig extends Remote {
	public File getConfig() throws java.rmi.RemoteException;
}
