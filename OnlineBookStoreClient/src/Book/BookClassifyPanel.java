package Book;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ClientRunner.Agent;
import ClientRunner.IMGSTATIC;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import RMI.ResultMessage;

/** clarification: class used to classify books */

@SuppressWarnings("serial")
public class BookClassifyPanel extends MPanel implements ActionListener {

	private BookUIController bookUIController;
	private ArrayList<BookPO> list = null;
	private BookInfoPanel[] bookInfoPanels;
	private int numOfBooks;
	private DirectoryPO directoryPO;
	private MButton returnButton;
	private MPanel contentPane;
	private JScrollPane scrollPane;

	public BookClassifyPanel(BookUIController bookUIController,
			DirectoryPO directoryPO) {
		this.bookUIController = bookUIController;
		this.directoryPO = directoryPO;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		try {
			ResultMessage resultMessage = Agent.bookService
					.getSelectedDirectory(directoryPO.getID());
			list = resultMessage.getResultSet();
			if (list != null) {
				numOfBooks = list.size();
			} else {
				numOfBooks = 0;
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}
		setSize(800, 530);
		setLocation(0, 70);
		setLayout(null);
		contentPane = new MPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				if (IMGSTATIC.homepageBG != null) {
					int height = scrollPane.getVerticalScrollBar().getValue();
					Composite composite = g2d.getComposite();
					g2d.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.8f));
					g2d.drawImage(IMGSTATIC.homepageBG, 0, height, 800, 530, this);
					g2d.setComposite(composite);
				}
				g2d.dispose();
			}
		};
		contentPane.setPreferredSize(new Dimension(780,
				570 + (numOfBooks - 10) * 50));
		contentPane.setLayout(null);
		contentPane.setLocation(0, 0);

		bookInfoPanels = new BookInfoPanel[numOfBooks];
		for (int i = 0; i < numOfBooks; i++) {
			bookInfoPanels[i] = new BookInfoPanel(bookUIController, list.get(i));
			bookInfoPanels[i].setLocation(19, 10 + 50 * i);
			contentPane.add(bookInfoPanels[i]);
		}
		returnButton = new MButton("返回");
		returnButton.setSize(80, 40);
		returnButton.setFocusable(false);
		returnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		returnButton.setLocation(670, 20 + 50 * numOfBooks);
		returnButton.addActionListener(this);
		contentPane.add(returnButton);
		scrollPane = new JScrollPane(contentPane);
		scrollPane.setSize(800, 530);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
	}

	public BookUIController getBookUIController() {
		return bookUIController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == returnButton) {
			bookUIController.setReturnView();
		}
	}
}
