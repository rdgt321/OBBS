package DBC;

import RMI.ResultMessage;
import Sale.OrderPO;

public interface OrderDAO{
	public ResultMessage addOrder(OrderPO orderPO);
	
	public ResultMessage deleteOrder(int orderID);
	
	public ResultMessage updateOrder(OrderPO orderPO);
	
	public ResultMessage orderQuery(int orderID);
	
	public ResultMessage getOrder(int memberID);
}
