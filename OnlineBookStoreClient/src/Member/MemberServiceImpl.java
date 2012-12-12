package Member;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RMI.ResultMessage;
import RMI.UserAgent;

public class MemberServiceImpl implements MemberService {
	private MemberService service_stub = null;

	public MemberServiceImpl() {
		try {
			service_stub = (MemberService) Naming.lookup("MemberService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultMessage login(String ID, String password)
			throws RemoteException {
		return service_stub.login(ID, password);
	}

	@Override
	public ResultMessage addMember(MemberPO memberPO) throws RemoteException {
		return service_stub.addMember(memberPO);
	}

	@Override
	public ResultMessage deleteMember(int memberID) throws RemoteException {
		return service_stub.deleteMember(memberID);
	}

	@Override
	public ResultMessage modifyMember(MemberPO memberPO) throws RemoteException {
		return service_stub.modifyMember(memberPO);
	}

	@Override
	public ResultMessage queryMember(int memberID) throws RemoteException {
		return service_stub.queryMember(memberID);
	}

	@Override
	public ResultMessage purchaseQuery(int memberID) throws RemoteException {
		return service_stub.purchaseQuery(memberID);
	}

	@Override
	public ResultMessage bookCollect(String bookISBN, int memberID)
			throws RemoteException {
		return service_stub.bookCollect(bookISBN, memberID);
	}

	@Override
	public ResultMessage cancelCollect(String bookISBN, int memberID)
			throws RemoteException {
		return service_stub.cancelCollect(bookISBN, memberID);
	}

	@Override
	public ResultMessage getCollectedBook(int memebrID) throws RemoteException {
		return service_stub.getCollectedBook(memebrID);
	}

	@Override
	public ResultMessage getCoupons(int memberID) throws RemoteException {
		return service_stub.getCoupons(memberID);
	}

	@Override
	public ResultMessage getEquivalentBond(int memberID) throws RemoteException {
		return service_stub.getEquivalentBond(memberID);
	}

	@Override
	public ResultMessage onlineValidate(UserAgent userAgent)
			throws RemoteException {
		return service_stub.onlineValidate(userAgent);
	}

}
