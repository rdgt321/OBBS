package Sale;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CartPO implements Serializable {
	int memberID;
	ArrayList<ItemPO> items;
	double totalprice = 0;

	public CartPO(int memberID, ArrayList<ItemPO> items) {
		this.memberID = memberID;
		this.items = items;
		calcPrice();
	}
	
	private void calcPrice(){
		for(ItemPO item:items){
			totalprice += item.getCount() * item.getNowprice();
		}
	}

	public int getMemberID() {
		return memberID;
	}

	public ArrayList<ItemPO> getItems() {
		return items;
	}

	public double getTotalprice() {
		return totalprice;
	}
}
