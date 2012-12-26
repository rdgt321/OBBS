package DBC;

import RMI.ResultMessage;
import Sale.ItemPO;

public interface OrderItemDAO {
	public ResultMessage addOrderItem(int orderID, ItemPO itemPO);

	public ResultMessage updateOrderItem(int orderID, ItemPO itemPO);

	public ResultMessage deleteOrderItem(int orderID, ItemPO itemPO);

	public ResultMessage queryOrderItem(int orderID, ItemPO itemPO);

	public ResultMessage queryOrderItem(String bookISBN, int memberID);

	public ResultMessage getOrderItems(int orderID);
}
