package Member;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BookCollectionPanel extends JPanel implements MouseListener,
		ActionListener {
	private MemberUIController memberUIController;
	private String[] collectionBook = { "��ѧ", "С˵", "����", "����", "����", "ʱ��",
			"�ٶ�", "����" };
	private JLabel[] bookLabels;
	private JButton[] deleteButtons;
	private JButton[] addCartButton;
	private JScrollPane scrollPane;
	private int size = collectionBook.length;

	public BookCollectionPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setSize(785, 500);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				for (int i = 1; i <= size; i++) {
					g.draw3DRect(0, 0, 765, 100 * i, true);

				}
			}
		};
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(785, 1000));
		scrollPane.setViewportView(panel);

		bookLabels = new JLabel[size];
		addCartButton = new JButton[size];
		deleteButtons = new JButton[size];

		for (int i = 0; i < size; i++) {
			bookLabels[i] = new JLabel(collectionBook[i]);
			addCartButton[i] = new JButton("���빺�ﳵ");
			deleteButtons[i] = new JButton("ɾ��");
			bookLabels[i].setSize(80, 35);
			addCartButton[i].setSize(100, 30);
			deleteButtons[i].setSize(100, 30);

			bookLabels[i].setLocation(10, 5 + 100 * i);
			addCartButton[i].setLocation(650, 15 + 100 * i);
			deleteButtons[i].setLocation(650, 60 + 100 * i);

			bookLabels[i].setFont(new Font("����_gb2312", Font.BOLD, 22));
			addCartButton.clone()[i].addActionListener(this);
			deleteButtons[i].addActionListener(this);
			panel.add(bookLabels[i]);
			panel.add(addCartButton[i]);
			panel.add(deleteButtons[i]);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < size; i++) {

			if (e.getSource() == deleteButtons[i]) {
				int confirm = JOptionPane.showConfirmDialog(this, "�Ƿ�ȷ��ɾ���ͼ�飿");
				if (confirm == JOptionPane.YES_OPTION) {

				} else {

				}
			} else if (e.getSource() == addCartButton[i]) {

			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
