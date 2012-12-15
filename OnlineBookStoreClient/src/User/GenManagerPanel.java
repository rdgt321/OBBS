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

import User.AdminPanel.tableModel;

@SuppressWarnings("serial")
public class GenManagerPanel extends JPanel implements MouseListener,
		ActionListener {
	private UserUIController userUIController;
	// main page
	private JLabel obsLabel, welcomLabel, exitLabel;
	private JButton addProButton, deliverButton;
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;
	
	//set promotion
	JPanel setPromotionPanel;
	
	//deliver promotion

	public GenManagerPanel(UserUIController userUIController) {
		this.userUIController = userUIController;
	}

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

		obsLabel = new JLabel("网上图书销售系统");
		obsLabel.setLocation(240, 0);
		obsLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 30));
		obsLabel.setSize(300, 80);
		
		welcomLabel = new JLabel("欢迎您，总经理");
		welcomLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(150, 40);
		welcomLabel.setLocation(560, 20);
		
		exitLabel = new JLabel("退出");
		exitLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);
		
		addProButton = new JButton("设置优惠券");
		addProButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		addProButton.setFocusable(false);
		addProButton.setSize(130, 40);
		addProButton.setLocation(30, 150);
		addProButton.addActionListener(this);
		
		deliverButton = new JButton("赠送优惠券");
		deliverButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		deliverButton.setFocusable(false);
		deliverButton.setSize(130, 40);
		deliverButton.setLocation(30, 200);
		deliverButton.addActionListener(this);

		this.add(obsLabel);
		this.add(welcomLabel);
		this.add(exitLabel);
		this.add(addProButton);
		this.add(deliverButton);

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

	public void initaddProView() {
		setPromotionPanel = new JPanel();
		setPromotionPanel.setLayout(null);
		
		
		
	}

	public void initdeliverView() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addProButton) {
			initaddProView();
		} else if (e.getSource() == deliverButton) {
			initdeliverView();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitLabel && e.getButton() == MouseEvent.BUTTON1) {
			userUIController.setMainPageView();
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
