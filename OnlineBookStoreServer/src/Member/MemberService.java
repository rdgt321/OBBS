package Member;

import RMI.Login;
import RMI.addMember;
import RMI.bookCollect;
import RMI.queryMember;
import RMI.deleteMember;
import RMI.modifyMember;
import RMI.queryRecord;

public interface MemberService extends Login, addMember, deleteMember,
		modifyMember, queryMember, queryRecord, bookCollect {

}
