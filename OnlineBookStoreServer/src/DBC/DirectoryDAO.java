package DBC;

import Book.DirectoryPO;
import RMI.ResultMessage;

public interface DirectoryDAO {
	public ResultMessage addDirectory(DirectoryPO directoryPO);

	public ResultMessage deleteDirectory(int directoryID);

	public ResultMessage modifyDirectory(DirectoryPO directoryPO);

	public ResultMessage getAllDirectories();

	public ResultMessage queryDirectoryByID(int directoryID);

	public ResultMessage queryDirectoryByName(String directroyName);

}
