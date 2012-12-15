package Promotion;

import java.io.Serializable;
import java.util.Calendar;

public class CouponsPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 550472540109466945L;
	private int counponsID;
	private int ownerID;
	private double discountRate;
	private Calendar endDate;
	private boolean used;

	public CouponsPO(int counponsID, int ownerID, double discountRate,
			Calendar endDate, boolean used) {
		super();
		this.counponsID = counponsID;
		this.ownerID = ownerID;
		this.discountRate = discountRate;
		this.endDate = endDate;
		this.used = used;
	}

	public int getCounponsID() {
		return counponsID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public boolean isUsed() {
		return used;
	}
}
