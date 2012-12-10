package DBC;

import RMI.ResultMessage;

public interface CollectDAO {
	public ResultMessage bookCollect(String bookISBN, int memberID);

	public ResultMessage queryCollect(int memberID, String bookISBN);

	public ResultMessage cancelCollect(String bookISBN, int memberID);

	public ResultMessage getCollectedBook(int memebrID);
}
