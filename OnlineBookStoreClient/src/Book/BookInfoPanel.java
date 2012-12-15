package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BookInfoPanel extends JPanel implements MouseListener{

	private BookUIController bookUIController;
	private BookPO bookPO;
	private JLabel bookNameLabel, authorLabel, priceLabel, pressLabel;
	private JButton collectButton, addToCartButton;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 40;
	
	public BookInfoPanel(BookUIController bookUIController, BookPO bookPO){
		super();
		setLayout(null);
		this.bookUIController = bookUIController;
		this.bookPO = bookPO;
		init();
	}
	
	public void init(){
		setSize(WIDTH, HEIGHT);
		bookNameLabel = new JLabel(bookPO.getName());
//		bookNameLabel = new JLabel("Life of Pi");
		bookNameLabel.setSize(130, 30);
		bookNameLabel.setForeground(Color.red);
		bookNameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		bookNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookNameLabel.setLocation(10, 5);
		bookNameLabel.addMouseListener(this);
		
		authorLabel = new JLabel(bookPO.getAuthor());
//		authorLabel = new JLabel("Yann Martel");
		authorLabel.setSize(100, 20);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		authorLabel.setLocation(150, 10);
		
		priceLabel = new JLabel("￥" + bookPO.getPrice());
//		priceLabel = new JLabel("￥ 36.10");
		priceLabel.setSize(60, 20);
		priceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		priceLabel.setLocation(280, 10);
		
		pressLabel = new JLabel(bookPO.getPress());
//		pressLabel = new JLabel("译林出版社");
		pressLabel.setSize(120, 20);
		pressLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		pressLabel.setLocation(370, 10);
		
		
		collectButton = new JButton("收藏图书");
		collectButton.setSize(100, 25);
		collectButton.setFocusable(false);
		collectButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		collectButton.setLocation(500, 9);
		
		addToCartButton = new JButton("加入购物车");
		addToCartButton.setSize(100, 25);
		addToCartButton.setFocusable(false);
		addToCartButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		addToCartButton.setLocation(630, 9);
		
		add(bookNameLabel);
		add(authorLabel);
		add(priceLabel);
		add(pressLabel);
		add(collectButton);
		add(addToCartButton);
	}
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == bookNameLabel && e.getButton() == MouseEvent.BUTTON1){
			bookUIController.createBookDetailView();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
