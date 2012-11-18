package Sale;

import RMI.Pay;
import RMI.addOrder;
import RMI.addToCast;
import RMI.deleteFromCast;
import RMI.modifyOrder;
import RMI.queryOrder;
import RMI.updateStock;

public interface SaleService extends addToCast, deleteFromCast, addOrder,
		 Pay, updateStock {

}
