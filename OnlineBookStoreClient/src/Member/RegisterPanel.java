package Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import RMI.ResultMessage;

@SuppressWarnings("serial")
public class RegisterPanel extends JPanel implements ActionListener {
	
	private MemberService memberService;
	private JLabel nameLabel, passwordLabel, phoneLabel, birthLabel, nameWarning, passwordWarning, phoneWarning;
	private JTextField regname = null;
	private JPasswordField regpass = null;
	private JTextField phone = null;
	private JTextField birth_yearField = null;
	private JTextField birth_monthField = null;
	private JTextField birth_dateField = null;
	private JButton registerbtn = null;
	private JButton returnbtn = null;

	private MemberUIController memberUIController;

	public RegisterPanel(MemberUIController memberUIController, MemberService memberService) {
		this.memberService = memberService;
		this.memberUIController = memberUIController;
	}

	public void init() {

		setSize(800, 600);
		setLayout(null);
		setLocation(0, 0);

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
		
		regname = new JTextField(30);
		regname.setSize(180, 35);
		regname.setText("");
		regname.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		regname.setLocation(350, 160);
		regname.setVisible(true);

		regpass = new JPasswordField(30);
		regpass.setSize(180, 35);
		regpass.setText("");
		regpass.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		regpass.setLocation(350, 220);
		regpass.setVisible(true);

		phone = new JTextField(30);
		phone.setSize(180, 35);
		phone.setText("");
		phone.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		phone.setLocation(350, 280);
		phone.setVisible(true);

		birth_yearField = new JTextField();
		birth_yearField.setSize(60, 35);
		birth_yearField.setText("");
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_yearField.setLocation(350, 335);
		
		JLabel yearTemp = new JLabel("年");
		yearTemp.setSize(20, 50);
		yearTemp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		yearTemp.setLocation(411, 330);
		
		birth_monthField = new JTextField();
		birth_monthField.setSize(30, 35);
		birth_monthField.setText("");
		birth_monthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_monthField.setLocation(432, 335);
		
		JLabel monthTemp = new JLabel("月");
		monthTemp.setSize(20, 50);
		monthTemp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		monthTemp.setLocation(463, 330);
		
		birth_dateField = new JTextField();
		birth_dateField.setSize(30, 35);
		birth_dateField.setText("");
		birth_dateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_dateField.setLocation(484, 335);
		
		JLabel dateTemp = new JLabel("日");
		dateTemp.setSize(20, 50);
		dateTemp.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		dateTemp.setLocation(515, 330);
		
		registerbtn = new JButton("注册");
		registerbtn.setSize(90, 40);
		registerbtn.setLocation(320, 410);
		registerbtn.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		registerbtn.addActionListener(this);

		returnbtn = new JButton("返回");
		returnbtn.setSize(90, 40);
		returnbtn.setLocation(420, 410);
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
			Calendar calendar = new GregorianCalendar(Integer.parseInt(birth_yearField.getText().trim()), Integer.parseInt(birth_monthField.getText().trim()), Integer.parseInt(birth_dateField.getText().trim()));
			if(SafeCheck.isLegalName(reg_login_name) 
					&& SafeCheck.isLegalPassword(reg_login_password) 
					&& SafeCheck.isLegalPhoneNumber(reg_phone)){
				nameWarning.setVisible(false);
				passwordWarning.setVisible(false);
				phoneWarning.setVisible(false);
			}
			else{
				if(!SafeCheck.isLegalName(reg_login_name)){
					nameWarning.setVisible(true);
				}
				else
					nameWarning.setVisible(false);
				if(!SafeCheck.isLegalPassword(reg_login_password)){
					passwordWarning.setVisible(true);
				}
				else
					passwordWarning.setVisible(false);
				if(!SafeCheck.isLegalPhoneNumber(reg_phone)){
					phoneWarning.setVisible(true);
				}
				else
					phoneWarning.setVisible(false);
				if(!SafeCheck.isLegalBirth(calendar)){
					
				}
			}
			validate();
			try {
				ResultMessage resultMessage = memberService.addMember(new MemberPO(-1, reg_login_name, new String(reg_login_password), reg_phone, calendar));
				if(resultMessage.isInvokeSuccess()){
					System.out.println("success");
				}
				else{
					//show the reason why register fail
					System.out.println(resultMessage.getPostScript());
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == returnbtn) {
			memberUIController.setReturnView();
		}
	}
}
