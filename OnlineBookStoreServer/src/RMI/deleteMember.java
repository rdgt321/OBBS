package RMI;

import java.rmi.Remote;

import Member.MemberPO;

public interface deleteMember extends Remote {
	public ResultMessage deleteMember(int memberID)
			throws java.rmi.RemoteException;
}
