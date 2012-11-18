package Book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Member.MemberUIController;
import Member.NavigatePanel;

public class MainpagePanel extends JPanel implements MouseListener {
	private BookUIController bookUIController;
	private String[] bookClassify = { "��ѧ", "С˵", "����", "����", "����", "ʱ��", "�ٶ�",
			"����" };
	private JLabel[] bookLabels;
	private JLabel[] moreLabels;
	private int size = bookClassify.length;

	public MainpagePanel(BookUIController bookUIController) {
		this.bookUIController = bookUIController;
	}

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(800, 1000));
		bookLabels = new JLabel[size];
		moreLabels = new JLabel[size];

		for (int i = 0; i < size; i++) {
			bookLabels[i] = new JLabel(bookClassify[i]);
			moreLabels[i] = new JLabel("��ࡶ");
			bookLabels[i].setSize(100, 40);
			moreLabels[i].setSize(100, 40);

			if (i % 2 == 0) {
				bookLabels[i].setLocation(10, 5 + 100 * i);
				moreLabels[i].setLocation(180, 5 + 100 * i);

			} else {
				bookLabels[i].setLocation(240, -95 + 100 * i);
				moreLabels[i].setLocation(420, -95 + 100 * i);

			}
			bookLabels[i].setFont(new Font("����_gb2312", Font.BOLD, 22));
			moreLabels[i].addMouseListener(this);
			panel.add(bookLabels[i]);
			panel.add(moreLabels[i]);
		}

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
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < size; i++) {
			if (e.getSource() == moreLabels[i]
					&& e.getButton() == MouseEvent.BUTTON1) {

			}
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
