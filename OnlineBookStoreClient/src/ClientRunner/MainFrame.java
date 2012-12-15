package ClientRunner;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Book.BookServiceImpl;
import Book.BookUIController;
import Member.MemberServiceImpl;
import Member.MemberUIController;
import Member.NavigatePanel;
import Promotion.PromotionServiceImpl;
import Sale.SaleServiceImpl;
import Sale.SaleUIController;
import User.UserServiceImpl;
import User.UserUIController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private MemberUIController memberUIController = new MemberUIController(this);
	private BookUIController bookUIController = new BookUIController(this);
	private UserUIController userUIController = new UserUIController(this);
	private SaleUIController saleUIController = new SaleUIController(this);
	private Component contentpanel;

	Runnable paintThread = new Runnable() {
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public MainFrame() {
		setResizable(false);
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
		setLayout(null);
		init();
	}

	public void init() {
		
		Agent.bookService = new BookServiceImpl();
		Agent.memberService = new MemberServiceImpl();
		Agent.promotionService = new PromotionServiceImpl();
		Agent.saleService = new SaleServiceImpl();
		Agent.userService = new UserServiceImpl();
		
		memberUIController.createNavigateView(NavigatePanel.BEFORE_STATE);
		bookUIController.createMainView();
		this.requestFocus();
		new Thread(paintThread).start();

		// agent = new Agent(new BookServiceImpl(), new MemberServiceImpl(), new
		// PromotionServiceImpl(), new SaleServiceImpl(), new
		// UserServiceImpl());
	}

	@Override
	public Component add(Component comp) {
		contentpanel = comp;
		return super.add(comp);
	}

	public void clear() {
		if (contentpanel != null) {
			this.remove(contentpanel);
			repaint();
			validate();
		}
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setTitle("����ͼ�鹺��ϵͳ");
		frame.setSize(806, 628);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public MemberUIController getMemberUIController() {
		return memberUIController;
	}

	public BookUIController getBookUIController() {
		return bookUIController;
	}

	public SaleUIController getSaleUIController() {
		return saleUIController;
	}

	public UserUIController getUserUIController() {
		return userUIController;
	}

	protected void processWindowEvent(WindowEvent e) {
		boolean flag = false;
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int result = JOptionPane.showConfirmDialog(this, ("ȷ��Ҫ�˳�ϵͳ��?"),
					("��ʾ"), JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if(Agent.userAgent != null){
					try {
						if (Agent.userAgent.getUserType() == Const.MEMBER) {
							Agent.memberService.logout(Agent.userAgent);
						} else {
							Agent.userService.logout(Agent.userAgent);
						}

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
							}
			if (result == JOptionPane.NO_OPTION) {
				flag = true;
			}
		}
		if (!flag) {
			super.processWindowEvent(e);
		}
	}
}
