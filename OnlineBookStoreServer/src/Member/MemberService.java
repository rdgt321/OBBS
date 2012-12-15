package Member;

import RMI.Login;
import RMI.addMember;
import RMI.bookCollect;
import RMI.cancelCollect;
import RMI.getCollectedBook;
import RMI.getCoupons;
import RMI.getEquivalentBond;
import RMI.getMembers;
import RMI.getMessage;
import RMI.logout;
import RMI.onlineValidate;
import RMI.queryMember;
import RMI.deleteMember;
import RMI.modifyMember;
import RMI.queryRecord;

public interface MemberService extends Login, addMember, deleteMember,
		modifyMember, queryMember, queryRecord, bookCollect, cancelCollect,
		getCollectedBook, getCoupons, getEquivalentBond, onlineValidate,
		logout, getMessage {

}
