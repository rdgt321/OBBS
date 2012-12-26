package Book;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ClientRunner.Agent;
import ClientRunner.Cache;
import ClientRunner.IMGSTATIC;
import ClientRunner.MPanel;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class MainpagePanel extends MPanel implements MouseListener {

	private BookUIController bookUIController;
	private ArrayList<DirectoryPO> list;
	private int size;
	private MPBookClaPanel[] bcpanels;

	private RankPanel rankPanel;
	private JScrollPane scrollPane;

	public MainpagePanel(BookUIController bookUIController) {
		this.bookUIController = bookUIController;
	}

	public void refresh() {
		Cache.clear();
		if (scrollPane != null) {
			remove(scrollPane);
		}
		try {
			ResultMessage resultMessage = Agent.bookService.getAllDirectories();
			list = resultMessage.getResultSet();
			if (list != null) {
				size = list.size();
				bcpanels = new MPBookClaPanel[size];
				Cache.directorys = (ArrayList<DirectoryPO>) list.clone();
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}
		MPanel panel = new MPanel() {
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
					g2d.drawImage(IMGSTATIC.homepageBG, 0, height, 800, 530,
							this);
					g2d.setComposite(composite);
				}
				g2d.dispose();
			}

		};

		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(780, 720 + (size / 2 - 3) * 240));
		panel.setOpaque(false);
		for (int i = 0; i < size; i++) {
			bcpanels[i] = new MPBookClaPanel(bookUIController, list.get(i));
			if (i % 2 == 0) {
				bcpanels[i].setLocation(10, 120 * i + 10);
			} else {
				bcpanels[i].setLocation(270, 120 * i - 110);
			}
			bcpanels[i].init();
			panel.add(bcpanels[i]);
		}

		rankPanel = new RankPanel(bookUIController);
		rankPanel.init();
		rankPanel.setLocation(550, 10);
		panel.add(rankPanel);

		scrollPane = new JScrollPane(panel);
		scrollPane.setSize(800, 530);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		Cache.saveToCache();
	}

	@SuppressWarnings("unchecked")
	public void init() {
		list = Cache.directorys;
		if (list != null) {
			size = list.size();
			bcpanels = new MPBookClaPanel[size];
		}
		setSize(800, 530);
		setLocation(0, 70);
		setVisible(true);
		setLayout(null);
		setOpaque(false);

		MPanel panel = new MPanel() {
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
					g2d.drawImage(IMGSTATIC.homepageBG, 0, height, 800, 530,
							this);
					g2d.setComposite(composite);
				}
				g2d.dispose();
			}
		};
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(780, 720 + (size / 2 - 3) * 240));
		panel.setOpaque(false);
		for (int i = 0; i < size; i++) {
			bcpanels[i] = new MPBookClaPanel(bookUIController, list.get(i));
			if (i % 2 == 0) {
				bcpanels[i].setLocation(10, 120 * i + 10);
			} else {
				bcpanels[i].setLocation(270, 120 * i - 110);
			}
			bcpanels[i].initWithCache(Cache.booksInDirectorys.get(i));
			panel.add(bcpanels[i]);
		}

		rankPanel = new RankPanel(bookUIController);
		rankPanel.initWithCache();
		rankPanel.setLocation(550, 10);
		panel.add(rankPanel);
		scrollPane = new JScrollPane(panel);
		scrollPane.setSize(800, 530);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setOpaque(false);
		add(scrollPane);
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
}
