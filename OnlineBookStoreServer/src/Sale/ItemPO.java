package Sale;

import java.io.Serializable;

public class ItemPO implements Serializable{
	private int orderID;
	private int memberID;
	private String bookISBN;
	private double nowprice;
	private int count;

	public ItemPO(int orderID, int memberID, String bookISBN,
			double nowprice, int count) {
		super();
		this.orderID = orderID;
		this.memberID = memberID;
		this.bookISBN = bookISBN;
		this.nowprice = nowprice;
		this.count = count;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getmemberID() {
		return memberID;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public int getCount() {
		return count;
	}

	public double getNowprice() {
		return nowprice;
	}
}
