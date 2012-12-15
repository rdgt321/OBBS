package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

public class Server extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5311815554577548014L;
	Routines routines = null;
	BookService bookService = null;
	MemberService memberService = null;
	PromotionService promotionService = null;
	SaleService saleService = null;
	UserService userService = null;
	ServerView serverView = null;

	public Server() {
		super();
		init();
		checkEnvironment();
		createView();
		startService();
		startRoutines();
	}

	public void init() {
		// UI风格设置
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Const.loadConfig();
	}

	public void startService() {
		try {
			LocateRegistry.createRegistry(1099);
			bookService = new BookServiceImpl();
			memberService = new MemberServiceImpl();
			promotionService = new PromotionServiceImpl();
			saleService = new SaleServiceImpl();
			userService = new UserServiceImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// checkPromotionSuit(userAgent);
	}

	public void startRoutines() {
		routines = Routines.getInstance();
		routines.run();
	}

	public void createView() {
		setSize(806, 628);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("在线图书销售系统服务器端");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverView = new ServerView();
		setContentPane(serverView);
	}

	public void checkEnvironment() {
		boolean mysql = mysqlInstalled();
		if (!mysql) {
			int ok = JOptionPane.showConfirmDialog(this,
					"您的机器尚未安装MYSQL(或独立版)，无法运行本系统，请点击确定下载官方最新版本");
			if (ok == JOptionPane.OK_OPTION) {
				if (java.awt.Desktop.isDesktopSupported()) {
					try {
						// 创建一个URI实例
						java.net.URI uri = java.net.URI
								.create("http://www.mysql.com/downloads/");
						// 获取当前系统桌面扩展
						java.awt.Desktop dp = java.awt.Desktop.getDesktop();
						// 判断系统桌面是否支持要执行的功能
						if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
							// 获取系统默认浏览器打开链接
							dp.browse(uri);
						}
					} catch (java.lang.NullPointerException e) {
						// 此为uri为空时抛出异常
					} catch (java.io.IOException e) {
						// 此为无法获取系统默认浏览器
					}
				}
			}
			System.exit(0);
		}
	}

	/**
	 * 用RUNTIME执行CMD命令查询注册表是否有MYSQL服务来确定机器上是否安装了MYSQL
	 * 
	 * @return boolean - 是否安装MYSQL
	 */
	public boolean mysqlInstalled() {
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runtime
					.exec("reg query "
							+ "HKEY_LOCAL_MACHINE\\SYSTEM\\CURRENTCONTROLSET\\SERVICES\\MYSQL");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String out = "";
		try {
			while ((out = in.readLine()) != null) {
				if (out.matches("(.*)\\s+REG_(.*)")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out != null;
	}

	public void checkPromotionSuit(UserAgent userAgent) {

	}

	public static void main(String[] args) {
		Server server = new Server();
	}

}
