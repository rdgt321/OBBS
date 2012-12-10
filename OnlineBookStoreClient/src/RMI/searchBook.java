package RMI;

public interface searchBook {
	public ResultMessage searchBook(String searchString,int type)
			throws java.rmi.RemoteException;
}
