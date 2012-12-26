package Member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Book.BookInOrderPanel;
import ClientRunner.Agent;
import ClientRunner.Const;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import Sale.ItemPO;
import Sale.OrderPO;

@SuppressWarnings("serial")
public class PurchaseRecordPanel extends MPanel implements MouseListener,
		ActionListener {

	private MemberUIController memberUIController;
	private ArrayList<OrderPO> list;
	private JLabel myLabel;
	private JLabel[] orderLabels;
	private JPanel side_barPanel;
	private int size;
	private ArrayList<ItemPO> item_list;
	private BookInOrderPanel bookInOrderPanel;
	private JScrollPane orderPane, listPane;
	private JPanel listContent;
	private int lastSelected = -1;
	private JLabel header, price, state;
	private MButton pay;

	public PurchaseRecordPanel(MemberUIController memberUIController,
			ArrayList<OrderPO> list) {
		this.memberUIController = memberUIController;
		this.list = list;
		size = list.size();
	}

	public void init() {
		setLayout(null);
		setSize(600, 430);

		side_barPanel = new JPanel();
		side_barPanel.setPreferredSize(new Dimension(140, 60 + 30 * size));
		side_barPanel.setLayout(null);
		side_barPanel.setOpaque(false);
		side_barPanel.setLocation(0, 0);

		myLabel = new JLabel();
		if (Agent.userAgent.getUserType() == Const.MEMBER) {
			myLabel.setText("我的订单");
		} else {
			myLabel.setText("ta的订单");
		}
		myLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		myLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myLabel.setSize(160, 20);
		myLabel.setLocation(0, 5);
		myLabel.setForeground(Color.GREEN.brighter());

		header = new JLabel(
				"     书名               ISBN                   数量             价格");
		header.setSize(420, 60);
		header.setLocation(0, 0);
		header.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

		price = new JLabel();
		price.setSize(160, 40);
		price.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

		state = new JLabel();
		state.setSize(260, 40);
		state.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

		pay = new MButton("立即支付");
		pay.setSize(120, 40);
		pay.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		pay.addActionListener(this);
		pay.setButtonColor(Color.RED.brighter());

		orderLabels = new JLabel[size];
		for (int i = 0; i < size; i++) {
			orderLabels[i] = new JLabel("订单号:" + list.get(i).getOrderID());
			orderLabels[i].setLocation(0, 30 + 30 * i);
			orderLabels[i].setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			orderLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			orderLabels[i].setSize(160, 30);
			orderLabels[i].addMouseListener(this);
			side_barPanel.add(orderLabels[i]);
		}
		side_barPanel.add(myLabel);
		orderPane = new JScrollPane(side_barPanel);
		orderPane.setSize(160, 430);
		orderPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		orderPane.getVerticalScrollBar().setUnitIncrement(20);
		orderPane.setOpaque(false);
		orderPane.getViewport().setOpaque(false);
		add(orderPane);
		listPane = new JScrollPane();
		listPane.setSize(440, 430);
		listPane.setLocation(160, 0);
		listPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		listPane.getVerticalScrollBar().setUnitIncrement(20);
		listPane.setOpaque(false);
		listPane.getViewport().setOpaque(false);
		add(listPane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < size; i++) {
			if (e.getSource() == orderLabels[i]) {
				if (lastSelected != -1) {
					orderLabels[lastSelected].setForeground(Color.BLACK);
				}
				orderLabels[i].setForeground(Color.RED.brighter());
				item_list = list.get(i).getBooks();

				listContent = new JPanel();
				listContent.setPreferredSize(new Dimension(420,
						140 + 60 * item_list.size()));
				listContent.setLocation(0, 0);
				listContent.setLayout(null);
				listContent.setOpaque(false);
				listContent.add(header);

				price.setText("总价格:"
						+ String.format("%.2f", list.get(i).getTotalprice()));
				price.setLocation(260, 60 + 60 * item_list.size());
				price.setForeground(Color.RED.brighter());
				int sdt = list.get(i).getState();
				if (sdt == Const.WAITING_FOR_PAYMENT) {
					state.setText("订单状态:尚未付款");
					if (Agent.userAgent.getUserType() == Const.MEMBER) {
						pay.setLocation(280, 100 + 60 * item_list.size());
						listContent.add(pay);
					}
				} else if (sdt == Const.DILIVERING) {
					state.setText("订单状态:已经发货");
				} else if (sdt == Const.DILIVERING_CASH) {
					state.setText("订单状态:已经发货(货到付款)");
				} else if (sdt == Const.ORDER_CANCEL) {
					state.setText("订单状态:交易取消");
				} else if (sdt == Const.TRADE_SUCCESS) {
					state.setText("订单状态:交易成功");
				}
				state.setLocation(0, 60 + 60 * item_list.size());
				for (int j = 0; j < item_list.size(); j++) {
					bookInOrderPanel = new BookInOrderPanel(memberUIController
							.getMainFrame().getBookUIController(),
							item_list.get(j));
					bookInOrderPanel.init();
					bookInOrderPanel.setLocation(0, 60 + 60 * j);
					listContent.add(bookInOrderPanel);
				}
				listContent.add(price);
				listContent.add(state);
				listPane.setViewportView(listContent);
				repaint();
				validate();
				lastSelected = i;
				break;
			}
		}
	}

	public int getSelectedOrder() {
		if (lastSelected != -1) {
			return list.get(lastSelected).getOrderID();
		}
		return -1;
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
		if (e.getSource() == pay) {
			memberUIController.getMainFrame().getSaleUIController()
					.setPaymentView(list.get(lastSelected).getOrderID());
		}
	}
}
