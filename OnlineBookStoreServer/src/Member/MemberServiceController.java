package Member;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import RMI.ResultMessage;

public class MemberServiceController extends UnicastRemoteObject implements
		MemberService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6042463106550629877L;

	public static void main(String[] args) {
		try {
			MemberServiceController m = new MemberServiceController();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	protected MemberServiceController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		LocateRegistry.createRegistry(1099);
		try {
			Naming.bind("rmi://localhost:1099/MemberService", this);
			System.out.println("testReady");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultMessage login(String ID, String password)
			throws RemoteException {
		System.out.println("test");
		return null;
	}

	@Override
	public ResultMessage addMember(String name, String password, String phone,
			Calendar birth) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteMember(String memberID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyMember(String memberID, String name,
			String password, String phone, Calendar birth)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage queryMember(String memberID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage purchaseQuery(String memberID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage bookCollect(String bookISBN) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
