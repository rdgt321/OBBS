package RMI;

import java.rmi.Remote;

import Member.MemberPO;


public interface addMember extends Remote {
	public ResultMessage addMember(MemberPO memberPO) throws java.rmi.RemoteException;
}
