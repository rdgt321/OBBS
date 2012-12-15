package DBC;

import RMI.ResultMessage;
import User.UserPO;

public interface UserDAO {
	public ResultMessage loginValidate(String ID, String password);

	public ResultMessage addUser(UserPO userPO);

	public ResultMessage deleteUser(int userID);

	public ResultMessage updateUser(UserPO userPO);
	
	public ResultMessage getUsers();

	public ResultMessage queryUser(String name);

	public ResultMessage queryUserByID(int userID);
}
