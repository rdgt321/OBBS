package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import ClientRunner.Agent;
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import RMI.ResultMessage;
import Sale.ItemPO;

@SuppressWarnings("serial")
public class BookInfoPanel extends MPanel implements MouseListener,
		ActionListener {

	private BookUIController bookUIController;
	private BookPO bookPO;
	private JLabel bookNameLabel, authorLabel, priceLabel, pressLabel;
	private MButton collectButton, addToCartButton;

	private final int WIDTH = 750;
	private final int HEIGHT = 40;

	public BookInfoPanel(BookUIController bookUIController, BookPO bookPO) {
		super();
		setLayout(null);
		this.bookUIController = bookUIController;
		this.bookPO = bookPO;
		init();
	}

	public void init() {
		setSize(WIDTH, HEIGHT);
		bookNameLabel = new JLabel(bookPO.getName());
		bookNameLabel.setSize(130, 30);
		bookNameLabel.setForeground(Color.red);
		bookNameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		bookNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookNameLabel.setLocation(10, 5);
		bookNameLabel.addMouseListener(this);

		authorLabel = new JLabel(bookPO.getAuthor());
		authorLabel.setSize(100, 20);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		authorLabel.setLocation(180, 10);

		priceLabel = new JLabel("￥" + bookPO.getPrice());
		priceLabel.setSize(60, 20);
		priceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		priceLabel.setLocation(280, 10);

		pressLabel = new JLabel(bookPO.getPress());
		pressLabel.setSize(120, 20);
		pressLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		pressLabel.setLocation(370, 10);

		collectButton = new MButton("收藏图书");
		collectButton.setSize(100, 25);
		collectButton.setFocusable(false);
		collectButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		collectButton.setLocation(500, 9);

		addToCartButton = new MButton("加入购物车");
		addToCartButton.setSize(100, 25);
		addToCartButton.setFocusable(false);
		addToCartButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 13));
		addToCartButton.setLocation(630, 9);

		collectButton.addActionListener(this);
		addToCartButton.addActionListener(this);

		add(bookNameLabel);
		add(authorLabel);
		add(priceLabel);
		add(pressLabel);
		add(collectButton);
		add(addToCartButton);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == bookNameLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addToCartButton) {
			if (Agent.userAgent == null) {
				ImageDialog.showNOImage(this, "您尚未登录");
				bookUIController.getMainFrame().getMemberUIController()
						.setLoginView();
			} else {
				try {
					ResultMessage resultMessage = Agent.saleService.addToCart(
							new ItemPO(-1, Agent.userAgent.getId(), bookPO
									.getISBN(), bookPO.getPrice(), 1),
							Agent.userAgent.getId());
					if (resultMessage.isInvokeSuccess()) {
						ImageDialog.showYesImage(this, "添加成功");
					} else {
						ImageDialog.showNOImage(this,
								resultMessage.getPostScript());
					}

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == collectButton) {
			if (Agent.userAgent == null) {
				ImageDialog.showNOImage(this, "您尚未登录");
				bookUIController.getMainFrame().getMemberUIController()
						.setLoginView();
			} else {
				try {
					ResultMessage resultMessage = Agent.memberService
							.bookCollect(bookPO.getISBN(),
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
		}
	}
}
