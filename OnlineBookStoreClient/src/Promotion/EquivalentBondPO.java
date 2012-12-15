package Promotion;

import java.io.Serializable;
import java.util.Calendar;

public class EquivalentBondPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3534068356576591377L;
	private int equivalentBondID;
	private int ownerID;
	private double useLimit;
	private double equivalentDenomination;
	private Calendar endDate;
	private boolean used;

	public EquivalentBondPO(int equivalentBondID, int ownerID, double useLimit,
			double equivalentDenomination, Calendar endDate, boolean used) {
		super();
		this.equivalentBondID = equivalentBondID;
		this.ownerID = ownerID;
		this.useLimit = useLimit;
		this.equivalentDenomination = equivalentDenomination;
		this.endDate = endDate;
		this.used = used;
	}

	public int getEquivalentBondID() {
		return equivalentBondID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public double getEquivalentDenomination() {
		return equivalentDenomination;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public boolean isUsed() {
		return used;
	}

	public double getUseLimit() {
		return useLimit;
	}

}
