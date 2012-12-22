package Sale;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Book.BookInCartPanel;
import ClientRunner.Agent;
import ClientRunner.Const;
import ClientRunner.ImageDialog;
import ClientRunner.IMGSTATIC;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MTextField;
import Member.MemberPO;
import Promotion.CouponsPO;
import Promotion.DiscoutBondBox;
import Promotion.EquiBondBox;
import Promotion.EquivalentBondPO;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class CartPanel extends MPanel implements ActionListener {
	private SaleUIController saleUIController;
	private ArrayList<CartPO> book_in_cart;
	private MButton submitButton;
	private JRadioButton useBondButton, notUseBondButton, useEarnedValue;
	private JRadioButton useEquiBond, useDiscountBond;
	private JLabel totalPriceLabel, realPirceLabel, integraltipLabel,
			bondtipLabel;
	private int size;
	private MPanel contentPane, useBondPanel, useEarnedValuePanel;
	private MTextField earnedValueField;
	private EquiBondBox equiBondBox;
	private DiscoutBondBox discountBondBox;
	private BookInCartPanel[] bookInCartPanels;
	private JScrollPane scrollPane;
	private int num_of_item;
	private double totalPrice;
	private double realPrice;
	private MemberPO memberPO;
	private CartPO cart;

	public CartPanel(SaleUIController saleUIController) {
		this.saleUIController = saleUIController;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		setSize(800, 530);
		setLocation(0, 70);
		setVisible(true);
		setLayout(null);

		try {
			ResultMessage resultMessage = Agent.saleService
					.getBooksInCart(Agent.userAgent.getId());
			book_in_cart = resultMessage.getResultSet();
			if (book_in_cart != null) {
				size = 1;
			} else {
				size = 0;
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		if (size != 0) {
			contentPane = new MPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					if (IMGSTATIC.homepageBG != null) {
						int height = scrollPane.getVerticalScrollBar()
								.getValue();
						Composite composite = g2d.getComposite();
						g2d.setComposite(AlphaComposite.getInstance(
								AlphaComposite.SRC_OVER, 0.8f));
						g2d.drawImage(IMGSTATIC.homepageBG, 0, height, 800,
								530, this);
						g2d.setComposite(composite);
					}
					g2d.dispose();
				}

			};
			contentPane.setLayout(null);
			contentPane.setPreferredSize(new Dimension(780,
					150 + 70 * num_of_item));
			cart = book_in_cart.get(0);
			ArrayList<ItemPO> list = cart.getItems();
			num_of_item = list.size();
			bookInCartPanels = new BookInCartPanel[num_of_item];
			for (int j = 0; j < num_of_item; j++) {
				bookInCartPanels[j] = new BookInCartPanel(saleUIController
						.getMainFrame().getBookUIController(), this,
						list.get(j));
				bookInCartPanels[j].init();
				bookInCartPanels[j].setLocation(10, 10 + 70 * j);
				contentPane.add(bookInCartPanels[j]);
			}
			submitButton = new MButton("提交订单");
			submitButton.setSize(120, 40);
			submitButton.setFocusable(false);
			submitButton.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
			submitButton.setLocation(580, 120 + 70 * num_of_item);
			submitButton.addActionListener(this);

			notUseBondButton = new JRadioButton("全额支付");
			notUseBondButton.setSize(100, 25);
			notUseBondButton.setFocusable(false);
			notUseBondButton.setSelected(true);
			notUseBondButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			notUseBondButton.setLocation(80, 30 + 60 * num_of_item);
			notUseBondButton.addActionListener(this);
			notUseBondButton.setOpaque(false);

			useBondButton = new JRadioButton("使用优惠券");
			useBondButton.setSize(120, 25);
			useBondButton.setFocusable(false);
			useBondButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useBondButton.setLocation(185, 30 + 60 * num_of_item);
			useBondButton.addActionListener(this);
			useBondButton.setOpaque(false);

			useEarnedValue = new JRadioButton("使用积分");
			useEarnedValue.setSize(100, 25);
			useEarnedValue.setFocusable(false);
			useEarnedValue.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useEarnedValue.setLocation(310, 30 + 60 * num_of_item);
			useEarnedValue.addActionListener(this);
			useEarnedValue.setOpaque(false);

			ButtonGroup group = new ButtonGroup();
			group.add(notUseBondButton);
			group.add(useBondButton);
			group.add(useEarnedValue);

			totalPriceLabel = new JLabel("商品总价：" + cart.totalprice);
			totalPriceLabel.setSize(280, 40);
			totalPriceLabel.setLocation(500, 40 + 60 * num_of_item);
			totalPriceLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

			realPirceLabel = new JLabel("实际价格:" + cart.totalprice);
			realPirceLabel.setSize(280, 40);
			realPirceLabel.setLocation(500, 80 + 60 * num_of_item);
			realPirceLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));
			realPirceLabel.setForeground(Color.RED.brighter());

			contentPane.add(notUseBondButton);
			contentPane.add(useBondButton);
			contentPane.add(useEarnedValue);
			contentPane.add(totalPriceLabel);
			contentPane.add(realPirceLabel);
			contentPane.add(submitButton);
		} else {
			JLabel nothingLabel = new JLabel("您的购物车中当前没有任何图书!");
			nothingLabel.setSize(400, 30);
			nothingLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			nothingLabel.setForeground(Color.red);
			nothingLabel.setLocation(250, 100);

			contentPane = new MPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g.create();
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					if (IMGSTATIC.homepageBG != null
							&& IMGSTATIC.boring != null) {
						Composite composite = g2d.getComposite();
						g2d.setComposite(AlphaComposite.getInstance(
								AlphaComposite.SRC_OVER, 0.8f));
						g2d.drawImage(IMGSTATIC.homepageBG, 0, 0, 800, 530,
								this);
						g2d.drawImage(IMGSTATIC.boring, 335, 210, 80, 80, this);
						g2d.setComposite(composite);
					}
					g2d.dispose();
				}
			};
			contentPane.setLayout(null);
			contentPane.add(nothingLabel);
			contentPane.setSize(780, 530);
			contentPane.setLocation(0, 0);
		}

		scrollPane = new JScrollPane(contentPane);
		scrollPane.setSize(800, 530);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		CalcPrice();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			int confirm = -1;
			int equivalentbondID = 0;
			int couponsID = 0;
			int integral = 0;
			int orderID = 0;
			if (useBondButton.isSelected() && useEquiBond != null
					&& useEquiBond.isSelected()) {
				EquivalentBondPO equivalentBondPO = (EquivalentBondPO) equiBondBox
						.getSelectedItem();
				equivalentbondID = equivalentBondPO.getEquivalentBondID();
				confirm = JOptionPane
						.showConfirmDialog(contentPane, "是否确认使用编号为:"
								+ equivalentbondID + "的等价券并支付？", "确认支付？",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
			} else if (useBondButton.isSelected() && useDiscountBond != null
					&& useDiscountBond.isSelected()) {
				CouponsPO couponsPO = (CouponsPO) discountBondBox
						.getSelectedItem();
				couponsID = couponsPO.getCounponsID();
				confirm = JOptionPane
						.showConfirmDialog(contentPane, "是否确认使用编号为:"
								+ couponsID + "的折扣券并支付？", "确认支付？",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
			} else if (useEarnedValue.isSelected()) {
				integral = Integer.parseInt(earnedValueField.getText());
				confirm = JOptionPane
						.showConfirmDialog(contentPane, "是否确认使用:" + integral
								+ "点积分并支付？", "确认支付？",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
			} else {
				confirm = JOptionPane.showConfirmDialog(contentPane, "是否确认支付？",
						"确认支付？", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
			}
			if (confirm == JOptionPane.NO_OPTION) {
				return;
			} else if (confirm == JOptionPane.YES_OPTION) {
				try {
					if (equivalentbondID != 0) {
						Agent.memberService.useEquivalentBond(equivalentbondID);
					} else if (couponsID != 0) {
						Agent.memberService.userCoupons(couponsID);
					} else if (integral != 0) {
						Agent.memberService.useIntegral(
								Agent.userAgent.getId(), -integral);
					}
					Calendar calendar = Calendar.getInstance();
					ResultMessage resultMessage = Agent.saleService
							.addOrder(new OrderPO(-1, Agent.userAgent.getId(),
									cart.getItems(), realPrice, calendar, 0));
					if (resultMessage.isInvokeSuccess()) {
						ImageDialog.showYesImage(this, "订单提交成功");
						orderID = (Integer) resultMessage.getResultSet().get(0);
					} else {
						ImageDialog.showNOImage(this,
								resultMessage.getPostScript());
					}
				} catch (RemoteException re) {
					re.printStackTrace();
				}
				saleUIController.setPaymentView(orderID);
			}
		} else if (e.getSource() == useBondButton) {
			setUseBondPanel();
		} else if (e.getSource() == useEarnedValue) {
			setUseEarnedValuePanel();
		} else if (e.getSource() == notUseBondButton) {
			setPayAllPanel();
		}
	}

	public void CalcPrice() {
		totalPrice = 0;
		for (int i = 0; i < num_of_item; i++) {
			totalPrice += bookInCartPanels[i].getPrice()
					* bookInCartPanels[i].getAmount();
		}
		realPrice = totalPrice;
		if (useBondButton.isSelected() && useEquiBond != null
				&& useEquiBond.isSelected()) {
			EquivalentBondPO equivalentBondPO = (EquivalentBondPO) equiBondBox
					.getSelectedItem();
			double denomination = equivalentBondPO.getEquivalentDenomination();
			realPrice = totalPrice - denomination;
		} else if (useBondButton.isSelected() && useDiscountBond != null
				&& useDiscountBond.isSelected()) {
			CouponsPO couponsPO = (CouponsPO) discountBondBox.getSelectedItem();
			double rate = couponsPO.getDiscountRate();
			realPrice = totalPrice * rate;
		} else if (useEarnedValue.isSelected()) {
			double num = Integer.parseInt(earnedValueField.getText());
			realPrice -= num / Const.INTEGRAL_RATE;
		}
		totalPriceLabel.setText("商品总价：" + totalPrice);
		realPirceLabel.setText("实际价格: " + String.format("%.2f", realPrice));
	}

	private void setPayAllPanel() {
		if (useBondPanel != null) {
			useEquiBond.setEnabled(false);
			useDiscountBond.setEnabled(false);
			useBondPanel.setVisible(false);
		}
		if (useEarnedValuePanel != null) {
			useEarnedValuePanel.setVisible(false);
		}
	}

	@SuppressWarnings("unchecked")
	private void setUseBondPanel() {
		if (useBondPanel == null) {
			useBondPanel = new MPanel();
			useBondPanel.setLayout(null);
			useBondPanel.setSize(400, 200);

			useEquiBond = new JRadioButton("使用等价券");
			useEquiBond.setSize(120, 30);
			useEquiBond.setEnabled(false);
			useEquiBond.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useEquiBond.setLocation(0, 0);
			useEquiBond.setOpaque(false);

			useDiscountBond = new JRadioButton("使用打折券");
			useDiscountBond.setSize(120, 30);
			useDiscountBond.setEnabled(false);
			useDiscountBond.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useDiscountBond.setLocation(130, 0);
			useDiscountBond.setOpaque(false);

			equiBondBox = new EquiBondBox();
			try {
				ResultMessage resultMessage = Agent.memberService
						.getEquivalentBond(Agent.userAgent.getId());
				ArrayList<EquivalentBondPO> list = resultMessage.getResultSet();
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						equiBondBox.addItem(list.get(i));
					}
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}

			bondtipLabel = new JLabel("订单面额小于等价券使用限制,无法使用该券");
			bondtipLabel.setSize(280, 20);
			equiBondBox.setFont(new Font("楷体_gb2312", Font.PLAIN, 15));
			bondtipLabel.setForeground(Color.RED.brighter());
			bondtipLabel.setLocation(20, 80);
			bondtipLabel.setVisible(false);

			equiBondBox.setSize(230, 20);
			equiBondBox.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			equiBondBox.setEnabled(false);
			equiBondBox.setVisible(false);
			equiBondBox.addItemListener(new ItemListener() {

				Object pre = null;

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (((EquivalentBondPO) e.getItem()).getUseLimit() > totalPrice) {
						equiBondBox.setSelectedItem(pre);
						bondtipLabel.setVisible(true);
					} else {
						CalcPrice();
						bondtipLabel.setVisible(false);
					}
					pre = e.getItem();
				}
			});
			equiBondBox.setLocation(20, 50);

			discountBondBox = new DiscoutBondBox();
			try {
				ResultMessage resultMessage = Agent.memberService
						.getCoupons(Agent.userAgent.getId());
				ArrayList<CouponsPO> list = resultMessage.getResultSet();
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						discountBondBox.addItem(list.get(i));
					}
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
			discountBondBox.setSize(230, 20);
			discountBondBox.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			discountBondBox.setEnabled(false);
			discountBondBox.setVisible(false);
			discountBondBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					CalcPrice();
				}
			});
			discountBondBox.setLocation(20, 50);

			useEquiBond.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					discountBondBox.setEnabled(false);
					discountBondBox.setVisible(false);

					equiBondBox.setEnabled(true);
					equiBondBox.setVisible(true);
				}
			});

			useDiscountBond.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					equiBondBox.setEnabled(false);
					equiBondBox.setVisible(false);

					discountBondBox.setEnabled(true);
					discountBondBox.setVisible(true);
				}
			});

			ButtonGroup group = new ButtonGroup();
			group.add(useEquiBond);
			group.add(useDiscountBond);

			useBondPanel.add(useEquiBond);
			useBondPanel.add(useDiscountBond);
			useBondPanel.add(equiBondBox);
			useBondPanel.add(discountBondBox);
			useBondPanel.add(bondtipLabel);
			useBondPanel.setVisible(false);
			useBondPanel.setLocation(80, 60 + 60 * num_of_item);
			contentPane.add(useBondPanel);
		}

		if (useEarnedValuePanel != null) {
			useEarnedValuePanel.setVisible(false);
		}

		useEquiBond.setEnabled(true);
		useDiscountBond.setEnabled(true);
		useBondPanel.setVisible(true);
	}

	private void setUseEarnedValuePanel() {
		if (useEarnedValuePanel == null) {
			useEarnedValuePanel = new MPanel();
			useEarnedValuePanel.setLayout(null);
			useEarnedValuePanel.setSize(400, 200);

			// 积分查询
			try {
				ResultMessage resultMessage = Agent.memberService
						.queryMember(Agent.userAgent.getId());
				memberPO = (MemberPO) resultMessage.getResultSet().get(0);
			} catch (RemoteException re) {
				re.printStackTrace();
			}
			String temp = "您当前拥有" + memberPO.getIntegral() + "积分";
			JLabel earnValueLabel = new JLabel(temp);
			earnValueLabel.setSize(300, 30);
			earnValueLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			earnValueLabel.setLocation(0, 0);

			JLabel label = new JLabel("请输入所要兑换的积分的值:");
			label.setSize(230, 30);
			label.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			label.setLocation(0, 35);

			integraltipLabel = new JLabel();
			integraltipLabel.setSize(250, 30);
			integraltipLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			integraltipLabel.setLocation(0, 70);
			integraltipLabel.setForeground(Color.RED);
			integraltipLabel.setVisible(false);

			earnedValueField = new MTextField();
			earnedValueField.setSize(100, 30);
			earnedValueField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			earnedValueField.setLocation(240, 35);
			earnedValueField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String txt = earnedValueField.getText();
					if (txt.matches("[\\d]+")) {
						int num = Integer.parseInt(txt);
						if (num <= 0) {
							earnedValueField.setText("");
							earnedValueField.requestFocus();
							integraltipLabel.setText("兑换数值应为大于0的正整数");
							integraltipLabel.setVisible(true);
						} else if (num > memberPO.getIntegral()) {
							earnedValueField.setText("");
							earnedValueField.requestFocus();
							integraltipLabel.setText("兑换数值不能超过积分数值");
							integraltipLabel.setVisible(true);
						} else {
							CalcPrice();
							integraltipLabel.setVisible(false);
						}
					} else {
						earnedValueField.setText("");
						earnedValueField.requestFocus();
						integraltipLabel.setText("请输入整数数字");
						integraltipLabel.setVisible(true);
					}
				}

			});

			useEarnedValuePanel.add(earnValueLabel);
			useEarnedValuePanel.add(label);
			useEarnedValuePanel.add(earnedValueField);
			useEarnedValuePanel.add(integraltipLabel);

			useEarnedValuePanel.setVisible(false);
			useEarnedValuePanel.setLocation(80, 60 + 60 * num_of_item);
			contentPane.add(useEarnedValuePanel);
		}

		if (useBondPanel != null) {
			useEquiBond.setEnabled(false);
			useDiscountBond.setEnabled(false);
			useBondPanel.setVisible(false);
		}
		useEarnedValuePanel.setVisible(true);
	}
}