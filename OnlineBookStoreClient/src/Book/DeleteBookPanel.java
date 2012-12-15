package Book;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DeleteBookPanel extends JPanel{
	private BookUIController bookUIController;
	
	private JLabel deleteBookISBNLabel;
	private JTextField deleteBookISBNField;
	
	public DeleteBookPanel(){
		init();
	}
	
	public DeleteBookPanel(BookUIController bookUIController){
		this.bookUIController = bookUIController;
		init();
	}
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}
	
	public void init(){
		setSize(350, 500);
		setLayout(null);
		
		deleteBookISBNLabel = new JLabel("请输入所要删除的图书的ISBN:");
		deleteBookISBNLabel.setSize(280, 30);
		deleteBookISBNLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		deleteBookISBNLabel.setLocation(10, 100);
		
		deleteBookISBNField = new JTextField();
		deleteBookISBNField.setSize(180, 30);
		deleteBookISBNField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		deleteBookISBNField.setLocation(50, 160);
		
		add(deleteBookISBNLabel);
		add(deleteBookISBNField);
	}
	
	public String getDeleteBookISBN(){
		return deleteBookISBNField.getText().trim();
	}
	
	public void clear(){
		deleteBookISBNField.setText("");
	}
}
