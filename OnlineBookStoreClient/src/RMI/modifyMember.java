package RMI;

import java.rmi.Remote;

import Member.MemberPO;

public interface modifyMember extends Remote {
	public ResultMessage modifyMember(MemberPO memberPO)
			throws java.rmi.RemoteException;
}
