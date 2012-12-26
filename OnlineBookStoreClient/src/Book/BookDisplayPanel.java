package Book;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import ClientRunner.Agent;
import ClientRunner.IMGSTATIC;
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MSlider;
import RMI.ResultMessage;
import Sale.ItemPO;

//show the detail information of a book

@SuppressWarnings("serial")
public class BookDisplayPanel extends MPanel implements ActionListener,
		MouseListener {
	private BookUIController bookUIController;
	private BookPanel bookPanel;
	private MSlider rateBar;
	private JLabel noRate;
	private JLabel rateTip;

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
		if (IMGSTATIC.homepageBG != null) {
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.8f));
			g2d.drawImage(IMGSTATIC.homepageBG, 0, 0, 800, 530, this);
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

		ResultMessage rateMessage = null;
		ResultMessage rated = null;
		ResultMessage purchased = null;
		try {
			rateMessage = Agent.bookService.getRate(bookPanel.getisbn());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		double rate = 0;
		if (rateMessage.isInvokeSuccess()) {
			rate = (Double) rateMessage.getResultSet().get(0);
		} else {
			noRate = new JLabel(rateMessage.getPostScript());
			noRate.setSize(360, 50);
			noRate.setLocation(420, 230);
			noRate.setFont(new Font("楷体_gb2312", Font.BOLD, 25));
			add(noRate);
		}
		if (Agent.userAgent == null) {
			rateTip = new JLabel("评价功能需登录后方能使用");
			rateTip.setFont(new Font("楷体_gb2312", Font.BOLD, 18));
			rateTip.setSize(300, 50);
			rateTip.setLocation(420, 170);
		} else {
			try {
				purchased = Agent.memberService.bookPurchased(
						bookPanel.getisbn(), Agent.userAgent.getId());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if (purchased.isInvokeSuccess()) {
				try {
					rated = Agent.bookService.queryRate(bookPanel.getisbn(),
							Agent.userAgent.getId());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (rated.isInvokeSuccess()) {
					rateTip = new JLabel("您还未评价过此书，快来评价吧!");
					rateTip.setFont(new Font("楷体_gb2312", Font.BOLD, 18));
					rateTip.setSize(300, 50);
					rateTip.setLocation(420, 170);
					rateTip.setCursor(new Cursor(Cursor.HAND_CURSOR));
					rateTip.addMouseListener(this);
				} else {
					rateTip = new JLabel(rated.getPostScript());
					rateTip.setFont(new Font("楷体_gb2312", Font.BOLD, 18));
					rateTip.setSize(300, 50);
					rateTip.setLocation(420, 170);
				}
			} else {
				rateTip = new JLabel("成功购买后方能评价");
				rateTip.setFont(new Font("楷体_gb2312", Font.BOLD, 18));
				rateTip.setSize(300, 50);
				rateTip.setLocation(420, 170);
			}
		}
		add(rateTip);
		rateBar = new MSlider(rate);
		rateBar.setLocation(420, 100);

		add(title);
		add(bookPanel);
		add(collectButton);
		add(addToCartButton);
		add(returnButton);
		add(rateBar);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == rateTip) {
			dealRate();
		}
	}

	private void dealRate() {
		rateBar.EnableRating();
		final BookDisplayPanel panel = this;
		rateTip.removeMouseListener(this);
		rateTip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		new Thread((new Runnable() {
			boolean success = false;

			@Override
			public void run() {
				while (rateBar.isEnabled()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					ResultMessage rate = Agent.bookService.setRate(
							bookPanel.getisbn(), Agent.userAgent.getId(),
							rateBar.getRate());
					if (rate.isInvokeSuccess()) {
						rateTip.setText("评价成功！");
						rateTip.setForeground(Color.RED.brighter());
						rateTip.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						success = true;
					} else {
						rateTip.setText("未知原因评价失败，请重新评价");
						success = false;
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (!success) {
					rateTip.addMouseListener(panel);
					rateTip.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		})).start();
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
