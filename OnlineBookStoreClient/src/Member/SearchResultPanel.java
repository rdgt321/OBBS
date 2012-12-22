package Member;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Book.BookInfoPanel;
import Book.BookPO;
<<<<<<< HEAD
import ClientRunner.IMGSTATIC;
=======
import ClientRunner.Loader;
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
import ClientRunner.MButton;
import ClientRunner.MPanel;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class SearchResultPanel extends MPanel implements ActionListener {

	private MemberUIController memberUIController;
	private ResultMessage resultMessage;
	private int size;
	private ArrayList<BookPO> list;
	private BookInfoPanel[] bookInfoPanels;
	private MButton returnButton;
	private JScrollPane scrollPane;
	private MPanel contentPane;
	private JLabel nothing;

	public SearchResultPanel(MemberUIController memberUIController,
			ResultMessage resultMessage) {
		this.memberUIController = memberUIController;
		this.resultMessage = resultMessage;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		setLayout(null);
		setSize(800, 530);
		setLocation(0, 70);

		list = resultMessage.getResultSet();
		if (list != null) {
			size = list.size();
		} else {
			size = 0;
		}
		contentPane = new MPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
<<<<<<< HEAD
				if (IMGSTATIC.homepageBG != null) {
=======
				if (Loader.homepageBG != null) {
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
					int height = scrollPane.getVerticalScrollBar().getValue();
					Composite composite = g2d.getComposite();
					g2d.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.8f));
<<<<<<< HEAD
					g2d.drawImage(IMGSTATIC.homepageBG, 0, height, 800, 530, this);
=======
					g2d.drawImage(Loader.homepageBG, 0, height, 800, 530, this);
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
					g2d.setComposite(composite);
				}
				g2d.dispose();
			}
		};
		contentPane.setPreferredSize(new Dimension(780, 42 * size + 80));
		contentPane.setLocation(0, 0);
		contentPane.setLayout(null);

		bookInfoPanels = new BookInfoPanel[size];
		for (int i = 0; i < size; i++) {
			bookInfoPanels[i] = new BookInfoPanel(memberUIController
					.getMainFrame().getBookUIController(), list.get(i));
			bookInfoPanels[i].setLocation(5, 10 + 50 * i);
			contentPane.add(bookInfoPanels[i]);
		}

		if (size == 0) {
			nothing = new JLabel("没有符合条件的书");
			nothing.setSize(250, 30);
			nothing.setLocation(250, 10);
			nothing.setFont(new Font("楷体_gb2312", Font.PLAIN, 30));
			nothing.setForeground(Color.RED);
			contentPane.add(nothing);
		}
		returnButton = new MButton("返回");
		returnButton.setSize(80, 40);
		returnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		returnButton.setFocusable(false);
		returnButton.setLocation(670, 45 * size + 50);
		returnButton.addActionListener(this);

		contentPane.add(returnButton);

		scrollPane = new JScrollPane(contentPane);
		scrollPane.setSize(800, 530);
		scrollPane.setLocation(0, 0);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
	}

	public MemberUIController getMemberUIController() {
		return memberUIController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == returnButton) {
			memberUIController.getMainFrame().getBookUIController()
					.setReturnView();
		}
	}
}
