package RMI;

import java.rmi.Remote;
import java.util.Calendar;

import Member.MemberPO;


public interface addMember extends Remote {
	public ResultMessage addMember(MemberPO memberPO) throws java.rmi.RemoteException;
}
