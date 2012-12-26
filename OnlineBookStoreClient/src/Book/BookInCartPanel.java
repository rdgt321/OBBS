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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ClientRunner.Agent;
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import RMI.ResultMessage;
import Sale.CartPanel;
import Sale.ItemPO;

@SuppressWarnings("serial")
public class BookInCartPanel extends MPanel implements MouseListener,
		ActionListener {

	private BookUIController bookUIController;
	private CartPanel cartPanel;
	private ItemPO itemPO;
	private JLabel nameLabel;
	private JLabel authorLabel;
	private JLabel priceLabel;
	private JLabel amountLabel;
	private JSpinner amountSpinner;
	private MButton delButton;
	private BookPO bookPO;

	public BookInCartPanel(BookUIController bookUIController,
			CartPanel cartPanel, ItemPO itemPO) {
		this.bookUIController = bookUIController;
		this.cartPanel = cartPanel;
		this.itemPO = itemPO;
	}

	public void init() {
		setLayout(null);
		setSize(740, 60);

		try {
			ResultMessage resultMessage = Agent.bookService
					.getSelectedBook(itemPO.getBookISBN());
			bookPO = (BookPO) resultMessage.getResultSet().get(0);
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		nameLabel = new JLabel(bookPO.getName());
		nameLabel.setSize(180, 30);
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nameLabel.setLocation(15, 15);
		nameLabel.addMouseListener(this);

		priceLabel = new JLabel("￥" + bookPO.getPrice());
		priceLabel.setSize(60, 30);
		priceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		priceLabel.setLocation(220, 15);

		authorLabel = new JLabel(bookPO.getAuthor());
		authorLabel.setSize(80, 30);
		authorLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		authorLabel.setLocation(310, 15);

		amountLabel = new JLabel("数量:");
		amountLabel.setSize(60, 30);
		amountLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		amountLabel.setLocation(403, 15);

		amountSpinner = new JSpinner(new SpinnerNumberModel());
		amountSpinner.setSize(50, 30);
		amountSpinner.setValue(1);
		amountSpinner.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		amountSpinner.setLocation(451, 15);
		amountSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cartPanel.CalcPrice();
			}
		});

		delButton = new MButton("删除");
		delButton.setSize(80, 30);
		delButton.setFocusable(false);
		delButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		delButton.setLocation(600, 15);
		delButton.addActionListener(this);

		add(nameLabel);
		add(priceLabel);
		add(authorLabel);
		add(amountLabel);
		add(amountSpinner);
		add(delButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == delButton) {
			try {
				ResultMessage resultMessage = Agent.saleService.deleteFromCart(
						bookPO.getISBN(), Agent.userAgent.getId());
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "成功从购物车中删除");
					bookUIController.getMainFrame().getMemberUIController()
							.setCartView();
				} else {
					ImageDialog
							.showNOImage(this, resultMessage.getPostScript());
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}

		}
	}

	public String getISBN() {
		return bookPO.getISBN();
	}

	public int getAmount() {
		return (Integer) amountSpinner.getValue();
	}

	public double getPrice() {
		return bookPO.getPrice();
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

}
