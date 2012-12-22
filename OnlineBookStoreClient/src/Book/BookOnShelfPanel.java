package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
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
public class BookOnShelfPanel extends MPanel implements MouseListener,
		ActionListener {
	private BookUIController bookUIController;

	private JLabel nameLabel, priceLabel, authorLabel, pressLabel;

	private BookPO bookPO;

	private MButton addToCartButton;
	private MButton deleteButton;

	public BookOnShelfPanel(BookUIController bookUIController, BookPO bookPO) {
		this.bookUIController = bookUIController;
		this.bookPO = bookPO;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void init() {
		setLayout(null);
		setSize(750, 50);

		nameLabel = new JLabel(bookPO.getName());
		nameLabel.setSize(160, 40);
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nameLabel.setLocation(10, 5);
		nameLabel.addMouseListener(this);

		priceLabel = new JLabel("￥" + bookPO.getPrice());
		priceLabel.setSize(60, 40);
		priceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		priceLabel.setLocation(180, 5);

		authorLabel = new JLabel(bookPO.getAuthor());
		authorLabel.setSize(80, 40);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		authorLabel.setLocation(270, 5);

		pressLabel = new JLabel(bookPO.getPress());
		pressLabel.setSize(120, 40);
		pressLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		pressLabel.setLocation(340, 5);

		addToCartButton = new MButton("加入购物车");
		addToCartButton.setSize(125, 40);
		addToCartButton.setFocusable(false);
		addToCartButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		addToCartButton.setLocation(470, 5);

		deleteButton = new MButton("删除");
		deleteButton.setSize(120, 40);
		deleteButton.setFocusable(false);
		deleteButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		deleteButton.setLocation(610, 5);

		addToCartButton.addActionListener(this);
		deleteButton.addActionListener(this);

		add(nameLabel);
		add(priceLabel);
		add(authorLabel);
		add(pressLabel);
		add(addToCartButton);
		add(deleteButton);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == nameLabel && e.getButton() == MouseEvent.BUTTON1) {
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
			try {
				ResultMessage resultMessage = Agent.saleService.addToCart(
						new ItemPO(-1, Agent.userAgent.getId(), bookPO
								.getISBN(), bookPO.getPrice(), 1),
						Agent.userAgent.getId());
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "添加成功");
				} else {
					ImageDialog
							.showNOImage(this, resultMessage.getPostScript());
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		} else if (e.getSource() == deleteButton) {
			try {
				ResultMessage resultMessage = Agent.memberService
						.cancelCollect(bookPO.getISBN(),
								Agent.userAgent.getId());
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "成功从收藏中移除");
					bookUIController.getMainFrame().getMemberUIController()
							.setBookCollView();
				} else {
					ImageDialog
							.showNOImage(this, resultMessage.getPostScript());
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		}
	}
}
