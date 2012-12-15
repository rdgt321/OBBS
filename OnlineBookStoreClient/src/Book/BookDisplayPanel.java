package Book;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BookDisplayPanel extends JPanel implements ActionListener{
	private BookUIController bookUIController;
	private BookService bookService;
	private BookPanel bookPanel;
	
	private JButton collectButton, addToCartButton, returnButton;
	
	public BookDisplayPanel(BookUIController bookUIController, BookService bookService, BookPanel bookPanel){
		this.bookUIController = bookUIController;
		this.bookService = bookService;
		this.bookPanel = bookPanel;
	}
	
	public void init(){
		setLayout(null);
		setSize(780, 540);
		
		JLabel title = new JLabel("图书详尽信息");
		title.setSize(200, 40);
		title.setFont(new Font("楷体_gb2312", Font.PLAIN, 25));
		title.setLocation(300, 0);
		
		bookPanel.setLocation(50, 40);
		
		collectButton = new JButton("收藏图书");
		collectButton.setFocusable(false);
		collectButton.setSize(135, 45);
		collectButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		collectButton.setLocation(420, 50);
		collectButton.addActionListener(this);
		
		addToCartButton = new JButton("加入购物车");
		addToCartButton.setFocusable(false);
		addToCartButton.setSize(135, 45);
		addToCartButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addToCartButton.setLocation(580, 50);
		addToCartButton.addActionListener(this);
		
		returnButton = new JButton("返回");
		returnButton.setFocusable(false);
		returnButton.setSize(100, 40);
		returnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		returnButton.setLocation(650, 440);
		returnButton.addActionListener(this);
		
		add(title);
		add(bookPanel);
		add(collectButton);
		add(addToCartButton);
		add(returnButton);
	}
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}
	
	public BookService getBookService(){
		return bookService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnButton){
			bookUIController.setReturnView();
		}else if(e.getSource() == collectButton){
			
		}else if(e.getSource() == addToCartButton){
			
		}
	}
	
	
}
