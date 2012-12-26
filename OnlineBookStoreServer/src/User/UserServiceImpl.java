package User;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import DBC.BookDAO;
import DBC.DAOFactory;
import DBC.DirectoryDAO;
import DBC.MemberDAO;
import DBC.OrderDAO;
import DBC.OrderItemDAO;
import DBC.PromotionDAO;
import DBC.UserDAO;
import Member.MemberPO;
import RMI.ResultMessage;
import RMI.UserAgent;
import Sale.ItemPO;
import Sale.OrderPO;
import Server.Const;
import Server.UserPool;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5323601938545642523L;
	private UserDAO userDAO = null;
	private OrderDAO orderDAO = null;
	private BookDAO bookDAO = null;
	private PromotionDAO promotionDAO = null;
	private DirectoryDAO directoryDAO = null;
	private MemberDAO memberDAO = null;
	private OrderItemDAO orderItemDAO = null;

	public UserServiceImpl() throws RemoteException {
		super();
		try {
			Naming.bind("rmi://localhost:1099/UserService", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		userDAO = DAOFactory.getUserDAO();
		orderDAO = DAOFactory.getOrderDAO();
		bookDAO = DAOFactory.getBookDAO();
		promotionDAO = DAOFactory.getPromotionDAO();
		directoryDAO = DAOFactory.getDirectoryDAO();
		memberDAO = DAOFactory.getMemberDAO();
		orderItemDAO = DAOFactory.getOrderItemDAO();
	}

	@Override
	public ResultMessage login(String ID, String password, String IP, int type)
			throws RemoteException {
		if (UserPool.getAgents().getSize() >= Const.MAX_CLIENT) {
			return new ResultMessage(false, null, "服务器繁忙，请稍后再试");
		}
		ResultMessage resultMessage = userDAO.loginValidate(ID, password);
		if (resultMessage.isInvokeSuccess()) {
			UserPO user = (UserPO) resultMessage.getResultSet().get(0);
			UserAgent userAgent = new UserAgent(user.getID(), user.getName(),
					user.getPassword(), user.getType());
			userAgent.lastRequest = System.currentTimeMillis();
			userAgent.ip = IP;
			if (userAgent.getUserType() != type) {
				return new ResultMessage(false, null, "无此类型用户名的用户存在");
			}
			if (UserPool.isOnline(userAgent)) {
				return new ResultMessage(false, null, "用户已经登录，请稍后再试");
			}
			UserPool.connect(userAgent);
			ArrayList<UserAgent> agent = new ArrayList<UserAgent>();
			agent.add(userAgent);
			return new ResultMessage(true, agent, "登录成功");
		}
		return resultMessage;
	}

	@Override
	public ResultMessage addUser(UserPO userPO) throws RemoteException {
		return userDAO.addUser(userPO);
	}

	@Override
	public ResultMessage deleteUser(int userID) throws RemoteException {
		return userDAO.deleteUser(userID);
	}

	@Override
	public ResultMessage modifyUser(UserPO userPO) throws RemoteException {
		return userDAO.updateUser(userPO);
	}

	@Override
	public ResultMessage queryUser(String name) throws RemoteException {
		return userDAO.queryUser(name);
	}

	@Override
	public ResultMessage addMember(MemberPO memberPO) throws RemoteException {
		return memberDAO.addMember(memberPO);
	}

	@Override
	public ResultMessage orderQuery(int orderID) throws RemoteException {
		return orderDAO.orderQuery(orderID);
	}

	@Override
	public ResultMessage orderModify(OrderPO orderPO) throws RemoteException {
		ResultMessage orderMessage = orderDAO.updateOrder(orderPO);
		if (orderMessage.isInvokeSuccess()) {
			boolean success = true;
			ArrayList<ItemPO> items = orderPO.getBooks();
			for (ItemPO item : items) {
				success &= orderItemDAO.updateOrderItem(orderPO.getOrderID(),
						item).isInvokeSuccess();
			}
			if (success) {
				return new ResultMessage(true, null, "update order success");
			}
		}
		return orderMessage;
	}

	@Override
	public ResultMessage getAllBooks() throws RemoteException {
		return bookDAO.queryAllBooks();
	}

	@Override
	public ResultMessage getAllDirectories() throws RemoteException {
		return directoryDAO.getAllDirectories();
	}

	@Override
	public ResultMessage getPormotionList() throws RemoteException {
		return promotionDAO.getPromotionList();
	}

	@Override
	public ResultMessage onlineValidate(UserAgent userAgent)
			throws RemoteException {
		UserPool.onlineValidate(userAgent);
		return null;
	}

	@Override
	public ResultMessage logout(UserAgent userAgent) throws RemoteException {
		UserPool.disconnect(userAgent);
		return null;
	}

	@Override
	public ResultMessage getMembers() throws RemoteException {
		return memberDAO.getMembers();
	}

	@Override
	public ResultMessage getUsers() throws RemoteException {
		return userDAO.getUsers();
	}

}
