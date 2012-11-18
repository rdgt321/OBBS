package User;

import RMI.Login;
import RMI.addMember;
import RMI.addUser;
import RMI.deleteUser;
import RMI.modifyOrder;
import RMI.modifyUser;
import RMI.queryOrder;
import RMI.queryUser;

public interface UserService extends Login, addUser, deleteUser, modifyUser,
		queryUser, addMember, queryOrder, modifyOrder {

}
