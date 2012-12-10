package DBC;

import RMI.ResultMessage;
import Sale.ItemPO;

public interface CartItemDAO{
	public ResultMessage addCartItem(int memberID, ItemPO itemPO);

	public ResultMessage updateCartItem(int memberID, ItemPO itemPO);

	public ResultMessage deleteCartItem(int memberID,String bookISBN);

	public ResultMessage queryCartItem(int memberID, String booISBN);

	public ResultMessage clearCart(int memberID);
	
	public ResultMessage getBooksInCart(int memberID);
}
