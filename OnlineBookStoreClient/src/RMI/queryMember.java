package RMI;

import java.rmi.Remote;

import Member.MemberPO;

public interface queryMember extends Remote {
	public ResultMessage queryMember(int memberID)
			throws java.rmi.RemoteException;
}
