package Member;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

import Book.BookPO;
import DBC.BookDAO;
import DBC.CartItemDAO;
import DBC.CartItemDAOImpl;
import DBC.CollectDAO;
import DBC.CouponsDAO;
import DBC.DAOFactory;
import DBC.EquivalentBondDAO;
import DBC.MemberDAO;
import DBC.OrderDAO;
import DBC.OrderItemDAO;
import RMI.ResultMessage;
import RMI.UserAgent;
import Sale.ItemPO;
import Sale.OrderPO;
import Server.Const;
import Server.UserPool;
import User.UserPO;

public class MemberServiceImpl extends UnicastRemoteObject implements
		MemberService {
	private BookDAO bookDAO = null;
	private CollectDAO collectDAO = null;
	private MemberDAO memberDAO = null;
	private OrderDAO orderDAO = null;
	private OrderItemDAO orderItemDAO = null;

	public MemberServiceImpl() throws RemoteException {
		super();
		try {
			Naming.bind("rmi://localhost:1099/MemberService", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		bookDAO = DAOFactory.getBookDAO();
		collectDAO = DAOFactory.getCollectDAO();
		memberDAO = DAOFactory.getMemberDAO();
		orderDAO = DAOFactory.getOrderDAO();
		orderItemDAO = DAOFactory.getOrderItemDAO();
	}

	@Override
	public ResultMessage login(String ID, String password)
			throws RemoteException {
		ResultMessage resultMessage = memberDAO.loginValidate(ID, password);
		if (resultMessage.isInvokeSuccess()) {
			MemberPO member = (MemberPO) resultMessage.getResultSet().get(0);
			UserAgent userAgent = new UserAgent(member.getID(),
					member.getName(), member.getPassword(), Const.MEMBER);
			userAgent.lastRequest = System.currentTimeMillis();
			if (UserPool.isOnline(userAgent)) {
				return new ResultMessage(false, null, "用户已经登录，请稍后再试");
			}
			UserPool.connect(userAgent);
			ArrayList<UserAgent> agent = new ArrayList<UserAgent>();
			agent.add(userAgent);
			return new ResultMessage(true, agent, "login success agent return");
		}
		return resultMessage;
	}

	@Override
	public ResultMessage addMember(MemberPO memberPO) throws RemoteException {
		return memberDAO.addMember(memberPO);
	}

	@Override
	public ResultMessage deleteMember(int memberID) throws RemoteException {
		return memberDAO.deleteMember(memberID);
	}

	@Override
	public ResultMessage modifyMember(MemberPO memberPO) throws RemoteException {
		return memberDAO.modifyMember(memberPO);
	}

	@Override
	public ResultMessage queryMember(int memberID) throws RemoteException {
		return memberDAO.queryMember(memberID);
	}

	@Override
	public ResultMessage purchaseQuery(int memberID) throws RemoteException {
		ArrayList<OrderPO> orderlist = null;
		try {
			ResultMessage orders = orderDAO.getOrder(memberID);
			orderlist = orders.getResultSet();
			ResultMessage items = null;
			for (int i = 0; i < orderlist.size(); i++) {
				OrderPO order = (OrderPO) orderlist.get(i);
				items = orderItemDAO.getOrderItems(order.getOrderID());
				order.setBooks(items.getResultSet());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage(false, null, "query fail,try again");
		}
		return new ResultMessage(true, orderlist, "query ok,orders return");

	}

	@Override
	public ResultMessage bookCollect(String bookISBN, int memberID)
			throws RemoteException {
		return collectDAO.bookCollect(bookISBN, memberID);
	}

	@Override
	public ResultMessage cancelCollect(String bookISBN, int memberID)
			throws RemoteException {
		return collectDAO.cancelCollect(bookISBN, memberID);
	}

	@Override
	public ResultMessage getCollectedBook(int memebrID) throws RemoteException {
		ResultMessage collectMessage = collectDAO.getCollectedBook(memebrID);
		if (collectMessage.isInvokeSuccess()) {
			ArrayList<BookPO> books = collectMessage.getResultSet();
			books = bookDAO.fill(books).getResultSet();
			return new ResultMessage(true, books, "collected books return");
		}
		return collectMessage;
	}

	@Override
	public ResultMessage onlineValidate(UserAgent userAgent)
			throws RemoteException {
		UserPool.onlineValidate(userAgent);
		return null;
	}

}
