package Book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class MainpagePanel extends JPanel implements MouseListener {
	
	private BookUIController bookUIController;
	
	private String[] bookClassify = { "文学", "小说", "经济", "管理", "考试", "时尚", "少儿",
			"旅游" };
	private int size = bookClassify.length;
	private MPBookClaPanel[] bcpanels = new MPBookClaPanel[size];

	private RankPanel rankPanel;

	public MainpagePanel(BookUIController bookUIController) {
		this.bookUIController = bookUIController;
	}

	public void init() {
		setSize(800, 540);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.white);
				g.fillRect(0, 0, 760, 1000);
			}
		};
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(760, 1000));
		for (int i = 0; i < size; i++) {
			bcpanels[i] = new MPBookClaPanel(bookUIController, bookClassify[i]);
			if (i % 2 == 0) {
				bcpanels[i].setLocation(10, 120 * i + 10);
			} else {
				bcpanels[i].setLocation(270, 120 * i - 110);
			}
			bcpanels[i].draw();
			panel.add(bcpanels[i]);
		}

		initrankPanel();
		rankPanel.setLocation(550, 10);
		panel.add(rankPanel);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setSize(780, 520);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
	}

	private void initrankPanel() {
		rankPanel = new RankPanel(null);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
	
	public BookUIController getBookUIController(){
		return bookUIController;
	}

}
