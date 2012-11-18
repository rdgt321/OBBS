package RMI;

public interface searchBook {
	public ResultMessage searchBook(String searchString)
			throws java.rmi.RemoteException;
}
