package User;

<<<<<<< HEAD
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
=======
import java.awt.Color;
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import ClientRunner.Agent;
import ClientRunner.Const;
<<<<<<< HEAD
import ClientRunner.IMGSTATIC;
=======
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MPasswordField;
import ClientRunner.MTextField;
import Member.MemberPO;
import Member.SafeCheck;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class AdminPanel extends MPanel implements ActionListener, MouseListener {
	private UserUIController userUIController;
	static final int MANAGE_CUS = 0;
	static final int MANAGE_SALESMANAGER = 1;
	static final int MANAGE_MANAGER = 2;
	private int manageType = 0;
	int selectedRow = -1;

	ArrayList<MemberPO> resultCustomers = new ArrayList<MemberPO>();
	ArrayList<UserPO> resultManagers = new ArrayList<UserPO>();
	ArrayList<UserPO> resultSalesManagers = new ArrayList<UserPO>();

	private JLabel obsLabel, welcomLabel, exitLabel, title;
	// main page
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;

	private JLabel customerLabel, salesmanLabel, manLabel;
	private MButton addButton, modifyButton, delButton, listButton;
	private JLabel nameLabel, nameLabel2, passwordLabel, passwordEnsureLable,
			phoneLabel, birthLabel, nameWarning, passwordWarning, phoneWarning,
			dateWarning;

	private MTextField nameField, phoneField, birth_yearField,
			birth_monthField, birth_dateField;
	private MPasswordField passwordField, passwordEnsureField;

	// customer management
	private MPanel cusaddPanel, cusmodifyPanel;
	private MButton addensureButton1, modifyensureButton1;

	// sales manager
	private MPanel salesaddPanel, salesmodifyPanel;
	private MButton addensureButton2, modifyensureButton2;

	// general manager
	private MPanel manaddPanel, manmodifyPanel;
	private MButton addensureButton3, modifyensureButton3;

	// table
	String[] customercolname = new String[] { "编号", "用户名", "密码", "联系电话", "生日" };
	String[] managercolname = new String[] { "编号", "用户名", "密码", "类别" };

	private String modifyName;

	public AdminPanel(UserUIController userUIController) {
		this.userUIController = userUIController;
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
			g2d.drawImage(IMGSTATIC.otherBG, 0, 0, 800, 600, this);
			g2d.setComposite(composite);
		}
		g2d.dispose();
	}

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setVisible(true);
		getData();

		obsLabel = new JLabel("网上图书销售系统");
		obsLabel.setLocation(240, 0);
		obsLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 30));
		obsLabel.setSize(300, 80);

		welcomLabel = new JLabel("欢迎您，管理员:" + Agent.userAgent.getName());
		welcomLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(200, 40);
		welcomLabel.setLocation(500, 20);

		exitLabel = new JLabel("退出");
		exitLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);

		customerLabel = new JLabel("管理顾客");
		customerLabel.setLocation(20, 80);
		customerLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 22));
		customerLabel.setSize(120, 50);
		customerLabel.setForeground(Color.red);

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

		addButton = new MButton("添加用户");
		addButton.setFocusable(false);
		addButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		addButton.setSize(120, 40);
		addButton.setLocation(40, 150);
		addButton.addActionListener(this);

		modifyButton = new MButton("修改用户");
		modifyButton.setFocusable(false);
		modifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		modifyButton.setSize(120, 40);
		modifyButton.setLocation(40, 200);
		modifyButton.addActionListener(this);

		delButton = new MButton("删除用户");
		delButton.setFocusable(false);
		delButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		delButton.setSize(120, 40);
		delButton.setLocation(40, 250);
		delButton.addActionListener(this);

		listButton = new MButton("用户列表");
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

		tableModel = new tableModel();
		resultTable = new JTable(tableModel);
		resultTable.setBorder(BorderFactory.createEtchedBorder());
		resultTable.setRowHeight(30); // 设置每行的高度为20
		resultTable.setRowHeight(0, 20); // 设置第1行的高度为15
		resultTable.setRowMargin(5); // 设置相邻两行单元格的距离
		resultTable.setRowSelectionAllowed(true);
		resultTable.setSelectionBackground(Color.LIGHT_GRAY); // 设置所选择行的背景色
		resultTable.setSelectionForeground(Color.red); // 设置所选择行的前景色
		resultTable.setGridColor(Color.black); // 设置网格线的颜色
		resultTable.setShowGrid(false); // 是否显示网格线
		resultTable.setShowHorizontalLines(false); // 是否显示水平的网格线
		resultTable.setShowVerticalLines(true); // 是否显示垂直的网格线
		resultTable.doLayout();
		resultTable.setBackground(Color.WHITE);
		resultTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dealResultTable();
			}

			private void dealResultTable() {
				selectedRow = resultTable.getSelectedRow();
			}
		});

		resultPanel = new JScrollPane(resultTable);
		resultPanel.setLocation(180, 150);
		resultPanel.setSize(600, 400);
		resultPanel.setVisible(true);
		resultPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		resultPanel.setOpaque(false);
		resultPanel.getViewport().setOpaque(false);
		this.add(resultPanel);
		validate();
	}

	private void initcusAddView() {
		cusaddPanel = new MPanel();
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
		nameLabel.setLocation(120, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(120, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureLable = new JLabel("确认密码:");
		passwordEnsureLable.setSize(120, 40);
		passwordEnsureLable.setLocation(120, 180);
		passwordEnsureLable.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(120, 230);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		birthLabel = new JLabel("生日:");
		birthLabel.setSize(80, 40);
		birthLabel.setLocation(120, 280);
		birthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new MTextField();
		nameField.setSize(150, 30);
		nameField.setLocation(240, 85);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordField = new MPasswordField();
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureField = new MPasswordField();
		passwordEnsureField.setSize(150, 30);
		passwordEnsureField.setLocation(240, 185);
		passwordEnsureField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneField = new MTextField();
		phoneField.setSize(150, 30);
		phoneField.setLocation(240, 235);
		phoneField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		birth_yearField = new MTextField();
		birth_yearField.setSize(60, 30);
		birth_yearField.setLocation(240, 285);
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		JLabel yearLabel = new JLabel("年");
		yearLabel.setSize(20, 30);
		yearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		yearLabel.setLocation(301, 285);

		birth_monthField = new MTextField();
		birth_monthField.setSize(40, 30);
		birth_monthField.setLocation(322, 285);
		birth_monthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		JLabel monthLabel = new JLabel("月");
		monthLabel.setSize(20, 30);
		monthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		monthLabel.setLocation(363, 285);

		birth_dateField = new MTextField();
		birth_dateField.setSize(40, 30);
		birth_dateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_dateField.setLocation(384, 285);

		JLabel dateLabel = new JLabel("日");
		dateLabel.setSize(20, 30);
		dateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		dateLabel.setLocation(425, 285);

		addensureButton1 = new MButton("添加");
		addensureButton1.setSize(100, 40);
		addensureButton1.setLocation(200, 330);
		addensureButton1.setFocusable(false);
		addensureButton1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addensureButton1.addActionListener(this);

		nameWarning = new JLabel("用户名不合法!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(420, 75);
		nameWarning.setVisible(false);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(420, 125);
		passwordWarning.setVisible(false);

		phoneWarning = new JLabel("电话号码不合法!");
		phoneWarning.setSize(148, 50);
		phoneWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		phoneWarning.setForeground(Color.red);
		phoneWarning.setLocation(420, 225);
		phoneWarning.setVisible(false);

		dateWarning = new JLabel("日期不合法!");
		dateWarning.setSize(148, 50);
		dateWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		dateWarning.setForeground(Color.red);
		dateWarning.setLocation(450, 275);
		dateWarning.setVisible(false);

		cusaddPanel.add(title);
		cusaddPanel.add(nameLabel);
		cusaddPanel.add(passwordLabel);
		cusaddPanel.add(passwordEnsureLable);
		cusaddPanel.add(phoneLabel);
		cusaddPanel.add(birthLabel);
		cusaddPanel.add(nameField);
		cusaddPanel.add(passwordField);
		cusaddPanel.add(passwordEnsureField);
		cusaddPanel.add(phoneField);
		cusaddPanel.add(birth_yearField);
		cusaddPanel.add(yearLabel);
		cusaddPanel.add(birth_monthField);
		cusaddPanel.add(monthLabel);
		cusaddPanel.add(birth_dateField);
		cusaddPanel.add(dateLabel);
		cusaddPanel.add(addensureButton1);
		cusaddPanel.add(nameWarning);
		cusaddPanel.add(passwordWarning);
		cusaddPanel.add(phoneWarning);
		cusaddPanel.add(dateWarning);
	}

	private void initcusModifyView() {
		cusmodifyPanel = new MPanel();
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
		nameLabel.setLocation(120, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("新密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(120, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureLable = new JLabel("确认新密码:");
		passwordEnsureLable.setSize(140, 40);
		passwordEnsureLable.setLocation(120, 180);
		passwordEnsureLable.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneLabel = new JLabel("联系电话:");
		phoneLabel.setSize(100, 40);
		phoneLabel.setLocation(120, 230);
		phoneLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
<<<<<<< HEAD

		birthLabel = new JLabel("生日:");
		birthLabel.setSize(80, 40);
		birthLabel.setLocation(120, 280);
		birthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameLabel2 = new JLabel();
		nameLabel2.setText(resultCustomers.get(selectedRow).getName());
		nameLabel2.setSize(100, 40);
		nameLabel2.setLocation(240, 80);
		nameLabel2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

=======

		birthLabel = new JLabel("生日:");
		birthLabel.setSize(80, 40);
		birthLabel.setLocation(120, 280);
		birthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameLabel2 = new JLabel();
		nameLabel2.setText(resultCustomers.get(selectedRow).getName());
		nameLabel2.setSize(100, 40);
		nameLabel2.setLocation(240, 80);
		nameLabel2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
		passwordField = new MPasswordField();
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureField = new MPasswordField();
		passwordEnsureField.setSize(150, 30);
		passwordEnsureField.setLocation(240, 185);
		passwordEnsureField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		phoneField = new MTextField();
		phoneField.setText(resultCustomers.get(selectedRow).getPhone());
		phoneField.setSize(150, 30);
		phoneField.setLocation(240, 235);
		phoneField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
<<<<<<< HEAD

		birth_yearField = new MTextField();
		birth_yearField.setSize(60, 30);
		birth_yearField.setLocation(240, 285);
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

=======

		birth_yearField = new MTextField();
		birth_yearField.setSize(60, 30);
		birth_yearField.setLocation(240, 285);
		birth_yearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
		JLabel yearLabel = new JLabel("年");
		yearLabel.setSize(20, 30);
		yearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		yearLabel.setLocation(301, 285);

		birth_monthField = new MTextField();
		birth_monthField.setSize(40, 30);
		birth_monthField.setLocation(322, 285);
		birth_monthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		JLabel monthLabel = new JLabel("月");
		monthLabel.setSize(20, 30);
		monthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		monthLabel.setLocation(363, 285);

		birth_dateField = new MTextField();
		birth_dateField.setSize(40, 30);
		birth_dateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		birth_dateField.setLocation(384, 285);

		JLabel dateLabel = new JLabel("日");
		dateLabel.setSize(20, 30);
		dateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		dateLabel.setLocation(425, 285);

		modifyensureButton1 = new MButton("确认修改");
		modifyensureButton1.setSize(120, 40);
		modifyensureButton1.setLocation(200, 330);
		modifyensureButton1.setFocusable(false);
		modifyensureButton1.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		modifyensureButton1.addActionListener(this);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(420, 125);
		passwordWarning.setVisible(false);

		phoneWarning = new JLabel("电话号码不合法!");
		phoneWarning.setSize(148, 50);
		phoneWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		phoneWarning.setForeground(Color.red);
		phoneWarning.setLocation(420, 225);
		phoneWarning.setVisible(false);

		dateWarning = new JLabel("日期不合法!");
		dateWarning.setSize(148, 50);
		dateWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		dateWarning.setForeground(Color.red);
		dateWarning.setLocation(450, 275);
		dateWarning.setVisible(false);

		cusmodifyPanel.add(title);
		cusmodifyPanel.add(nameLabel);
		cusmodifyPanel.add(passwordLabel);
		cusmodifyPanel.add(passwordEnsureLable);
		cusmodifyPanel.add(phoneLabel);
		cusmodifyPanel.add(birthLabel);
		cusmodifyPanel.add(nameLabel2);
		cusmodifyPanel.add(passwordField);
		cusmodifyPanel.add(passwordEnsureField);
		cusmodifyPanel.add(phoneField);
		cusmodifyPanel.add(birth_yearField);
		cusmodifyPanel.add(yearLabel);
		cusmodifyPanel.add(birth_monthField);
		cusmodifyPanel.add(monthLabel);
		cusmodifyPanel.add(birth_dateField);
		cusmodifyPanel.add(dateLabel);
		cusmodifyPanel.add(modifyensureButton1);
		cusmodifyPanel.add(passwordWarning);
		cusmodifyPanel.add(phoneWarning);
		cusmodifyPanel.add(dateWarning);
<<<<<<< HEAD
=======

>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
	}

	private void initsalesAddView() {

		salesaddPanel = new MPanel();
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
		nameLabel.setLocation(120, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(120, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureLable = new JLabel("确认密码:");
		passwordEnsureLable.setSize(140, 40);
		passwordEnsureLable.setLocation(120, 180);
		passwordEnsureLable.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new MTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new MPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);
		passwordEnsureField = new MPasswordField();
		passwordEnsureField.setSize(150, 30);
		passwordEnsureField.setLocation(240, 185);
		passwordEnsureField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		addensureButton2 = new MButton("添加");
		addensureButton2.setSize(100, 40);
		addensureButton2.setFocusable(false);
		addensureButton2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addensureButton2.setLocation(200, 230);
		addensureButton2.addActionListener(this);

		nameWarning = new JLabel("用户名不合法!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(420, 75);
		nameWarning.setVisible(false);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(420, 125);
		passwordWarning.setVisible(false);

		salesaddPanel.add(title);
		salesaddPanel.add(nameLabel);
		salesaddPanel.add(passwordLabel);
		salesaddPanel.add(passwordEnsureLable);
		salesaddPanel.add(nameField);
		salesaddPanel.add(passwordField);
		salesaddPanel.add(passwordEnsureField);
		salesaddPanel.add(addensureButton2);
		salesaddPanel.add(nameWarning);
		salesaddPanel.add(passwordWarning);
	}

	private void initsalesModifyView() {
		salesmodifyPanel = new MPanel();
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
		nameLabel.setLocation(120, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("新密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(120, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureLable = new JLabel("确认新密码:");
		passwordEnsureLable.setSize(140, 40);
		passwordEnsureLable.setLocation(120, 180);
		passwordEnsureLable.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameLabel2 = new JLabel();
		nameLabel2.setText(resultSalesManagers.get(selectedRow).getName());
		nameLabel2.setSize(100, 40);
		nameLabel2.setLocation(240, 80);
		nameLabel2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordField = new MPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);
		passwordEnsureField = new MPasswordField();
		passwordEnsureField.setSize(150, 30);
		passwordEnsureField.setLocation(240, 185);
		passwordEnsureField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		modifyensureButton2 = new MButton("修改");
		modifyensureButton2.setSize(100, 40);
		modifyensureButton2.setFocusable(false);
		modifyensureButton2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyensureButton2.setLocation(200, 230);
		modifyensureButton2.addActionListener(this);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(420, 125);
		passwordWarning.setVisible(false);

		salesmodifyPanel.add(title);
		salesmodifyPanel.add(nameLabel);
		salesmodifyPanel.add(passwordLabel);
		salesmodifyPanel.add(passwordEnsureLable);
		salesmodifyPanel.add(nameLabel2);
		salesmodifyPanel.add(passwordField);
		salesmodifyPanel.add(passwordEnsureField);
		salesmodifyPanel.add(modifyensureButton2);
		salesmodifyPanel.add(passwordWarning);
	}

	private void initmanAddView() {
		manaddPanel = new MPanel();
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
		nameLabel.setLocation(120, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(120, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureLable = new JLabel("确认密码:");
		passwordEnsureLable.setSize(140, 40);
		passwordEnsureLable.setLocation(120, 180);
		passwordEnsureLable.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameField = new MTextField(30);
		nameField.setSize(150, 30);
		nameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		nameField.setLocation(240, 85);

		passwordField = new MPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);
		passwordEnsureField = new MPasswordField();
		passwordEnsureField.setSize(150, 30);
		passwordEnsureField.setLocation(240, 185);
		passwordEnsureField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		addensureButton3 = new MButton("添加");
		addensureButton3.setSize(100, 40);
		addensureButton3.setFocusable(false);
		addensureButton3.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addensureButton3.setLocation(200, 230);
		addensureButton3.addActionListener(this);

		nameWarning = new JLabel("用户名不合法!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(420, 75);
		nameWarning.setVisible(false);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(420, 125);
		passwordWarning.setVisible(false);

		manaddPanel.add(title);
		manaddPanel.add(nameLabel);
		manaddPanel.add(passwordLabel);
		manaddPanel.add(passwordEnsureLable);
		manaddPanel.add(nameField);
		manaddPanel.add(passwordField);
		manaddPanel.add(passwordEnsureField);
		manaddPanel.add(addensureButton3);
		manaddPanel.add(nameWarning);
		manaddPanel.add(passwordWarning);
	}

	private void initmanModifyView() {

		manmodifyPanel = new MPanel();
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
		nameLabel.setLocation(120, 80);
		nameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordLabel = new JLabel("新密码:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(120, 130);
		passwordLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordEnsureLable = new JLabel("确认新密码:");
		passwordEnsureLable.setSize(140, 40);
		passwordEnsureLable.setLocation(120, 180);
		passwordEnsureLable.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		nameLabel2 = new JLabel();
		nameLabel2.setText(resultManagers.get(selectedRow).getName());
		nameLabel2.setSize(100, 40);
		nameLabel2.setLocation(240, 80);
		nameLabel2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		passwordField = new MPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		passwordField.setLocation(240, 135);
		passwordEnsureField = new MPasswordField();
		passwordEnsureField.setSize(150, 30);
		passwordEnsureField.setLocation(240, 185);
		passwordEnsureField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		modifyensureButton3 = new MButton("修改");
		modifyensureButton3.setSize(100, 40);
		modifyensureButton3.setFocusable(false);
		modifyensureButton3.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyensureButton3.setLocation(200, 230);
		modifyensureButton3.addActionListener(this);

		passwordWarning = new JLabel("密码不合法!");
		passwordWarning.setSize(140, 50);
		passwordWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		passwordWarning.setForeground(Color.red);
		passwordWarning.setLocation(420, 125);
		passwordWarning.setVisible(false);

		manmodifyPanel.add(title);
		manmodifyPanel.add(nameLabel);
		manmodifyPanel.add(passwordLabel);
		manmodifyPanel.add(passwordEnsureLable);
		manmodifyPanel.add(nameLabel2);
		manmodifyPanel.add(passwordField);
		manmodifyPanel.add(passwordEnsureField);
		manmodifyPanel.add(modifyensureButton3);
		manmodifyPanel.add(passwordWarning);

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
				if (selectedRow != -1) {
					initcusModifyView();
					resultPanel.setViewportView(cusmodifyPanel);
					cusmodifyPanel.requestFocus();
				} else {
					JOptionPane.showMessageDialog(this, "请先选择一个用户！");
				}
				break;
			case MANAGE_SALESMANAGER:
				if (selectedRow != -1) {
					initsalesModifyView();
					resultPanel.setViewportView(salesmodifyPanel);
					salesmodifyPanel.requestFocus();
				} else {
					JOptionPane.showMessageDialog(this, "请先选择一个用户！");
				}
				break;
			case MANAGE_MANAGER:
				if (selectedRow != -1) {
					initmanModifyView();
					resultPanel.setViewportView(manmodifyPanel);
					manmodifyPanel.requestFocus();
				} else {
					JOptionPane.showMessageDialog(this, "请先选择一个用户！");
				}
				break;
			default:
				break;
			}
		} else if (e.getSource() == delButton) {
			if (selectedRow != -1) {
				if (JOptionPane.showConfirmDialog(this, "确认删除？") == JOptionPane.YES_OPTION) {
					try {
						switch (manageType) {
						case MANAGE_CUS:
							ResultMessage exist = Agent.memberService
									.deleteMember(resultCustomers.get(
											selectedRow).getID());
							if (exist.isInvokeSuccess()) {
								ImageDialog.showYesImage(this, "删除成功");
							} else {
								ImageDialog.showNOImage(this, "删除失败");
							}
							break;
						case MANAGE_MANAGER:
							ResultMessage exist2 = Agent.userService
									.deleteUser(resultManagers.get(selectedRow)
											.getID());
							if (exist2.isInvokeSuccess()) {
								ImageDialog.showYesImage(this, "删除成功");
							} else {
								ImageDialog.showNOImage(this, "删除失败");
							}
							break;
						case MANAGE_SALESMANAGER:
							ResultMessage exist3 = Agent.userService
									.deleteUser(resultSalesManagers.get(
											selectedRow).getID());
							if (exist3.isInvokeSuccess()) {
								ImageDialog.showYesImage(this, "删除成功");
							} else {
								ImageDialog.showNOImage(this, "删除失败");
							}
							break;
						default:
							break;
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "请先选择要删除的项目");
			}
			getData();
			tableModel.fireTableDataChanged();
		} else if (e.getSource() == listButton) {
			switch (manageType) {
			case MANAGE_CUS:
				getData();
				tableModel.fireTableDataChanged();
				resultPanel.setViewportView(resultTable);
				break;
			case MANAGE_SALESMANAGER:
				getData();
				tableModel.fireTableDataChanged();
				resultPanel.setViewportView(resultTable);
				break;
			case MANAGE_MANAGER:
				getData();
				tableModel.fireTableDataChanged();
				resultPanel.setViewportView(resultTable);
				break;
			default:
				break;
			}
		} else if (e.getSource() == addensureButton1) {
			nameWarning.setVisible(false);
			passwordWarning.setVisible(false);
			phoneWarning.setVisible(false);
			dateWarning.setVisible(false);

			String reg_name = nameField.getText();
			char[] reg_password = passwordField.getPassword();
			char[] reg_ensurepassword = passwordEnsureField.getPassword();
			String regpassword = new String(reg_password);
			String regensurepassword = new String(reg_ensurepassword);
			String reg_phone = phoneField.getText();
			String year = birth_yearField.getText().trim();
			String month = birth_monthField.getText().trim();
			String date = birth_dateField.getText().trim();
			Calendar birth = null;
			boolean valid = true;
			try {
				birth = new GregorianCalendar(Integer.parseInt(year),
						Integer.parseInt(month) - 1, Integer.parseInt(date));
			} catch (Exception e1) {
				e1.printStackTrace();
				dateWarning.setVisible(true);
				valid = false;
			}

			if (!SafeCheck.isLegalName(reg_name)) {
				nameWarning.setVisible(true);
				valid = false;
			}
			if (!SafeCheck.isLegalPassword(reg_password)
					|| !SafeCheck.isLegalPassword(reg_ensurepassword)
					|| !SafeCheck
							.passwordEnsure(regpassword, regensurepassword)) {
				passwordWarning.setVisible(true);
				valid = false;
			}
			if (!SafeCheck.isLegalPhoneNumber(reg_phone)) {
				phoneWarning.setVisible(true);
				valid = false;
			}
			if (!SafeCheck.isLegalBirth(birth)) {
				dateWarning.setVisible(true);
				valid = false;
			}
			if (year.equals("") || month.equals("") || date.equals("")) {
				dateWarning.setVisible(true);
				valid = false;
			}

			validate();
			if (!valid) {
				return;
			}
			try {
				ResultMessage resultMessage = Agent.userService
						.addMember(new MemberPO(-1, reg_name, new String(
								reg_password), reg_phone, birth, 0));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(cusaddPanel, "添加成功");

				} else {
					ImageDialog.showNOImage(this, "添加失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == addensureButton2) {
			passwordWarning.setVisible(false);

			String reg_name = nameField.getText();
			char[] reg_password = passwordField.getPassword();
			char[] reg_ensurepassword = passwordEnsureField.getPassword();
			String regpassword = new String(reg_password);
			String regensurepassword = new String(reg_ensurepassword);

			if (!SafeCheck.isLegalPassword(reg_password)
					|| !SafeCheck
							.passwordEnsure(regpassword, regensurepassword)) {
				passwordWarning.setVisible(true);
				return;
			}
			validate();
			try {
				ResultMessage resultMessage = Agent.userService
						.addUser(new SalesManagerPO(-1, reg_name, new String(
								reg_password)));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "添加成功");
				} else {
					ImageDialog.showNOImage(this, "添加失败");
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		} else if (e.getSource() == addensureButton3) {
			passwordWarning.setVisible(false);

			String reg_name = nameField.getText();
			char[] reg_password = passwordField.getPassword();
			char[] reg_ensurepassword = passwordEnsureField.getPassword();
			String regpassword = new String(reg_password);
			String regensurepassword = new String(reg_ensurepassword);

			if (!SafeCheck.isLegalPassword(reg_password)
					|| !SafeCheck
							.passwordEnsure(regpassword, regensurepassword)) {
				passwordWarning.setVisible(true);
				return;
			}
			validate();
			try {
				ResultMessage resultMessage = Agent.userService
						.addUser(new GeneralManagerPO(-1, reg_name, new String(
								reg_password)));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "添加成功");
				} else {
					ImageDialog.showNOImage(this, "添加失败");
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		} else if (e.getSource() == modifyensureButton1) {
			passwordWarning.setVisible(false);
			phoneWarning.setVisible(false);
			dateWarning.setVisible(false);

			char[] modifypassword = passwordField.getPassword();
			char[] modifyEnsurepassword = passwordEnsureField.getPassword();
			String password = new String(modifypassword);
			String ensurepassword = new String(modifyEnsurepassword);
			String modifyphone = phoneField.getText();
			String year = birth_yearField.getText().trim();
			String month = birth_monthField.getText().trim();
			String date = birth_dateField.getText().trim();
			Calendar birth = null;
			boolean valid = true;
			try {
				birth = new GregorianCalendar(Integer.parseInt(year),
						Integer.parseInt(month) - 1, Integer.parseInt(date));
			} catch (Exception e1) {
				e1.printStackTrace();
				dateWarning.setVisible(true);
				valid = false;
			}
			if (!SafeCheck.isLegalPassword(modifypassword)
					|| !SafeCheck.isLegalPassword(modifyEnsurepassword)
					|| !SafeCheck.passwordEnsure(password, ensurepassword)) {
				passwordWarning.setVisible(true);
				valid = false;
			}
			if (!SafeCheck.isLegalPhoneNumber(modifyphone)) {
				phoneWarning.setVisible(true);
				valid = false;
			}
			if (!SafeCheck.isLegalBirth(birth)) {
				dateWarning.setVisible(true);
				valid = false;
			}

			if (year == null || month == null || date == null) {
				dateWarning.setVisible(true);
				valid = false;
			}

			validate();
			if (!valid) {
				return;
			}
			try {
				ResultMessage resultMessage = Agent.memberService
						.modifyMember(new MemberPO(resultCustomers.get(
								selectedRow).getID(), resultCustomers.get(
								selectedRow).getName(), password, modifyphone,
								birth, resultCustomers.get(selectedRow)
										.getIntegral()));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "修改成功");
				} else {
					ImageDialog.showNOImage(this, "修改失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == modifyensureButton2) {
			passwordWarning.setVisible(false);

			char[] modifypassword = passwordField.getPassword();
			char[] modifyEnsurepassword = passwordEnsureField.getPassword();
			String password = new String(modifypassword);
			String ensurepassword = new String(modifyEnsurepassword);

			if (!SafeCheck.isLegalPassword(modifypassword)
					|| !SafeCheck.isLegalPassword(modifyEnsurepassword)
					|| !SafeCheck.passwordEnsure(password, ensurepassword)) {
				passwordWarning.setVisible(true);
				return;
			}

			validate();
			ResultMessage resultMessage;
			try {
				resultMessage = Agent.userService
						.modifyUser(new SalesManagerPO(resultSalesManagers.get(
								selectedRow).getID(), resultSalesManagers.get(
								selectedRow).getName(), password));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "修改成功");
				} else {
					ImageDialog.showNOImage(this, "修改失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == modifyensureButton3) {
			passwordWarning.setVisible(false);

			char[] modifypassword = passwordField.getPassword();
			char[] modifyEnsurepassword = passwordEnsureField.getPassword();
			String password = new String(modifypassword);
			String ensurepassword = new String(modifyEnsurepassword);

			if (!SafeCheck.isLegalPassword(modifypassword)
					|| !SafeCheck.isLegalPassword(modifyEnsurepassword)
					|| !SafeCheck.passwordEnsure(password, ensurepassword)) {
				passwordWarning.setVisible(true);
				return;
			}
			validate();
			ResultMessage resultMessage;
			try {
				resultMessage = Agent.userService
						.modifyUser(new GeneralManagerPO(resultManagers.get(
								selectedRow).getID(), resultManagers.get(
								selectedRow).getName(), password));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "修改成功");
				} else {
					ImageDialog.showNOImage(this, "修改失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void getData() {
		if (manageType == MANAGE_CUS) {
			resultCustomers.clear();
			try {
				ResultMessage exist = Agent.userService.getMembers();
				if (exist.isInvokeSuccess()) {
					resultCustomers = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "暂无用户");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			ArrayList<UserPO> users = null;
			ResultMessage exist = null;
			try {
				exist = Agent.userService.getUsers();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if (exist.isInvokeSuccess()) {
				users = exist.getResultSet();
			}
			if (manageType == MANAGE_SALESMANAGER) {
				resultSalesManagers.clear();
				for (UserPO user : users) {
					if (user.getType() == Const.SALESMANAGER) {
						resultSalesManagers.add(user);
					}
				}
				if (resultSalesManagers.size() == 0) {
					ImageDialog.showNOImage(this, "暂无用户");
				}
			} else if (manageType == MANAGE_MANAGER) {
				resultManagers.clear();
				for (UserPO user : users) {
					if (user.getType() == Const.GENERALMANAGER) {
						resultManagers.add(user);
					}
				}
				if (resultManagers.size() == 0) {
					ImageDialog.showNOImage(this, "暂无用户");
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == customerLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			if (manageType != MANAGE_CUS) {
				switch (manageType) {
				case MANAGE_MANAGER:
					manLabel.setForeground(Color.black);
					break;
				case MANAGE_SALESMANAGER:
					salesmanLabel.setForeground(Color.black);
					break;
				default:
					break;
				}
			}
			manageType = MANAGE_CUS;
			getData();
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			resultPanel.setViewportView(resultTable);
			customerLabel.setForeground(Color.red);

		} else if (e.getSource() == salesmanLabel
				&& e.getButton() == MouseEvent.BUTTON1) {

			if (manageType != MANAGE_SALESMANAGER) {
				switch (manageType) {
				case MANAGE_MANAGER:
					manLabel.setForeground(Color.black);
					break;
				case MANAGE_CUS:
					customerLabel.setForeground(Color.black);
					break;
				default:
					break;
				}
			}
			manageType = MANAGE_SALESMANAGER;
			getData();
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			resultPanel.setViewportView(resultTable);
			salesmanLabel.setForeground(Color.red);

		} else if (e.getSource() == manLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			if (manageType != MANAGE_MANAGER) {
				switch (manageType) {
				case MANAGE_SALESMANAGER:
					salesmanLabel.setForeground(Color.black);
					break;
				case MANAGE_CUS:
					customerLabel.setForeground(Color.black);
					break;
				default:
					break;
				}
			}
			manageType = MANAGE_MANAGER;
			getData();
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			resultPanel.setViewportView(resultTable);
			manLabel.setForeground(Color.red);

		} else if (e.getSource() == exitLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			try {
				Agent.userService.logout(Agent.userAgent);
				Agent.userAgent = null;
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
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
			switch (manageType) {
			case MANAGE_CUS:
				return resultCustomers.size();
			case MANAGE_MANAGER:
				return resultManagers.size();
			case MANAGE_SALESMANAGER:
				return resultSalesManagers.size();
			default:
				break;
			}
			return 0;
		}

		@Override
		public int getColumnCount() {
			switch (manageType) {
			case MANAGE_CUS:
				return customercolname.length;
			case MANAGE_MANAGER:
				return managercolname.length;
			case MANAGE_SALESMANAGER:
				return managercolname.length;
			default:
				break;
			}
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			switch (manageType) {
			case MANAGE_CUS:
				switch (columnIndex) {
				case 0:
					return resultCustomers.get(rowIndex).getID();
				case 1:
					return resultCustomers.get(rowIndex).getName();
				case 2:
					return resultCustomers.get(rowIndex).getPassword();
				case 3:
					return resultCustomers.get(rowIndex).getPhone();
				case 4:
					return simpleDateFormat.format(resultCustomers
							.get(rowIndex).getBirth().getTime());
				default:
					break;
				}
				break;
			case MANAGE_MANAGER:
				switch (columnIndex) {
				case 0:
					return resultManagers.get(rowIndex).getID();
				case 1:
					return resultManagers.get(rowIndex).getName();
				case 2:
					return resultManagers.get(rowIndex).getPassword();
				case 3:
					return resultManagers.get(rowIndex).getType();
				default:
					break;
				}
				break;
			case MANAGE_SALESMANAGER:
				switch (columnIndex) {
				case 0:
					return resultSalesManagers.get(rowIndex).getID();
				case 1:
					return resultSalesManagers.get(rowIndex).getName();
				case 2:
					return resultSalesManagers.get(rowIndex).getPassword();
				case 3:
					return resultSalesManagers.get(rowIndex).getType();
				default:
					break;
				}
				break;
			default:
				break;
			}
			return null;
		}

		@Override
		public String getColumnName(int column) {
			switch (manageType) {
			case MANAGE_CUS:
				return customercolname[column];
			case MANAGE_MANAGER:
				return managercolname[column];
			case MANAGE_SALESMANAGER:
				return managercolname[column];
			default:
				break;
			}
			return "";
		}
	}

}
