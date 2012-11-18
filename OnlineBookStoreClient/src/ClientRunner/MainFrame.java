package ClientRunner;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Book.BookUIController;
import Member.MemberUIController;
import User.UserUIController;

public class MainFrame extends JFrame {
	private MemberUIController memberUIController = new MemberUIController(this);
	private BookUIController bookUIController = new BookUIController(this);
	private UserUIController userUIController = new UserUIController(this);
	private Component contentpanel;

	Runnable paintThread = new Runnable() {

		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(33);
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
		 memberUIController.createNavigateView();
		 bookUIController.createMainView();
		// userUIController.createManagerView();
		//userUIController.createSalesManagerView();
		this.requestFocus();
		new Thread(paintThread).start();

	}

	@Override
	public Component add(Component comp) {
		contentpanel = comp;
		return super.add(comp);
	}

	public void clear() {
		if (contentpanel != null) {
			this.remove(contentpanel);
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

}
