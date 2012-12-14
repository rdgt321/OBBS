package DBC;

import Member.MemberPO;
import RMI.ResultMessage;

public interface MemberDAO {
	public ResultMessage addMember(MemberPO memberPO);

	public ResultMessage deleteMember(int memberID);

	public ResultMessage modifyMember(MemberPO memberPO);

	public ResultMessage queryMember(int memberID);

	public ResultMessage queryMemberByName(String memberName);

	public ResultMessage getMembers(int pagenum, int num_per_page);

	public ResultMessage getMembers();
	
	public ResultMessage getBirthMembers();

	public ResultMessage getTotalNum();

	public ResultMessage loginValidate(String ID, String password);
}
