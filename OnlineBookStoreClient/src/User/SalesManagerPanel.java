package User;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Book.BookPO;
import Book.BookPanel;
import Book.CatalogAddPanel;
import Book.DirectoryPO;
import ClientRunner.Agent;
import ClientRunner.IMGSTATIC;
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MComboBox;
import ClientRunner.MPanel;
import Member.MemberPO;
import Member.PurchaseRecordPanel;
import Promotion.PromotionComBox;
import Promotion.PromotionPO;
import RMI.ResultMessage;
import Sale.OrderPO;

@SuppressWarnings("serial")
public class SalesManagerPanel extends MPanel implements MouseListener,
		ActionListener {

	private UserUIController userUIController;
	private BookPO bookPO;

	ArrayList<BookPO> resultBooks = new ArrayList<BookPO>();
	ArrayList<DirectoryPO> resultBookClassifys = new ArrayList<DirectoryPO>();
	ArrayList<MemberPO> resultmembers = new ArrayList<MemberPO>();
	ArrayList<OrderPO> resultorders = new ArrayList<OrderPO>();
	ArrayList<PromotionPO> resultpromotions = new ArrayList<PromotionPO>();
	String[] bookcolomn = { "名称", "ISBN", "作者", "出版社", "价格", "特价" };
	String[] bookClassifyColomn = { "编号", "名称" };
	String[] members = { "编号", "用户名" };

	static final int MANAGE_BOOK = 2;
	static final int MANAGE_BOOKCLASSIFY = 3;
	static final int MANAGE_MEMBER = 0;
	static final int MANAGE_PROMOTION = 1;
	private int manageType = 0;
	int selectedRow = -1;

	// main page
	private JLabel obsLabel, welcomLabel, exitLabel;
	tableModel tableModel;
	JTable resultTable;
	MPanel resultPanel;
	private JLabel cusInfoLabel, promotionLabel, bookLabel, bookclassifyLabel;
	private MPanel memberContentPane, cusinfoPanel, promotionPanel;
	private PurchaseRecordPanel orderPanel;
	private MPanel bookPanel, catalogManagePanel;
	private JScrollPane bookJScrollPane, bookClassifyJScrollPane;

	// customer information
	private JLabel membername, membername1, memberphone, memberphone1,
			memberdate, memberdate1, membernameWarning;
	private MButton historyButton, searchButton, orderButton;
	private JScrollPane memberPOPane;

	// order
	private JLabel orderLabel2;
	private MButton orderensureButton;
	String temp[] = { "尚未付款", "已发货", "已发货(货到付款)", "交易取消", "交易成功" };
	JComboBox<String> orderBox;
	// promotion
	private JLabel bondLabel, proLabel;
	private MButton ensureButton;
	private PromotionComBox promotionName;
	private JScrollPane memPane;

	// book management
	private MButton addBookButton, deleteBookButton, modifyBookButton,
			ensureAddButton, ensureModButton, listButton;
	private BookPanel addBookPanel;
	private JLabel nameWarning, ISBNWarning, authorWarning, pressWarning,
			yearWarning, priceWarning, spepriceWarning;

	// book classify
	private MButton addClassifyButton, delClassifyButton, modClassifyButton,
			ensureAddCatalogButton, ensureModCatalogButton, listButton2;
	private CatalogAddPanel catalogAddPanel;
	private JLabel catnameWarning;

	public SalesManagerPanel(UserUIController userUIController) {
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

		welcomLabel = new JLabel("欢迎您，销售经理:" + Agent.userAgent.getName());
		welcomLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(200, 40);
		welcomLabel.setLocation(520, 20);

		exitLabel = new JLabel("退出");
		exitLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);

		cusInfoLabel = new JLabel("顾客信息");
		cusInfoLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cusInfoLabel.setSize(120, 40);
		cusInfoLabel.setLocation(10, 80);
		cusInfoLabel.addMouseListener(this);

		promotionLabel = new JLabel("优惠券赠送");
		promotionLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		promotionLabel.setSize(120, 40);
		promotionLabel.setLocation(140, 80);
		promotionLabel.addMouseListener(this);

		bookLabel = new JLabel("图书管理");
		bookLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		bookLabel.setSize(120, 40);
		bookLabel.setLocation(270, 80);
		bookLabel.addMouseListener(this);

		bookclassifyLabel = new JLabel("图书类别管理");
		bookclassifyLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		bookclassifyLabel.setSize(120, 40);
		bookclassifyLabel.setLocation(400, 80);
		bookclassifyLabel.addMouseListener(this);

		this.add(obsLabel);
		this.add(welcomLabel);
		this.add(exitLabel);
		this.add(cusInfoLabel);
		this.add(promotionLabel);
		this.add(bookLabel);
		this.add(bookclassifyLabel);

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

		resultPanel = new MPanel();
		resultPanel.setLocation(0, 140);
		resultPanel.setSize(800, 430);
		resultPanel.setVisible(true);
		resultPanel.setLayout(null);
		this.add(resultPanel);
		repaint();
		validate();
	}

	public void initcusinfoView() {
		cusinfoPanel = initPanel(cusinfoPanel);
		manageType = MANAGE_MEMBER;
		getData();
		tableModel = new tableModel();
		resultTable.setModel(tableModel);
		memberPOPane = new JScrollPane(resultTable);
		memberPOPane.setLocation(0, 0);
		memberPOPane.setSize(200, 300);
		memberPOPane.setVisible(true);
		memberPOPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberPOPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		memberPOPane.setOpaque(false);
		memberPOPane.getViewport().setOpaque(false);

		searchButton = new MButton("查看信息");
		searchButton.setSize(160, 30);
		searchButton.setLocation(20, 305);
		searchButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		searchButton.addActionListener(this);

		historyButton = new MButton("查看购买记录");
		historyButton.setSize(160, 30);
		historyButton.setLocation(20, 345);
		historyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 17));
		historyButton.addActionListener(this);

		orderButton = new MButton("修改订单状态");
		orderButton.setSize(160, 30);
		orderButton.setVisible(false);
		orderButton.setEnabled(false);
		orderButton.setLocation(20, 395);
		orderButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 17));
		orderButton.addActionListener(this);

		memberContentPane = new MPanel();
		memberContentPane.setSize(600, 430);
		memberContentPane.setLocation(200, 0);
		memberContentPane.setLayout(null);

		cusinfoPanel.add(searchButton);
		cusinfoPanel.add(historyButton);
		cusinfoPanel.add(orderButton);
		cusinfoPanel.add(memberPOPane);
		cusinfoPanel.add(memberContentPane);
		validate();
	}

	public void orderView(int orderID) {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setSize(new Dimension(600, 430));

		JLabel idlabel = new JLabel("订单号:" + orderID);
		idlabel.setSize(200, 40);
		idlabel.setLocation(240, 10);
		idlabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		orderLabel2 = new JLabel("请选择新订单状态:");
		orderLabel2.setSize(200, 40);
		orderLabel2.setLocation(150, 60);
		orderLabel2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		orderensureButton = new MButton("确认修改");
		orderensureButton.setSize(120, 40);
		orderensureButton.setVisible(true);
		orderensureButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		orderensureButton.setLocation(240, 120);
		orderensureButton.addActionListener(this);

		orderBox = new MComboBox<String>(temp);
		orderBox.setSelectedIndex(0);
		orderBox.setSize(160, 40);
		orderBox.setVisible(true);
		orderBox.setLocation(350, 60);

		panel.add(idlabel);
		panel.add(orderLabel2);
		panel.add(orderensureButton);
		panel.add(orderBox);
		panel.requestFocus();
		memberContentPane.removeAll();
		memberContentPane.add(panel);
		memberContentPane.validate();
		memberContentPane.requestFocus();
		repaint();
	}

	public void memberInfoView(MemberPO memberPO) {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setSize(new Dimension(600, 430));

		membername = new JLabel("用户名:");
		membername.setSize(100, 40);
		membername.setLocation(30, 50);
		membername.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		membername1 = new JLabel(memberPO.getName());
		membername1.setSize(300, 40);
		membername1.setLocation(140, 50);
		membername1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		memberphone = new JLabel("联系电话:");
		memberphone.setSize(100, 40);
		memberphone.setLocation(30, 100);
		memberphone.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		memberphone1 = new JLabel(memberPO.getPhone());
		memberphone1.setSize(300, 40);
		memberphone1.setLocation(140, 100);
		memberphone1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		memberdate = new JLabel("出生日期:");
		memberdate.setSize(100, 40);
		memberdate.setLocation(30, 150);
		memberdate.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String birth = sdf.format(memberPO.getBirth().getTime());
		memberdate1 = new JLabel(birth);
		memberdate1.setSize(300, 40);
		memberdate1.setLocation(140, 150);
		memberdate1.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));

		panel.add(membername);
		panel.add(membername1);
		panel.add(memberphone);
		panel.add(memberphone1);
		panel.add(memberdate);
		panel.add(memberdate1);
		panel.requestFocus();
		memberContentPane.removeAll();
		memberContentPane.add(panel);
		memberContentPane.validate();
		memberContentPane.requestFocus();
		repaint();
	}

	public void initpromotionView() {
		promotionPanel = initPanel(promotionPanel);

		bondLabel = new JLabel("请选择促销策略:");
		bondLabel.setSize(150, 40);
		bondLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		bondLabel.setLocation(40, 15);

		proLabel = new JLabel("促销策略:");
		proLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		proLabel.setSize(100, 40);
		proLabel.setLocation(40, 65);

		ensureButton = new MButton("确认赠送");
		ensureButton.setSize(120, 40);
		ensureButton.setVisible(true);
		ensureButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensureButton.setLocation(50, 300);
		ensureButton.addActionListener(this);

		promotionPanel.add(bondLabel);
		promotionPanel.add(ensureButton);
		promotionPanel.add(proLabel);
	}

	public void initbookView() {
		bookPanel = initPanel(bookPanel);

		addBookButton = new MButton("添加图书");
		addBookButton.setSize(120, 40);
		addBookButton.setFocusable(false);
		addBookButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addBookButton.setLocation(10, 15);
		addBookButton.addActionListener(this);

		deleteBookButton = new MButton("删除图书");
		deleteBookButton.setSize(120, 40);
		deleteBookButton.setFocusable(false);
		deleteBookButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		deleteBookButton.setLocation(10, 95);
		deleteBookButton.addActionListener(this);

		modifyBookButton = new MButton("修改图书");
		modifyBookButton.setSize(120, 40);
		modifyBookButton.setFocusable(false);
		modifyBookButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modifyBookButton.setLocation(10, 175);
		modifyBookButton.addActionListener(this);

		listButton = new MButton("图书列表");
		listButton.setFocusable(false);
		listButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		listButton.setSize(120, 40);
		listButton.setLocation(10, 255);
		listButton.addActionListener(this);

		bookJScrollPane = new JScrollPane(resultTable);
		bookJScrollPane.setLocation(160, 0);
		bookJScrollPane.setSize(600, 430);
		bookJScrollPane.setVisible(true);
		bookJScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bookJScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		bookJScrollPane.getVerticalScrollBar().setUnitIncrement(15);
		bookJScrollPane.setOpaque(false);
		bookJScrollPane.getViewport().setOpaque(false);

		bookPanel.add(addBookButton);
		bookPanel.add(deleteBookButton);
		bookPanel.add(modifyBookButton);
		bookPanel.add(listButton);
		bookPanel.add(bookJScrollPane);
		validate();
	}

	public void initbookAddView() {
		addBookPanel = new BookPanel(userUIController.getMainFrame()
				.getBookUIController());
		addBookPanel.createNewBookView();
		addBookPanel.enableModification();
		addBookPanel.setVisible(true);
		addBookPanel.setLocation(10, 0);
		addBookPanel.setPreferredSize(new Dimension(addBookPanel.getWidth(),
				addBookPanel.getHeight()));

		ensureAddButton = new MButton("确认添加");
		ensureAddButton.setSize(120, 40);
		ensureAddButton.setFocusable(true);
		ensureAddButton.setVisible(true);
		ensureAddButton.setEnabled(true);
		ensureAddButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensureAddButton.setLocation(400, 360);
		ensureAddButton.addActionListener(this);

		nameWarning = new JLabel("书名不合法!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(340, 5);
		nameWarning.setVisible(false);

		ISBNWarning = new JLabel("ISBN不合法!");
		ISBNWarning.setSize(140, 50);
		ISBNWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		ISBNWarning.setForeground(Color.red);
		ISBNWarning.setLocation(340, 95);
		ISBNWarning.setVisible(false);

		authorWarning = new JLabel("作者名不合法!");
		authorWarning.setSize(140, 50);
		authorWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		authorWarning.setForeground(Color.red);
		authorWarning.setLocation(300, 145);
		authorWarning.setVisible(false);

		pressWarning = new JLabel("出版社不合法!");
		pressWarning.setSize(140, 50);
		pressWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		pressWarning.setForeground(Color.red);
		pressWarning.setLocation(340, 185);
		pressWarning.setVisible(false);

		yearWarning = new JLabel("日期不合法!");
		yearWarning.setSize(140, 50);
		yearWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		yearWarning.setForeground(Color.red);
		yearWarning.setLocation(340, 275);
		yearWarning.setVisible(false);

		priceWarning = new JLabel("价格不合法!");
		priceWarning.setSize(140, 50);
		priceWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		priceWarning.setForeground(Color.red);
		priceWarning.setLocation(340, 50);
		priceWarning.setVisible(false);

		spepriceWarning = new JLabel("特价不合法!");
		spepriceWarning.setSize(140, 50);
		spepriceWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		spepriceWarning.setForeground(Color.red);
		spepriceWarning.setLocation(340, 230);
		spepriceWarning.setVisible(false);

		addBookPanel.add(ensureAddButton);
		addBookPanel.add(nameWarning);
		addBookPanel.add(ISBNWarning);
		addBookPanel.add(authorWarning);
		addBookPanel.add(pressWarning);
		addBookPanel.add(yearWarning);
		addBookPanel.add(priceWarning);
		addBookPanel.add(spepriceWarning);

	}

	public void initbookModifyView() {
		addBookPanel = new BookPanel(userUIController.getMainFrame()
				.getBookUIController(), bookPO);
		addBookPanel.createBookInfoView();
		addBookPanel.enableModification();
		addBookPanel.setVisible(true);
		addBookPanel.setLocation(10, 0);
		addBookPanel.setPreferredSize(new Dimension(addBookPanel.getWidth(),
				addBookPanel.getHeight()));

		ensureModButton = new MButton("确认修改");
		ensureModButton.setSize(120, 40);
		ensureModButton.setFocusable(true);
		ensureModButton.setVisible(true);
		ensureModButton.setEnabled(true);
		ensureModButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensureModButton.setLocation(400, 360);
		ensureModButton.addActionListener(this);

		nameWarning = new JLabel("书名不合法!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(340, 5);
		nameWarning.setVisible(false);

		ISBNWarning = new JLabel("ISBN不合法!");
		ISBNWarning.setSize(140, 50);
		ISBNWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		ISBNWarning.setForeground(Color.red);
		ISBNWarning.setLocation(340, 95);
		ISBNWarning.setVisible(false);

		authorWarning = new JLabel("作者名不合法!");
		authorWarning.setSize(140, 50);
		authorWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		authorWarning.setForeground(Color.red);
		authorWarning.setLocation(340, 140);
		authorWarning.setVisible(false);

		pressWarning = new JLabel("出版社不合法!");
		pressWarning.setSize(140, 50);
		pressWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		pressWarning.setForeground(Color.red);
		pressWarning.setLocation(340, 185);
		pressWarning.setVisible(false);

		priceWarning = new JLabel("价格不合法!");
		priceWarning.setSize(140, 50);
		priceWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		priceWarning.setForeground(Color.red);
		priceWarning.setLocation(340, 50);
		priceWarning.setVisible(false);

		spepriceWarning = new JLabel("特价不合法!");
		spepriceWarning.setSize(140, 50);
		spepriceWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		spepriceWarning.setForeground(Color.red);
		spepriceWarning.setLocation(340, 230);
		spepriceWarning.setVisible(false);

		yearWarning = new JLabel("日期不合法!");
		yearWarning.setSize(140, 50);
		yearWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		yearWarning.setForeground(Color.red);
		yearWarning.setLocation(340, 275);
		yearWarning.setVisible(false);

		addBookPanel.add(ensureModButton);
		addBookPanel.add(nameWarning);
		addBookPanel.add(ISBNWarning);
		addBookPanel.add(authorWarning);
		addBookPanel.add(pressWarning);
		addBookPanel.add(yearWarning);
		addBookPanel.add(priceWarning);
		addBookPanel.add(spepriceWarning);

	}

	public void initbookClassifyView() {
		catalogManagePanel = initPanel(catalogManagePanel);

		addClassifyButton = new MButton("添加类别");
		addClassifyButton.setSize(120, 40);
		addClassifyButton.setFocusable(false);
		addClassifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addClassifyButton.setLocation(10, 15);
		addClassifyButton.addActionListener(this);

		delClassifyButton = new MButton("删除类别");
		delClassifyButton.setSize(120, 40);
		delClassifyButton.setFocusable(false);
		delClassifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		delClassifyButton.setLocation(10, 95);
		delClassifyButton.addActionListener(this);

		modClassifyButton = new MButton("修改类别");
		modClassifyButton.setSize(120, 40);
		modClassifyButton.setFocusable(false);
		modClassifyButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		modClassifyButton.setLocation(10, 175);
		modClassifyButton.addActionListener(this);

		listButton2 = new MButton("类别列表");
		listButton2.setFocusable(false);
		listButton2.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		listButton2.setSize(120, 40);
		listButton2.setLocation(10, 255);
		listButton2.addActionListener(this);

		bookClassifyJScrollPane = new JScrollPane(resultTable);
		bookClassifyJScrollPane.setLocation(160, 10);
		bookClassifyJScrollPane.setSize(600, 640);
		bookClassifyJScrollPane.setVisible(true);
		bookClassifyJScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bookClassifyJScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		bookClassifyJScrollPane.setOpaque(false);
		bookClassifyJScrollPane.getViewport().setOpaque(false);

		catalogManagePanel.add(addClassifyButton);
		catalogManagePanel.add(delClassifyButton);
		catalogManagePanel.add(modClassifyButton);
		catalogManagePanel.add(listButton2);
		catalogManagePanel.add(bookClassifyJScrollPane);
		validate();
	}

	public void initbookClaAddView() {
		catalogAddPanel = new CatalogAddPanel(userUIController.getMainFrame()
				.getBookUIController());
		catalogAddPanel.init();
		catalogAddPanel.setLocation(10, 10);
		catalogAddPanel.setVisible(true);

		ensureAddCatalogButton = new MButton("添加");
		ensureAddCatalogButton.setSize(80, 40);
		ensureAddCatalogButton.setFocusable(false);
		ensureAddCatalogButton.setEnabled(true);
		ensureAddCatalogButton.setVisible(true);
		ensureAddCatalogButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensureAddCatalogButton.setLocation(100, 100);
		ensureAddCatalogButton.addActionListener(this);

		catnameWarning = new JLabel("类别不合法!");
		catnameWarning.setSize(140, 50);
		catnameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		catnameWarning.setForeground(Color.red);
		catnameWarning.setLocation(330, 15);
		catnameWarning.setVisible(false);

		catalogAddPanel.add(ensureAddCatalogButton);
		catalogAddPanel.add(catnameWarning);

	}

	public void initbookClaModView() {
		catalogAddPanel = new CatalogAddPanel(userUIController.getMainFrame()
				.getBookUIController());
		catalogAddPanel.init();
		catalogAddPanel.setLocation(10, 10);
		catalogAddPanel.setVisible(true);

		ensureModCatalogButton = new MButton("修改");
		ensureModCatalogButton.setSize(80, 40);
		ensureModCatalogButton.setFocusable(false);
		ensureModCatalogButton.setEnabled(true);
		ensureModCatalogButton.setVisible(true);
		ensureModCatalogButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ensureModCatalogButton.setLocation(100, 100);
		ensureModCatalogButton.addActionListener(this);

		catnameWarning = new JLabel("类别不合法!");
		catnameWarning.setSize(140, 50);
		catnameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		catnameWarning.setForeground(Color.red);
		catnameWarning.setLocation(330, 15);
		catnameWarning.setVisible(false);

		catalogAddPanel.add(ensureModCatalogButton);
		catalogAddPanel.add(catnameWarning);
	}

	public void getData() {
		if (manageType == MANAGE_BOOK) {
			resultBooks.clear();
			try {
				ResultMessage exist = Agent.bookService.getAllBooks();
				if (exist.isInvokeSuccess()) {
					resultBooks = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "暂无图书");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (manageType == MANAGE_BOOKCLASSIFY) {
			resultBookClassifys.clear();
			try {
				ResultMessage exist = Agent.bookService.getAllDirectories();
				if (exist.isInvokeSuccess()) {
					resultBookClassifys = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "暂无图书类别");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (manageType == MANAGE_MEMBER) {
			resultmembers.clear();

			try {
				ResultMessage exist = Agent.userService.getMembers();
				if (exist.isInvokeSuccess()) {
					resultmembers = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "暂无用户");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
		if (manageType == MANAGE_PROMOTION) {
			resultpromotions.clear();
			try {
				ResultMessage exist = Agent.promotionService.getPormotionList();
				if (exist.isInvokeSuccess()) {
					resultpromotions = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "暂无促销策略");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitLabel && e.getButton() == MouseEvent.BUTTON1) {
			try {
				Agent.userService.logout(Agent.userAgent);
				Agent.userAgent = null;
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			userUIController.setMainPageView();
		} else if (e.getSource() == cusInfoLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initcusinfoView();
			resultPanel.removeAll();
			resultPanel.add(cusinfoPanel);
			resultPanel.validate();
			validate();
			repaint();
			cusinfoPanel.requestFocus();
		} else if (e.getSource() == promotionLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initpromotionView();
			manageType = MANAGE_PROMOTION;
			getData();
			if (resultpromotions.size() > 0) {
				promotionName = new PromotionComBox();
				for (int i = 0; i < resultpromotions.size(); i++) {
					promotionName.addItem(resultpromotions.get(i));
				}
				promotionName.setSelectedIndex(0);
				promotionName.setFocusable(false);
				promotionName.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
				promotionName.setSize(150, 35);
				promotionName.setLocation(140, 65);
				promotionPanel.add(promotionName);
			}
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			memPane = new JScrollPane();
			memPane.setSize(460, 450);
			memPane.setLocation(320, 0);
			memPane.setViewportView(resultTable);
			memPane.setOpaque(false);
			memPane.getViewport().setOpaque(false);
			resultPanel.removeAll();
			resultPanel.add(promotionPanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			promotionPanel.requestFocus();
			promotionPanel.add(memPane);
		} else if (e.getSource() == bookLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initbookView();
			manageType = MANAGE_BOOK;
			getData();
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			bookJScrollPane.setViewportView(resultTable);
			resultPanel.removeAll();
			resultPanel.add(bookPanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			bookPanel.requestFocus();
		} else if (e.getSource() == bookclassifyLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initbookClassifyView();
			manageType = MANAGE_BOOKCLASSIFY;
			getData();
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			bookClassifyJScrollPane.setViewportView(resultTable);
			resultPanel.removeAll();
			resultPanel.add(catalogManagePanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			catalogManagePanel.requestFocus();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "请先选择用户!");
			} else {
				MemberPO memberPO = null;
				try {
					ResultMessage resultMessage = Agent.memberService
							.queryMember(resultmembers.get(selectedRow).getID());
					if (resultMessage.isInvokeSuccess()) {
						memberPO = (MemberPO) resultMessage.getResultSet().get(
								0);
					} else {
						return;
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if (memberPO == null) {
					return;
				}
				memberInfoView(memberPO);
			}

		} else if (e.getSource() == historyButton) {
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "请先选择用户!");
			} else {

				try {
					ResultMessage resultMessage = Agent.memberService
							.purchaseQuery(resultmembers.get(selectedRow)
									.getID());
					if (resultMessage.isInvokeSuccess()) {
						resultorders = resultMessage.getResultSet();
						orderPanel = new PurchaseRecordPanel(userUIController
								.getMainFrame().getMemberUIController(),
								resultorders);
						orderPanel.init();
						memberContentPane.removeAll();
						memberContentPane.add(orderPanel);
						memberContentPane.validate();
						memberContentPane.requestFocus();
						repaint();
						orderButton.setVisible(true);
						orderButton.setEnabled(true);
						if (resultorders.size() == 0) {
							JOptionPane.showMessageDialog(this, "该用户无购买记录");
						}
					} else {
						ImageDialog.showNOImage(this, "查找失败");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == orderButton) {
			if (orderPanel.getSelectedOrder() == -1) {
				JOptionPane.showMessageDialog(this, "请先选择订单!");
			} else {
				orderView(orderPanel.getSelectedOrder());
			}

		} else if (e.getSource() == orderensureButton) {
			ResultMessage resultMessage;
			try {
				resultMessage = Agent.userService.orderModify(new OrderPO(
						resultorders.get(selectedRow).getOrderID(),
						resultmembers.get(selectedRow).getID(), resultorders
								.get(selectedRow).getBooks(), resultorders.get(
								selectedRow).getTotalprice(), resultorders.get(
								selectedRow).getDate(), orderBox
								.getSelectedIndex()));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "修改成功");
				} else {
					ImageDialog.showNOImage(this, "修改失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == ensureButton) {
			ResultMessage resultMessage = null;
			try {
				resultMessage = Agent.promotionService.triggerPromotion(
						resultmembers.get(selectedRow).getID(),
						((PromotionPO) promotionName.getSelectedItem())
								.getPromotionID());
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "赠送成功");
				} else {
					ImageDialog.showNOImage(this, "赠送失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == listButton) {

			getData();
			tableModel.fireTableDataChanged();
			bookJScrollPane.setViewportView(resultTable);
			resultPanel.removeAll();
			resultPanel.add(bookPanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			bookPanel.requestFocus();
		} else if (e.getSource() == listButton2) {

			getData();
			tableModel.fireTableDataChanged();
			bookClassifyJScrollPane.setViewportView(resultTable);
			resultPanel.removeAll();
			resultPanel.add(catalogManagePanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			catalogManagePanel.requestFocus();

		} else if (e.getSource() == addBookButton) {

			initbookAddView();
			bookJScrollPane.setViewportView(addBookPanel);
			resultPanel.removeAll();
			resultPanel.add(bookPanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			bookPanel.requestFocus();
			validate();
		} else if (e.getSource() == modifyBookButton) {
			if (selectedRow != -1) {
				try {
					ResultMessage resultMessage = Agent.bookService
							.getSelectedBook(resultBooks.get(selectedRow)
									.getISBN());
					if (resultMessage.isInvokeSuccess()) {
						bookPO = (BookPO) resultMessage.getResultSet().get(0);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				initbookModifyView();
				bookJScrollPane.setViewportView(addBookPanel);
				resultPanel.removeAll();
				resultPanel.add(bookPanel);
				resultPanel.validate();
				resultPanel.requestFocus();
				repaint();
				bookPanel.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "请先选择要修改的图书！");
			}

		} else if (e.getSource() == addClassifyButton) {

			initbookClaAddView();
			bookClassifyJScrollPane.setViewportView(catalogAddPanel);
			resultPanel.removeAll();
			resultPanel.add(catalogManagePanel);
			resultPanel.validate();
			resultPanel.requestFocus();
			repaint();
			catalogManagePanel.requestFocus();

		} else if (e.getSource() == modClassifyButton) {
			if (selectedRow != -1) {
				initbookClaModView();
				bookClassifyJScrollPane.setViewportView(catalogAddPanel);
				resultPanel.removeAll();
				resultPanel.add(catalogManagePanel);
				resultPanel.validate();
				resultPanel.requestFocus();
				repaint();
				catalogManagePanel.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "请先选择要修改的图书类别！");
			}

		} else if (e.getSource() == ensureAddButton) {
			String bookName = addBookPanel.getBookName();
			String isbn = addBookPanel.getisbn();
			String author = addBookPanel.getAuthor();
			String press = addBookPanel.getPress();
			Calendar date = addBookPanel.getPublishDate();
			double price = addBookPanel.getPrice();
			double specialPrice = addBookPanel.getSpecialPrice();

			boolean valid = true;
			if (bookName != null && isbn != null && author != null
					&& press != null && price > 0 && specialPrice > 0) {
				nameWarning.setVisible(false);
				ISBNWarning.setVisible(false);
				authorWarning.setVisible(false);
				pressWarning.setVisible(false);
				priceWarning.setVisible(false);
			}
			if (bookName == null) {
				nameWarning.setVisible(true);
				valid = false;
			}
			if (isbn == null) {
				ISBNWarning.setVisible(true);
				valid = false;
			}
			if (author == null) {
				authorWarning.setVisible(true);
				valid = false;
			}
			if (press == null) {
				pressWarning.setVisible(true);
				valid = false;
			}
			if (price <= 0) {
				priceWarning.setVisible(true);
				valid = false;
			}
			if (specialPrice <= 0 || specialPrice > price) {
				spepriceWarning.setVisible(true);
				valid = false;
			}
			if (date == null) {
				yearWarning.setVisible(true);
				valid = false;
			}

			validate();
			if (!valid) {
				return;
			}
			try {
				ResultMessage resultMessage = Agent.bookService
						.addBook(new BookPO(addBookPanel.getBookName(),
								addBookPanel.getisbn(), addBookPanel
										.getAuthor(), addBookPanel.getPress(),
								addBookPanel.getDescription(), addBookPanel
										.getDirectoryID(), addBookPanel
										.getPublishDate(), addBookPanel
										.getPrice(), addBookPanel
										.getSpecialPrice()));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "添加成功");
					addBookPanel.clear();
				} else {
					ImageDialog.showNOImage(this, "添加失败");
					addBookPanel.clear();
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == ensureModButton) {
			String bookName = addBookPanel.getBookName();
			String isbn = addBookPanel.getisbn();
			String author = addBookPanel.getAuthor();
			String press = addBookPanel.getPress();
			Calendar date = addBookPanel.getPublishDate();
			double price = addBookPanel.getPrice();
			double specialPrice = addBookPanel.getSpecialPrice();

			boolean valid = true;
			if (bookName.length() > 0 && isbn.length() > 0
					&& author.length() > 0 && press.length() > 0 && price >= 0
					&& specialPrice >= 0) {
				nameWarning.setVisible(false);
				ISBNWarning.setVisible(false);
				authorWarning.setVisible(false);
				pressWarning.setVisible(false);
				priceWarning.setVisible(false);
			}
			if (bookName == null) {
				nameWarning.setVisible(true);
				valid = false;
			}
			if (isbn == null) {
				ISBNWarning.setVisible(true);
				valid = false;
			}
			if (author == null) {
				authorWarning.setVisible(true);
				valid = false;
			}
			if (press == null) {
				pressWarning.setVisible(true);
				valid = false;
			}
			if (price <= 0) {
				priceWarning.setVisible(true);
				valid = false;
			}
			if (specialPrice <= 0 || specialPrice > price) {
				spepriceWarning.setVisible(true);
				valid = false;
			}
			if (date == null) {
				yearWarning.setVisible(true);
				valid = false;
			}

			validate();
			if (!valid) {
				return;
			}
			try {
				ResultMessage resultMessage = Agent.bookService
						.modifyBook(new BookPO(addBookPanel.getBookName(),
								addBookPanel.getisbn(), addBookPanel
										.getAuthor(), addBookPanel.getPress(),
								addBookPanel.getDescription(), addBookPanel
										.getDirectoryID(), addBookPanel
										.getPublishDate(), addBookPanel
										.getPrice(), addBookPanel
										.getSpecialPrice()));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "修改成功");
					addBookPanel.clear();
				} else {
					System.err.println(resultMessage.getPostScript());
					ImageDialog.showNOImage(this, "修改失败");
					addBookPanel.clear();
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == ensureAddCatalogButton) {
			String catalog_name = catalogAddPanel.getCatalog().trim();
			if (catalog_name.length() > 0) {
				catnameWarning.setVisible(false);
			} else {
				catnameWarning.setVisible(true);
				return;
			}
			try {
				ResultMessage resultMessage = Agent.bookService
						.addDirectory(new DirectoryPO(-1, catalog_name));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "添加成功");
				} else {
					ImageDialog.showNOImage(this, "添加失败");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

			catalogAddPanel.clear();
		} else if (e.getSource() == ensureModCatalogButton) {
			String catalog_name = catalogAddPanel.getCatalog().trim();
			if (catalog_name.length() > 0) {
				catnameWarning.setVisible(false);
			} else {
				catnameWarning.setVisible(true);
				return;
			}
			try {
				ResultMessage resultMessage = Agent.bookService
						.modifyDirectory(new DirectoryPO(resultBookClassifys
								.get(selectedRow).getID(), catalog_name));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "修改成功");
				} else {
					ImageDialog.showNOImage(this, "修改失败");
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
			catalogAddPanel.clear();

		} else if (e.getSource() == deleteBookButton) {
			if (selectedRow != -1) {
				if (JOptionPane.showConfirmDialog(this, "确认删除？") == JOptionPane.YES_OPTION) {
					ResultMessage exist;
					try {
						exist = Agent.bookService.deleteBook(resultBooks.get(
								selectedRow).getISBN());
						if (exist.isInvokeSuccess()) {
							ImageDialog.showYesImage(this, "删除成功");
						} else {
							ImageDialog.showNOImage(this, "删除失败");
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}

			} else {
				JOptionPane.showMessageDialog(this, "请先选择要删除的图书");
			}
			getData();
			tableModel.fireTableDataChanged();
		} else if (e.getSource() == delClassifyButton) {
			if (selectedRow != -1) {
				if (JOptionPane.showConfirmDialog(this, "确认删除？") == JOptionPane.YES_OPTION) {

					ResultMessage exist;
					try {
						exist = Agent.bookService
								.deleteDirectory(resultBookClassifys.get(
										selectedRow).getID());
						if (exist.isInvokeSuccess()) {
							ImageDialog.showYesImage(this, "删除成功");
						} else {
							ImageDialog.showNOImage(this, "删除失败");
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "请先选择要删除的图书类别");
			}
			getData();
			tableModel.fireTableDataChanged();
		}

	}

	private MPanel initPanel(MPanel panel) {
		panel = new MPanel();
		panel.setSize(800, 430);
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setVisible(true);
		return panel;
	}

	class tableModel extends AbstractTableModel {
		@Override
		public int getRowCount() {
			switch (manageType) {
			case MANAGE_BOOK:
				return resultBooks.size();
			case MANAGE_BOOKCLASSIFY:
				return resultBookClassifys.size();
			case MANAGE_MEMBER:
				return resultmembers.size();
			case MANAGE_PROMOTION:
				return resultmembers.size();
			default:
				break;
			}
			return 0;
		}

		@Override
		public int getColumnCount() {
			switch (manageType) {
			case MANAGE_BOOK:
				return bookcolomn.length;
			case MANAGE_BOOKCLASSIFY:
				return bookClassifyColomn.length;
			case MANAGE_MEMBER:
				return members.length;
			case MANAGE_PROMOTION:
				return members.length;
			default:
				break;
			}
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			switch (manageType) {
			case MANAGE_BOOK:
				switch (columnIndex) {
				case 0:
					return resultBooks.get(rowIndex).getName();
				case 1:
					return resultBooks.get(rowIndex).getISBN();
				case 2:
					return resultBooks.get(rowIndex).getAuthor();
				case 3:
					return resultBooks.get(rowIndex).getPress();
				case 4:
					return resultBooks.get(rowIndex).getPrice();
				case 5:
					return resultBooks.get(rowIndex).getSpecialPrice();
				default:
					break;
				}
				break;
			case MANAGE_BOOKCLASSIFY:
				switch (columnIndex) {
				case 0:
					return resultBookClassifys.get(rowIndex).getID();
				case 1:
					return resultBookClassifys.get(rowIndex).getName();

				default:
					break;
				}
				break;
			case MANAGE_MEMBER:
				switch (columnIndex) {
				case 0:
					return resultmembers.get(rowIndex).getID();
				case 1:
					return resultmembers.get(rowIndex).getName();

				default:
					break;
				}
				break;
			case MANAGE_PROMOTION:
				switch (columnIndex) {
				case 0:
					return resultmembers.get(rowIndex).getID();
				case 1:
					return resultmembers.get(rowIndex).getName();

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
			case MANAGE_BOOK:
				return bookcolomn[column];
			case MANAGE_BOOKCLASSIFY:
				return bookClassifyColomn[column];
			case MANAGE_MEMBER:
				return members[column];
			case MANAGE_PROMOTION:
				return members[column];
			default:
				break;
			}
			return "";
		}
	}
}
