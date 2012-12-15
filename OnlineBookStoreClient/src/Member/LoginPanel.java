package Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ClientRunner.Agent;
import ClientRunner.MainFrame;
import RMI.ResultMessage;
import RMI.UserAgent;
import User.UserService;
import User.UserUIController;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener {
	
	private JLabel nameLabel, passwordLabel, classifyLabel, nameWarning, passwordWarning;
	private JComboBox<String> classify;
	private JTextField loginname = null;
	private JPasswordField loginpass = null;
	private JButton loginbtn = null;
	private JButton returnbtn = null;

	private MemberUIController memberUIController;
	private UserUIController userUIController;
	private MemberService memberService;
	private UserService userService;
	
	public LoginPanel(MemberUIController memberUIController, MemberService memberService, UserService userService) {
		this.memberUIController = memberUIController;
		this.memberService = memberService;
		this.userService = userService;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon imageIcon = new ImageIcon("materials/login.png");
		Image image = imageIcon.getImage();
		if(image != null){
			g.drawImage(image, 40, 160, 380, 280, this);
		}
	}

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setLocation(0, 0);
		
		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 50);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameLabel.setLocation(430, 160);
		
		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(60, 50);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordLabel.setLocation(430, 230);
		
		classifyLabel = new JLabel("类别:");
		classifyLabel.setSize(60, 50);
		classifyLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		classifyLabel.setLocation(430, 295);

		loginname = new JTextField(30);
		loginname.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		loginname.setText("");
		repaint();
		validate();
		loginname.setSize(180, 35);
		loginname.setLocation(540, 170);
		loginname.setVisible(true);
		
		loginpass = new JPasswordField(30);
		loginpass.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		loginpass.setText("");
		repaint();
		validate();
		loginpass.setSize(180, 35);
		loginpass.setLocation(540, 240);
		loginpass.setVisible(true);
		
		String[] types = { "顾客", "销售经理", "总经理", "系统管理员" };
		classify = new JComboBox<>(types);
		classify.setFocusable(false);
		classify.setSelectedIndex(0);
		repaint();
		validate();
		classify.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		classify.setSelectedItem(types[0]);
		classify.setSize(180, 35);
		classify.setLocation(540, 305);

		nameWarning = new JLabel("用户名不合法!");
		nameWarning.setSize(140, 20);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(540, 215);
		nameWarning.setVisible(false);
		
		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 20);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(540, 280);
		passwordWarning.setVisible(false);
		
		loginbtn = new JButton("登陆");
		loginbtn.setSize(90, 40);
		loginbtn.setLocation(455, 380);
		loginbtn.setFocusable(false);
		loginbtn.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		loginbtn.addActionListener(this);
		
		returnbtn = new JButton("返回");
		returnbtn.setSize(90, 40);
		returnbtn.setLocation(600, 380);
		returnbtn.setFocusable(false);
		returnbtn.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		returnbtn.addActionListener(this);
		
		add(nameLabel);
		add(passwordLabel);
		add(classifyLabel);
		add(loginname);
		add(loginpass);
		add(classify);
		add(loginbtn);
		add(returnbtn);
		add(nameWarning);
		add(passwordWarning);
		
	}
	
	public MemberService getMemberService(){
		return memberService;
	}
	
	public UserService getUserService(){
		return userService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginbtn) {
			if(loginname.getText().trim() != null){
				String login_name = loginname.getText();
				char[] password = loginpass.getPassword();
				if(SafeCheck.isLegalName(login_name)
						&& SafeCheck.isLegalPassword(password)){
					nameWarning.setVisible(false);
					passwordWarning.setVisible(false);
					if(classify.getSelectedIndex() == 0){
						try {
							ResultMessage resultMessage = memberService.login(login_name, new String(password), InetAddress.getLocalHost().getHostAddress());
							if(resultMessage.isInvokeSuccess()){
								memberUIController.setMainpageView();
								memberUIController.setAfterLoginNavigate();
								Agent.userAgent = (UserAgent)resultMessage.getResultSet().get(0);
							}
						}catch(RemoteException e1){
							e1.printStackTrace();
						}catch(UnknownHostException e1){
							e1.printStackTrace();
						}
					}
					if(classify.getSelectedIndex() == 1){
						try {
							ResultMessage resultMessage = userService.login(login_name, new String(password), InetAddress.getLocalHost().getHostAddress());
							if(resultMessage.isInvokeSuccess()){
								memberUIController.hideNavigateView();
								MainFrame mainFrame = memberUIController.getMainFrame();
								userUIController = new UserUIController(mainFrame);
								userUIController.createSalesManagerView();
								Agent.userAgent = (UserAgent)resultMessage.getResultSet().get(0);
							}
						}catch(RemoteException e1){
							e1.printStackTrace();
						}catch(UnknownHostException e1){
							e1.printStackTrace();
						}
					}
					if(classify.getSelectedIndex() == 2){
						try {
							ResultMessage resultMessage = userService.login(login_name, new String(password), InetAddress.getLocalHost().getHostAddress());
							if(resultMessage.isInvokeSuccess()){
								memberUIController.hideNavigateView();
								MainFrame mainFrame = memberUIController.getMainFrame();
								userUIController = new UserUIController(mainFrame);
								userUIController.createManagerView();
								Agent.userAgent = (UserAgent)resultMessage.getResultSet().get(0);
							}
						}catch(RemoteException e1){
							e1.printStackTrace();
						}catch(UnknownHostException e1){
							e1.printStackTrace();
						}
					}
					if(classify.getSelectedIndex() == 3){
						try {
							ResultMessage resultMessage = userService.login(login_name, new String(password), InetAddress.getLocalHost().getHostAddress());
							if(resultMessage.isInvokeSuccess()){
								memberUIController.hideNavigateView();
								MainFrame mainFrame = memberUIController.getMainFrame();
								userUIController = new UserUIController(mainFrame);
								userUIController.createAdminView();
								Agent.userAgent = (UserAgent)resultMessage.getResultSet().get(0);
							}
						}catch(RemoteException e1){
							e1.printStackTrace();
						}catch(UnknownHostException e1){
							e1.printStackTrace();
						}
					}
				}
				else{
					if(!SafeCheck.isLegalName(login_name)){
						nameWarning.setVisible(true);
					}
					else
						nameWarning.setVisible(false);
					if(!SafeCheck.isLegalPassword(password)){
						passwordWarning.setVisible(true);
					}
					else
						passwordWarning.setVisible(false);
				}
			}    
		} 
		else 
			if (e.getSource() == returnbtn) {
				memberUIController.setReturnView();
			}
	}
}
