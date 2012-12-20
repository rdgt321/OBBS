package Book;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import ClientRunner.Agent;
import ClientRunner.ImageDialog;
import ClientRunner.Loader;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import RMI.ResultMessage;
import Sale.ItemPO;

//show the detail information of a book

@SuppressWarnings("serial")
public class BookDisplayPanel extends MPanel implements ActionListener {
	private BookUIController bookUIController;
	private BookPanel bookPanel;

	private MButton collectButton, addToCartButton, returnButton;

	public BookDisplayPanel(BookUIController bookUIController,
			BookPanel bookPanel) {
		this.bookUIController = bookUIController;
		this.bookPanel = bookPanel;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (Loader.homepageBG != null) {
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.8f));
			g2d.drawImage(Loader.homepageBG, 0, 0, 800, 530, this);
			g2d.setComposite(composite);
		}
		g2d.dispose();
	}

	public void init() {
		setLayout(null);
		setSize(800, 530);

		JLabel title = new JLabel("图书详尽信息");
		title.setSize(200, 40);
		title.setFont(new Font("楷体_gb2312", Font.PLAIN, 25));
		title.setLocation(330, 0);
		title.setForeground(Color.CYAN.brighter());

		bookPanel.setLocation(50, 35);

		collectButton = new MButton("收藏图书");
		collectButton.setFocusable(false);
		collectButton.setSize(135, 45);
		collectButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		collectButton.setLocation(420, 50);
		collectButton.addActionListener(this);

		addToCartButton = new MButton("加入购物车");
		addToCartButton.setFocusable(false);
		addToCartButton.setSize(135, 45);
		addToCartButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addToCartButton.setLocation(580, 50);
		addToCartButton.addActionListener(this);

		returnButton = new MButton("返回");
		returnButton.setFocusable(false);
		returnButton.setSize(100, 40);
		returnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		returnButton.setLocation(650, 440);
		returnButton.addActionListener(this);

		add(title);
		add(bookPanel);
		add(collectButton);
		add(addToCartButton);
		add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == returnButton) {
			bookUIController.setReturnView();
		} else if (e.getSource() == collectButton) {
			if (Agent.userAgent == null) {
				ImageDialog.showNOImage(this, "您尚未登录");
				bookUIController.getMainFrame().getMemberUIController()
						.setLoginView();
			} else {
				try {
					ResultMessage resultMessage = Agent.memberService
							.bookCollect(bookPanel.getisbn(),
									Agent.userAgent.getId());
					if (resultMessage.isInvokeSuccess()) {
						ImageDialog.showYesImage(this, "收藏成功");
					} else {
						ImageDialog.showNOImage(this,
								resultMessage.getPostScript());
					}
				} catch (RemoteException re) {
					re.printStackTrace();
				}
			}
		} else if (e.getSource() == addToCartButton) {
			if (Agent.userAgent == null) {
				ImageDialog.showNOImage(this, "您尚未登录");
			} else {
				try {
					ResultMessage resultMessage = Agent.saleService.addToCart(
							new ItemPO(-1, Agent.userAgent.getId(), bookPanel
									.getisbn(), bookPanel.getPrice(), 1),
							Agent.userAgent.getId());
					if (resultMessage.isInvokeSuccess()) {
						ImageDialog.showYesImage(this, "成功添加到购物车");
					} else {
						ImageDialog.showNOImage(this,
								resultMessage.getPostScript());
					}
				} catch (RemoteException re) {
					re.printStackTrace();
				}
			}
		}
	}
}
