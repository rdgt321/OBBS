package Sale;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import ClientRunner.Agent;
import ClientRunner.Const;
import ClientRunner.Encrypt;
import ClientRunner.ImageDialog;
import ClientRunner.IMGSTATIC;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MPasswordField;
import ClientRunner.MTextField;

@SuppressWarnings("serial")
public class PaymentPanel extends MPanel implements ActionListener {
	private SaleUIController saleUIController;
	private MPanel crecardpanel;
	private JLabel addressLabel, payTypeLabel, passwordLabel, orderLabel;
	private MTextField addressField;
	private MButton ensureButton;
	private JRadioButton creditCardButton, cashButton, icbcButton, abcButton,
			ccbButton;
	private MPasswordField passwordField;
	private int orderID;

	public PaymentPanel(SaleUIController saleUIController, int orderID) {
		this.saleUIController = saleUIController;
		this.orderID = orderID;
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
		setSize(800, 530);
		setLocation(0, 70);
		setVisible(true);
		setLayout(null);

		orderLabel = new JLabel("订单号:" + orderID);
		orderLabel.setSize(120, 40);
		orderLabel.setLocation(100, 0);
		orderLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		addressLabel = new JLabel("收货地址：");
		addressLabel.setSize(120, 40);
		addressLabel.setLocation(100, 40);
		addressLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		addressField = new MTextField(60);
		addressField.setSize(300, 40);
		addressField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addressField.setLocation(220, 40);

		payTypeLabel = new JLabel("付款方式：");
		payTypeLabel.setSize(120, 40);
		payTypeLabel.setLocation(100, 110);
		payTypeLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		creditCardButton = new JRadioButton("信用卡");
		creditCardButton.setSize(100, 40);
		creditCardButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		creditCardButton.setFocusable(false);
		creditCardButton.setLocation(230, 110);
		creditCardButton.addActionListener(this);
		creditCardButton.setOpaque(false);

		cashButton = new JRadioButton("货到付款");
		cashButton.setSize(130, 40);
		cashButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cashButton.setFocusable(false);
		cashButton.setSelected(true);
		cashButton.setLocation(330, 110);
		cashButton.addActionListener(this);
		cashButton.setOpaque(false);

		ButtonGroup group = new ButtonGroup();
		group.add(creditCardButton);
		group.add(cashButton);

		ensureButton = new MButton("确认");
		ensureButton.setSize(80, 40);
		ensureButton.setLocation(550, 300);
		ensureButton.setFocusable(false);
		ensureButton.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		ensureButton.addActionListener(this);

		passwordLabel = new JLabel("请再次输入密码以确认交易:");
		passwordLabel.setSize(280, 40);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		passwordLabel.setLocation(100, 250);

		passwordField = new MPasswordField();
		passwordField.setSize(300, 40);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(220, 300);

		add(orderLabel);
		add(addressLabel);
		add(addressField);
		add(payTypeLabel);
		add(creditCardButton);
		add(cashButton);
		add(passwordLabel);
		add(passwordField);
		add(ensureButton);
	}

	private void createCreCard() {
		crecardpanel = new MPanel();

		crecardpanel.setSize(500, 100);
		crecardpanel.setVisible(true);
		crecardpanel.setLayout(null);

		JLabel prompt = new JLabel("请选择您所要进行交易的银行");
		prompt.setSize(300, 30);
		prompt.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		prompt.setLocation(0, 5);

		icbcButton = new JRadioButton("中国工商银行");
		icbcButton.setSize(150, 30);
		icbcButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		icbcButton.setFocusable(false);
		icbcButton.setSelected(true);
		icbcButton.setLocation(0, 40);
		icbcButton.setOpaque(false);

		abcButton = new JRadioButton("中国农业银行");
		abcButton.setSize(150, 30);
		abcButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		abcButton.setFocusable(false);
		abcButton.setLocation(160, 40);
		abcButton.setOpaque(false);

		ccbButton = new JRadioButton("中国建设银行");
		ccbButton.setSize(150, 30);
		ccbButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ccbButton.setFocusable(false);
		ccbButton.setLocation(320, 40);
		ccbButton.setOpaque(false);

		ButtonGroup group = new ButtonGroup();
		group.add(icbcButton);
		group.add(abcButton);
		group.add(ccbButton);

		crecardpanel.add(prompt);
		crecardpanel.add(icbcButton);
		crecardpanel.add(abcButton);
		crecardpanel.add(ccbButton);
		crecardpanel.setLocation(120, 160);
		this.add(crecardpanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ensureButton) {
			boolean validate = false;
			boolean success = false;
			try {
				validate = Agent.userAgent.getPassword().equals(
						Encrypt.md5(String.copyValueOf(
								passwordField.getPassword()).trim()));
				if (validate) {
					if (creditCardButton.isSelected()) {
						success = Agent.memberService.paymentFinishi(orderID,
								Const.DILIVERING).isInvokeSuccess();
					} else if (cashButton.isSelected()) {
						success = Agent.memberService.paymentFinishi(orderID,
								Const.DILIVERING_CASH).isInvokeSuccess();
					}
					if (success) {
						saleUIController.setDealSuccessView();
					} else {
						ImageDialog.showNOImage(this, "支付失败");
					}
				} else {
					ImageDialog.showNOImage(this, "密码错误");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == creditCardButton) {
			if (crecardpanel == null) {
				createCreCard();
			}
			icbcButton.setEnabled(true);
			abcButton.setEnabled(true);
			ccbButton.setEnabled(true);
			crecardpanel.setVisible(true);
			validate();
		} else if (e.getSource() == cashButton) {
			if (crecardpanel != null) {
				icbcButton.setEnabled(false);
				abcButton.setEnabled(false);
				ccbButton.setEnabled(false);
				crecardpanel.setVisible(false);
			}
		}
	}
}
