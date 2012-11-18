package User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import User.AdminPanel.tableModel;

public class SalesManagerPanel extends JPanel implements MouseListener,
		ActionListener {
	private UserUIController userUIController;
	// mainpage
	private JLabel obsLabel, welcomLabel, exitLabel;
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;
	private JLabel cusInfoLabel, orderLabel, promotionLabel, bookLabel,
			bookclassifyLabel;
	private JPanel cusinfoPanel, orderPanel, promotionPanel, bookPanel,
			bookclasifyPanel;
	// cusinfo
	private JLabel inputLabel;
	private JTextField idField;
	private JButton inputButton;

	// order

	// promotion

	// book

	// bookclassify

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
		welcomLabel = new JLabel("��ӭ�����۾���");
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

		// addProButton = new JButton("�����Ż�ȯ");
		// addProButton.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		// addProButton.setSize(120, 40);
		// addProButton.setLocation(40, 150);
		// addProButton.addActionListener(this);
		// deliverButton = new JButton("�����Ż�ȯ");
		// deliverButton.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		// deliverButton.setSize(120, 40);
		// deliverButton.setLocation(40, 200);
		// deliverButton.addActionListener(this);

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
		validate();
	}

	public void initcusinfoView() {
		cusinfoPanel = new JPanel();
		cusinfoPanel.setSize(780, 450);
		cusinfoPanel.setLocation(5, 140);
		cusinfoPanel.setLayout(null);
		cusinfoPanel.setVisible(true);

		inputLabel = new JLabel("�������û�ID:");
		inputLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		inputLabel.setSize(120, 40);
		inputLabel.setLocation(40, 10);
		idField = new JTextField(20);
		idField.setSize(150, 30);
		idField.setLocation(170, 15);
		inputButton = new JButton("ȷ��");
		inputButton.setSize(80, 30);
		inputButton.setLocation(330, 15);
		inputButton.addActionListener(this);

		cusinfoPanel.add(inputLabel);
		cusinfoPanel.add(idField);
		cusinfoPanel.add(inputButton);

	}

	public void initorderView() {

	}

	public void initpromotionView() {

	}

	public void initbookView() {

	}

	public void initbookClassifyView() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitLabel && e.getButton() == MouseEvent.BUTTON1) {
			userUIController.setMainPage();
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
		// TODO Auto-generated method stub

	}
}
