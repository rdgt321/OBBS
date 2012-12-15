package User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ClientRunner.Const;
import Member.MemberPO;
import RMI.ResultMessage;
import RMI.UserAgent;
import Sale.OrderPO;

public class UserServiceImpl implements UserService {

	private UserService service_stub = null;

	public UserServiceImpl() {
		try {
			service_stub = (UserService) Naming.lookup(Const.SERVER
					+ "UserService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultMessage login(String ID, String password, String IP)
			throws RemoteException {
		return service_stub.login(ID, password, IP);
	}

	@Override
	public ResultMessage addUser(UserPO userPO) throws RemoteException {
		return service_stub.addUser(userPO);
	}

	@Override
	public ResultMessage deleteUser(int userID) throws RemoteException {
		return service_stub.deleteUser(userID);
	}

	@Override
	public ResultMessage modifyUser(UserPO userPO) throws RemoteException {
		return service_stub.modifyUser(userPO);
	}

	@Override
	public ResultMessage queryUser(String name) throws RemoteException {
		return service_stub.queryUser(name);
	}

	@Override
	public ResultMessage addMember(MemberPO memberPO) throws RemoteException {
		return service_stub.addMember(memberPO);
	}

	@Override
	public ResultMessage orderQuery(int orderID) throws RemoteException {
		return service_stub.orderQuery(orderID);
	}

	@Override
	public ResultMessage orderModify(OrderPO orderPO) throws RemoteException {
		return service_stub.orderModify(orderPO);
	}

	@Override
	public ResultMessage getAllBooks() throws RemoteException {
		return service_stub.getAllBooks();
	}

	@Override
	public ResultMessage getAllDirectories() throws RemoteException {
		return service_stub.getAllDirectories();
	}

	@Override
	public ResultMessage getPormotionList() throws RemoteException {
		return service_stub.getPormotionList();
	}

	@Override
	public ResultMessage onlineValidate(UserAgent userAgent)
			throws RemoteException {
		return service_stub.onlineValidate(userAgent);
	}

	@Override
	public ResultMessage logout(UserAgent userAgent) throws RemoteException {
		return service_stub.logout(userAgent);
	}

	@Override
	public ResultMessage getMembers() throws RemoteException {
		return service_stub.getMembers();
	}

	@Override
	public ResultMessage getUsers() throws RemoteException {
		return service_stub.getUsers();
	}

}
