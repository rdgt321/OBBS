package Sale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class OrderPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3301251128897213126L;
	int orderID;
	int memberID;
	ArrayList<ItemPO> books;
	double totalprice;
	Calendar date;
	int state;
	
	public OrderPO(int orderID,int memberID, ArrayList<ItemPO> books, double totalprice,
			Calendar date, int state) {
		super();
		this.orderID = orderID;
		this.memberID = memberID;
		this.books = books;
		this.totalprice = totalprice;
		this.date = date;
		this.state = state;
	}
	
	
	public int getOrderID(){
		return orderID;
	}

	public int getMemberID() {
		return memberID;
	}

	public ArrayList<ItemPO> getBooks() {
		return books;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public Calendar getDate() {
		return date;
	}

	public int getState() {
		return state;
	}


	public void setBooks(ArrayList<ItemPO> books) {
		this.books = books;
	}
}
