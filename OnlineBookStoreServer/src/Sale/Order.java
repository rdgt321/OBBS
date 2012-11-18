package Sale;

import java.util.ArrayList;
import java.util.Calendar;

import Book.Book;
import Member.Member;

public class Order {
	String memberID;
	ArrayList<String> bookISBNs;
	double totalprice;
	Calendar date;
	int state;
}
