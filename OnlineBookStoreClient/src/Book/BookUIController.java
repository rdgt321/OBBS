package Book;

import javax.swing.JScrollPane;

import ClientRunner.Agent;
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
		if (mainpagePanel == null) {
			mainpagePanel = new MainpagePanel(this);
			mainpagePanel.init();
			mainFrame.clear();
			mainFrame.add(mainpagePanel);
			mainpagePanel.requestFocus();
			mainpagePanel.validate();
			return;
		}
		mainFrame.clear();
		mainpagePanel.refresh();
		mainFrame.add(mainpagePanel);
		mainpagePanel.requestFocus();
		mainpagePanel.validate();
		mainpagePanel.repaint();
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

	public void createBookClassifyView(DirectoryPO directoryPO) {
		bookClassifyPanel = new BookClassifyPanel(this, directoryPO);
		bookClassifyPanel.init();
		mainFrame.clear();
		mainFrame.add(bookClassifyPanel);
		bookClassifyPanel.repaint();
		bookClassifyPanel.validate();
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
