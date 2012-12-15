package Book;

import javax.swing.JPanel;

/**
 * @author zhangxufan*/

/*clarification: class used to classify books*/

@SuppressWarnings("serial")
public class BookClassifyPanel extends JPanel{
	
	private BookUIController bookUIController;
	private BookInfoPanel[] bookInfoPanels;
	private int numOfBooks;
	
	public BookClassifyPanel(BookUIController bookUIController){
		this.bookUIController = bookUIController;
	}
	
	public void init(){
		setSize(1000, 540);
		setLocation(0, 0);
		setLayout(null);
		numOfBooks = 20;
		bookInfoPanels = new BookInfoPanel[numOfBooks];
		for(int i = 0; i < numOfBooks; i ++){
			bookInfoPanels[i] = new BookInfoPanel(bookUIController, null);
			bookInfoPanels[i].setLocation(19, 10 + 50 * i);
			add(bookInfoPanels[i]);
		}
	}
	
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}
}
