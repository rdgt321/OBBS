package Member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class CartPanel extends JPanel implements ActionListener {
	private MemberUIController memberUIController;
	private String[] collectionBook = { "��ѧ", "С˵", "����", "����", "����", "ʱ��",
			"�ٶ�", "����" };
	private JLabel[] bookLabels;
	private JButton[] deleteButtons;
	private JButton buyEnsureButton, returnButton;
	private JLabel totalPriceLabel;
	private int size = collectionBook.length;

	public CartPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);
		JPanel panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				for (int i = 1; i <= size; i++) {
					g.draw3DRect(0, 0, 770, 100 * i, true);

				}
			}

		};
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800, 1000));

		bookLabels = new JLabel[size];
		deleteButtons = new JButton[size];

		for (int i = 0; i < size; i++) {
			bookLabels[i] = new JLabel(collectionBook[i]);
			deleteButtons[i] = new JButton("ɾ��");
			bookLabels[i].setSize(80, 35);
			deleteButtons[i].setSize(60, 30);

			bookLabels[i].setLocation(10, 5 + 100 * i);
			deleteButtons[i].setLocation(700, 10 + 100 * i);

			bookLabels[i].setFont(new Font("����_gb2312", Font.BOLD, 22));
			deleteButtons[i].addActionListener(this);
			panel.add(bookLabels[i]);
			panel.add(deleteButtons[i]);
		}
		buyEnsureButton = new JButton("ȷ�Ϲ���");
		buyEnsureButton.setSize(80, 40);
		buyEnsureButton.setLocation(550, 100 + 100 * size);
		returnButton = new JButton("����");
		returnButton.setSize(80, 40);
		returnButton.setLocation(650, 100 + 100 * size);
		buyEnsureButton.addActionListener(this);
		returnButton.addActionListener(this);
		totalPriceLabel = new JLabel("��Ʒ�ܼۣ�");
		totalPriceLabel.setSize(140, 40);
		totalPriceLabel.setLocation(500, 40 + 100 * size);
		totalPriceLabel.setFont(new Font("����_gb2312", Font.BOLD, 24));
		panel.add(totalPriceLabel);
		panel.add(buyEnsureButton);
		panel.add(returnButton);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setSize(780, 500);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < size; i++) {
			if (e.getSource() == deleteButtons[i]) {
				int confirm = JOptionPane.showConfirmDialog(this, "�Ƿ�ȷ��ɾ���ͼ�飿");
				if (confirm == JOptionPane.YES_OPTION) {

				} else {

				}
			}
		}
		if (e.getSource() == buyEnsureButton) {

		} else if (e.getSource() == returnButton) {

		}
	}
}
