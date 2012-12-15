package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BookRecord extends JPanel implements MouseListener{	
	private final int WIDTH = 240;
	private final int HEIGHT = 40;
	
	private BookUIController bookUIController;
	private JLabel nameLabel, authorLabel;
	
	private BookPO bookPO;
	
	public BookRecord(BookUIController bookUIController, BookPO bookPO){
		super();
		this.bookUIController = bookUIController;
		this.bookPO = bookPO;
		
		init();
	}
	
	public void init(){
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		
//		nameLabel = new JLabel(bookPO.getName());
		nameLabel = new JLabel("谁的青春不迷茫");
		nameLabel.setSize(150, 30);
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nameLabel.setLocation(10, 5);
		nameLabel.addMouseListener(this);
		
//		authorLabel = new JLabel(bookPO.getAuthor());
		authorLabel = new JLabel("刘同");
		authorLabel.setSize(60, 20);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		authorLabel.setLocation(170, 10);
		
		add(nameLabel);
		add(authorLabel);
	}

	public BookUIController getBookUIController(){
		return bookUIController;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == nameLabel && e.getButton() == MouseEvent.BUTTON1){
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
