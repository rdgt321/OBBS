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
import ClientRunner.MPanel;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class RankPanel extends MPanel implements MouseListener {

	private BookUIController bookUIController;

	private final int SIZE = 10;
	private ArrayList<BookPO> bookPOs;
	private JLabel[] rankLabels, nameLabels;
	private int currentSize;

	public RankPanel(BookUIController bookUIController) {
		super();
		this.bookUIController = bookUIController;
		setLayout(null);
		setSize(220, 400);
		setVisible(true);
		JLabel ranklabel = new JLabel("销售排行榜");
		ranklabel.setSize(150, 50);
		ranklabel.setFont(new Font("宋体", Font.BOLD, 20));
		ranklabel.setLocation(45, 5);
		add(ranklabel);
		init();
	}

	@SuppressWarnings("unchecked")
	public void init() {
		try {
			ResultMessage resultMessage = Agent.bookService
					.getTopBooksInTotal(SIZE);
			bookPOs = resultMessage.getResultSet();
			if (bookPOs != null) {
				currentSize = bookPOs.size();
			} else {
				currentSize = 0;
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}
		rankLabels = new JLabel[currentSize];
		nameLabels = new JLabel[currentSize];
		for (int i = 0; i < currentSize; i++) {
			rankLabels[i] = new JLabel("" + (i + 1) + ".");
			rankLabels[i].setSize(30, 25);
			rankLabels[i].setFont(new Font("宋体", Font.PLAIN, 20));
			rankLabels[i].setForeground(Color.red);
			rankLabels[i].setLocation(10, 55 + 33 * i);

			nameLabels[i] = new JLabel(bookPOs.get(i).getName());
			nameLabels[i].setCursor(Cursor
					.getPredefinedCursor(Cursor.HAND_CURSOR));
			nameLabels[i].setForeground(Color.red);
			nameLabels[i].setSize(150, 25);
			nameLabels[i].setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			nameLabels[i].setLocation(41, 55 + 33 * i);
			nameLabels[i].addMouseListener(this);

			add(rankLabels[i]);
			add(nameLabels[i]);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		GradientPaint gp = new GradientPaint(1, 1, Color.RED.brighter(), 250,
				43, Color.WHITE.darker());
		g2d.setPaint(gp);
		g2d.fillRoundRect(0, 0, WIDTH, HEIGHT, 20, 10);
		g2d.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < currentSize; i++) {
			if (e.getSource() == nameLabels[i]) {
				bookUIController.createBookDetailView(bookPOs.get(i));
			}
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
