package ClientRunner;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Book.BookService;
import Book.BookServiceImpl;
import Member.MemberService;
import Member.MemberServiceImpl;
import Promotion.PromotionService;
import Promotion.PromotionServiceImpl;
import RMI.UserAgent;
import Sale.SaleService;
import Sale.SaleServiceImpl;
import User.UserService;
import User.UserServiceImpl;

public class Agent {
	public static boolean alive = false;
	public static BookService bookService;
	public static MemberService memberService;
	public static PromotionService promotionService;
	public static SaleService saleService;
	public static UserService userService;
	public static UserAgent userAgent;

	public static void startService() {
		boolean success = true;
		try {
			bookService = new BookServiceImpl();
			memberService = new MemberServiceImpl();
			promotionService = new PromotionServiceImpl();
			saleService = new SaleServiceImpl();
			userService = new UserServiceImpl();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			success = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			success = false;
		} catch (NotBoundException e) {
			e.printStackTrace();
			success = false;
		}
		if (success) {
			alive = true;
		} else {
			alive = false;
			ImageDialog.showNOImage(null, "与服务器连接失败T.T请稍后再试");
		}
	}

	public static void onlineValidate() {
		boolean success = true;
		if (userAgent != null) {
			if (userAgent.getUserType() == Const.MEMBER) {
				try {
					memberService.onlineValidate(userAgent);
				} catch (RemoteException e) {
					e.printStackTrace();
					success = false;
				}
			} else {
				try {
					userService.onlineValidate(userAgent);
				} catch (RemoteException e) {
					e.printStackTrace();
					success = false;
				}
			}
			if (!success) {
				ImageDialog.showNOImage(null, "与服务器连接断开T.T请检查网络或等待服务器开启");
				alive = false;
			}
		}
	}
}
