package Member;

import ClientRunner.Agent;
import ClientRunner.MainFrame;
import RMI.ResultMessage;

public class MemberUIController {
	private MainFrame mainFrame;
	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private NavigatePanel navigatePanel;
	private BookCollectionPanel bookCollectionPanel;
	private SearchResultPanel searchResultPanel;
	private InforCenterPanel inforCenterPanel;

	public MemberUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void createNavigateView(int state) {
		navigatePanel = new NavigatePanel(this);
		if (state == NavigatePanel.AFTER_STATE) {
			navigatePanel.init(NavigatePanel.AFTER_STATE);
		} else if (state == NavigatePanel.BEFORE_STATE) {
			navigatePanel.init(NavigatePanel.BEFORE_STATE);
		}
		mainFrame.add(navigatePanel);
		navigatePanel.validate();
		navigatePanel.repaint();
	}

	public void setAfterLoginNavigate() {
		if (navigatePanel == null) {
			navigatePanel = new NavigatePanel(this);
		}
		navigatePanel.init(NavigatePanel.AFTER_STATE);
		navigatePanel.validate();
		navigatePanel.repaint();
	}

	public void setLoginView() {
		loginPanel = new LoginPanel(this);
		loginPanel.init();
		mainFrame.clear();
		mainFrame.add(loginPanel);
		loginPanel.requestFocus();
	}

	public void setRegisterView() {
		registerPanel = new RegisterPanel(this, Agent.memberService);
		registerPanel.init();
		mainFrame.clear();
		mainFrame.add(registerPanel);
		registerPanel.requestFocus();
	}

	public void setBookCollView() {
		bookCollectionPanel = new BookCollectionPanel(this);
		bookCollectionPanel.init();
		mainFrame.clear();
		mainFrame.add(bookCollectionPanel);
		bookCollectionPanel.requestFocus();
		bookCollectionPanel.repaint();
		bookCollectionPanel.validate();
	}

	public void setInforView() {
		inforCenterPanel = new InforCenterPanel(this);
		inforCenterPanel.init();
		mainFrame.clear();
		mainFrame.add(inforCenterPanel);
		inforCenterPanel.requestFocus();
		inforCenterPanel.validate();
	}

	public void setReturnView() {
		mainFrame.getBookUIController().setReturnView();
	}

	public void setMainpageView() {
		mainFrame.getBookUIController().setReturnView();
	}

	public void setCartView() {
		mainFrame.getSaleUIController().setCartView();
	}

	public void displaySearchResult(ResultMessage resultMessage) {
		searchResultPanel = new SearchResultPanel(this, resultMessage);
		searchResultPanel.init();
		mainFrame.clear();
		mainFrame.add(searchResultPanel);
		searchResultPanel.requestFocus();
		searchResultPanel.validate();
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void hideNavigateView() {
		navigatePanel.setVisible(false);
		mainFrame.validate();
	}

	public void displayNavigateView() {
		navigatePanel.setVisible(true);
		mainFrame.validate();
	}
}
