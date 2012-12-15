package Sale;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class OrderEnsurePanel extends JPanel implements ActionListener {
	private SaleUIController saleUIController;
	private String[] collectionBook = { "文学", "小说", "经济", "管理" };
	private int size = collectionBook.length;
	private JLabel[] bookLabels;
	private JLabel totalPriceLabel;
	private JButton ensureButton, cancelButton;

	public OrderEnsurePanel(SaleUIController saleUIController) {
		this.saleUIController = saleUIController;
	}

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);
		JPanel panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 1; i <= size; i++) {
					g.draw3DRect(0, 0, 770, 100 * i, true);

				}
			}

		};
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800, 1000));

		bookLabels = new JLabel[size];

		for (int i = 0; i < size; i++) {
			bookLabels[i] = new JLabel(collectionBook[i]);
			bookLabels[i].setSize(80, 35);
			bookLabels[i].setLocation(10, 5 + 100 * i);
			bookLabels[i].setFont(new Font("楷体_gb2312", Font.BOLD, 22));
			panel.add(bookLabels[i]);
		}

		totalPriceLabel = new JLabel("商品总价：");
		totalPriceLabel.setSize(140, 40);
		totalPriceLabel.setLocation(500, 40 + 100 * size);
		totalPriceLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));
		
		ensureButton = new JButton("提交订单");
		ensureButton.setEnabled(true);
		ensureButton.setSize(118, 40);
		ensureButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensureButton.setLocation(500, 100 + 100 * size);
		
		cancelButton = new JButton("取消");
		cancelButton.setSize(100, 40);
		cancelButton.setFocusable(false);
		cancelButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cancelButton.setLocation(625, 100 + 100 * size);
		
		ensureButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		panel.add(totalPriceLabel);
		panel.add(ensureButton);
		panel.add(cancelButton);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setSize(780, 500);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ensureButton) {
			saleUIController.setPaymentView();
		} else if (e.getSource() == cancelButton) {

		}
	}
}
