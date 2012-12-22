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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import ClientRunner.Agent;
import ClientRunner.Const;
import ClientRunner.IMGSTATIC;
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MComboBox;
import ClientRunner.MPanel;
import ClientRunner.MPasswordField;
import ClientRunner.MTextField;
import ClientRunner.MainFrame;
import RMI.ResultMessage;
import RMI.UserAgent;
import User.UserUIController;

@SuppressWarnings("serial")
public class LoginPanel extends MPanel implements ActionListener {

	private JLabel nameLabel, passwordLabel, classifyLabel, nameWarning,
			passwordWarning;
	private MComboBox<String> classify;
	private MTextField loginname = null;
	private MPasswordField loginpass = null;
	private MButton loginbtn = null;
	private MButton returnbtn = null;

	private MemberUIController memberUIController;
	private UserUIController userUIController;

	public LoginPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (IMGSTATIC.loginBG != null) {
			g2d.drawImage(IMGSTATIC.loginBG, 0, 0, 800, 530, this);
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.6f));
			g2d.setColor(Color.BLUE);
			g2d.fillRoundRect(215, 130, 380, 240, 20, 20);
			g2d.setComposite(composite);
		}
		g2d.dispose();
	}

	public void init() {
		setSize(800, 530);
		setLayout(null);
		setLocation(0, 70);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 50);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameLabel.setLocation(250, 160);
		nameLabel.setForeground(Color.WHITE);

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(60, 50);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordLabel.setLocation(250, 230);
		passwordLabel.setForeground(Color.WHITE);

		classifyLabel = new JLabel("类别:");
		classifyLabel.setSize(60, 50);
		classifyLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		classifyLabel.setLocation(250, 295);
		classifyLabel.setForeground(Color.WHITE);

		loginname = new MTextField(30);
		loginname.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		loginname.setText("");
		loginname.setSize(180, 35);
		loginname.setLocation(350, 170);
		loginname.setOpaque(false);

		loginpass = new MPasswordField(30);
		loginpass.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		loginpass.setText("");
		loginpass.setSize(180, 35);
		loginpass.setLocation(350, 240);
		loginpass.setOpaque(false);

		String[] types = { "顾客", "销售经理", "总经理", "系统管理员" };
		classify = new MComboBox<String>(types);
		classify.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		classify.setSize(180, 35);
		classify.setLocation(350, 305);

		nameWarning = new JLabel("用户名不合法!");
		nameWarning.setSize(140, 20);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(350, 215);
		nameWarning.setVisible(false);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 20);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(350, 280);
		passwordWarning.setVisible(false);

		loginbtn = new MButton("登陆");
		loginbtn.setButtonColor(Color.BLUE);
		loginbtn.setSize(90, 40);
		loginbtn.setLocation(285, 380);
		loginbtn.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		loginbtn.addActionListener(this);

		returnbtn = new MButton("返回");
		returnbtn.setButtonColor(Color.BLUE);
		returnbtn.setSize(90, 40);
		returnbtn.setLocation(410, 380);
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
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginbtn) {
			if (loginname.getText().trim() != null) {
				String login_name = loginname.getText();
				char[] password = loginpass.getPassword();
				if (SafeCheck.isLegalName(login_name)
						&& SafeCheck.isLegalPassword(password)) {
					nameWarning.setVisible(false);
					passwordWarning.setVisible(false);
					if (classify.getSelectedIndex() == 0) {
						try {
							ResultMessage resultMessage = Agent.memberService
									.login(login_name, new String(password),
											InetAddress.getLocalHost()
													.getHostAddress(),
											Const.MEMBER);
							if (resultMessage.isInvokeSuccess()) {
								Agent.userAgent = (UserAgent) resultMessage
										.getResultSet().get(0);
								memberUIController.setMainpageView();
								memberUIController.setAfterLoginNavigate();
							} else {
								ImageDialog.showNOImage(this,
										resultMessage.getPostScript());
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						}
					}
					if (classify.getSelectedIndex() == 1) {
						try {
							ResultMessage resultMessage = Agent.userService
									.login(login_name, new String(password),
											InetAddress.getLocalHost()
													.getHostAddress(),
											Const.SALESMANAGER);
							if (resultMessage.isInvokeSuccess()) {
								Agent.userAgent = (UserAgent) resultMessage
										.getResultSet().get(0);
								memberUIController.hideNavigateView();
								MainFrame mainFrame = memberUIController
										.getMainFrame();
								userUIController = new UserUIController(
										mainFrame);
								userUIController.createSalesManagerView();
							} else {
								ImageDialog.showNOImage(this,
										resultMessage.getPostScript());
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						}
					}
					if (classify.getSelectedIndex() == 2) {
						try {
							ResultMessage resultMessage = Agent.userService
									.login(login_name, new String(password),
											InetAddress.getLocalHost()
													.getHostAddress(),
											Const.GENERALMANAGER);
							if (resultMessage.isInvokeSuccess()) {
								Agent.userAgent = (UserAgent) resultMessage
										.getResultSet().get(0);
								memberUIController.hideNavigateView();
								MainFrame mainFrame = memberUIController
										.getMainFrame();
								userUIController = new UserUIController(
										mainFrame);
								userUIController.createManagerView();
							} else {
								ImageDialog.showNOImage(this,
										resultMessage.getPostScript());
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						}
					}
					if (classify.getSelectedIndex() == 3) {
						try {
							ResultMessage resultMessage = Agent.userService
									.login(login_name, new String(password),
											InetAddress.getLocalHost()
													.getHostAddress(),
											Const.ADMIN);
							if (resultMessage.isInvokeSuccess()) {
								Agent.userAgent = (UserAgent) resultMessage
										.getResultSet().get(0);
								memberUIController.hideNavigateView();
								MainFrame mainFrame = memberUIController
										.getMainFrame();
								userUIController = new UserUIController(
										mainFrame);
								userUIController.createAdminView();
							} else {
								ImageDialog.showNOImage(this,
										resultMessage.getPostScript());
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					if (!SafeCheck.isLegalName(login_name)) {
						nameWarning.setVisible(true);
					} else
						nameWarning.setVisible(false);
					if (!SafeCheck.isLegalPassword(password)) {
						passwordWarning.setVisible(true);
					} else
						passwordWarning.setVisible(false);
				}
			}
		} else if (e.getSource() == returnbtn) {
			memberUIController.setReturnView();
		}
	}
}
