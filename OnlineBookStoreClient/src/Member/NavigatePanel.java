package Member;

//direct panel
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ClientRunner.Agent;
import ClientRunner.Const;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class NavigatePanel extends JPanel implements ActionListener,
		MouseListener {
	public static final int BEFORE_STATE = 0;
	public static final int AFTER_STATE = 1;

	private MemberUIController memberUIController;

	private JTextField searchField;
	private JComboBox<String> condition;
	private JButton searchButton;
	private JLabel loginLabel, registerLabel, homePageLabel, bookCollectionLabel, cartLabel,
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
		searchField.setSize(230, 35);
		searchField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		searchField.setLocation(20, 10);
		searchField.setVisible(true);
		
		condition = new JComboBox<>();
		condition.setFocusable(false);
		condition.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		condition.addItem("综合");
		condition.addItem("书名");
		condition.addItem("作者");
		condition.setSize(65, 35);
		condition.setLocation(260, 10);
		
		searchButton = new JButton("搜索");
		searchButton.setSize(75, 35);
		searchButton.setLocation(335, 10);
		searchButton.setFocusable(false);
		searchButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		searchButton.addActionListener(this);
		
		tab();
		
		if (state == 0) {
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

		} 
		else 
			if (state == 1) {
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
		repaint();
		validate();
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
			remove(bookCollectionLabel);
			remove(cartLabel);
			remove(infoCenterLabel);
			remove(exitLabel);
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
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String keyWord = searchField.getText().trim();
			int type = condition.getSelectedIndex();
			switch(type){
				case 0:
					try {
						ResultMessage resultMessage = Agent.bookService.searchBook(keyWord, Const.SEARCH_BY_MULTI);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
				case 1:
					break;
				case 2:
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
				&& e.getButton() == MouseEvent.BUTTON1){
			memberUIController.setMainpageView();
		}else if (e.getSource() == bookCollectionLabel
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
