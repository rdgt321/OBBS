package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JLabel;

import ClientRunner.Agent;
import ClientRunner.Cache;
import ClientRunner.MPanel;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class MPBookClaPanel extends MPanel implements MouseListener {

	private BookUIController bookUIController;

	private JLabel bookLabel;
	private JLabel moreLabel;
	private String bookclassifyName;
	private ArrayList<BookPO> list;
	private BookRecord[] bookRecords;
	private final int SIZE = 4;
	private DirectoryPO directoryPO;
	private int currentSize;

	public MPBookClaPanel(BookUIController bookUIController,
			DirectoryPO directoryPO) {
		this.bookUIController = bookUIController;
		this.directoryPO = directoryPO;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawLine(0, 43, 250, 43);
		Graphics2D g2d = (Graphics2D) g.create();
		GradientPaint gp = new GradientPaint(1, 1, Color.YELLOW.brighter(),
				250, 43, Color.WHITE);
		g2d.setPaint(gp);
		g2d.fillRoundRect(0, 0, 250, 43, 20, 10);
		g2d.dispose();
	}

	public void initWithCache(ArrayList<BookPO> books) {
		bookclassifyName = directoryPO.getName();
		list = books;
		if (list != null) {
			currentSize = list.size();
		} else {
			currentSize = 0;
		}
		setSize(250, 220);
		setVisible(true);
		setLayout(null);

		bookLabel = new JLabel(bookclassifyName);
		bookLabel.setSize(100, 40);
		bookLabel.setLocation(10, 3);

		moreLabel = new JLabel("更多>>");
		moreLabel.setSize(80, 40);
		moreLabel.setLocation(190, 2);
		moreLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moreLabel.addMouseListener(this);

		bookLabel.setFont(new Font("宋体", Font.BOLD, 20));
		bookLabel.addMouseListener(this);

		bookRecords = new BookRecord[currentSize];

		for (int i = 0; i < currentSize; i++) {
			bookRecords[i] = new BookRecord(bookUIController, list.get(i));
			bookRecords[i].setLocation(5, 47 + 43 * i);
			this.add(bookRecords[i]);
		}
		this.add(bookLabel);
		this.add(moreLabel);
	}

	@SuppressWarnings("unchecked")
	public void init() {
		bookclassifyName = directoryPO.getName();
		try {
			ResultMessage resultMessage = Agent.bookService
					.getTopBooksInDirectory(directoryPO.getID(), SIZE);
			list = resultMessage.getResultSet();
			if (list != null) {
				currentSize = list.size();
			} else {
				currentSize = 0;
			}
			Cache.booksInDirectorys.add((ArrayList<BookPO>) list.clone());
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		setSize(250, 220);
		setVisible(true);
		setLayout(null);

		bookLabel = new JLabel(bookclassifyName);
		bookLabel.setSize(100, 40);
		bookLabel.setLocation(10, 3);

		moreLabel = new JLabel("更多>>");
		moreLabel.setSize(80, 40);
		moreLabel.setLocation(190, 2);
		moreLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moreLabel.addMouseListener(this);

		bookLabel.setFont(new Font("宋体", Font.BOLD, 20));
		bookLabel.addMouseListener(this);

		bookRecords = new BookRecord[currentSize];

		for (int i = 0; i < currentSize; i++) {
			bookRecords[i] = new BookRecord(bookUIController, list.get(i));
			bookRecords[i].setLocation(5, 47 + 43 * i);
			this.add(bookRecords[i]);
		}
		this.add(bookLabel);
		this.add(moreLabel);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == moreLabel && e.getButton() == MouseEvent.BUTTON1) {
			bookUIController.createBookClassifyView(directoryPO);
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
