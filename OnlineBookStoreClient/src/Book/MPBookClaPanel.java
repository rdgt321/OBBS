package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MPBookClaPanel extends JPanel implements MouseListener {
	
	private BookUIController bookUIController;
	
	private JLabel bookLabel;
	private JLabel moreLabel;
	private String bookclassifyName;
	private BookRecord[] bookRecord;
	private final int SIZE = 4;

	public MPBookClaPanel(BookUIController bookUIController, String name) {
		this.bookUIController = bookUIController;
		this.bookclassifyName = name;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 250, 220);
		g.setColor(Color.GRAY);
		g.drawLine(0, 43, 250, 43);
		g.draw3DRect(0, 0, 249, 219, true);
	}

	public void draw() {
		setSize(250, 220);
		setVisible(true);
		setLayout(null);
		bookLabel = new JLabel(bookclassifyName);
		bookLabel.setSize(100, 40);
		bookLabel.setLocation(10, 3);
		
		moreLabel = new JLabel("¸ü¶à >>");
		moreLabel.setSize(80, 40);
		moreLabel.setLocation(190, 2);
		moreLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moreLabel.addMouseListener(this);

		bookLabel.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		moreLabel.addMouseListener(this);
		
		bookRecord = new BookRecord[SIZE];
		
		for(int i = 0; i < bookRecord.length; i ++){
			bookRecord[i] = new BookRecord(bookUIController, null);
			bookRecord[i].setLocation(5, 47 + 43 * i);
			this.add(bookRecord[i]);
		}
		
		this.add(bookLabel);
		this.add(moreLabel);

	}
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == moreLabel && e.getButton() == MouseEvent.BUTTON1) {
			bookUIController.createBookClassifyView();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
