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
		mainFrame.add(adminPanel);
		adminPanel.requestFocus();
		adminPanel.validate();
	}

	public void createManagerView() {
		genManagerPanel = new GenManagerPanel(this);
		genManagerPanel.init();
		mainFrame.add(genManagerPanel);
		genManagerPanel.requestFocus();
		genManagerPanel.validate();
	}

	public void createSalesManagerView() {
		salesManagerPanel = new SalesManagerPanel(this);
		salesManagerPanel.init();
		mainFrame.add(salesManagerPanel);
		salesManagerPanel.requestFocus();
		salesManagerPanel.validate();
	}

	public void setMainPage() {
		mainFrame.getMemberUIController().setMainpageView();

	}

}
