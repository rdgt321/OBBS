package Book;

import ClientRunner.MainFrame;
import Member.MemberUIController;
import Member.NavigatePanel;

public class BookUIController {
	private MainFrame mainFrame;
	private MainpagePanel mainpagePanel;

	public BookUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setReturnView() {
		if (mainpagePanel == null) {
			mainpagePanel = new MainpagePanel(this);
			mainpagePanel.init();
		}
		mainFrame.clear();
		mainFrame.add(mainpagePanel);
		mainpagePanel.requestFocus();
		mainpagePanel.repaint();
		mainpagePanel.validate();

	}

	public void createMainView() {
		if (mainpagePanel == null) {
			mainpagePanel = new MainpagePanel(this);
			mainpagePanel.init();
		}
		mainFrame.add(mainpagePanel);
		mainpagePanel.repaint();
		mainpagePanel.validate();
	}

}
