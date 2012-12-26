package Member;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*safe discipline*/
public class SafeCheck {

	// check whether the login name meets demand
	public static boolean isLegalName(String loginName) {
		if (loginName.equals("")) {
			return false;
		}
		if (!nameWithinLimited(loginName))
			return false;
		if (!isDigitOrAlphabet(loginName))
			return false;
		return true;
	}

	// check whether the password meets demand
	public static boolean isLegalPassword(char[] password) {
		if (!passwordWithinLimited(password))
			return false;
		if (!isDigitOrAlphabet(password))
			return false;
		String passwordString = new String(password);
		if (passwordString.equals("")) {
			return false;
		}
		return true;
	}

	public static boolean passwordEnsure(String password, String ensurepassword) {
		if (password.compareTo(ensurepassword) == 0) {
			return true;
		} else {
			return false;
		}
	}

	// check whether the phone number meets demand
	public static boolean isLegalPhoneNumber(String phone) {
		if (phone.equals("")) {
			return false;
		}
		if (!phoneNumberProperLength(phone))
			return false;
		int length = phone.length();
		char[] temp = phone.toCharArray();
		for (int i = 0; i < length; i++) {
			if (!isDigit(temp[i]))
				return false;
		}
		return true;
	}

	// check whether the birth of the new register is legal
	@SuppressWarnings({ "static-access" })
	public static boolean isLegalBirth(Calendar calendar) {
		int year = calendar.get(calendar.YEAR);
		int std_year = Calendar.getInstance().get(Calendar.getInstance().YEAR);
		int month = calendar.get(calendar.MONTH) + 1;
		int date = calendar.get(calendar.DATE);
		if (year == std_year) {
			int std_month = Calendar.getInstance().get(
					Calendar.getInstance().MONTH) + 1;
			if (month > std_month)
				return false;
			if (month == std_month) {
				int std_date = Calendar.getInstance().get(
						Calendar.getInstance().DATE);
				if (date > std_date)
					return false;
			}
		}
		if (year <= 1900 || year > std_year) {
			return false;
		}
		if (month <= 0 || month > 12)
			return false;
		if (date <= 0 || date > calendar.get(calendar.DAY_OF_MONTH))
			return false;
		return true;
	}

	// check whether the login name is within limited length ranging from 0~9
	private static boolean nameWithinLimited(String loginName) {
		int length = loginName.length();
		return (length > 0 && length <= 9);
	}

	// check whether the password is within limited length ranging from 6~12
	private static boolean passwordWithinLimited(char[] password) {
		int length = password.length;
		return (length >= 6 && length <= 12);
	}

	// check whether the length of the phone number is 11
	private static boolean phoneNumberProperLength(String phone) {

		int length = phone.length();
		return (length == 11);
	}

	// check whether the login name is composed of only digit and alphabet
	private static boolean isDigitOrAlphabet(String name) {
		char[] temp = name.toCharArray();
		int length = temp.length;
		for (int i = 0; i < length; i++) {
			if (!isDigit(temp[i]) && !isAlphabet(temp[i]))
				return false;
		}
		return true;
	}

	// check whether the password is composed of only digit and alphabet
	private static boolean isDigitOrAlphabet(char[] password) {
		int length = password.length;
		for (int i = 0; i < length; i++) {
			if (!isDigit(password[i]) && !isAlphabet(password[i]))
				return false;
		}
		return true;
	}

	// check whether the character is a digit
	private static boolean isDigit(char c) {
		if (c >= '0' && c <= '9')
			return true;
		return false;
	}

	// check whether the character is an alphabet
	private static boolean isAlphabet(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		return false;
	}

	// check whether the equiValue is legal
	public static boolean isLegalValue(String value) {
		if (!value.equals("")) {
			char[] temp = value.toCharArray();
			int k = 0;
			for (int i = 0; i < temp.length; i++) {
				if (!(temp[i] >= '0' && temp[i] <= '9'))
					k = k + 1;
			}
			if (k == 0 && Integer.parseInt(value) > 0) {
				return true;
			}
		}
		return false;
	}

	// check weather the endDate is legal
	public static boolean isLegalEndDate(Calendar startDate, Calendar endDate) {
		if (startDate.compareTo(endDate) < 0) {
			return true;
		}
		return false;
	}

	// check weather the startDate is legal
	public static boolean isLegalStartDate(Calendar startDate) {
		Calendar calendar = new GregorianCalendar(Calendar.getInstance().get(
				Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		if (startDate.compareTo(calendar) >= 0) {
			return true;
		}
		return false;
	}
}
