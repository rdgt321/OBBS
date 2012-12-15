package User;

import ClientRunner.MainFrame;

public class UserUIController {
	private MainFrame mainFrame;
	private AdminPanel adminPanel;
	private GenManagerPanel genManagerPanel;
	private SalesManagerPanel salesManagerPanel;

	public UserUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void createAdminView() {
		adminPanel = new AdminPanel(this);
		adminPanel.init();
		mainFrame.clear();
		mainFrame.add(adminPanel);
		adminPanel.requestFocus();
		adminPanel.validate();
	}

	public void createManagerView() {
		genManagerPanel = new GenManagerPanel(this);
		genManagerPanel.init();
		mainFrame.clear();
		mainFrame.add(genManagerPanel);
		genManagerPanel.requestFocus();
		genManagerPanel.validate();
	}

	public void createSalesManagerView() {
		salesManagerPanel = new SalesManagerPanel(this);
		salesManagerPanel.init();
		mainFrame.clear();
		mainFrame.add(salesManagerPanel);
		salesManagerPanel.requestFocus();
		salesManagerPanel.validate();
		mainFrame.repaint();
		mainFrame.validate();
	}

	public void setMainPageView() {
		mainFrame.getMemberUIController().displayNavigateView();
		mainFrame.getMemberUIController().setMainpageView();
	}
	
	public MainFrame getMainFrame(){
		return mainFrame;
	}

}
