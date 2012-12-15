package Promotion;

import java.io.Serializable;
import java.util.Calendar;

public class PromotionPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3743500415696307006L;
	private int promotionID;
	private int leastIntegral;
	private String name;
	private Calendar startDate, endDate;
	private double discountRate;
	private double equivalentDenomination;
	private double bondUseLimit;

	public PromotionPO(int promotionID, int leastIntegral, String name,
			Calendar startDate, Calendar endDate, double discountRate,
			double equivalentDenomination, double bondUseLimit) {
		super();
		this.promotionID = promotionID;
		this.leastIntegral = leastIntegral;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountRate = discountRate;
		this.equivalentDenomination = equivalentDenomination;
		this.bondUseLimit = bondUseLimit;
	}

	public int getLeastIntegral() {
		return leastIntegral;
	}

	public String getName() {
		return name;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public double getEquivalentDenomination() {
		return equivalentDenomination;
	}

	public int getPromotionID() {
		return promotionID;
	}

	public double getBondUseLimit() {
		return bondUseLimit;
	}
}
