package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import ClientRunner.MPanel;

@SuppressWarnings("serial")
public class BookRecord extends MPanel implements MouseListener {
	private final int WIDTH = 240;
	private final int HEIGHT = 40;

	private BookUIController bookUIController;
	private JLabel nameLabel, authorLabel;

	private BookPO bookPO;

	public BookRecord(BookUIController bookUIController, BookPO bookPO) {
		super();
		this.bookUIController = bookUIController;
		this.bookPO = bookPO;
		init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		GradientPaint gp = new GradientPaint(1, 1, Color.WHITE.brighter(),
				250, 43, Color.WHITE.darker());
		g2d.setPaint(gp);
		g2d.fillRoundRect(0, 0, WIDTH, HEIGHT, 20, 10);
		g2d.dispose();
	}

	public void init() {
		setSize(WIDTH, HEIGHT);
		setLayout(null);

		nameLabel = new JLabel(bookPO.getName());
		nameLabel.setSize(150, 30);
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nameLabel.setLocation(10, 5);
		nameLabel.addMouseListener(this);

		authorLabel = new JLabel(bookPO.getAuthor());
		authorLabel.setSize(60, 20);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		authorLabel.setLocation(170, 10);

		add(nameLabel);
		add(authorLabel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == nameLabel && e.getButton() == MouseEvent.BUTTON1) {
			bookUIController.createBookDetailView(bookPO);
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
