package User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Book.BookPO;
import Book.BookPanel;
import Book.DeleteBookPanel;
import Book.ModifyBookPanel;
import ClientRunner.Agent;
import User.AdminPanel.tableModel;

@SuppressWarnings("serial")
public class SalesManagerPanel extends JPanel implements MouseListener,
		ActionListener {
	
	private UserUIController userUIController;
	
	// main page
	private JLabel obsLabel, welcomLabel, exitLabel;
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;
	private JLabel cusInfoLabel, orderLabel, promotionLabel, bookLabel,
			bookclassifyLabel;
	private JPanel cusinfoPanel, orderPanel, promotionPanel, bookPanel,
			bookclasifyPanel;
	
	// customer information
	private JLabel inputLabel;
	private JTextField nameField;
	private JButton inputButton;

	// order
	private JLabel orderNumber;
	private JTextField orderNumberField;
	private JButton queryButton;

	// promotion
	private JLabel bondLabel;
	private JLabel equiBondLabel;
	private JLabel discountBondLabel;
	private JTextField equiBondField;
	private JTextField discountBondField;
	private JRadioButton equiBond;
	private JRadioButton discountBond;
	private JLabel destCusNameLabel;
	private JTextField destCusNameField;
	private boolean isEquiBond = false;
	private boolean isDiscountBond = false;
	private JButton ensureButton;

	//book management
	private JButton addBookButton;
	private JButton deleteBookButton;
	private JButton modifyBookButton;
	private BookPanel addBookPanel = null;
	private DeleteBookPanel deleteBookPanel = null;
	private ModifyBookPanel modifyBookPanel = null;
	private JButton ensureAddButton;
	private JButton ensureDelButton;
	private JButton ensureModButton;
	
	// book classify
	private JButton addClassifyButton;
	private JButton delClassifyButton;
	private JButton modClassifyButton;
	

	public SalesManagerPanel(UserUIController userUIController) {
		this.userUIController = userUIController;
	}

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

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

		orderLabel = new JLabel("��������");
		orderLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		orderLabel.setSize(120, 40);
		orderLabel.setLocation(140, 80);
		orderLabel.addMouseListener(this);

		promotionLabel = new JLabel("�Ż�ȯ����");
		promotionLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		promotionLabel.setSize(120, 40);
		promotionLabel.setLocation(270, 80);
		promotionLabel.addMouseListener(this);

		bookLabel = new JLabel("ͼ�����");
		bookLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		bookLabel.setSize(120, 40);
		bookLabel.setLocation(400, 80);
		bookLabel.addMouseListener(this);

		bookclassifyLabel = new JLabel("ͼ��������");
		bookclassifyLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		bookclassifyLabel.setSize(120, 40);
		bookclassifyLabel.setLocation(530, 80);
		bookclassifyLabel.addMouseListener(this);

		this.add(obsLabel);
		this.add(welcomLabel);
		this.add(exitLabel);
		this.add(cusInfoLabel);
		this.add(orderLabel);
		this.add(promotionLabel);
		this.add(bookLabel);
		this.add(bookclassifyLabel);

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

		inputLabel = new JLabel("�������û���:");
		inputLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		inputLabel.setSize(150, 40);
		inputLabel.setLocation(40, 15);

		nameField = new JTextField(20);
		nameField.setSize(160, 40);
		nameField.setLocation(180, 15);
		nameField.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		inputButton = new JButton("ȷ��");
		inputButton.setSize(80, 40);
		inputButton.setFocusable(false);
		inputButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		inputButton.setLocation(360, 15);
		
		inputButton.addActionListener(this);

		cusinfoPanel.add(inputLabel);
		cusinfoPanel.add(nameField);
		cusinfoPanel.add(inputButton);

	}

	public void initorderView() {
		orderPanel = initPanel(orderPanel);
		
		orderNumber = new JLabel("��������Ҫ��ѯ�Ķ�����:");
		orderNumber.setSize(230, 40);
		orderNumber.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		orderNumber.setLocation(40, 15);
		
		orderNumberField = new JTextField();
		orderNumberField.setSize(180, 40);
		orderNumberField.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		orderNumberField.setLocation(275, 15);
		
		queryButton = new JButton("����");
		queryButton.setSize(80, 40);
		queryButton.setFocusable(false);
		queryButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		queryButton.setLocation(460, 15);
		
		queryButton.addActionListener(this);
		
		orderPanel.add(orderNumber);
		orderPanel.add(orderNumberField);
		orderPanel.add(queryButton);
	}

	public void initpromotionView() {
		promotionPanel = initPanel(promotionPanel);

		bondLabel = new JLabel("��ѡ������Ҫ���͵��Ż�ȯ������:");
		bondLabel.setSize(320, 40);
		bondLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		bondLabel.setLocation(40, 15);
		
		equiBond = new JRadioButton("�ȼ�ȯ");
		equiBond.setSize(100, 40);
		equiBond.setFocusable(false);
		equiBond.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		equiBond.setLocation(90, 55);
		
		discountBond = new JRadioButton("����ȯ");
		discountBond.setSize(100, 40);
		discountBond.setFocusable(false);
		discountBond.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		discountBond.setLocation(220, 55);
		
		equiBondLabel = new JLabel("��������Ҫ���͵ĵȼ�ȯ����ֵ:");
		equiBondLabel.setSize(300, 30);
		equiBondLabel.setVisible(false);
		equiBondLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		equiBondLabel.setLocation(40, 100);
		
		equiBondField = new JTextField();
		equiBondField.setSize(130, 30);
		equiBondField.setVisible(false);
		equiBondField.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		equiBondField.setLocation(345, 100);
		
		discountBondLabel = new JLabel("��������Ҫ���͵Ĵ���ȯ������:");
		discountBondLabel.setSize(300, 30);
		discountBondLabel.setVisible(false);
		discountBondLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		discountBondLabel.setLocation(40, 100);
		
		discountBondField = new JTextField();
		discountBondField.setSize(130, 30);
		discountBondField.setVisible(false);
		discountBondField.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		discountBondField.setLocation(345, 100);
		
		destCusNameLabel = new JLabel("��������Ҫ���͵Ĺ˿͵��û���:");
		destCusNameLabel.setSize(300, 30);
		destCusNameLabel.setVisible(false);
		destCusNameLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		destCusNameLabel.setLocation(40, 140);
		
		destCusNameField = new JTextField();
		destCusNameField.setSize(130, 30);
		destCusNameField.setVisible(false);
		destCusNameField.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		destCusNameField.setLocation(345, 140);
		
		ensureButton = new JButton("ȷ������");
		ensureButton.setSize(120, 40);
		ensureButton.setVisible(false);
		ensureButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureButton.setLocation(410, 180);
		
		ensureButton.addActionListener(this);
		
		equiBond.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isEquiBond = true;
				isDiscountBond = false;

				equiBondLabel.setVisible(isEquiBond);
				equiBondField.setText("");
				equiBondField.setVisible(isEquiBond);
				
				discountBondLabel.setVisible(isDiscountBond);
				discountBondField.setText("");
				discountBondField.setVisible(isDiscountBond);
				
				destCusNameLabel.setVisible(isEquiBond || isDiscountBond);
				destCusNameField.setText("");
				destCusNameField.setVisible(isEquiBond || isDiscountBond);

				ensureButton.setVisible(isEquiBond || isDiscountBond);
			}
		});
		
		discountBond.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isEquiBond = false;
				isDiscountBond = true;
				
				equiBondLabel.setVisible(isEquiBond);
				equiBondField.setText("");
				equiBondField.setVisible(isEquiBond);
				
				discountBondLabel.setVisible(isDiscountBond);
				discountBondField.setText("");
				discountBondField.setVisible(isDiscountBond);
				
				destCusNameLabel.setVisible(isEquiBond || isDiscountBond);
				destCusNameField.setText("");
				destCusNameField.setVisible(isEquiBond || isDiscountBond);
				
				ensureButton.setVisible(isEquiBond || isDiscountBond);
			}
		});
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(equiBond);
		group.add(discountBond);
		
		promotionPanel.add(bondLabel);
		promotionPanel.add(equiBond);
		promotionPanel.add(discountBond);
		promotionPanel.add(equiBondLabel);
		promotionPanel.add(equiBondField);
		promotionPanel.add(discountBondLabel);
		promotionPanel.add(discountBondField);
		promotionPanel.add(destCusNameLabel);
		promotionPanel.add(destCusNameField);
		promotionPanel.add(ensureButton);
	}

	public void initbookView() {
		bookPanel = initPanel(bookPanel);
		
		addBookButton = new JButton("���ͼ��");
		addBookButton.setSize(130, 40);
		addBookButton.setFocusable(false);
		addBookButton.setFont(new Font("����_gb2312", Font.PLAIN, 23));
		addBookButton.setLocation(10, 15);
		
		addBookPanel = new BookPanel(userUIController.getMainFrame().getBookUIController());
		addBookPanel.createNewBookView();
		addBookPanel.enableModification();
		addBookPanel.setVisible(false);
		addBookPanel.setLocation(220, 10);
		
		ensureAddButton = new JButton("ȷ�����");
		ensureAddButton.setSize(120, 40);
		ensureAddButton.setFocusable(false);
		ensureAddButton.setVisible(false);
		ensureAddButton.setEnabled(false);
		ensureAddButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureAddButton.setLocation(600, 355);
		ensureAddButton.addActionListener(this);
		
		deleteBookButton = new JButton("ɾ��ͼ��");
		deleteBookButton.setSize(130, 40);
		deleteBookButton.setFocusable(false);
		deleteBookButton.setFont(new Font("����_gb2312", Font.PLAIN, 23));
		deleteBookButton.setLocation(10, 95);
		
		deleteBookPanel = new DeleteBookPanel();
		deleteBookPanel.setVisible(false);
		deleteBookPanel.setLocation(220, 10);
		
		ensureDelButton = new JButton("ȷ��ɾ��");
		ensureDelButton.setSize(120, 40);
		ensureDelButton.setFocusable(false);
		ensureDelButton.setVisible(false);
		ensureDelButton.setEnabled(false);
		ensureDelButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureDelButton.setLocation(600, 355);
		ensureDelButton.addActionListener(this);
		
		modifyBookButton = new JButton("�޸�ͼ��");
		modifyBookButton.setSize(130, 40);
		modifyBookButton.setFocusable(false);
		modifyBookButton.setFont(new Font("����_gb2312", Font.PLAIN, 23));
		modifyBookButton.setLocation(10, 175);
		
		modifyBookPanel = new ModifyBookPanel();
		modifyBookPanel.setVisible(false);
		modifyBookPanel.setLocation(220, 10);
		
		ensureModButton = new JButton("ȷ���޸�");
		ensureModButton.setSize(120, 40);
		ensureModButton.setFocusable(false);
		ensureModButton.setVisible(false);
		ensureModButton.setEnabled(false);
		ensureModButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensureModButton.setLocation(600, 355);
		ensureModButton.addActionListener(this);
		
		addBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteBookPanel.setVisible(false);
				ensureDelButton.setEnabled(false);
				ensureDelButton.setVisible(false);
				
				modifyBookPanel.setVisible(false);
				ensureModButton.setEnabled(false);
				ensureModButton.setVisible(false);
				
				addBookPanel.clear();
				addBookPanel.setVisible(true);
				ensureAddButton.setEnabled(true);
				ensureAddButton.setVisible(true);
			}
		});
		
		deleteBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBookPanel.setVisible(false);
				ensureAddButton.setEnabled(false);
				ensureAddButton.setVisible(false);
				
				modifyBookPanel.setVisible(false);
				ensureModButton.setEnabled(false);
				ensureModButton.setEnabled(false);
				
				deleteBookPanel.clear();
				deleteBookPanel.setVisible(true);
				ensureDelButton.setEnabled(true);
				ensureDelButton.setVisible(true);
			}
		});
		
		modifyBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addBookPanel.setVisible(false);
				ensureAddButton.setEnabled(false);
				ensureAddButton.setVisible(false);
				
				deleteBookPanel.setVisible(false);
				ensureDelButton.setEnabled(false);
				ensureDelButton.setVisible(false);
				
				modifyBookPanel.clear();
				modifyBookPanel.setVisible(true);
				ensureModButton.setEnabled(true);
				ensureModButton.setVisible(true);
			}
		});
		
		bookPanel.add(addBookButton);
		bookPanel.add(deleteBookButton);
		bookPanel.add(modifyBookButton);
		bookPanel.add(addBookPanel);
		bookPanel.add(deleteBookPanel);
		bookPanel.add(modifyBookPanel);
		bookPanel.add(ensureAddButton);
		bookPanel.add(ensureDelButton);
		bookPanel.add(ensureModButton);
	}

	public void initbookClassifyView() {
		bookclasifyPanel = initPanel(bookclasifyPanel);
		
		addClassifyButton = new JButton("������");
		addClassifyButton.setSize(130, 40);
		addClassifyButton.setFocusable(false);
		addClassifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 23));
		addClassifyButton.setLocation(10, 15);
		
		delClassifyButton = new JButton("ɾ�����");
		delClassifyButton.setSize(130, 40);
		delClassifyButton.setFocusable(false);
		delClassifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 23));
		delClassifyButton.setLocation(10, 95);
		
		modClassifyButton = new JButton("�޸����");
		modClassifyButton.setSize(130, 40);
		modClassifyButton.setFocusable(false);
		modClassifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 23));
		modClassifyButton.setLocation(10, 175);
		
		bookclasifyPanel.add(addClassifyButton);
		bookclasifyPanel.add(delClassifyButton);
		bookclasifyPanel.add(modClassifyButton);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitLabel && e.getButton() == MouseEvent.BUTTON1) {
			userUIController.setMainPageView();
		} else if (e.getSource() == cusInfoLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initcusinfoView();
			resultPanel.setViewportView(cusinfoPanel);
			cusinfoPanel.requestFocus();
		} else if (e.getSource() == orderLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initorderView();
			resultPanel.setViewportView(orderPanel);
			orderPanel.requestFocus();
		} else if (e.getSource() == promotionLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initpromotionView();
			resultPanel.setViewportView(promotionPanel);
			promotionPanel.requestFocus();
		} else if (e.getSource() == bookLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initbookView();
			resultPanel.setViewportView(bookPanel);
			bookPanel.requestFocus();
		} else if (e.getSource() == bookclassifyLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initbookClassifyView();
			resultPanel.setViewportView(bookclasifyPanel);
			bookclasifyPanel.requestFocus();
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
		if(e.getSource() == inputButton){
			
		}else if(e.getSource() == queryButton){
				
		}else if(e.getSource() == ensureButton){
			
		}else if(e.getSource() == ensureAddButton){
//			Agent.bookService.addBook(new BookPO(addBookPanel.getBookName(), addBookPanel.getisbn(), addBookPanel.getAuthor(), addBookPanel.getPress(), addBookPanel.getDescription(), , publishDate, price, specialPrice))
		}else if(e.getSource() == ensureDelButton){
			
		}else if(e.getSource() == ensureDelButton){
			
		}
	}

	private JPanel initPanel(JPanel panel) {
		panel = new JPanel();
		panel.setSize(780, 450);
		panel.setLocation(5, 140);
		panel.setLayout(null);
		panel.setVisible(true);
		return panel;
	}
}
