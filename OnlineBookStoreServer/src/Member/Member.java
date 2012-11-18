package Member;

import java.util.Calendar;

public class Member {
	String ID;
	String name;
	String password;
	String phone;
	Calendar birth;
	int integral;
	
	public Member(String name, String password, String phone,
			Calendar birth) {
		super();
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
	}

	public String getID() {
		return ID;
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
