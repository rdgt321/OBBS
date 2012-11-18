package Member;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NavigatePanel extends JPanel implements ActionListener,
		MouseListener {
	public static final int BEFORE_STATE = 0;
	public static final int AFTER_STATE = 1;

	private MemberUIController memberUIController;
	private JTextField searchField;
	private JButton searchButton;
	private JLabel loginLabel, registerLabel, bookCollectionLabel, cartLabel,
			infoCenterLabel, exitLabel;

	public NavigatePanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	public void init(int state) {
		setSize(800, 70);
		setLocation(0, 0);
		setVisible(true);
		setLayout(null);

		searchField = new JTextField(30);
		searchField.setSize(300, 35);
		searchField.setLocation(80, 10);
		searchField.setVisible(true);

		searchButton = new JButton("����");
		searchButton.setSize(80, 35);
		searchButton.setLocation(380, 10);
		searchButton.setFont(new Font("����_gb2312", Font.PLAIN, 20));
		searchButton.addActionListener(this);

		if (state == 0) {
			loginLabel = new JLabel("��½");
			loginLabel.setSize(40, 50);
			loginLabel.setLocation(680, 5);
			loginLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
			registerLabel = new JLabel("|ע��");
			registerLabel.setSize(60, 50);
			registerLabel.setLocation(720, 5);
			registerLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
			loginLabel.addMouseListener(this);
			registerLabel.addMouseListener(this);

			add(searchField);
			add(searchButton);
			add(loginLabel);
			add(registerLabel);

		} else if (state == 1) {
			bookCollectionLabel = new JLabel("�ҵ����");
			bookCollectionLabel.setSize(80, 50);
			bookCollectionLabel.setLocation(500, 5);
			bookCollectionLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
			cartLabel = new JLabel("|���ﳵ");
			cartLabel.setSize(60, 50);
			cartLabel.setLocation(580, 5);
			cartLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
			infoCenterLabel = new JLabel("|�˻�����");
			infoCenterLabel.setSize(80, 50);
			infoCenterLabel.setLocation(650, 5);
			infoCenterLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
			exitLabel = new JLabel("|�˳�");
			exitLabel.setSize(60, 50);
			exitLabel.setLocation(740, 5);
			exitLabel.setFont(new Font("����_gb2312", Font.PLAIN, 18));
			bookCollectionLabel.addMouseListener(this);
			cartLabel.addMouseListener(this);
			infoCenterLabel.addMouseListener(this);
			exitLabel.addMouseListener(this);

			add(searchField);
			add(searchButton);
			add(bookCollectionLabel);
			add(cartLabel);
			add(infoCenterLabel);
			add(exitLabel);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == loginLabel && e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setLoginView();
		} else if (e.getSource() == registerLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setRegisterView();
		} else if (e.getSource() == bookCollectionLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setBookCollView();
		} else if (e.getSource() == cartLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setCartView();
		} else if (e.getSource() == infoCenterLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setInforView();
		} else if (e.getSource() == exitLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setMainpageView();
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
