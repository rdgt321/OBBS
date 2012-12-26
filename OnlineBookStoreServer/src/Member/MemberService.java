package Member;

import RMI.Login;
import RMI.addMember;
import RMI.bookCollect;
import RMI.bookPurchased;
import RMI.cancelCollect;
import RMI.deleteMember;
import RMI.getCollectedBook;
import RMI.getCoupons;
import RMI.getEquivalentBond;
import RMI.getMessage;
import RMI.logout;
import RMI.modifyMember;
import RMI.onlineValidate;
import RMI.paymentFinishi;
import RMI.queryMember;
import RMI.queryRecord;
import RMI.readMessage;
import RMI.useCoupons;
import RMI.useEquivalentBond;
import RMI.useIntegral;

public interface MemberService extends Login, addMember, deleteMember,
		modifyMember, queryMember, queryRecord, bookPurchased, bookCollect,
		cancelCollect, getCollectedBook, getCoupons, getEquivalentBond,
		useCoupons, useEquivalentBond, useIntegral, paymentFinishi,
		onlineValidate, logout, getMessage, readMessage {

}
