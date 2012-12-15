package Test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Calendar;

import Book.BookPO;
import Book.BookService;
import Book.BookServiceImpl;
import Member.MemberPO;
import Member.MemberService;
import Member.MemberServiceImpl;
import Server.Const;
import User.UserService;

public class test {

	public static void main(String[] args) {
		// Const.loadConfig();
		// Connection con = ConnectionFactory.getConnection();
		// String sql =
		// "insert into order_item(orderid,memberid,bookisbn,nowprice,count) values (?,?,?,?,?)";
		// PreparedStatement ps;
		// int row = 0;
		// try {
		// ps = con.prepareStatement(sql);
		// ps.setInt(1, 4);
		// ps.setInt(2, 1);
		// ps.setString(3, "testisbn4");
		// ps.setDouble(4, 0.1);
		// ps.setInt(5, 4);
		// row =ps.executeUpdate();
		// con.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// System.out.println(row);

		// test test = new test();
		// try {
		// System.out.println(URLDecoder.decode(test.getClass()
		// .getResource("").getPath(), "UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }

		// Calendar today = new GregorianCalendar(2012, 11, 15);
		// Calendar today2 = Calendar.getInstance();
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(simpleDateFormat.format(today.getTime()));
		// System.out.println(simpleDateFormat.format(today2.getTime()));

		try {
			Const.loadConfig();
			LocateRegistry.createRegistry(1099);
			BookService bookService = new BookServiceImpl();
			MemberService memberService = new MemberServiceImpl();
			System.out.println(bookService.addBook(
					new BookPO("test", "test", "test", "test", "test", 1,
							Calendar.getInstance(), 0, 0)).isInvokeSuccess());
			System.out.println(memberService.addMember(
					new MemberPO(-1, "testcollect", "123", "1111111", Calendar
							.getInstance())).isInvokeSuccess());
			memberService.bookCollect("test", 1);
			ArrayList<BookPO> books = memberService.getCollectedBook(1)
					.getResultSet();
			System.out.println(books.size());
			System.out.println(books.get(0).getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}