package User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import ClientRunner.Agent;
import Member.MemberPO;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel implements ActionListener, MouseListener {
	private UserUIController userUIController;
	static final int MANAGE_CUS = 0;
	static final int MANAGE_SALESMANAGER = 1;
	static final int MANAGE_MANAGER = 2;
	private int manageType = 0;

	private JLabel obsLabel, welcomLabel, exitLabel, title;
	// main page
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;

	private JLabel customerLabel, salesmanLabel, manLabel;
	private JButton addButton, modifyButton, delButton, listButton;
	private JLabel nameLabel, passwordLabel, phoneLabel, birthLabel;

	private JTextField nameField, phoneField, birth_yearField,
			birth_monthField, birth_dateField;
	private JPasswordField passwordField;

	// customer management
	private JPanel cusaddPanel, cusmodifyPanel, cusdelPanel;
	private JButton addensureButton1, modifyensureButton1, delensureButton1,
			cusreturnButton;

	// sales manager
	private JPanel salesaddPanel, salesmodifyPanel, salesdelPanel;
	private JButton addensureButton2, modifyensureButton2, delensureButton2,
			salesreturnButton;

	// general manager
	private JPanel manaddPanel, manmodifyPanel, mandelPanel;
	private JButton addensureButton3, modifyensureButton3, delensureButton3,
			manreturnButton;

	public AdminPanel(UserUIController userUIController) {
		this.userUIController = userUIController;
	}

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

		obsLabel = new JLabel("网上图书销售系统");
		obsLabel.setLocation(240, 0);
		obsLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 30));
		obsLabel.setSize(300, 80);

		welcomLabel = new JLabel("欢迎您，admin");
		welcomLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(140, 40);
		welcomLabel.setLocation(560, 20);

		exitLabel = new JLabel("退出");
		exitLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);

		customerLabel = new JLabel("管理顾客");
		customerLabel.setLocation(20, 80);
		customerLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 22));
		customerLabel.setSize(120, 50);

		salesmanLabel = new JLabel("管理销售经理");
		salesmanLabel.setLocation(150, 80);
		salesmanLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 22));
		salesmanLabel.setSize(180, 50);

		manLabel = new JLabel("管理总经理");
		manLabel.setLocation(340, 80);
		manLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 22));
		manLabel.setSize(180, 50);

		customerLabel.addMouseListener(this);
		salesmanLabel.addMouseListener(this);
		manLabel.addMouseListener(this);

		addButton = new JButton("添加用户");
		addButton.setFocusable(false);
		addButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		addButton.setSize(120, 40);
		addButton.setLocation(40, 150);
		addButton.addActionListener(this);

		modifyButton = new JButton("修改用户");
		modifyButton.setFocusable(false);
		modifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		modifyButton.setSize(120, 40);
		modifyButton.setLocation(40, 200);
		modifyButton.addActionListener(this);

		delButton = new JButton("删除用户");
		delButton.setFocusable(false);
		delButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		delButton.setSize(120, 40);
		delButton.setLocation(40, 250);
		delButton.addActionListener(this);

		listButton = new JButton("用户列表");
		listButton.setFocusable(false);
		listButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		listButton.setSize(120, 40);
		listButton.setLocation(40, 300);
		listButton.addActionListener(this);

		this.add(addButton);
		this.add(modifyButton);
		this.add(delButton);
		this.add(listButton);
		this.add(obsLabel);
		this.add(welcomLabel);
		this.add(exitLabel);
		this.add(customerLabel);
		this.add(salesmanLabel);
		this.add(manLabel);

		resultPanel = new JScrollPane();
		resultPanel.setLocation(180, 150);
		resultPanel.setSize(600, 400);
		resultPanel.setVisible(true);
		resultPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(resultPanel);
		validate();
	}

	private void initcusAddView() {
		cusaddPanel = new JPanel();
		cusaddPanel.setSize(500, 450);
		cusaddPanel.setLocation(100, 100);
		cusaddPanel.setLayout(null);
		cusaddPanel.setVisible(true);

		title = new JLabel("添加顾客");
		title.setSize(100, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(230, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(150, 180);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		birthLabel = new JLabel("生日:");
		birthLabel.setSize(80, 40);
		birthLabel.setLocation(150, 230);
		birthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField();
		nameField.setSize(150, 30);
		nameField.setLocation(240, 85);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordField = new JPasswordField();
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneField = new JTextField();
		phoneField.setSize(150, 30);
		phoneField.setLocation(240, 185);
		phoneField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		birth_yearField = new JTextField();
		birth_yearField.setSize(60, 30);
		birth_yearField.setLocation(240, 235);
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		JLabel yearLabel = new JLabel("年");
		yearLabel.setSize(20, 30);
		yearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		yearLabel.setLocation(301, 235);

		birth_monthField = new JTextField();
		birth_monthField.setSize(40, 30);
		birth_monthField.setLocation(322, 235);
		birth_monthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		JLabel monthLabel = new JLabel("月");
		monthLabel.setSize(20, 30);
		monthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		monthLabel.setLocation(363, 235);

		birth_dateField = new JTextField();
		birth_dateField.setSize(40, 30);
		birth_dateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_dateField.setLocation(384, 235);

		JLabel dateLabel = new JLabel("日");
		dateLabel.setSize(20, 30);
		dateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		dateLabel.setLocation(425, 235);

		addensureButton1 = new JButton("添加");
		addensureButton1.setSize(100, 40);
		addensureButton1.setLocation(200, 280);
		addensureButton1.setFocusable(false);
		addensureButton1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addensureButton1.addActionListener(this);

		cusreturnButton = new JButton("返回");
		cusreturnButton.setSize(100, 40);
		cusreturnButton.setLocation(320, 280);
		cusreturnButton.setFocusable(false);
		cusreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cusreturnButton.addActionListener(this);

		cusaddPanel.add(title);
		cusaddPanel.add(nameLabel);
		cusaddPanel.add(passwordLabel);
		cusaddPanel.add(phoneLabel);
		cusaddPanel.add(birthLabel);
		cusaddPanel.add(nameField);
		cusaddPanel.add(passwordField);
		cusaddPanel.add(phoneField);
		cusaddPanel.add(birth_yearField);
		cusaddPanel.add(yearLabel);
		cusaddPanel.add(birth_monthField);
		cusaddPanel.add(monthLabel);
		cusaddPanel.add(birth_dateField);
		cusaddPanel.add(dateLabel);
		cusaddPanel.add(addensureButton1);
		cusaddPanel.add(cusreturnButton);
	}

	private void initcusModifyView() {

		cusmodifyPanel = new JPanel();
		cusmodifyPanel.setSize(500, 450);
		cusmodifyPanel.setLocation(100, 100);
		cusmodifyPanel.setLayout(null);
		cusmodifyPanel.setVisible(true);

		title = new JLabel("修改顾客");
		title.setSize(100, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(230, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);

		modifyensureButton1 = new JButton("修改");
		modifyensureButton1.setSize(100, 40);
		modifyensureButton1.setFocusable(false);
		modifyensureButton1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyensureButton1.setLocation(200, 180);
		modifyensureButton1.addActionListener(this);

		cusreturnButton = new JButton("返回");
		cusreturnButton.setSize(100, 40);
		cusreturnButton.setFocusable(false);
		cusreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cusreturnButton.setLocation(320, 180);
		cusreturnButton.addActionListener(this);

		cusmodifyPanel.add(title);
		cusmodifyPanel.add(nameLabel);
		cusmodifyPanel.add(passwordLabel);
		cusmodifyPanel.add(nameField);
		cusmodifyPanel.add(passwordField);
		cusmodifyPanel.add(modifyensureButton1);
		cusmodifyPanel.add(cusreturnButton);
	}

	private void initcusDelView() {

		cusdelPanel = new JPanel();
		cusdelPanel.setSize(500, 450);
		cusdelPanel.setLocation(100, 100);
		cusdelPanel.setLayout(null);
		cusdelPanel.setVisible(true);

		title = new JLabel("删除顾客");
		title.setSize(100, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(230, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		delensureButton1 = new JButton("删除");
		delensureButton1.setSize(100, 40);
		delensureButton1.setFocusable(false);
		delensureButton1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		delensureButton1.setLocation(200, 180);
		delensureButton1.addActionListener(this);

		cusreturnButton = new JButton("返回");
		cusreturnButton.setSize(100, 40);
		cusreturnButton.setFocusable(false);
		cusreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cusreturnButton.setLocation(320, 180);
		cusreturnButton.addActionListener(this);

		cusdelPanel.add(title);
		cusdelPanel.add(nameLabel);
		cusdelPanel.add(nameField);
		cusdelPanel.add(delensureButton1);
		cusdelPanel.add(cusreturnButton);
	}

	private void initsalesAddView() {

		salesaddPanel = new JPanel();
		salesaddPanel.setSize(500, 450);
		salesaddPanel.setLocation(100, 100);
		salesaddPanel.setLayout(null);
		salesaddPanel.setVisible(true);

		title = new JLabel("添加销售经理");
		title.setSize(150, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(205, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);

		addensureButton2 = new JButton("添加");
		addensureButton2.setSize(100, 40);
		addensureButton2.setFocusable(false);
		addensureButton2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addensureButton2.setLocation(200, 180);
		addensureButton2.addActionListener(this);

		salesreturnButton = new JButton("返回");
		salesreturnButton.setSize(100, 40);
		salesreturnButton.setFocusable(false);
		salesreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		salesreturnButton.setLocation(320, 180);
		salesreturnButton.addActionListener(this);

		salesaddPanel.add(title);
		salesaddPanel.add(nameLabel);
		salesaddPanel.add(passwordLabel);
		salesaddPanel.add(nameField);
		salesaddPanel.add(passwordField);
		salesaddPanel.add(addensureButton2);
		salesaddPanel.add(salesreturnButton);
	}

	private void initsalesModifyView() {
		salesmodifyPanel = new JPanel();
		salesmodifyPanel.setSize(500, 450);
		salesmodifyPanel.setLocation(100, 100);
		salesmodifyPanel.setLayout(null);
		salesmodifyPanel.setVisible(true);

		title = new JLabel("修改销售经理");
		title.setSize(150, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(205, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);

		modifyensureButton2 = new JButton("修改");
		modifyensureButton2.setSize(100, 40);
		modifyensureButton2.setFocusable(false);
		modifyensureButton2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyensureButton2.setLocation(200, 180);
		modifyensureButton2.addActionListener(this);

		salesreturnButton = new JButton("返回");
		salesreturnButton.setSize(100, 40);
		salesreturnButton.setFocusable(false);
		salesreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		salesreturnButton.setLocation(320, 180);
		salesreturnButton.addActionListener(this);

		salesmodifyPanel.add(title);
		salesmodifyPanel.add(nameLabel);
		salesmodifyPanel.add(passwordLabel);
		salesmodifyPanel.add(nameField);
		salesmodifyPanel.add(passwordField);
		salesmodifyPanel.add(modifyensureButton2);
		salesmodifyPanel.add(salesreturnButton);
	}

	private void initsalesDelView() {

		salesdelPanel = new JPanel();
		salesdelPanel.setSize(500, 450);
		salesdelPanel.setLocation(100, 100);
		salesdelPanel.setLayout(null);
		salesdelPanel.setVisible(true);

		title = new JLabel("删除销售经理");
		title.setSize(150, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(205, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		delensureButton2 = new JButton("删除");
		delensureButton2.setSize(100, 40);
		delensureButton2.setFocusable(false);
		delensureButton2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		delensureButton2.setLocation(200, 180);
		delensureButton2.addActionListener(this);

		salesreturnButton = new JButton("返回");
		salesreturnButton.setSize(100, 40);
		salesreturnButton.setFocusable(false);
		salesreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		salesreturnButton.setLocation(320, 180);
		salesreturnButton.addActionListener(this);

		salesdelPanel.add(title);
		salesdelPanel.add(nameLabel);
		salesdelPanel.add(nameField);
		salesdelPanel.add(delensureButton2);
		salesdelPanel.add(salesreturnButton);
	}

	private void initmanAddView() {
		manaddPanel = new JPanel();
		manaddPanel.setSize(500, 450);
		manaddPanel.setLocation(100, 100);
		manaddPanel.setLayout(null);
		manaddPanel.setVisible(true);

		title = new JLabel("添加总经理");
		title.setSize(150, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(210, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);

		addensureButton3 = new JButton("添加");
		addensureButton3.setSize(100, 40);
		addensureButton3.setFocusable(false);
		addensureButton3.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addensureButton3.setLocation(200, 180);
		addensureButton3.addActionListener(this);

		manreturnButton = new JButton("返回");
		manreturnButton.setSize(100, 40);
		manreturnButton.setFocusable(false);
		manreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		manreturnButton.setLocation(320, 180);
		manreturnButton.addActionListener(this);

		manaddPanel.add(title);
		manaddPanel.add(nameLabel);
		manaddPanel.add(passwordLabel);
		manaddPanel.add(nameField);
		manaddPanel.add(passwordField);
		manaddPanel.add(addensureButton3);
		manaddPanel.add(manreturnButton);
	}

	private void initmanModifyView() {

		manmodifyPanel = new JPanel();
		manmodifyPanel.setSize(500, 450);
		manmodifyPanel.setLocation(100, 100);
		manmodifyPanel.setLayout(null);
		manmodifyPanel.setVisible(true);

		title = new JLabel("修改总经理");
		title.setSize(150, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(210, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);

		modifyensureButton3 = new JButton("修改");
		modifyensureButton3.setSize(100, 40);
		modifyensureButton3.setFocusable(false);
		modifyensureButton3.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyensureButton3.setLocation(200, 180);
		modifyensureButton3.addActionListener(this);

		manreturnButton = new JButton("返回");
		manreturnButton.setSize(100, 40);
		manreturnButton.setFocusable(false);
		manreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		manreturnButton.setLocation(320, 180);
		manreturnButton.addActionListener(this);

		manmodifyPanel.add(title);
		manmodifyPanel.add(nameLabel);
		manmodifyPanel.add(passwordLabel);
		manmodifyPanel.add(nameField);
		manmodifyPanel.add(passwordField);
		manmodifyPanel.add(modifyensureButton3);
		manmodifyPanel.add(manreturnButton);

	}

	private void initmanDelView() {
		mandelPanel = new JPanel();
		mandelPanel.setSize(500, 450);
		mandelPanel.setLocation(100, 100);
		mandelPanel.setLayout(null);
		mandelPanel.setVisible(true);

		title = new JLabel("删除总经理");
		title.setSize(150, 40);
		title.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		title.setLocation(210, 20);

		nameLabel = new JLabel("用户名:");
		nameLabel.setSize(100, 40);
		nameLabel.setLocation(150, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new JTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		delensureButton3 = new JButton("删除");
		delensureButton3.setSize(100, 40);
		delensureButton3.setFocusable(false);
		delensureButton3.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		delensureButton3.setLocation(200, 180);
		delensureButton3.addActionListener(this);

		manreturnButton = new JButton("返回");
		manreturnButton.setSize(100, 40);
		manreturnButton.setFocusable(false);
		manreturnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		manreturnButton.setLocation(320, 180);
		manreturnButton.addActionListener(this);

		mandelPanel.add(title);
		mandelPanel.add(nameLabel);
		mandelPanel.add(nameField);
		mandelPanel.add(delensureButton3);
		mandelPanel.add(manreturnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			switch (manageType) {
			case MANAGE_CUS:
				initcusAddView();
				resultPanel.setViewportView(cusaddPanel);
				cusaddPanel.requestFocus();
				break;
			case MANAGE_SALESMANAGER:
				initsalesAddView();
				resultPanel.setViewportView(salesaddPanel);
				salesaddPanel.requestFocus();
				break;
			case MANAGE_MANAGER:
				initmanAddView();
				resultPanel.setViewportView(manaddPanel);
				manaddPanel.requestFocus();
				break;
			default:
				break;
			}
		} else if (e.getSource() == modifyButton) {
			switch (manageType) {
			case MANAGE_CUS:
				initcusModifyView();
				resultPanel.setViewportView(cusmodifyPanel);
				cusmodifyPanel.requestFocus();
				break;
			case MANAGE_SALESMANAGER:
				initsalesModifyView();
				resultPanel.setViewportView(salesmodifyPanel);
				salesmodifyPanel.requestFocus();
				break;
			case MANAGE_MANAGER:
				initmanModifyView();
				resultPanel.setViewportView(manmodifyPanel);
				manmodifyPanel.requestFocus();
				break;
			default:
				break;
			}
		} else if (e.getSource() == delButton) {
			switch (manageType) {
			case MANAGE_CUS:
				initcusDelView();
				resultPanel.setViewportView(cusdelPanel);
				cusdelPanel.requestFocus();
				break;
			case MANAGE_SALESMANAGER:
				initsalesDelView();
				resultPanel.setViewportView(salesdelPanel);
				salesdelPanel.requestFocus();
				break;
			case MANAGE_MANAGER:
				initmanDelView();
				resultPanel.setViewportView(mandelPanel);
				mandelPanel.requestFocus();
				break;
			default:
				break;
			}
		} else if (e.getSource() == listButton) {
			switch (manageType) {
			case MANAGE_CUS:

				break;
			case MANAGE_SALESMANAGER:

				break;
			case MANAGE_MANAGER:

				break;
			default:
				break;
			}
		} else if (e.getSource() == addensureButton1) {
				String reg_name = nameField.getText();
				char[] reg_password = passwordField.getPassword();
				String reg_phone = phoneField.getText();
				Calendar birth = new GregorianCalendar(
						Integer.parseInt(birth_yearField.getText().trim()),
						Integer.parseInt(birth_monthField.getText().trim()),
						Integer.parseInt(birth_dateField.getText().trim()));
				try {
					ResultMessage resultMessage = Agent.userService
							.addMember(new MemberPO(-1, reg_name, new String(
									reg_password), reg_phone, birth));
					if (resultMessage.isInvokeSuccess()) {
						System.out.println("success");
					} else {
						System.out.println(resultMessage.getPostScript());
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
		}else if(e.getSource() == addensureButton2){
			String reg_name = nameField.getText();
			char[] reg_password = passwordField.getPassword();
			try{
				ResultMessage resultMessage = Agent.userService.addUser(new SalesManagerPO(-1, reg_name, new String(reg_password)));
				if(resultMessage.isInvokeSuccess()){
					System.out.println("success");
				}
				else{
					System.out.println(resultMessage.getPostScript());
				}
			}catch(RemoteException re){
				re.printStackTrace();
			}
		}else if(e.getSource() == addensureButton3){
			String reg_name = nameField.getText();
			char[] reg_password = passwordField.getPassword();
			try{
				ResultMessage resultMessage = Agent.userService.addUser(new GeneralManagerPO(-1, reg_name, new String(reg_password)));
				if(resultMessage.isInvokeSuccess()){
					System.out.println("success");
				}
				else{
					System.out.println(resultMessage.getPostScript());
				}
			}catch(RemoteException re){
				re.printStackTrace();
			}
		} else if (e.getSource() == modifyensureButton1) {

		} else if (e.getSource() == delensureButton1) {

		} else if (e.getSource() == modifyensureButton2) {

		} else if (e.getSource() == delensureButton2) {

		} else if (e.getSource() == modifyensureButton3) {

		} else if (e.getSource() == delensureButton3) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == customerLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			manageType = MANAGE_CUS;
		} else if (e.getSource() == salesmanLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			manageType = MANAGE_SALESMANAGER;
		} else if (e.getSource() == manLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			manageType = MANAGE_MANAGER;
		} else if (e.getSource() == exitLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			userUIController.setMainPageView();
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

	class tableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return 0;
		}

		@Override
		public int getColumnCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}

	}
}
