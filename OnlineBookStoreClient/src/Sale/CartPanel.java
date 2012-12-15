package Sale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Member.MemberPO;

@SuppressWarnings("serial")
public class CartPanel extends JPanel implements ActionListener {
	private SaleUIController saleUIController;
	private String[] collectionBook = { "文学", "小说", "经济", "管理", "考试", "时尚",
			"少儿", "旅游" };
	private JLabel[] bookLabels;
	private JButton[] deleteButtons;
	private JButton buyEnsureButton;
	private JRadioButton useBondButton, notUseBondButton, useEarnedValue;
	private JRadioButton useEquiBond, useDiscountBond;
	private JLabel totalPriceLabel;
	private int size = collectionBook.length;
	private JPanel panel, useBondPanel, useEarnedValuePanel;
	private JTextField earnedValueField;
	private JComboBox<String> equiBondBox, discountBondBox;
	private int pieceOfCommodity = 1;
	private MemberPO memberPO;
	

	public CartPanel(SaleUIController saleUIController) {
		this.saleUIController = saleUIController;
	}

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);
		if(pieceOfCommodity != 0){
			panel = new JPanel() {

				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					for (int i = 1; i <= size; i++) {
						g.draw3DRect(0, 0, 770, 100 * i, true);
					}
				}

			};
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(800, 980));
			bookLabels = new JLabel[size];
			deleteButtons = new JButton[size];

			JScrollPane scroll[] = new JScrollPane[size];
			for (int i = 0; i < size; i++) {
				bookLabels[i] = new JLabel(collectionBook[i]);
				deleteButtons[i] = new JButton("删除");
				deleteButtons[i].setFocusable(false);
				scroll[i] = new JScrollPane();

				bookLabels[i].setSize(80, 35);
				deleteButtons[i].setSize(80, 35);
				scroll[i].setSize(550, 80);

				bookLabels[i].setLocation(10, 5 + 100 * i);
				deleteButtons[i].setLocation(680, 15 + 100 * i);
				scroll[i].setLocation(100, 10 + 100 * i);

				bookLabels[i].setFont(new Font("楷体_gb2312", Font.BOLD, 22));
				deleteButtons[i].setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
				scroll[i].setFont(new Font("楷体_gb2312", Font.BOLD, 20));

				deleteButtons[i].addActionListener(this);

				panel.add(bookLabels[i]);
				panel.add(deleteButtons[i]);
				panel.add(scroll[i]);
				
			}
			
			buyEnsureButton = new JButton("确认");
			buyEnsureButton.setSize(80, 40);
			buyEnsureButton.setFocusable(false);
			buyEnsureButton.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
			buyEnsureButton.setLocation(600, 100 + 100 * size);
			
			buyEnsureButton.addActionListener(this);
			
			notUseBondButton = new JRadioButton("全额支付");
			notUseBondButton.setSize(100, 25);
			notUseBondButton.setFocusable(false);
			notUseBondButton.setSelected(true);
			notUseBondButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			notUseBondButton.setLocation(80, 30 + 100 * size);
			notUseBondButton.addActionListener(this);
			
			useBondButton = new JRadioButton("使用优惠券");
			useBondButton.setSize(120, 25);
			useBondButton.setFocusable(false);
			useBondButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useBondButton.setLocation(185, 30 + 100 * size);
			useBondButton.addActionListener(this);
			
			useEarnedValue = new JRadioButton("使用积分");
			useEarnedValue.setSize(100, 25);
			useEarnedValue.setFocusable(false);
			useEarnedValue.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useEarnedValue.setLocation(310, 30 + 100 * size);
			useEarnedValue.addActionListener(this);
			
			ButtonGroup group = new ButtonGroup();
			group.add(notUseBondButton);
			group.add(useBondButton);
			group.add(useEarnedValue);
			
			totalPriceLabel = new JLabel("商品总价：");
			totalPriceLabel.setSize(140, 40);
			totalPriceLabel.setLocation(500, 40 + 100 * size);
			totalPriceLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 24));

			panel.add(notUseBondButton);
			panel.add(useBondButton);
			panel.add(useEarnedValue);
			panel.add(totalPriceLabel);
			panel.add(buyEnsureButton);
		}
		else{
			JLabel nothingLabel = new JLabel("您的购物车中当前没有任何图书!");
			nothingLabel.setSize(400, 30);
			nothingLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			nothingLabel.setForeground(Color.red);
			nothingLabel.setLocation(250, 100);
			
			panel = new JPanel(){
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					ImageIcon imageIcon = new ImageIcon("materials/boring.gif");
					Image image = imageIcon.getImage();
					if(image != null){
						g.drawImage(image, 335, 210, 80, 80, this);
					}
				}
			};
			panel.setLayout(null);
			panel.add(nothingLabel);
			panel.setSize(780, 500);
			panel.setLocation(0, 0);
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
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < size; i++) {
			if (e.getSource() == deleteButtons[i]) {
				int confirm = JOptionPane.showConfirmDialog(this, "是否确认删除该图书？");
				if (confirm == JOptionPane.YES_OPTION) {
					
				}
				break;
			}
		}
		if (e.getSource() == buyEnsureButton) {
			saleUIController.setOrderEnsureView();
		} else if(e.getSource() == useBondButton) {
			setUseBondPanel();
		}else if(e.getSource() == useEarnedValue) {
			setUseEarnedValuePanel();
		}else if(e.getSource() == notUseBondButton){
			setPayAllPanel();
		}
	}
	
	private void setPayAllPanel(){
		if(useBondPanel != null){
			useEquiBond.setEnabled(false);
			useDiscountBond.setEnabled(false);
			useBondPanel.setVisible(false);
		}
		if(useEarnedValuePanel != null){
			useEarnedValuePanel.setVisible(false);
		}
	}
	
	private void setUseBondPanel(){
		if(useBondPanel == null){
			useBondPanel = new JPanel();
			useBondPanel.setLayout(null);
			useBondPanel.setSize(400, 200);
			
			useEquiBond = new JRadioButton("使用等价券");
			useEquiBond.setSize(120, 30);
			useEquiBond.setFocusable(false);
			useEquiBond.setSelected(true);
			useEquiBond.setEnabled(false);
			useEquiBond.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useEquiBond.setLocation(0, 0);
			
			useDiscountBond = new JRadioButton("使用打折券");
			useDiscountBond.setSize(120, 30);
			useDiscountBond.setFocusable(false);
			useDiscountBond.setEnabled(false);
			useDiscountBond.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			useDiscountBond.setLocation(130, 0);
			
			equiBondBox = new JComboBox<String>();
			equiBondBox.setSize(230, 20);
			
			equiBondBox.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			
			ButtonGroup group = new ButtonGroup();
			group.add(useEquiBond);
			group.add(useDiscountBond);
			
			useBondPanel.add(useEquiBond);
			useBondPanel.add(useDiscountBond);
			useBondPanel.setVisible(false);
			useBondPanel.setLocation(80, 60 + 100 * size);
			panel.add(useBondPanel);
		}
		
		if(useEarnedValuePanel != null){
			useEarnedValuePanel.setVisible(false);
		}
		
		useEquiBond.setEnabled(true);
		useDiscountBond.setEnabled(true);
		useBondPanel.setVisible(true);
	}
	
	private void setUseEarnedValuePanel(){
		if(useEarnedValuePanel == null){
			useEarnedValuePanel = new JPanel();
			useEarnedValuePanel.setLayout(null);
			useEarnedValuePanel.setSize(400, 200);
			
//			String temp = "您当前拥有" + memberPO.getIntegral() + "积分";
			String temp = "您当前拥有100积分";
			JLabel earnValueLabel = new JLabel(temp);
			earnValueLabel.setSize(300, 30);
			earnValueLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			earnValueLabel.setLocation(0, 0);
			
			JLabel label = new JLabel("请输入所要兑换的积分的值:");
			label.setSize(230, 30);
			label.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			label.setLocation(0, 35);
			
			earnedValueField = new JTextField();
			earnedValueField.setSize(100, 30);
			earnedValueField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			earnedValueField.setLocation(240, 35);
			
			useEarnedValuePanel.add(earnValueLabel);
			useEarnedValuePanel.add(label);
			useEarnedValuePanel.add(earnedValueField);
			
			useEarnedValuePanel.setVisible(false);
			useEarnedValuePanel.setLocation(80, 60 + 100 * size);
			panel.add(useEarnedValuePanel);
		}
		
		if(useBondPanel != null){
			useEquiBond.setEnabled(false);
			useDiscountBond.setEnabled(false);
			useBondPanel.setVisible(false);
		}
		useEarnedValuePanel.setVisible(true);
	}
	
}
