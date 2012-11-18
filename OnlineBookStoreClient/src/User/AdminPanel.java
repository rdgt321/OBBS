package User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class AdminPanel extends JPanel implements ActionListener, MouseListener {
	private UserUIController userUIController;
	static final int MANAGE_CUS = 0;
	static final int MANAGE_SALESMANAGER = 1;
	static final int MANAGE_MANAGER = 2;
	private int manageType;

	private JLabel obsLabel, welcomLabel, exitLabel;
	// mainpage
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;

	private JLabel customerLabel, salesmanLabel, manLabel;
	private JButton addButton, modifyButton, delButton, listButton;
	private JLabel idLabel, passwordLabel;
	private JTextField idField;
	private JPasswordField passwordField;

	// customerManage
	private JPanel cusaddPanel, cusmodifyPanel, cusdelPanel;
	private JButton addensureButton1, modifyensureButton1, delensureButton1,
			cusreturnButton;

	// salesmanager
	private JPanel salesaddPanel, salesmodifyPanel, salesdelPanel;
	private JButton addensureButton2, modifyensureButton2, delensureButton2,
			salesreturnButton;

	// manager
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

		obsLabel = new JLabel("����ͼ������ϵͳ");
		obsLabel.setLocation(240, 0);
		obsLabel.setFont(new Font("����_gb2312", Font.BOLD, 30));
		obsLabel.setSize(300, 80);
		welcomLabel = new JLabel("��ӭ��admin");
		welcomLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(140, 40);
		welcomLabel.setLocation(560, 20);
		exitLabel = new JLabel("�˳�");
		exitLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);

		customerLabel = new JLabel("����˿�");
		customerLabel.setLocation(20, 80);
		customerLabel.setFont(new Font("����_gb2312", Font.PLAIN, 22));
		customerLabel.setSize(120, 50);
		salesmanLabel = new JLabel("�������۾���");
		salesmanLabel.setLocation(150, 80);
		salesmanLabel.setFont(new Font("����_gb2312", Font.PLAIN, 22));
		salesmanLabel.setSize(180, 50);
		manLabel = new JLabel("�����ܾ���");
		manLabel.setLocation(340, 80);
		manLabel.setFont(new Font("����_gb2312", Font.PLAIN, 22));
		manLabel.setSize(180, 50);
		customerLabel.addMouseListener(this);
		salesmanLabel.addMouseListener(this);
		manLabel.addMouseListener(this);

		addButton = new JButton("����û�");
		addButton.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		addButton.setSize(120, 40);
		addButton.setLocation(40, 150);
		addButton.addActionListener(this);
		modifyButton = new JButton("�޸��û�");
		modifyButton.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		modifyButton.setSize(120, 40);
		modifyButton.setLocation(40, 200);
		modifyButton.addActionListener(this);
		delButton = new JButton("ɾ���û�");
		delButton.setFont(new Font("����_gb2312", Font.PLAIN, 18));
		delButton.setSize(120, 40);
		delButton.setLocation(40, 250);
		delButton.addActionListener(this);
		listButton = new JButton("�û��б�");
		listButton.setFont(new Font("����_gb2312", Font.PLAIN, 18));
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

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		passwordLabel = new JLabel("����:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);

		addensureButton1 = new JButton("���");
		addensureButton1.setSize(100, 40);
		addensureButton1.setLocation(200, 180);
		addensureButton1.addActionListener(this);
		cusreturnButton = new JButton("����");
		cusreturnButton.setSize(100, 40);
		cusreturnButton.setLocation(320, 180);
		cusreturnButton.addActionListener(this);

		cusaddPanel.add(idLabel);
		cusaddPanel.add(passwordLabel);
		cusaddPanel.add(idField);
		cusaddPanel.add(passwordField);
		cusaddPanel.add(addensureButton1);
		cusaddPanel.add(cusreturnButton);
	}

	private void initcusModifyView() {
		cusmodifyPanel = new JPanel();
		cusmodifyPanel.setSize(500, 450);
		cusmodifyPanel.setLocation(100, 100);
		cusmodifyPanel.setLayout(null);
		cusmodifyPanel.setVisible(true);

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		passwordLabel = new JLabel("����:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);

		modifyensureButton1 = new JButton("�޸�");
		modifyensureButton1.setSize(100, 40);
		modifyensureButton1.setLocation(200, 180);
		modifyensureButton1.addActionListener(this);
		cusreturnButton = new JButton("����");
		cusreturnButton.setSize(100, 40);
		cusreturnButton.setLocation(320, 180);
		cusreturnButton.addActionListener(this);

		cusmodifyPanel.add(idLabel);
		cusmodifyPanel.add(passwordLabel);
		cusmodifyPanel.add(idField);
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

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		delensureButton1 = new JButton("ɾ��");
		delensureButton1.setSize(100, 40);
		delensureButton1.setLocation(200, 180);
		delensureButton1.addActionListener(this);
		cusreturnButton = new JButton("����");
		cusreturnButton.setSize(100, 40);
		cusreturnButton.setLocation(320, 180);
		cusreturnButton.addActionListener(this);

		cusdelPanel.add(idLabel);
		cusdelPanel.add(idField);
		cusdelPanel.add(delensureButton1);
		cusdelPanel.add(cusreturnButton);
	}

	private void initsalesAddView() {
		salesaddPanel = new JPanel();
		salesaddPanel.setSize(500, 450);
		salesaddPanel.setLocation(100, 100);
		salesaddPanel.setLayout(null);
		salesaddPanel.setVisible(true);

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		passwordLabel = new JLabel("����:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		addensureButton2 = new JButton("���");
		addensureButton2.setSize(100, 40);
		addensureButton2.setLocation(200, 180);
		addensureButton2.addActionListener(this);
		salesreturnButton = new JButton("����");
		salesreturnButton.setSize(100, 40);
		salesreturnButton.setLocation(320, 180);
		salesreturnButton.addActionListener(this);

		salesaddPanel.add(idLabel);
		salesaddPanel.add(passwordLabel);
		salesaddPanel.add(idField);
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

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		passwordLabel = new JLabel("����:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		modifyensureButton2 = new JButton("�޸�");
		modifyensureButton2.setSize(100, 40);
		modifyensureButton2.setLocation(200, 180);
		modifyensureButton2.addActionListener(this);
		salesreturnButton = new JButton("����");
		salesreturnButton.setSize(100, 40);
		salesreturnButton.setLocation(320, 180);
		salesreturnButton.addActionListener(this);

		salesmodifyPanel.add(idLabel);
		salesmodifyPanel.add(passwordLabel);
		salesmodifyPanel.add(idField);
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

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		delensureButton2 = new JButton("ɾ��");
		delensureButton2.setSize(100, 40);
		delensureButton2.setLocation(200, 180);
		delensureButton2.addActionListener(this);
		salesreturnButton = new JButton("����");
		salesreturnButton.setSize(100, 40);
		salesreturnButton.setLocation(320, 180);
		salesreturnButton.addActionListener(this);

		salesdelPanel.add(idLabel);
		salesdelPanel.add(idField);
		salesdelPanel.add(delensureButton2);
		salesdelPanel.add(salesreturnButton);

	}

	private void initmanAddView() {
		manaddPanel = new JPanel();
		manaddPanel.setSize(500, 450);
		manaddPanel.setLocation(100, 100);
		manaddPanel.setLayout(null);
		manaddPanel.setVisible(true);

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		passwordLabel = new JLabel("����:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		addensureButton3 = new JButton("���");
		addensureButton3.setSize(100, 40);
		addensureButton3.setLocation(200, 180);
		addensureButton3.addActionListener(this);
		manreturnButton = new JButton("����");
		manreturnButton.setSize(100, 40);
		manreturnButton.setLocation(320, 180);
		manreturnButton.addActionListener(this);

		manaddPanel.add(idLabel);
		manaddPanel.add(passwordLabel);
		manaddPanel.add(idField);
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

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		passwordLabel = new JLabel("����:");
		passwordLabel.setSize(100, 40);
		passwordLabel.setLocation(150, 130);
		passwordLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		passwordField = new JPasswordField(30);
		passwordField.setSize(150, 30);
		passwordField.setLocation(240, 135);
		modifyensureButton3 = new JButton("�޸�");
		modifyensureButton3.setSize(100, 40);
		modifyensureButton3.setLocation(200, 180);
		modifyensureButton3.addActionListener(this);
		manreturnButton = new JButton("����");
		manreturnButton.setSize(100, 40);
		manreturnButton.setLocation(320, 180);
		manreturnButton.addActionListener(this);

		manmodifyPanel.add(idLabel);
		manmodifyPanel.add(passwordLabel);
		manmodifyPanel.add(idField);
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

		idLabel = new JLabel("�û�ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(150, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		idField = new JTextField(30);
		idField.setSize(150, 30);
		idField.setLocation(240, 85);
		delensureButton3 = new JButton("ɾ��");
		delensureButton3.setSize(100, 40);
		delensureButton3.setLocation(200, 180);
		delensureButton3.addActionListener(this);
		manreturnButton = new JButton("����");
		manreturnButton.setSize(100, 40);
		manreturnButton.setLocation(320, 180);
		manreturnButton.addActionListener(this);

		mandelPanel.add(idLabel);
		mandelPanel.add(idField);
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
		}
		if (e.getSource() == addensureButton1) {

		} else if (e.getSource() == modifyensureButton1) {

		} else if (e.getSource() == delensureButton1) {

		} else if (e.getSource() == addensureButton2) {

		} else if (e.getSource() == modifyensureButton2) {

		} else if (e.getSource() == delensureButton2) {

		} else if (e.getSource() == addensureButton3) {

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
		}else if (e.getSource() == exitLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			userUIController.setMainPage();
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
