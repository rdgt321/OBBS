package DBC;

import RMI.ResultMessage;

public interface RateDAO {
	public ResultMessage addRate(String bookISBN, int memberID, double rate);

	public ResultMessage queryRate(String bookISBN, int memberID);

	public ResultMessage getRate(String bookISBN);
}
