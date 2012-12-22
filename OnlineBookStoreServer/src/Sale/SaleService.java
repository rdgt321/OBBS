package Sale;

import RMI.addOrder;
import RMI.addToCart;
import RMI.deleteFromCart;
import RMI.getBooksInCart;
import RMI.updateCart;

public interface SaleService extends addToCart, deleteFromCart, addOrder,
		getBooksInCart, updateCart{

}
