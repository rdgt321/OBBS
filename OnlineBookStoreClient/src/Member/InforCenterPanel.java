package Member;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ClientRunner.Agent;
import ClientRunner.ImageDialog;
import ClientRunner.IMGSTATIC;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MPasswordField;
import ClientRunner.MTextField;
import Promotion.CouponsPO;
import Promotion.EquivalentBondPO;
import RMI.ResultMessage;
import Sale.OrderPO;

@SuppressWarnings("serial")
public class InforCenterPanel extends MPanel implements MouseListener,
		ActionListener {

	private MemberUIController memberUIController;
	private MemberPO memberPO;
	private JLabel personalInfoLabel, buyHistoryLabel, passwordLabel,
			promotionLabel, messageCenterLabel;
	private MPanel contentPane;
	// personalInfoView
	private JLabel nameLabel, phoneLabel, birthLabel, nameDetail, phoneDetail,
			birthDetail;
	private MButton modifyButton, ensuremodifyButton, modifyreturnButton;
	private MTextField nameField, phoneField, birth_yearField,
			birth_monthField, birth_dateField;

	// buyHistoryView
	private PurchaseRecordPanel purchaseRecordPanel;
	ArrayList<OrderPO> list;

	// passwordView
	private JLabel prePassword, newPassword;
	private MPasswordField prePasswordField, newPasswordField;
	private MButton ensureButton, returnButton;

	// promotionView
	private JLabel equiBondLabel;
	private JLabel discoutBondLabel;
	private int equiBondNum;
	private int discoutnBondNum;
	private ArrayList<CouponsPO> coupons_list;
	private ArrayList<EquivalentBondPO> equi_list;

	// message center view
	private MessageCenterPanel messageCenterPanel;

	public InforCenterPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (IMGSTATIC.otherBG != null) {
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.8f));
			g2d.drawImage(IMGSTATIC.otherBG, 0, 0, 800, 530, this);
			g2d.setComposite(composite);
		}
		g2d.dispose();
	}

	public void init() {
		setSize(800, 530);
		setLocation(0, 70);
		setVisible(true);
		setLayout(null);
		contentPane = new MPanel();
		contentPane.setLocation(180, 50);
		contentPane.setSize(600, 430);
		contentPane.setLayout(null);

		personalInfoLabel = new JLabel("个人信息");
		personalInfoLabel.setSize(140, 40);
		personalInfoLabel.setLocation(50, 50);
		personalInfoLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

		buyHistoryLabel = new JLabel("购买记录");
		buyHistoryLabel.setSize(140, 40);
		buyHistoryLabel.setLocation(50, 100);
		buyHistoryLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

		passwordLabel = new JLabel("修改密码");
		passwordLabel.setSize(140, 40);
		passwordLabel.setLocation(50, 150);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

		promotionLabel = new JLabel("优惠信息");
		promotionLabel.setSize(140, 40);
		promotionLabel.setLocation(50, 200);
		promotionLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

		messageCenterLabel = new JLabel("站内信");
		messageCenterLabel.setSize(140, 40);
		messageCenterLabel.setLocation(50, 250);
		messageCenterLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

		personalInfoLabel.addMouseListener(this);
		buyHistoryLabel.addMouseListener(this);
		passwordLabel.addMouseListener(this);
		promotionLabel.addMouseListener(this);
		messageCenterLabel.addMouseListener(this);

		add(personalInfoLabel);
		add(buyHistoryLabel);
		add(passwordLabel);
		add(promotionLabel);
		add(messageCenterLabel);
		add(contentPane);

		try {
			ResultMessage resultMessage = Agent.memberService
					.queryMember(Agent.userAgent.getId());
			if (resultMessage != null) {
				memberPO = (MemberPO) resultMessage.getResultSet().get(0);
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	private void initPersonalInfoView() {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setSize(600, 430);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(100, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		nameDetail = new JLabel(memberPO.getName());
		nameDetail.setSize(300, 40);
		nameDetail.setLocation(200, 80);
		nameDetail.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(100, 130);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		phoneDetail = new JLabel(memberPO.getPhone());
		phoneDetail.setSize(300, 40);
		phoneDetail.setLocation(200, 130);
		phoneDetail.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		birthLabel = new JLabel("出生日期:");
		birthLabel.setSize(100, 40);
		birthLabel.setLocation(100, 180);
		birthLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String birth = sdf.format(memberPO.getBirth().getTime());
		birthDetail = new JLabel(birth);
		birthDetail.setSize(300, 40);
		birthDetail.setLocation(200, 180);
		birthDetail.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		modifyButton = new MButton("修改");
		modifyButton.setSize(100, 40);
		modifyButton.setFocusable(false);
		modifyButton.setLocation(160, 240);
		modifyButton.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		modifyButton.addActionListener(this);

		panel.add(nameLabel);
		panel.add(nameDetail);
		panel.add(phoneLabel);
		panel.add(phoneDetail);
		panel.add(birthLabel);
		panel.add(birthDetail);
		panel.add(modifyButton);
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.validate();
		contentPane.requestFocus();
		repaint();
	}

	@SuppressWarnings("static-access")
	private void initmodifyView() {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setSize(600, 430);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(100, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		nameField = new MTextField(30);
		nameField.setSize(150, 40);
		nameField.setText(memberPO.getName());
		nameField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		nameField.setLocation(220, 80);

		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(100, 130);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		phoneField = new MTextField(30);
		phoneField.setSize(150, 40);
		phoneField.setText(memberPO.getPhone());
		phoneField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		phoneField.setLocation(220, 130);

		birthLabel = new JLabel("出生日期:");
		birthLabel.setSize(100, 40);
		birthLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		birthLabel.setLocation(100, 180);

		Calendar calendar = memberPO.getBirth();

		birth_yearField = new MTextField();
		birth_yearField.setSize(60, 40);
		birth_yearField.setText("" + calendar.get(calendar.YEAR));
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_yearField.setLocation(220, 180);

		JLabel yearLabel = new JLabel("年");
		yearLabel.setSize(20, 40);
		yearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		yearLabel.setLocation(281, 180);

		birth_monthField = new MTextField();
		birth_monthField.setSize(40, 40);
		birth_monthField.setText("" + (calendar.get(calendar.MONTH) + 1));
		birth_monthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_monthField.setLocation(302, 180);

		JLabel monthLabel = new JLabel("月");
		monthLabel.setSize(20, 40);
		monthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		monthLabel.setLocation(343, 180);

		birth_dateField = new MTextField();
		birth_dateField.setSize(40, 40);
		birth_dateField.setText("" + calendar.get(calendar.DATE));
		birth_dateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_dateField.setLocation(364, 180);

		JLabel dateLabel = new JLabel("日");
		dateLabel.setSize(20, 40);
		dateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		dateLabel.setLocation(405, 180);

		ensuremodifyButton = new MButton("确认修改");
		ensuremodifyButton.setSize(120, 40);
		ensuremodifyButton.setLocation(140, 240);
		ensuremodifyButton.setFocusable(false);
		ensuremodifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensuremodifyButton.addActionListener(this);

		modifyreturnButton = new MButton("返回");
		modifyreturnButton.setSize(120, 40);
		modifyreturnButton.setLocation(280, 240);
		modifyreturnButton.setFocusable(false);
		modifyreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyreturnButton.addActionListener(this);

		panel.add(nameLabel);
		panel.add(phoneLabel);
		panel.add(birthLabel);
		panel.add(nameField);
		panel.add(phoneField);
		panel.add(birth_yearField);
		panel.add(yearLabel);
		panel.add(birth_monthField);
		panel.add(monthLabel);
		panel.add(birth_dateField);
		panel.add(dateLabel);
		panel.add(ensuremodifyButton);
		panel.add(modifyreturnButton);
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.validate();
		contentPane.requestFocus();
		repaint();
	}

	@SuppressWarnings("unchecked")
	private void initBuyHisView() {
		// set the information of the purchase history table
		try {
			ResultMessage resultMessage = Agent.memberService
					.purchaseQuery(Agent.userAgent.getId());
			list = resultMessage.getResultSet();
			if (list != null) {
				purchaseRecordPanel = new PurchaseRecordPanel(
						memberUIController, list);
				purchaseRecordPanel.init();
				contentPane.removeAll();
				contentPane.add(purchaseRecordPanel);
				contentPane.validate();
				contentPane.requestFocus();
				repaint();
			} else {
				ImageDialog.showNOImage(this, resultMessage.getPostScript());
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	private void initPasswordView() {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setSize(600, 430);

		prePassword = new JLabel("旧密码:");
		prePassword.setSize(100, 40);
		prePassword.setLocation(100, 80);
		prePassword.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		newPassword = new JLabel("新密码:");
		newPassword.setSize(100, 40);
		newPassword.setLocation(100, 130);
		newPassword.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		prePasswordField = new MPasswordField(20);
		prePasswordField.setSize(150, 40);
		prePasswordField.setLocation(180, 80);
		prePasswordField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		newPasswordField = new MPasswordField(20);
		newPasswordField.setSize(150, 40);
		newPasswordField.setLocation(180, 130);
		newPasswordField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		ensureButton = new MButton("确认修改");
		ensureButton.setSize(130, 40);
		ensureButton.setLocation(120, 190);
		ensureButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		returnButton = new MButton("取消");
		returnButton.setSize(120, 40);
		returnButton.setLocation(270, 190);
		returnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		ensureButton.addActionListener(this);
		returnButton.addActionListener(this);

		panel.add(prePassword);
		panel.add(newPassword);
		panel.add(prePasswordField);
		panel.add(newPasswordField);
		panel.add(ensureButton);
		panel.add(returnButton);
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.validate();
		contentPane.requestFocus();
		repaint();
	}

	@SuppressWarnings("unchecked")
	private void initPromotionView() {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setSize(600, 430);

		try {
			ResultMessage resultMessage = Agent.memberService
					.getCoupons(Agent.userAgent.getId());
			coupons_list = resultMessage.getResultSet();
			if (coupons_list != null) {
				discoutnBondNum = coupons_list.size();
			} else {
				discoutnBondNum = 0;
			}

		} catch (RemoteException re) {
			re.printStackTrace();
		}
		try {
			ResultMessage resultMessage = Agent.memberService
					.getEquivalentBond(Agent.userAgent.getId());
			equi_list = resultMessage.getResultSet();
			if (equi_list != null) {
				equiBondNum = equi_list.size();
			} else {
				equiBondNum = 0;
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		if (equiBondNum == 0 && discoutnBondNum == 0) {
			JLabel temp = new JLabel("对不起,您当前没有任何优惠券!");
			JPanel nothing = new JPanel() {
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					if (IMGSTATIC.boring != null) {
						g.drawImage(IMGSTATIC.boring, 0, 0, getWidth(),
								getHeight(), this);
					}
				}
			};
			nothing.setLayout(null);
			nothing.setSize(80, 80);
			nothing.setLocation(235, 150);
			nothing.setOpaque(false);

			temp.setSize(400, 30);
			temp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			temp.setForeground(Color.red);
			temp.setLocation(150, 70);

			panel.add(temp);
			panel.add(nothing);
		} else {
			String s1 = "您有" + equiBondNum + "张等价券";
			equiBondLabel = new JLabel(s1);
			equiBondLabel.setSize(400, 30);
			equiBondLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			equiBondLabel.setLocation(100, 50);

			String s2 = "您有" + discoutnBondNum + "张打折券";
			discoutBondLabel = new JLabel(s2);
			discoutBondLabel.setSize(400, 30);
			discoutBondLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			discoutBondLabel.setLocation(100, 90);

			panel.add(equiBondLabel);
			panel.add(discoutBondLabel);
		}
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.validate();
		contentPane.requestFocus();
		repaint();
	}

	public void initMessageCenterView() {
		if (messageCenterPanel == null) {
			messageCenterPanel = new MessageCenterPanel(memberUIController);
			messageCenterPanel.init();
		}
		contentPane.removeAll();
		contentPane.add(messageCenterPanel);
		contentPane.validate();
		contentPane.requestFocus();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == modifyButton) {
			initmodifyView();
		} else if (e.getSource() == modifyreturnButton) {
			initPersonalInfoView();
		} else if (e.getSource() == ensuremodifyButton) {
			try {
				int year = Integer.parseInt(birth_yearField.getText().trim());
				int month = Integer.parseInt(birth_monthField.getText().trim());
				int date = Integer.parseInt(birth_dateField.getText().trim());

				ResultMessage resultMessage = Agent.memberService
						.modifyMember(new MemberPO(memberPO.getID(), nameField
								.getText().trim(), memberPO.getPassword(),
								phoneField.getText(), new GregorianCalendar(
										year, month, date), memberPO
										.getIntegral()));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "信息修改成功");
				} else {
					ImageDialog
							.showNOImage(this, resultMessage.getPostScript());
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		} else if (e.getSource() == ensureButton) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == personalInfoLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initPersonalInfoView();
		} else if (e.getSource() == buyHistoryLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initBuyHisView();
		} else if (e.getSource() == passwordLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initPasswordView();
		} else if (e.getSource() == promotionLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initPromotionView();
		} else if (e.getSource() == messageCenterLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initMessageCenterView();
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
