package Member;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

<<<<<<< HEAD
import ClientRunner.IMGSTATIC;
=======
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MPasswordField;
import ClientRunner.MTextField;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class RegisterPanel extends MPanel implements ActionListener {

	private MemberService memberService;
	private JLabel nameLabel, passwordLabel, phoneLabel, birthLabel,
			nameWarning, passwordWarning, phoneWarning;
	private MTextField regname = null;
	private MPasswordField regpass = null;
	private MTextField phone = null;
	private MTextField birth_yearField = null;
	private MTextField birth_monthField = null;
	private MTextField birth_dateField = null;
	private MButton registerbtn = null;
	private MButton returnbtn = null;
<<<<<<< HEAD
=======

	private Image bg = null;
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca

	private MemberUIController memberUIController;

	public RegisterPanel(MemberUIController memberUIController,
			MemberService memberService) {
		this.memberService = memberService;
		this.memberUIController = memberUIController;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
<<<<<<< HEAD
		if (IMGSTATIC.loginBG != null) {
			g2d.drawImage(IMGSTATIC.loginBG, 0, 0, 800, 530, this);
=======
		if (bg != null) {
			g2d.drawImage(bg, 0, 0, 800, 530, this);
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.6f));
			g2d.setColor(Color.BLUE);
			g2d.fillRoundRect(215, 130, 380, 260, 20, 20);
			g2d.setComposite(composite);
		}
	}

	public void init() {

		setSize(800, 530);
		setLayout(null);
		setLocation(0, 70);
<<<<<<< HEAD
=======

		bg = Toolkit.getDefaultToolkit().getImage("materials\\login.png");
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 50);
		nameLabel.setLocation(250, 150);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(60, 50);
		passwordLabel.setLocation(250, 210);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 50);
		phoneLabel.setLocation(250, 270);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		birthLabel = new JLabel("生日:");
		birthLabel.setSize(80, 50);
		birthLabel.setLocation(250, 330);
		birthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameWarning = new JLabel("用户名不合法!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(535, 150);
		nameWarning.setVisible(false);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(535, 210);
		passwordWarning.setVisible(false);

		phoneWarning = new JLabel("电话号码不合法!");
		phoneWarning.setSize(148, 50);
		phoneWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		phoneWarning.setForeground(Color.red);
		phoneWarning.setLocation(535, 270);
		phoneWarning.setVisible(false);

		regname = new MTextField(30);
		regname.setSize(180, 35);
		regname.setText("");
		regname.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		regname.setLocation(350, 160);
		regname.setVisible(true);
		regname.setOpaque(false);

		regpass = new MPasswordField(30);
		regpass.setSize(180, 35);
		regpass.setText("");
		regpass.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		regpass.setLocation(350, 220);
		regpass.setVisible(true);
		regpass.setOpaque(false);

		phone = new MTextField(30);
		phone.setSize(180, 35);
		phone.setText("");
		phone.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		phone.setLocation(350, 280);
		phone.setVisible(true);
		phone.setOpaque(false);

		birth_yearField = new MTextField();
		birth_yearField.setSize(60, 35);
		birth_yearField.setText("");
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_yearField.setLocation(350, 335);
		birth_yearField.setOpaque(false);

		JLabel yearTemp = new JLabel("年");
		yearTemp.setSize(20, 50);
		yearTemp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		yearTemp.setLocation(411, 330);

		birth_monthField = new MTextField();
		birth_monthField.setSize(30, 35);
		birth_monthField.setText("");
		birth_monthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_monthField.setLocation(432, 335);
		birth_monthField.setOpaque(false);

		JLabel monthTemp = new JLabel("月");
		monthTemp.setSize(20, 50);
		monthTemp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		monthTemp.setLocation(463, 330);

		birth_dateField = new MTextField();
		birth_dateField.setSize(30, 35);
		birth_dateField.setText("");
		birth_dateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_dateField.setLocation(484, 335);
		birth_dateField.setOpaque(false);

		JLabel dateTemp = new JLabel("日");
		dateTemp.setSize(20, 50);
		dateTemp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		dateTemp.setLocation(515, 330);

		registerbtn = new MButton("注册");
		registerbtn.setButtonColor(Color.BLUE);
		registerbtn.setSize(90, 40);
		registerbtn.setFocusable(false);
		registerbtn.setLocation(290, 410);
		registerbtn.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		registerbtn.addActionListener(this);

		returnbtn = new MButton("返回");
		returnbtn.setButtonColor(Color.BLUE);
		returnbtn.setSize(90, 40);
		returnbtn.setLocation(420, 410);
		returnbtn.setFocusable(false);
		returnbtn.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		returnbtn.addActionListener(this);

		add(nameLabel);
		add(passwordLabel);
		add(phoneLabel);
		add(birthLabel);
		add(birth_yearField);
		add(yearTemp);
		add(birth_monthField);
		add(monthTemp);
		add(birth_dateField);
		add(dateTemp);
		add(regname);
		add(regpass);
		add(phone);
		add(registerbtn);
		add(returnbtn);
		add(nameWarning);
		add(passwordWarning);
		add(phoneWarning);
		repaint();
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerbtn) {
			String reg_login_name = regname.getText().trim();
			char[] reg_login_password = regpass.getPassword();
			String reg_phone = phone.getText().trim();
			Calendar calendar = new GregorianCalendar(
					Integer.parseInt(birth_yearField.getText().trim()),
					Integer.parseInt(birth_monthField.getText().trim()),
					Integer.parseInt(birth_dateField.getText().trim()));
			if (SafeCheck.isLegalName(reg_login_name)
					&& SafeCheck.isLegalPassword(reg_login_password)
					&& SafeCheck.isLegalPhoneNumber(reg_phone)) {
				nameWarning.setVisible(false);
				passwordWarning.setVisible(false);
				phoneWarning.setVisible(false);
			} else {
				if (!SafeCheck.isLegalName(reg_login_name)) {
					nameWarning.setVisible(true);
				} else
					nameWarning.setVisible(false);
				if (!SafeCheck.isLegalPassword(reg_login_password)) {
					passwordWarning.setVisible(true);
				} else
					passwordWarning.setVisible(false);
				if (!SafeCheck.isLegalPhoneNumber(reg_phone)) {
					phoneWarning.setVisible(true);
				} else
					phoneWarning.setVisible(false);
				if (!SafeCheck.isLegalBirth(calendar)) {

				}
			}
			validate();
			try {
				ResultMessage resultMessage = memberService
						.addMember(new MemberPO(-1, reg_login_name, new String(
								reg_login_password), reg_phone, calendar, 0));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this,
							resultMessage.getPostScript());
					memberUIController.setReturnView();
				} else {
					ImageDialog
							.showNOImage(this, resultMessage.getPostScript());
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == returnbtn) {
			memberUIController.setReturnView();
		}
	}
}
