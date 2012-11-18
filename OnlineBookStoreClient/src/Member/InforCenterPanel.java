package Member;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class InforCenterPanel extends JPanel implements MouseListener,
		ActionListener {
	private MemberUIController memberUIController;
	private JScrollPane resultPane;
	private JLabel personalInfoLabel, buyHistoryLabel, passwordLabel,
			promotionLabel;
	// personalInfoView
	private JLabel idLabel, telLabel, birthLabel;
	private JButton modifyButton, ensuremodifyButton, modifyreturnButton;
	private JTextField idField, telField, birthField;

	// buyHistoryView

	// passwordView
	private JLabel prePassword, newPassword;
	private JPasswordField prePasswordField, newPasswordField;
	private JButton ensureButton, returnButton;

	public InforCenterPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	// promotionView

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);

		personalInfoLabel = new JLabel("������Ϣ");
		personalInfoLabel.setSize(140, 40);
		personalInfoLabel.setLocation(50, 50);
		personalInfoLabel.setFont(new Font("����_gb2312", Font.BOLD, 24));
		buyHistoryLabel = new JLabel("�����¼");
		buyHistoryLabel.setSize(140, 40);
		buyHistoryLabel.setLocation(50, 100);
		buyHistoryLabel.setFont(new Font("����_gb2312", Font.BOLD, 24));
		passwordLabel = new JLabel("�޸�����");
		passwordLabel.setSize(140, 40);
		passwordLabel.setLocation(50, 150);
		passwordLabel.setFont(new Font("����_gb2312", Font.BOLD, 24));
		promotionLabel = new JLabel("�Ż���Ϣ");
		promotionLabel.setSize(140, 40);
		promotionLabel.setLocation(50, 200);
		promotionLabel.setFont(new Font("����_gb2312", Font.BOLD, 24));
		personalInfoLabel.addMouseListener(this);
		buyHistoryLabel.addMouseListener(this);
		passwordLabel.addMouseListener(this);
		promotionLabel.addMouseListener(this);

		add(personalInfoLabel);
		add(buyHistoryLabel);
		add(passwordLabel);
		add(promotionLabel);

		resultPane = new JScrollPane();
		resultPane.setSize(600, 440);
		resultPane.setLocation(180, 50);
		resultPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		resultPane.getVerticalScrollBar().setUnitIncrement(20);
		add(resultPane);
	}

	public void initPersonalInfoView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		idLabel = new JLabel("�û���ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(100, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		telLabel = new JLabel("��ϵ�绰:");
		telLabel.setSize(100, 40);
		telLabel.setLocation(100, 130);
		telLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		birthLabel = new JLabel("��������:");
		birthLabel.setSize(100, 40);
		birthLabel.setLocation(100, 180);
		birthLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		modifyButton = new JButton("�޸�");
		modifyButton.setSize(100, 40);
		modifyButton.setLocation(160, 240);
		modifyButton.addActionListener(this);
		panel.add(idLabel);
		panel.add(telLabel);
		panel.add(birthLabel);
		panel.add(modifyButton);
		resultPane.setViewportView(panel);
	}

	private void initmodifyView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		idLabel = new JLabel("�û���ID:");
		idLabel.setSize(100, 40);
		idLabel.setLocation(100, 80);
		idLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		telLabel = new JLabel("��ϵ�绰:");
		telLabel.setSize(100, 40);
		telLabel.setLocation(100, 130);
		telLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		birthLabel = new JLabel("��������:");
		birthLabel.setSize(100, 40);
		birthLabel.setLocation(100, 180);
		birthLabel.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		ensuremodifyButton = new JButton("ȷ���޸�");
		ensuremodifyButton.setSize(100, 40);
		ensuremodifyButton.setLocation(160, 240);
		ensuremodifyButton.addActionListener(this);
		modifyreturnButton = new JButton("����");
		modifyreturnButton.setSize(100, 40);
		modifyreturnButton.setLocation(280, 240);
		modifyreturnButton.addActionListener(this);
		idField = new JTextField(30);
		idField.setSize(120, 30);
		idField.setLocation(220, 80);
		telField = new JTextField(30);
		telField.setSize(120, 30);
		telField.setLocation(220, 130);
		birthField = new JTextField(30);
		birthField.setSize(120, 30);
		birthField.setLocation(220, 180);

		panel.add(idLabel);
		panel.add(telLabel);
		panel.add(birthLabel);
		panel.add(idField);
		panel.add(telField);
		panel.add(birthField);
		panel.add(ensuremodifyButton);
		panel.add(modifyreturnButton);
		resultPane.setViewportView(panel);
	}

	public void initBuyHisView() {

	}

	public void initPasswordView() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500, 430));

		prePassword = new JLabel("������:");
		prePassword.setSize(100, 40);
		prePassword.setLocation(100, 80);
		prePassword.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		newPassword = new JLabel("������:");
		newPassword.setSize(100, 40);
		newPassword.setLocation(100, 130);
		newPassword.setFont(new Font("����_gb2312", Font.PLAIN, 20));

		prePasswordField = new JPasswordField(20);
		prePasswordField.setSize(150, 40);
		prePasswordField.setLocation(180, 80);
		newPasswordField = new JPasswordField(20);
		newPasswordField.setSize(150, 40);
		newPasswordField.setLocation(180, 130);

		ensureButton = new JButton("ȷ���޸�");
		ensureButton.setSize(100, 40);
		ensureButton.setLocation(140, 190);
		returnButton = new JButton("ȡ��");
		returnButton.setSize(100, 40);
		returnButton.setLocation(260, 190);
		ensureButton.addActionListener(this);
		returnButton.addActionListener(this);

		panel.add(prePassword);
		panel.add(newPassword);
		panel.add(prePasswordField);
		panel.add(newPasswordField);
		panel.add(ensureButton);
		panel.add(returnButton);
		resultPane.setViewportView(panel);

	}

	public void initPromotionView() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == modifyButton) {
			initmodifyView();
		} else if (e.getSource() == modifyreturnButton) {
			initPersonalInfoView();
		} else if (e.getSource() == ensuremodifyButton) {

		} else if (e.getSource() == ensureButton) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == personalInfoLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initPersonalInfoView();
		} else if (e.getSource() == buyHistoryLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initBuyHisView();
		} else if (e.getSource() == passwordLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initPasswordView();
		} else if (e.getSource() == promotionLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			initPromotionView();
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

}
