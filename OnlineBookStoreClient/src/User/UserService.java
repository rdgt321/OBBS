package User;

import RMI.Login;
import RMI.addMember;
import RMI.addUser;
import RMI.deleteUser;
import RMI.getAllBooks;
import RMI.getAllDirectories;
import RMI.getMembers;
import RMI.getPromotionList;
import RMI.getUsers;
import RMI.logout;
import RMI.modifyOrder;
import RMI.modifyUser;
import RMI.onlineValidate;
import RMI.queryOrder;
import RMI.queryUser;

public interface UserService extends Login, addUser, deleteUser, modifyUser,
		queryUser, addMember, queryOrder, modifyOrder, getAllBooks,
		getAllDirectories, getPromotionList, getMembers, getUsers,
		onlineValidate, logout {

}
