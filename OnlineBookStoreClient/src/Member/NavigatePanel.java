package Member;

//direct panel
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import ClientRunner.Agent;
import ClientRunner.Const;
import ClientRunner.IMGSTATIC;
import ClientRunner.MButton;
import ClientRunner.MComboBox;
import ClientRunner.MPanel;
import ClientRunner.MTextField;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class NavigatePanel extends MPanel implements ActionListener,
		MouseListener {
	public static final int BEFORE_STATE = 0;
	public static final int AFTER_STATE = 1;

	private MemberUIController memberUIController;

	private MTextField searchField;
	private MComboBox<String> condition;
	private MButton searchButton;
	private JLabel loginLabel, registerLabel, homePageLabel,
			bookCollectionLabel, cartLabel, infoCenterLabel, exitLabel;

	public NavigatePanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (IMGSTATIC.navigatorBG != null) {
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.6f));
			g2d.drawImage(IMGSTATIC.navigatorBG, 0, 0, 800, 70, this);
			g2d.setComposite(composite);
		}
	}

	public void init(int state) {
		setSize(800, 70);
		setLocation(0, 0);
		setVisible(true);
		setLayout(null);

		tab();

		searchField = new MTextField(30);
		searchField.setSize(230, 35);
		searchField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		searchField.setLocation(20, 10);
		searchField.setVisible(true);
		searchField.setOpaque(false);

		condition = new MComboBox<String>();
		condition.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		condition.addItem("综合");
		condition.addItem("书名");
		condition.addItem("作者");
		condition.setSize(65, 35);
		condition.setLocation(260, 10);
		condition.setOpaque(false);

		searchButton = new MButton("搜索");
		searchButton.setSize(75, 35);
		searchButton.setLocation(335, 10);
		searchButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		searchButton.addActionListener(this);
		searchButton.setButtonColor(Color.CYAN.darker());

		if (state == BEFORE_STATE) {
			loginLabel = new JLabel("登陆");
			loginLabel.setSize(40, 50);
			loginLabel.setLocation(680, 5);
			loginLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			registerLabel = new JLabel("|注册");
			registerLabel.setSize(60, 50);
			registerLabel.setLocation(720, 5);
			registerLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			loginLabel.addMouseListener(this);
			registerLabel.addMouseListener(this);

			add(loginLabel);
			add(registerLabel);

		} else if (state == AFTER_STATE) {
			homePageLabel = new JLabel("首页");
			homePageLabel.setSize(50, 50);
			homePageLabel.setLocation(470, 5);
			homePageLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

			bookCollectionLabel = new JLabel("|我的书架");
			bookCollectionLabel.setSize(80, 50);
			bookCollectionLabel.setLocation(510, 5);
			bookCollectionLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

			cartLabel = new JLabel("|购物车");
			cartLabel.setSize(60, 50);
			cartLabel.setLocation(590, 5);
			cartLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

			infoCenterLabel = new JLabel("|账户中心");
			infoCenterLabel.setSize(80, 50);
			infoCenterLabel.setLocation(655, 5);
			infoCenterLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

			exitLabel = new JLabel("|退出");
			exitLabel.setSize(60, 50);
			exitLabel.setLocation(740, 5);
			exitLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));

			homePageLabel.addMouseListener(this);
			bookCollectionLabel.addMouseListener(this);
			cartLabel.addMouseListener(this);
			infoCenterLabel.addMouseListener(this);
			exitLabel.addMouseListener(this);

			add(homePageLabel);
			add(bookCollectionLabel);
			add(cartLabel);
			add(infoCenterLabel);
			add(exitLabel);
		}
		add(searchField);
		add(condition);
		add(searchButton);
		validate();
		repaint();
	}

	public void tab() {
		if (homePageLabel != null) {

			searchField.setEnabled(false);
			searchField.setVisible(false);
			searchButton.setEnabled(false);
			searchButton.setVisible(false);

			homePageLabel.setEnabled(false);
			homePageLabel.setVisible(false);

			bookCollectionLabel.setEnabled(false);
			bookCollectionLabel.setVisible(false);

			cartLabel.setEnabled(false);
			cartLabel.setVisible(false);

			infoCenterLabel.setEnabled(false);
			infoCenterLabel.setVisible(false);

			exitLabel.setEnabled(false);
			exitLabel.setVisible(false);

			remove(searchField);
			remove(searchButton);
			remove(homePageLabel);
			remove(bookCollectionLabel);
			remove(cartLabel);
			remove(infoCenterLabel);
			remove(exitLabel);
			searchField = null;
			searchButton = null;
			homePageLabel = null;
			bookCollectionLabel = null;
			cartLabel = null;
			infoCenterLabel = null;
			exitLabel = null;
		}
		if (loginLabel != null) {
			searchField.setEnabled(false);
			searchField.setVisible(false);
			searchButton.setEnabled(false);
			searchButton.setVisible(false);

			loginLabel.setEnabled(false);
			loginLabel.setVisible(false);
			registerLabel.setEnabled(false);
			registerLabel.setVisible(false);

			remove(searchField);
			remove(searchButton);
			remove(loginLabel);
			remove(registerLabel);
			searchField = null;
			searchButton = null;
			loginLabel = null;
			registerLabel = null;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String keyWord = searchField.getText().trim();
			int type = condition.getSelectedIndex();
			switch (type) {
			case 0:
				try {
					ResultMessage resultMessage = Agent.bookService.searchBook(
							keyWord, Const.SEARCH_BY_MULTI);
					memberUIController.displaySearchResult(resultMessage);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				break;
			case 1:
				try {
					ResultMessage resultMessage = Agent.bookService.searchBook(
							keyWord, Const.SEARCH_BY_NAME);
					memberUIController.displaySearchResult(resultMessage);
				} catch (RemoteException re) {
					re.printStackTrace();
				}
				break;
			case 2:
				try {
					ResultMessage resultMessage = Agent.bookService.searchBook(
							keyWord, Const.SEARCH_BY_AUTHOR);
					memberUIController.displaySearchResult(resultMessage);
				} catch (RemoteException re) {
					re.printStackTrace();
				}
				break;
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == loginLabel && e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setLoginView();
		} else if (e.getSource() == registerLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setRegisterView();
		} else if (e.getSource() == homePageLabel
				&& e.getButton() == MouseEvent.BUTTON1) {
			memberUIController.setMainpageView();
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
			this.init(BEFORE_STATE);
			try {
				Agent.memberService.logout(Agent.userAgent);
			} catch (RemoteException e1) {
				e1.printStackTrace();
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
