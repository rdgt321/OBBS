package Member;

import javax.swing.JPanel;

import Book.BookUIController;
import ClientRunner.MainFrame;

public class MemberUIController {
	private MainFrame mainFrame;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private NavigatePanel navigatePanel;
	private BookCollectionPanel bookCollectionPanel;
	private CartPanel cartPanel;
	private InforCenterPanel inforCenterPanel;
	private JPanel panelNow = null;

	public MemberUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void createNavigateView() {
		navigatePanel = new NavigatePanel(this);
		navigatePanel.init(navigatePanel.AFTER_STATE);
		mainFrame.add(navigatePanel);
	}

	public void setLoginView() {
		if (loginPanel == null) {
			loginPanel = new LoginPanel(this);
			loginPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(loginPanel);
		loginPanel.requestFocus();
	}

	public void setRegisterView() {
		if (registerPanel == null) {
			registerPanel = new RegisterPanel(this);
			registerPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(registerPanel);
		registerPanel.requestFocus();
	}

	public void setBookCollView() {
		if (bookCollectionPanel == null) {
			bookCollectionPanel = new BookCollectionPanel(this);
			bookCollectionPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(bookCollectionPanel);
		bookCollectionPanel.requestFocus();
		bookCollectionPanel.repaint();
		bookCollectionPanel.validate();
	}

	public void setCartView() {
		if (cartPanel == null) {
			cartPanel = new CartPanel(this);
			cartPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(cartPanel);
		cartPanel.requestFocus();
		cartPanel.validate();
	}

	public void setInforView() {
		if (inforCenterPanel == null) {
			inforCenterPanel = new InforCenterPanel(this);
			inforCenterPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(inforCenterPanel);
		inforCenterPanel.requestFocus();
		inforCenterPanel.validate();
	}

	public void setReturnView() {
		mainFrame.getBookUIController().setReturnView();
	}

	public void setMainpageView() {
		if (navigatePanel == null) {
			navigatePanel = new NavigatePanel(this);
			navigatePanel.init(navigatePanel.BEFORE_STATE);
		}
		mainFrame.clear();
		mainFrame.add(navigatePanel);
		navigatePanel.requestFocus();
		navigatePanel.validate();
		mainFrame.getBookUIController().createMainView();
	}

}
