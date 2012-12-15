package Book;

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ClientRunner.Agent;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class BookPanel extends JPanel {
	
	private BookUIController bookUIController;
	private BookPO bookPO;
	
	private JLabel nameLabel, priceLabel, isbnLabel, authorLabel, pressLabel, publishDateLabel, bookTypeLabel, specialPriceLabel, descriptionLabel;
	
	private JTextField nameField, isbnField, authorField, pressField, publishDateYearField, publishDateMonthField, publishDateDateField, priceField, specialPriceField;
	private JComboBox<String> bookType;
	
	private JTextArea descriptionArea;
	
	public BookPanel(BookUIController bookUIController){
		this.bookUIController = bookUIController;
	}
	
	public BookPanel(BookUIController bookUIController, BookPO bookPO) {
		this.bookUIController = bookUIController;
		this.bookPO = bookPO;
	}

	public void createNewBookView(){
		init();
	}
	
	public void createBookInfoView(){
		init();
		addBookInfo();
	}
	
	public void init() {
		setSize(350, 500);
		setLayout(null);
		
		nameLabel = new JLabel("图书名称:");
		nameLabel.setSize(100, 30);
		nameLabel.setLocation(10, 10);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		priceLabel = new JLabel("图书价格:");
		priceLabel.setSize(100, 30);
		priceLabel.setLocation(10, 55);
		priceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
	
		isbnLabel = new JLabel("ISBN:");
		isbnLabel.setSize(100, 30);
		isbnLabel.setLocation(10, 100);
		isbnLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		authorLabel = new JLabel("作者:");
		authorLabel.setSize(100, 30);
		authorLabel.setLocation(10, 145);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		pressLabel = new JLabel("出版社:");
		pressLabel.setSize(100, 30);
		pressLabel.setLocation(10, 190);
		pressLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		specialPriceLabel = new JLabel("图书特价:");
		specialPriceLabel.setSize(100, 30);
		specialPriceLabel.setLocation(10, 235);
		specialPriceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		publishDateLabel = new JLabel("出版日期:");
		publishDateLabel.setSize(100, 30);
		publishDateLabel.setLocation(10, 280);
		publishDateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		bookTypeLabel = new JLabel("图书类别");
		bookTypeLabel.setSize(100, 30);
		bookTypeLabel.setLocation(10, 325);
		bookTypeLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		descriptionLabel = new JLabel("图书描述:");
		descriptionLabel.setSize(100, 30);
		descriptionLabel.setLocation(10, 370);
		descriptionLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		nameField = new JTextField();
		nameField.setSize(150, 30);
		nameField.setLocation(115, 10);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		priceField = new JTextField();
		priceField.setSize(120, 30);
		priceField.setLocation(115, 55);		
		priceField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		isbnField = new JTextField();
		isbnField.setSize(160, 30);
		isbnField.setLocation(115, 100);
		isbnField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		authorField = new JTextField();
		authorField.setSize(150, 30);
		authorField.setLocation(115, 145);
		authorField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		pressField = new JTextField();
		pressField.setSize(160, 30);
		pressField.setLocation(115, 190);
		pressField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		specialPriceField = new JTextField();
		specialPriceField.setSize(120, 30);
		specialPriceField.setLocation(115, 235);
		specialPriceField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		publishDateYearField = new JTextField();
		publishDateYearField.setSize(60, 30);
		publishDateYearField.setLocation(115, 280);
		publishDateYearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		JLabel yearLabel = new JLabel("年");
		yearLabel.setSize(20, 30);
		yearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		yearLabel.setLocation(176, 280);
		
		publishDateMonthField = new JTextField();
		publishDateMonthField.setSize(40, 30);
		publishDateMonthField.setLocation(197, 280);
		publishDateMonthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		JLabel monthLabel = new JLabel("月");
		monthLabel.setSize(20, 30);
		monthLabel.setLocation(238, 280);
		monthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		publishDateDateField = new JTextField();
		publishDateDateField.setSize(40, 30);
		publishDateDateField.setLocation(259, 280);
		publishDateDateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		JLabel dateLabel = new JLabel("日");
		dateLabel.setSize(20, 30);
		dateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		dateLabel.setLocation(300, 280);
		
		try{
			ResultMessage resultMessage = Agent.bookService.getAllDirectories();
			bookType = new JComboBox<String>();
			@SuppressWarnings("unchecked")
			ArrayList<DirectoryPO> list = resultMessage.getResultSet();
			for(int i = 0; i < list.size(); i ++){
				bookType.addItem(list.get(i).getName());
			}
		}catch(RemoteException re){
			re.printStackTrace();
		}
		bookType.setSize(120, 30);
		bookType.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		bookType.setLocation(115, 325);
		
		descriptionArea = new JTextArea();
		descriptionArea.setSize(190, 120);
		descriptionArea.setLocation(115, 370);
		descriptionArea.setLineWrap(true);
		descriptionArea.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(descriptionArea);
		scrollPane.setSize(190, 90);
		scrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLocation(115, 325);	
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		add(nameLabel);
		add(nameField);
		add(isbnLabel);
		add(isbnField);
		add(authorLabel);
		add(authorField);
		add(pressLabel);
		add(pressField);
		add(publishDateLabel);
		add(publishDateYearField);
		add(yearLabel);
		add(publishDateMonthField);
		add(monthLabel);
		add(publishDateDateField);
		add(dateLabel);
		add(priceLabel);
		add(priceField);
		add(specialPriceLabel);
		add(specialPriceField);
		add(descriptionLabel);
		add(scrollPane);
	}
	
	private void addBookInfo(){
//		if(bookPO != null){
//			nameField.setText(bookPO.getName());
//			authorField.setText(bookPO.getAuthor());
//			isbnField.setText(bookPO.getISBN());
//			pressField.setText(bookPO.getPress());
//			publishDateYearField.setText(String.valueOf(bookPO.getPublishDate().YEAR));
//			publishDateMonthField.setText(String.valueOf(bookPO.getPublishDate().MONTH));
//			publishDateDateField.setText(String.valueOf(bookPO.getPublishDate().DATE));
//			priceField.setText(String.valueOf(bookPO.getPrice()));
//			specialPriceField.setText(String.valueOf(bookPO.getSpecialPrice()));
//			descriptionArea.setText(bookPO.getDescription());
			
			nameField.setText("Life Of PI");
			authorField.setText("Yann Martel");
			isbnField.setText("978-7-5605-4251-5");
			pressField.setText("译林出版社");
			publishDateYearField.setText("2012");
			publishDateMonthField.setText("12");
			publishDateDateField.setText("12");
			priceField.setText("36.1");
			specialPriceField.setText("36.1");
			descriptionArea.setText("畅销书籍");
			
			nameField.setEditable(false);
			isbnField.setEditable(false);
			authorField.setEditable(false);
			pressField.setEditable(false);
			publishDateYearField.setEditable(false);
			publishDateMonthField.setEditable(false);
			publishDateDateField.setEditable(false);
			priceField.setEditable(false);
			specialPriceField.setEditable(false);
			descriptionArea.setEditable(false);
//		}
	}
	
	public String getBookName(){
		return nameField.getText().trim();
	} 
	
	public String getisbn(){
		return isbnField.getText().trim();
	}
	
	public String getAuthor(){
		return authorField.getText().trim();
	}
	
	public String getPress(){
		return pressField.getText().trim();
	}
	
	public Calendar getPublishDate(){
		return new GregorianCalendar(Integer.parseInt(publishDateYearField.getText().trim()), Integer.parseInt(publishDateMonthField.getText().trim()), Integer.parseInt(publishDateDateField.getText().trim()));
		
	}
	
	public String getPrice(){
		return priceField.getText().trim();
	}
	
	public String getSpecialPrice(){
		return specialPriceField.getText().trim();
	}
	
	public String getDescription(){
		return descriptionArea.getText().trim();
	}
	
	public void enableModification(){
		enableModifyName();
		enableModifyISBN();
		enableModifyAuthor();
		enableModifyPress();
		enableModifyPublishDate();
		enableModifyPrice();
		enableModifySpecialPrice();
		enableModifyDescription();
	}
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}
	
	public void clear(){
		nameField.setText("");
		isbnField.setText("");
		authorField.setText("");
		pressField.setText("");
		publishDateYearField.setText("");
		publishDateMonthField.setText("");
		publishDateDateField.setText("");
		priceField.setText("");
		specialPriceField.setText("");
		descriptionArea.setText("");
	}
	
	//allow the user to modify or set the name of the book
	private void enableModifyName(){
		nameField.setEditable(true);
	}
	
	//allow the user to modify or set the isbn of the book
	private void enableModifyISBN(){
		isbnField.setEditable(true);
	}
	
	//allow the user to modify or set the author of the book
	private void enableModifyAuthor(){
		authorField.setEditable(true);
	}
	
	//allow the user to modify or set the press of the book
	private void enableModifyPress(){
		pressField.setEditable(true);
	}
	
	//allow the user to modify or set the publish date of the book
	private void enableModifyPublishDate(){
		publishDateYearField.setEditable(true);
		publishDateMonthField.setEditable(true);
		publishDateDateField.setEditable(true);
	}
	
	//allow the user to modify or set the price of the book
	private void enableModifyPrice(){
		priceField.setEditable(true);
	}
	
	//allow the user to modify or set special price of the book
	private void enableModifySpecialPrice(){
		specialPriceField.setEditable(true);
	}
	
	//allow the user to modify or set the description displayed in the description area
	private void enableModifyDescription(){
		descriptionArea.setEditable(true);
	}
}
