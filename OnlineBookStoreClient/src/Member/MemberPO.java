package Member;

import java.io.Serializable;
import java.util.Calendar;

public class MemberPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8868931573080191062L;
	private int memberID;
	private String name;
	private String password;
	private String phone;
	private Calendar birth;
	private int integral;

	public MemberPO(int memberID, String name, String password, String phone,
			Calendar birth, int integral) {
		super();
		this.memberID = memberID;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
		this.integral = integral;
	}

	public int getID() {
		return memberID;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public Calendar getBirth() {
		return birth;
	}

	public int getIntegral() {
		return integral;
	}
}
