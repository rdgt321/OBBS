package Member;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Book.BookOnShelfPanel;
import Book.BookPO;
import ClientRunner.Agent;
import ClientRunner.Loader;
import ClientRunner.MPanel;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class BookCollectionPanel extends MPanel {
	private MemberUIController memberUIController;
	private JScrollPane scrollPane;
	private int size;
	private ArrayList<BookPO> list;
	private BookOnShelfPanel[] bookOnShelfPanels;
	private MPanel contentPane;

	public BookCollectionPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		setSize(800, 530);
		setLocation(0, 70);
		setVisible(true);
		setLayout(null);

		try {
			ResultMessage resultMessage = Agent.memberService
					.getCollectedBook(Agent.userAgent.getId());
			list = resultMessage.getResultSet();
			if (list != null) {
				size = list.size();
				bookOnShelfPanels = new BookOnShelfPanel[size];
			} else {
				size = 0;
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		contentPane = null;
		if (size != 0) {
			contentPane = new MPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					if (Loader.homepageBG != null) {
						int height = scrollPane.getVerticalScrollBar()
								.getValue();
						Composite composite = g2d.getComposite();
						g2d.setComposite(AlphaComposite.getInstance(
								AlphaComposite.SRC_OVER, 0.8f));
						g2d.drawImage(Loader.homepageBG, 0, height, 800, 530,
								this);
						g2d.setComposite(composite);
					}
					g2d.dispose();
				}

			};
			contentPane.setLayout(null);
			contentPane.setPreferredSize(new Dimension(780, 20 + 60 * size));

			for (int i = 0; i < size; i++) {
				bookOnShelfPanels[i] = new BookOnShelfPanel(memberUIController
						.getMainFrame().getBookUIController(), list.get(i));
				bookOnShelfPanels[i].init();
				bookOnShelfPanels[i].setLocation(5, 5 + 60 * i);
				contentPane.add(bookOnShelfPanels[i]);
			}
			scrollPane = new JScrollPane(contentPane);
			scrollPane.setSize(800, 530);
			scrollPane.setLocation(0, 0);
			scrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.getVerticalScrollBar().setUnitIncrement(20);
			add(scrollPane);
		} else {
			JLabel nothingLabel = new JLabel("您的书架中尚未收藏任何图书,赶快收藏吧!");
			nothingLabel.setSize(400, 30);
			nothingLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			nothingLabel.setForeground(Color.red);
			nothingLabel.setLocation(230, 100);

			contentPane = new MPanel() {
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					ImageIcon imageIcon = new ImageIcon("materials/boring.gif");
					Image image = imageIcon.getImage();
					if (image != null) {
						g.drawImage(image, 335, 210, 80, 80, this);
					}
				}
			};

			contentPane.setLayout(null);
			contentPane.setSize(780, 500);
			contentPane.add(nothingLabel);
			scrollPane = new JScrollPane(contentPane);
			scrollPane.setSize(800, 530);
			scrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.getVerticalScrollBar().setUnitIncrement(20);
			add(scrollPane);
		}
	}
}
