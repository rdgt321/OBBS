package Book;

import javax.swing.JScrollPane;

import ClientRunner.Agent;
import ClientRunner.MainFrame;

public class BookUIController {
	private MainFrame mainFrame;
	private MainpagePanel mainpagePanel;
	private BookClassifyPanel bookClassifyPanel;
	private BookDisplayPanel bookDisplayPanel;
	private JScrollPane scrollPane;

	public BookUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		scrollPane = new JScrollPane();
		scrollPane.setSize(780, 520);
		scrollPane.setLayout(null);
		scrollPane.setLocation(0, 70);
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
	
	public void createBookClassifyView(){
		if(bookClassifyPanel == null){
			bookClassifyPanel = new BookClassifyPanel(this);
			bookClassifyPanel.init();
		}
		mainFrame.clear();
		scrollPane.add(bookClassifyPanel);
		mainFrame.add(scrollPane);
		scrollPane.requestFocus();
		scrollPane.repaint();
		scrollPane.validate();
	}
	
	public void createBookDetailView(){
		BookPanel bookPanel = new BookPanel(this, null);
		bookPanel.createBookInfoView();
		if(bookDisplayPanel == null){
			bookDisplayPanel = new BookDisplayPanel(this, Agent.bookService, bookPanel);
			bookDisplayPanel.init();
		}
		mainFrame.clear();
		bookDisplayPanel.setLocation(0, 70);
		mainFrame.add(bookDisplayPanel);
		bookDisplayPanel.requestFocus();
	}

}
