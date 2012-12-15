package Member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class InforCenterPanel extends JPanel implements MouseListener,
		ActionListener {
	
	private MemberUIController memberUIController;
	private MemberPO memberPO;
	private JScrollPane resultPane;
	private JLabel personalInfoLabel, buyHistoryLabel, passwordLabel,
			promotionLabel;
	
	// personalInfoView
	private JLabel nameLabel, phoneLabel, birthLabel, nameDetail, phoneDetail, birthDetail;
	private JButton modifyButton, ensuremodifyButton, modifyreturnButton;
	private JTextField nameField, phoneField, birthField;

	// buyHistoryView
	private JTable purchaseHistory;

	// passwordView
	private JLabel prePassword, newPassword;
	private JPasswordField prePasswordField, newPasswordField;
	private JButton ensureButton, returnButton;

	public InforCenterPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	// promotionView
	private JLabel equiBondLabel;
	private JLabel discoutBondLabel;
	private int equiBondNum = 0;
	private int discoutnBondNum = 0;

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);

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
		
		personalInfoLabel.addMouseListener(this);
		buyHistoryLabel.addMouseListener(this);
		passwordLabel.addMouseListener(this);
		promotionLabel.addMouseListener(this);

		add(personalInfoLabel);
		add(buyHistoryLabel);
		add(passwordLabel);
		add(promotionLabel);

		resultPane = new JScrollPane();
		resultPane.setSize(600, 440);
		resultPane.setLocation(180, 50);
		resultPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		resultPane.getVerticalScrollBar().setUnitIncrement(20);
		add(resultPane);
	}

	public void initPersonalInfoView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(100, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		nameDetail = new JLabel("memberPO传过来用户名");
//		nameDetail = new JLabel(memberPO.getName());
		nameDetail.setSize(300, 40);
		nameDetail.setLocation(200, 80);
		nameDetail.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(100, 130);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		phoneDetail = new JLabel("memberPO传过来联系电话");
//		phoneDetail = new JLabel(memberPO.getPhone());
		phoneDetail.setSize(300, 40);
		phoneDetail.setLocation(200, 130);
		phoneDetail.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		birthLabel = new JLabel("出生日期:");
		birthLabel.setSize(100, 40);
		birthLabel.setLocation(100, 180);
		birthLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		birthDetail = new JLabel("memberPO传过来出生日期");
//		birthDetail = new JLabel(String.valueOf(memberPO.getBirth()));
		birthDetail.setSize(300, 40);
		birthDetail.setLocation(200, 180);
		birthDetail.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		modifyButton = new JButton("修改");
		modifyButton.setSize(100, 40);
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
		resultPane.setViewportView(panel);
	}

	private void initmodifyView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(100, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		nameField = new JTextField(30);
		nameField.setSize(150, 40);
		nameField.setText("原用户名");
//		nameField.setText(memberPO.getName());
		nameField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		nameField.setLocation(220, 80);
		
		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(100, 130);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		phoneField = new JTextField(30);
		phoneField.setSize(150, 40);
		phoneField.setText("原联系电话");
//		phoneField.setText(memberPO.getPhone());
		phoneField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		phoneField.setLocation(220, 130);
		
		birthLabel = new JLabel("出生日期:");
		birthLabel.setSize(100, 40);
		birthLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		birthLabel.setLocation(100, 180);
		
		birthField = new JTextField(30);
		birthField.setSize(150, 40);
		birthField.setText("原来的生日");
//		birthField.setText(String.valueOf(memberPO.getBirth()));
		birthField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		birthField.setLocation(220, 180);
		
		ensuremodifyButton = new JButton("确认修改");
		ensuremodifyButton.setSize(120, 40);
		ensuremodifyButton.setLocation(140, 240);
		ensuremodifyButton.setFocusable(false);
		ensuremodifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensuremodifyButton.addActionListener(this);
		
		modifyreturnButton = new JButton("返回");
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
		panel.add(birthField);
		panel.add(ensuremodifyButton);
		panel.add(modifyreturnButton);
		resultPane.setViewportView(panel);
	}

	public void initBuyHisView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));
		
		//set the information of the purchase history table
		purchaseHistory = new JTable();
		purchaseHistory.setSize(500, 430);
		resultPane.setViewportView(panel);
	}

	public void initPasswordView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		prePassword = new JLabel("旧密码:");
		prePassword.setSize(100, 40);
		prePassword.setLocation(100, 80);
		prePassword.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		newPassword = new JLabel("新密码:");
		newPassword.setSize(100, 40);
		newPassword.setLocation(100, 130);
		newPassword.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		prePasswordField = new JPasswordField(20);
		prePasswordField.setSize(150, 40);
		prePasswordField.setLocation(180, 80);
		prePasswordField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		newPasswordField = new JPasswordField(20);
		newPasswordField.setSize(150, 40);
		newPasswordField.setLocation(180, 130);
		newPasswordField.setFont(new Font("楷体_gb2312", Font.BOLD, 20));

		ensureButton = new JButton("确认修改");
		ensureButton.setSize(130, 40);
		ensureButton.setLocation(120, 190);
		ensureButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		
		returnButton = new JButton("取消");
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
		resultPane.setViewportView(panel);

	}

	public void initPromotionView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		if(equiBondNum == 0 && discoutnBondNum == 0){
			JLabel temp = new JLabel("对不起,您当前没有任何优惠券!");
			JPanel nothing = new JPanel(){
				public void paintComponent(Graphics g){
					super.paintComponent(g);
					ImageIcon imageIcon = new ImageIcon("materials/boring.gif");
					Image image = imageIcon.getImage();
					if(image != null){
						g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
					}
				}
			};
			nothing.setLayout(null);
			nothing.setSize(80, 80);
			nothing.setLocation(235, 150);
			
			temp.setSize(400, 30);
			temp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			temp.setForeground(Color.red);
			temp.setLocation(150, 70);
			
			panel.add(temp);
			panel.add(nothing);
		}
		else{
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
		
		resultPane.setViewportView(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == modifyButton) {
			initmodifyView();
		} else if (e.getSource() == modifyreturnButton) {
			initPersonalInfoView();
		} else if (e.getSource() == ensuremodifyButton) {

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
	
	public MemberUIController getMemberUIController(){
		return memberUIController;
	}

}
