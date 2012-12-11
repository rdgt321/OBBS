package ClientRunner;

import java.rmi.RemoteException;

import Book.BookService;
import Book.BookServiceImpl;
import Member.MemberService;
import Member.MemberServiceImpl;
import Promotion.PromotionService;
import Promotion.PromotionServiceImpl;
import RMI.ResultMessage;
import RMI.UserAgent;
import Sale.SaleService;
import Sale.SaleServiceImpl;
import User.UserService;
import User.UserServiceImpl;

public class test {

	public static void main(String[] args) {
		BookService bookService = new BookServiceImpl();
		MemberService memberService = new MemberServiceImpl();
		PromotionService promotionService = new PromotionServiceImpl();
		SaleService saleService = new SaleServiceImpl();
		UserService userService = new UserServiceImpl();
		ResultMessage resultMessage = null;
		UserAgent userAgent = null;
		try {
			resultMessage = userService.login("admin", "123456");
			System.out.println(resultMessage.isInvokeSuccess());
			System.out.println(resultMessage.getPostScript());
			userAgent = (UserAgent) resultMessage.getResultSet().get(0);
			userService.onlineValidate(userAgent);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
