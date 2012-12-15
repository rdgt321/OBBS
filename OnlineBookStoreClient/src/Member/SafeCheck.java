package Member;

import java.util.Calendar;

/**@author zhangxufan*/

/*safe discipline*/
public class SafeCheck {
	
	//check whether the login name meets demand
	public static boolean isLegalName(String loginName){
		if(!nameWithinLimited(loginName))
			return false;
		if(!isDigitOrAlphabet(loginName))
			return false;
		return true;
	}
	
	//check whether the password meets demand
	public static boolean isLegalPassword(char[] password){
		if(!passwordWithinLimited(password))
			return false;
		if(!isDigitOrAlphabet(password))
			return false;
		return true;
	}
	
	//check whether the phone number meets demand
	public static boolean isLegalPhoneNumber(String phone){
		if(!phoneNumberProperLength(phone))
			return false;
		int length = phone.length();
		char[] temp = phone.toCharArray();
		for(int i = 0; i < length; i ++){
			if(!isDigit(temp[i]))
				return false;
		}
		return true;
	}
	
	//check whether the birth of the new register is legal
	@SuppressWarnings({ "static-access"})
	public static boolean isLegalBirth(Calendar calendar){
		if(calendar.YEAR == Calendar.getInstance().YEAR){
			if(calendar.MONTH > Calendar.getInstance().MONTH)
				return false;
			if(calendar.MONTH == Calendar.getInstance().MONTH)
				if(calendar.DATE > Calendar.getInstance().DATE)
					return false;
		}
		if(calendar.YEAR <= 1900 || calendar.YEAR > Calendar.getInstance().YEAR){
			return false;
		}
		if(calendar.MONTH <= 0 || calendar.MONTH > 12)
			return false;
		if(calendar.DATE <= 0 || calendar.DATE > Calendar.DAY_OF_MONTH)
			return false;
		return true;
	}
	
	//check whether the login name is within limited length ranging from 0~9
	private static boolean nameWithinLimited(String loginName){
		int length = loginName.length();
		return (length > 0 && length <= 9);
	}
	
	//check whether the password is within limited length ranging from 6~12
	private static boolean passwordWithinLimited(char[] password){
		int length = password.length;
		return (length >= 6 && length <= 12);
	}
	
	//check whether the length of the phone number is 11
	private static boolean phoneNumberProperLength(String phone){
		int length = phone.length();
		return (length == 11);
	}
	
	//check whether the login name is composed of only digit and alphabet
	private static boolean isDigitOrAlphabet(String name){
		char[] temp = name.toCharArray();
		int length = temp.length;
		for(int i = 0; i < length; i ++){
			if(!isDigit(temp[i]) && !isAlphabet(temp[i]))
				return false;
		}
		return true;
	}
	
	//check whether the password is composed of only digit and alphabet
	private static boolean isDigitOrAlphabet(char[] password){
		int length = password.length;
		for(int i = 0; i < length; i ++){
			if(!isDigit(password[i]) && !isAlphabet(password[i]))
				return false;
		}
		return true;
	}
	
	//check whether the character is a digit
	private static boolean isDigit(char c){
		if(c >= '0' && c <= '9')
			return true;
		return false;
	}
	
	//check whether the character is an alphabet
	private static boolean isAlphabet(char c){
		if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z' ))
			return true;
		return false;
	}
}
