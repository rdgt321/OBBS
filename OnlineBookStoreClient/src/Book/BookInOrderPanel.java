package Book;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ClientRunner.Agent;
import ClientRunner.MPanel;
import RMI.ResultMessage;
import Sale.ItemPO;

@SuppressWarnings("serial")
public class BookInOrderPanel extends MPanel {

	private BookUIController bookUIController;
	private ItemPO itemPO;
	private JLabel nameLabel;
	private JLabel isbnLabel;
	private JLabel priceLabel;
	private JLabel amountLabel;
	private BookPO bookPO;

	public BookInOrderPanel(BookUIController bookUIController, ItemPO itemPO) {
		setLayout(null);
		setSize(420, 50);
		this.bookUIController = bookUIController;
		this.itemPO = itemPO;
	}

	public void init() {
		try {
			ResultMessage resultMessage = Agent.bookService
					.getSelectedBook(itemPO.getBookISBN());
			bookPO = (BookPO) resultMessage.getResultSet().get(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		nameLabel = new JLabel(bookPO.getName());
		nameLabel.setSize(100, 30);
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameLabel.setLocation(0, 10);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		isbnLabel = new JLabel(bookPO.getISBN());
		isbnLabel.setSize(160, 30);
		isbnLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		isbnLabel.setLocation(100, 10);
		isbnLabel.setHorizontalAlignment(SwingConstants.CENTER);

		amountLabel = new JLabel("" + itemPO.getCount());
		amountLabel.setSize(80, 30);
		amountLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		amountLabel.setLocation(260, 10);
		amountLabel.setHorizontalAlignment(SwingConstants.CENTER);

		priceLabel = new JLabel("￥" + bookPO.getPrice() * itemPO.getCount());
		priceLabel.setSize(85, 30);
		priceLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
		priceLabel.setLocation(340, 10);
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

		add(nameLabel);
		add(isbnLabel);
		add(amountLabel);
		add(priceLabel);
	}
}
