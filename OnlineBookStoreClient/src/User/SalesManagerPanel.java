package User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
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
	String[] bookcolomn = { "����", "ISBN", "����", "������", "�۸�", "�ؼ�" };
	String[] bookClassifyColomn = { "���", "����" };
	String[] members = { "���", "�û���" };
	String[] orders = { "�������", "״̬" };

	static final int MANAGE_BOOK = 2;
	static final int MANAGE_BOOKCLASSIFY = 3;
	static final int MANAGE_MEMBER = 0;
	static final int MANAGE_ORDER = 1;
	private int manageType = 0;
	int selectedRow = -1;

	// main page
	private JLabel obsLabel, welcomLabel, exitLabel;
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;
	private JLabel cusInfoLabel, promotionLabel, bookLabel, bookclassifyLabel;
	private MPanel cusinfoPanel, orderPanel, promotionPanel;
	private MPanel bookPanel, catalogManagePanel;
	private JScrollPane bookJScrollPane, bookClassifyJScrollPane;

	// customer information
	private JLabel membername, membername1, memberphone, memberphone1,
			memberdate, memberdate1, membernameWarning;
	private MButton historyButton, searchButton, orderButton;
	private JScrollPane memberJScrollPane, memberPOPane;

	// order
	private JLabel orderLabel2;
	private MButton orderensureButton;
	String temp[] = { "��δ����", "�Ѹ���", "�ѷ���", "���׳ɹ�", "����ʧ��" };
	JComboBox<String> orderBox;
	// promotion
	private JLabel bondLabel, proLabel;
	private MButton ensureButton;
	private PromotionComBox promotionName;
	JScrollPane memPane;

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

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setVisible(true);
		getData();
		obsLabel = new JLabel("����ͼ������ϵͳ");
		obsLabel.setLocation(240, 0);
		obsLabel.setFont(new Font("����_gb2312", Font.BOLD, 30));
		obsLabel.setSize(300, 80);

		welcomLabel = new JLabel("��ӭ�������۾���");
		welcomLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(150, 40);
		welcomLabel.setLocation(560, 20);

		exitLabel = new JLabel("�˳�");
		exitLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);

		cusInfoLabel = new JLabel("�˿���Ϣ");
		cusInfoLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		cusInfoLabel.setSize(120, 40);
		cusInfoLabel.setLocation(10, 80);
		cusInfoLabel.addMouseListener(this);

		// orderLabel = new JLabel("��������");
		// orderLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		// orderLabel.setSize(120, 40);
		// orderLabel.setLocation(140, 80);
		// orderLabel.addMouseListener(this);

		promotionLabel = new JLabel("�Ż�ȯ����");
		promotionLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		promotionLabel.setSize(120, 40);
		promotionLabel.setLocation(140, 80);
		promotionLabel.addMouseListener(this);

		bookLabel = new JLabel("ͼ�����");
		bookLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		bookLabel.setSize(120, 40);
		bookLabel.setLocation(270, 80);
		bookLabel.addMouseListener(this);

		bookclassifyLabel = new JLabel("ͼ��������");
		bookclassifyLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		bookclassifyLabel.setSize(120, 40);
		bookclassifyLabel.setLocation(400, 80);
		bookclassifyLabel.addMouseListener(this);

		this.add(obsLabel);
		this.add(welcomLabel);
		this.add(exitLabel);
		this.add(cusInfoLabel);
		// this.add(orderLabel);
		this.add(promotionLabel);
		this.add(bookLabel);
		this.add(bookclassifyLabel);

		tableModel = new tableModel();
		resultTable = new JTable(tableModel);
		resultTable.setBorder(BorderFactory.createEtchedBorder());
		resultTable.setRowHeight(30); // ����ÿ�еĸ߶�Ϊ20
		resultTable.setRowHeight(0, 20); // ���õ�1�еĸ߶�Ϊ15
		resultTable.setRowMargin(5); // �����������е�Ԫ��ľ���
		resultTable.setRowSelectionAllowed(true);
		resultTable.setSelectionBackground(Color.LIGHT_GRAY); // ������ѡ���еı���ɫ
		resultTable.setSelectionForeground(Color.red); // ������ѡ���е�ǰ��ɫ
		resultTable.setGridColor(Color.black); // ���������ߵ���ɫ
		resultTable.setShowGrid(false); // �Ƿ���ʾ������
		resultTable.setShowHorizontalLines(false); // �Ƿ���ʾˮƽ��������
		resultTable.setShowVerticalLines(true); // �Ƿ���ʾ��ֱ��������
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

		resultPanel = new JScrollPane();
		resultPanel.setLocation(5, 140);
		resultPanel.setSize(780, 450);
		resultPanel.setVisible(true);
		resultPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
		memberPOPane.setLocation(0, 10);
		memberPOPane.setSize(200, 400);
		memberPOPane.setVisible(true);
		memberPOPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberPOPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		memberJScrollPane = new JScrollPane();
		memberJScrollPane.setLocation(350, 10);
		memberJScrollPane.setSize(400, 400);
		memberJScrollPane.setVisible(true);
		memberJScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberJScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		searchButton = new MButton("�鿴��Ϣ");
		searchButton.setSize(120, 30);
		searchButton.setFocusable(false);
		searchButton.setLocation(205, 10);
		searchButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		searchButton.addActionListener(this);

		historyButton = new MButton("�鿴�����¼");
		historyButton.setSize(140, 30);
		historyButton.setFocusable(false);
		historyButton.setLocation(205, 60);
		historyButton.setFont(new Font("����_gb2312", Font.PLAIN, 17));
		historyButton.addActionListener(this);

		orderButton = new MButton("�޸Ķ���״̬");
		orderButton.setSize(140, 30);
		orderButton.setFocusable(false);
		orderButton.setVisible(false);
		orderButton.setEnabled(false);
		orderButton.setLocation(205, 110);
		orderButton.setFont(new Font("����_gb2312", Font.PLAIN, 17));
		orderButton.addActionListener(this);

		cusinfoPanel.add(searchButton);
		cusinfoPanel.add(historyButton);
		cusinfoPanel.add(orderButton);
		cusinfoPanel.add(memberJScrollPane);
		cusinfoPanel.add(memberPOPane);

		validate();

	}

	public void orderView() {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		orderLabel2 = new JLabel("��ѡ���¶���״̬:");
		orderLabel2.setSize(200, 40);
		orderLabel2.setLocation(10, 10);
		orderLabel2.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		orderensureButton = new MButton("ȷ���޸�");
		orderensureButton.setSize(120, 40);
		orderensureButton.setVisible(true);
		orderensureButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		orderensureButton.setLocation(40, 120);
		orderensureButton.addActionListener(this);

		orderBox = new JComboBox<String>(temp);
		orderBox.setSelectedIndex(0);
		orderBox.setSize(160, 40);
		orderBox.setVisible(true);
		orderBox.setLocation(10, 60);

		panel.add(orderLabel2);
		panel.add(orderensureButton);
		panel.add(orderBox);
		memberJScrollPane.setViewportView(panel);
		panel.requestFocus();
	}

	public void memberInfoView(MemberPO memberPO) {
		MPanel panel = new MPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		membername = new JLabel("�û���:");
		membername.setSize(100, 40);
		membername.setLocation(30, 50);
		membername.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		membername1 = new JLabel(memberPO.getName());
		membername1.setSize(300, 40);
		membername1.setLocation(140, 50);
		membername1.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		memberphone = new JLabel("��ϵ�绰:");
		memberphone.setSize(100, 40);
		memberphone.setLocation(30, 100);
		memberphone.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		memberphone1 = new JLabel(memberPO.getPhone());
		memberphone1.setSize(300, 40);
		memberphone1.setLocation(140, 100);
		memberphone1.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		memberdate = new JLabel("��������:");
		memberdate.setSize(100, 40);
		memberdate.setLocation(30, 150);
		memberdate.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		String birth = sdf.format(memberPO.getBirth().getTime());
		memberdate1 = new JLabel(birth);
		memberdate1.setSize(300, 40);
		memberdate1.setLocation(140, 150);
		memberdate1.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		panel.add(membername);
		panel.add(membername1);
		panel.add(memberphone);
		panel.add(memberphone1);
		panel.add(memberdate);
		panel.add(memberdate1);
		memberJScrollPane.setViewportView(panel);
		panel.requestFocus();
	}

	public void initpromotionView() {
		promotionPanel = initPanel(promotionPanel);

		bondLabel = new JLabel("��ѡ���������:");
		bondLabel.setSize(150, 40);
		bondLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		bondLabel.setLocation(40, 15);

		proLabel = new JLabel("��������:");
		proLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		proLabel.setSize(100, 40);
		proLabel.setLocation(40, 65);

		ensureButton = new MButton("ȷ������");
		ensureButton.setSize(120, 40);
		ensureButton.setVisible(true);
		ensureButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureButton.setLocation(50, 300);
		ensureButton.addActionListener(this);

		promotionPanel.add(bondLabel);
		promotionPanel.add(ensureButton);
		promotionPanel.add(proLabel);
	}

	public void initbookView() {
		bookPanel = initPanel(bookPanel);

		addBookButton = new MButton("���ͼ��");
		addBookButton.setSize(120, 40);
		addBookButton.setFocusable(false);
		addBookButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		addBookButton.setLocation(10, 15);
		addBookButton.addActionListener(this);

		deleteBookButton = new MButton("ɾ��ͼ��");
		deleteBookButton.setSize(120, 40);
		deleteBookButton.setFocusable(false);
		deleteBookButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		deleteBookButton.setLocation(10, 95);
		deleteBookButton.addActionListener(this);

		modifyBookButton = new MButton("�޸�ͼ��");
		modifyBookButton.setSize(120, 40);
		modifyBookButton.setFocusable(false);
		modifyBookButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		modifyBookButton.setLocation(10, 175);
		modifyBookButton.addActionListener(this);

		listButton = new MButton("ͼ���б�");
		listButton.setFocusable(false);
		listButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		listButton.setSize(120, 40);
		listButton.setLocation(10, 255);
		listButton.addActionListener(this);

		bookJScrollPane = new JScrollPane(resultTable);
		bookJScrollPane.setLocation(160, 10);
		bookJScrollPane.setSize(600, 640);
		bookJScrollPane.setVisible(true);
		bookJScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bookJScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
		addBookPanel.setLocation(10, 10);

		ensureAddButton = new MButton("ȷ�����");
		ensureAddButton.setSize(120, 40);
		ensureAddButton.setFocusable(true);
		ensureAddButton.setVisible(true);
		ensureAddButton.setEnabled(true);
		ensureAddButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureAddButton.setLocation(400, 360);
		ensureAddButton.addActionListener(this);

		nameWarning = new JLabel("�������Ϸ�!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(340, 5);
		nameWarning.setVisible(false);

		ISBNWarning = new JLabel("ISBN���Ϸ�!");
		ISBNWarning.setSize(140, 50);
		ISBNWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		ISBNWarning.setForeground(Color.red);
		ISBNWarning.setLocation(340, 95);
		ISBNWarning.setVisible(false);

		authorWarning = new JLabel("���������Ϸ�!");
		authorWarning.setSize(140, 50);
		authorWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		authorWarning.setForeground(Color.red);
		authorWarning.setLocation(300, 145);
		authorWarning.setVisible(false);

		pressWarning = new JLabel("�����粻�Ϸ�!");
		pressWarning.setSize(140, 50);
		pressWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		pressWarning.setForeground(Color.red);
		pressWarning.setLocation(340, 185);
		pressWarning.setVisible(false);

		yearWarning = new JLabel("���ڲ��Ϸ�!");
		yearWarning.setSize(140, 50);
		yearWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		yearWarning.setForeground(Color.red);
		yearWarning.setLocation(340, 275);
		yearWarning.setVisible(false);

		priceWarning = new JLabel("�۸񲻺Ϸ�!");
		priceWarning.setSize(140, 50);
		priceWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		priceWarning.setForeground(Color.red);
		priceWarning.setLocation(340, 50);
		priceWarning.setVisible(false);

		spepriceWarning = new JLabel("�ؼ۲��Ϸ�!");
		spepriceWarning.setSize(140, 50);
		spepriceWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
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
		addBookPanel.setLocation(10, 10);

		ensureModButton = new MButton("ȷ���޸�");
		ensureModButton.setSize(120, 40);
		ensureModButton.setFocusable(true);
		ensureModButton.setVisible(true);
		ensureModButton.setEnabled(true);
		ensureModButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureModButton.setLocation(400, 360);
		ensureModButton.addActionListener(this);

		nameWarning = new JLabel("�������Ϸ�!");
		nameWarning.setSize(140, 50);
		nameWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		nameWarning.setForeground(Color.red);
		nameWarning.setLocation(340, 5);
		nameWarning.setVisible(false);

		ISBNWarning = new JLabel("ISBN���Ϸ�!");
		ISBNWarning.setSize(140, 50);
		ISBNWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		ISBNWarning.setForeground(Color.red);
		ISBNWarning.setLocation(340, 95);
		ISBNWarning.setVisible(false);

		authorWarning = new JLabel("���������Ϸ�!");
		authorWarning.setSize(140, 50);
		authorWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		authorWarning.setForeground(Color.red);
		authorWarning.setLocation(340, 140);
		authorWarning.setVisible(false);

		pressWarning = new JLabel("�����粻�Ϸ�!");
		pressWarning.setSize(140, 50);
		pressWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		pressWarning.setForeground(Color.red);
		pressWarning.setLocation(340, 185);
		pressWarning.setVisible(false);

		priceWarning = new JLabel("�۸񲻺Ϸ�!");
		priceWarning.setSize(140, 50);
		priceWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		priceWarning.setForeground(Color.red);
		priceWarning.setLocation(340, 50);
		priceWarning.setVisible(false);

		spepriceWarning = new JLabel("�ؼ۲��Ϸ�!");
		spepriceWarning.setSize(140, 50);
		spepriceWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		spepriceWarning.setForeground(Color.red);
		spepriceWarning.setLocation(340, 230);
		spepriceWarning.setVisible(false);

		yearWarning = new JLabel("���ڲ��Ϸ�!");
		yearWarning.setSize(140, 50);
		yearWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
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

		addClassifyButton = new MButton("������");
		addClassifyButton.setSize(120, 40);
		addClassifyButton.setFocusable(false);
		addClassifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		addClassifyButton.setLocation(10, 15);
		addClassifyButton.addActionListener(this);

		delClassifyButton = new MButton("ɾ�����");
		delClassifyButton.setSize(120, 40);
		delClassifyButton.setFocusable(false);
		delClassifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		delClassifyButton.setLocation(10, 95);
		delClassifyButton.addActionListener(this);

		modClassifyButton = new MButton("�޸����");
		modClassifyButton.setSize(120, 40);
		modClassifyButton.setFocusable(false);
		modClassifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		modClassifyButton.setLocation(10, 175);
		modClassifyButton.addActionListener(this);

		listButton2 = new MButton("����б�");
		listButton2.setFocusable(false);
		listButton2.setFont(new Font("����_gb2312", Font.PLAIN, 20));
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

		ensureAddCatalogButton = new MButton("���");
		ensureAddCatalogButton.setSize(80, 40);
		ensureAddCatalogButton.setFocusable(false);
		ensureAddCatalogButton.setEnabled(true);
		ensureAddCatalogButton.setVisible(true);
		ensureAddCatalogButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureAddCatalogButton.setLocation(100, 100);
		ensureAddCatalogButton.addActionListener(this);

		catnameWarning = new JLabel("��𲻺Ϸ�!");
		catnameWarning.setSize(140, 50);
		catnameWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
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

		ensureModCatalogButton = new MButton("�޸�");
		ensureModCatalogButton.setSize(80, 40);
		ensureModCatalogButton.setFocusable(false);
		ensureModCatalogButton.setEnabled(true);
		ensureModCatalogButton.setVisible(true);
		ensureModCatalogButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureModCatalogButton.setLocation(100, 100);
		ensureModCatalogButton.addActionListener(this);

		catnameWarning = new JLabel("��𲻺Ϸ�!");
		catnameWarning.setSize(140, 50);
		catnameWarning.setFont(new Font("����_gb2312", Font.PLAIN, 18));
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
					ImageDialog.showNOImage(this, "����ͼ��");
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
					ImageDialog.showNOImage(this, "����ͼ�����");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (manageType == MANAGE_MEMBER) {
			resultmembers.clear();
			resultpromotions.clear();
			try {
				ResultMessage exist = Agent.userService.getMembers();
				if (exist.isInvokeSuccess()) {
					resultmembers = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "�����û�");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				ResultMessage exist = Agent.promotionService.getPormotionList();
				if (exist.isInvokeSuccess()) {
					resultpromotions = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "���޴�������");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (manageType == MANAGE_ORDER) {
			resultorders.clear();
			try {
				ResultMessage exist = Agent.memberService
						.purchaseQuery(resultmembers.get(selectedRow).getID());
				if (exist.isInvokeSuccess()) {
					resultBooks = exist.getResultSet();
				} else {
					ImageDialog.showNOImage(this, "����ͼ��");
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

			resultPanel.setViewportView(cusinfoPanel);
			cusinfoPanel.requestFocus();
		} else if (e.getSource() == promotionLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initpromotionView();
			manageType = MANAGE_MEMBER;
			getData();
			if (resultpromotions.size() > 0) {
				promotionName = new PromotionComBox();
				for (int i = 0; i < resultpromotions.size(); i++) {
					promotionName.addItem(resultpromotions.get(i));
				}
				promotionName.setSelectedIndex(0);
				promotionName.setFocusable(false);
				promotionName.setFont(new Font("����_gb2312", Font.PLAIN, 20));
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
			resultPanel.setViewportView(promotionPanel);
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
			resultPanel.setViewportView(bookPanel);
			bookPanel.requestFocus();
		} else if (e.getSource() == bookclassifyLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initbookClassifyView();
			manageType = MANAGE_BOOKCLASSIFY;
			getData();
			tableModel = new tableModel();
			resultTable.setModel(tableModel);
			bookClassifyJScrollPane.setViewportView(resultTable);
			resultPanel.setViewportView(catalogManagePanel);
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
				JOptionPane.showMessageDialog(this, "����ѡ���û�!");
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
				JOptionPane.showMessageDialog(this, "����ѡ���û�!");
			} else {

				try {
					ResultMessage resultMessage = Agent.memberService
							.purchaseQuery(resultmembers.get(selectedRow)
									.getID());
					if (resultMessage.isInvokeSuccess()) {
						resultorders = resultMessage.getResultSet();
						PurchaseRecordPanel panel = new PurchaseRecordPanel(
								userUIController.getMainFrame()
										.getMemberUIController(), resultorders);
						memberJScrollPane.setViewportView(panel);
						panel.requestFocus();
						orderButton.setVisible(true);
						orderButton.setEnabled(true);
						if (resultorders.size() == 0) {
							JOptionPane.showMessageDialog(this, "���û��޹����¼");
						}
					} else {
						ImageDialog.showNOImage(this, "����ʧ��");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == orderButton) {
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "����ѡ�񶩵�!");
			} else {
				orderView();
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
					ImageDialog.showYesImage(this, "�޸ĳɹ�");
				} else {
					ImageDialog.showNOImage(this, "�޸�ʧ��");
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
					ImageDialog.showYesImage(this, "���ͳɹ�");
				} else {
					ImageDialog.showNOImage(this, "����ʧ��");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == listButton) {

			getData();
			tableModel.fireTableDataChanged();
			bookJScrollPane.setViewportView(resultTable);
			resultPanel.setViewportView(bookPanel);
			bookPanel.requestFocus();

		} else if (e.getSource() == listButton2) {

			getData();
			tableModel.fireTableDataChanged();
			bookClassifyJScrollPane.setViewportView(resultTable);
			resultPanel.setViewportView(catalogManagePanel);
			catalogManagePanel.requestFocus();

		} else if (e.getSource() == addBookButton) {

			initbookAddView();
			bookJScrollPane.setViewportView(addBookPanel);
			resultPanel.setViewportView(bookPanel);
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
				resultPanel.setViewportView(bookPanel);
				bookPanel.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "����ѡ��Ҫ�޸ĵ�ͼ�飡");
			}

		} else if (e.getSource() == addClassifyButton) {

			initbookClaAddView();
			bookClassifyJScrollPane.setViewportView(catalogAddPanel);
			resultPanel.setViewportView(catalogManagePanel);
			catalogManagePanel.requestFocus();

		} else if (e.getSource() == modClassifyButton) {
			if (selectedRow != -1) {
				initbookClaModView();
				bookClassifyJScrollPane.setViewportView(catalogAddPanel);
				resultPanel.setViewportView(catalogManagePanel);
				catalogManagePanel.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "����ѡ��Ҫ�޸ĵ�ͼ�����");
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
					ImageDialog.showYesImage(this, "��ӳɹ�");
					addBookPanel.clear();
				} else {
					ImageDialog.showNOImage(this, "���ʧ��");
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
					ImageDialog.showYesImage(this, "�޸ĳɹ�");
					addBookPanel.clear();
				} else {
					System.err.println(resultMessage.getPostScript());
					ImageDialog.showNOImage(this, "�޸�ʧ��");
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
					ImageDialog.showYesImage(this, "��ӳɹ�");
				} else {
					ImageDialog.showNOImage(this, "���ʧ��");
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
					ImageDialog.showYesImage(this, "�޸ĳɹ�");
				} else {
					ImageDialog.showNOImage(this, "�޸�ʧ��");
				}
			} catch (RemoteException re) {
				re.printStackTrace();
			}
			catalogAddPanel.clear();

		} else if (e.getSource() == deleteBookButton) {
			if (selectedRow != -1) {
				if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ����") == JOptionPane.YES_OPTION) {
					ResultMessage exist;
					try {
						exist = Agent.bookService.deleteBook(resultBooks.get(
								selectedRow).getISBN());
						if (exist.isInvokeSuccess()) {
							ImageDialog.showYesImage(this, "ɾ���ɹ�");
						} else {
							ImageDialog.showNOImage(this, "ɾ��ʧ��");
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}

			} else {
				JOptionPane.showMessageDialog(this, "����ѡ��Ҫɾ����ͼ��");
			}
			getData();
			tableModel.fireTableDataChanged();
		} else if (e.getSource() == delClassifyButton) {
			if (selectedRow != -1) {
				if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ����") == JOptionPane.YES_OPTION) {

					ResultMessage exist;
					try {
						exist = Agent.bookService
								.deleteDirectory(resultBookClassifys.get(
										selectedRow).getID());
						if (exist.isInvokeSuccess()) {
							ImageDialog.showYesImage(this, "ɾ���ɹ�");
						} else {
							ImageDialog.showNOImage(this, "ɾ��ʧ��");
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "����ѡ��Ҫɾ����ͼ�����");
			}
			getData();
			tableModel.fireTableDataChanged();
		}

	}

	private MPanel initPanel(MPanel panel) {
		panel = new MPanel();
		panel.setSize(780, 450);
		panel.setLocation(5, 140);
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
			case MANAGE_ORDER:
				return resultorders.size();
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
			case MANAGE_ORDER:
				return orders.length;
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
			case MANAGE_ORDER:
				switch (columnIndex) {
				case 0:
					return resultorders.get(rowIndex).getOrderID();
				case 1:
					return resultorders.get(rowIndex).getState();

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
			case MANAGE_ORDER:
				return orders[column];
			default:
				break;
			}
			return "";
		}
	}
}
