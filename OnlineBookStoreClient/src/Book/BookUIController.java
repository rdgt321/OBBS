package Book;

import ClientRunner.MainFrame;

public class BookUIController {
	private MainFrame mainFrame;
	private MainpagePanel mainpagePanel;
	private BookClassifyPanel bookClassifyPanel;
	private BookDisplayPanel bookDisplayPanel;

	public BookUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setReturnView() {
		mainFrame.clear();
		mainpagePanel.refresh();
		mainFrame.add(mainpagePanel);
		mainpagePanel.requestFocus();
		mainpagePanel.validate();
		mainpagePanel.repaint();
	}
	
	public void refreshMainView(){
		mainpagePanel.refresh();
		mainpagePanel.validate();
		mainpagePanel.repaint();
	}

	public void createMainView() {
		if (mainpagePanel == null) {
			mainpagePanel = new MainpagePanel(this);
			mainpagePanel.init();
		}
		mainFrame.add(mainpagePanel);
		mainpagePanel.validate();
		mainpagePanel.repaint();
	}

	public void createBookClassifyView(DirectoryPO directoryPO) {
		bookClassifyPanel = new BookClassifyPanel(this, directoryPO);
		bookClassifyPanel.init();
		mainFrame.clear();
		mainFrame.add(bookClassifyPanel);
		bookClassifyPanel.validate();
		bookClassifyPanel.repaint();
	}

	public void createBookDetailView(BookPO bookPO) {
		BookPanel bookPanel = new BookPanel(this, bookPO);
		bookPanel.createBookInfoView();
		bookDisplayPanel = new BookDisplayPanel(this, bookPanel);
		bookDisplayPanel.init();
		mainFrame.clear();
		bookDisplayPanel.setLocation(0, 70);
		mainFrame.add(bookDisplayPanel);
		bookDisplayPanel.requestFocus();
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}
}
