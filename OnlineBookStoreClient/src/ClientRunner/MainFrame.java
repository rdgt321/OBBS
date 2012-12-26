package ClientRunner;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Book.BookUIController;
import Member.MemberUIController;
import Member.NavigatePanel;
import Sale.SaleUIController;
import User.UserUIController;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private MemberUIController memberUIController = new MemberUIController(this);
	private BookUIController bookUIController = new BookUIController(this);
	private UserUIController userUIController = new UserUIController(this);
	private SaleUIController saleUIController = new SaleUIController(this);
	private Component contentpanel;

	public MainFrame() {
		init();
		createView();
		startService();
	}

	private void init() {
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
		IMGSTATIC.startLoading();
		Cache.loadFromCache();
	}

	private void createView() {
		setTitle("在线图书销售系统");
		setIconImage(Toolkit.getDefaultToolkit().getImage("materials/icon.png"));
		setSize(806, 628);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		memberUIController.createNavigateView(NavigatePanel.BEFORE_STATE);
		bookUIController.createMainView();
	}

	private void startService() {
		new Thread(Routines.getInstance()).start();
		while (!Agent.alive) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		bookUIController.refreshMainView();
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
			int result = JOptionPane.showConfirmDialog(this, ("确定要退出系统吗？"),
					("提示"), JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (Agent.userAgent != null) {
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
