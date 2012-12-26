package User;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ClientRunner.Agent;
import ClientRunner.IMGSTATIC;
import ClientRunner.ImageDialog;
import ClientRunner.MButton;
import ClientRunner.MPanel;
import ClientRunner.MTextField;
import Member.SafeCheck;
import Promotion.PromotionPO;
import RMI.ResultMessage;
import User.AdminPanel.tableModel;

@SuppressWarnings("serial")
public class GenManagerPanel extends MPanel implements MouseListener,
		ActionListener {
	private UserUIController userUIController;
	// main page
	private JLabel obsLabel, welcomLabel, exitLabel;
	private MButton addProButton;
	tableModel tableModel;
	JTable resultTable;
	JScrollPane resultPanel;

	// set promotion
	private MPanel setPromotionPanel, equiPanel, discountPanel;
	private JRadioButton equiBond, discountBond;
	private JLabel equiValueLabel, useConditionLabel, startDateLabel,
			endDateLabel, startYearLabel, startMonthLabel, startDayLabel,
			noUseLabel, endYearLabel, endMonthLabel, endDayLabel,
			discountRateLabel, zheLabel, equiValueWarning, useConWarning,
			disRateWarning, startDateWarning, endDateWarning,
			promotionNameLabel, promotionNameWarning, leastLabel,
			leastIntegralWarning;
	private MTextField equiValueField, useConditionField, startYearField,
			startMonthField, startDayfField, endYearField, endMonthField,
			endDayfField, discountRateField, promotionNameField,
			leastLabelField;
	private MButton addButton;

	// deliver promotion

	public GenManagerPanel(UserUIController userUIController) {
		this.userUIController = userUIController;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (IMGSTATIC.otherBG != null) {
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.8f));
			g2d.drawImage(IMGSTATIC.otherBG, 0, 0, 800, 600, this);
			g2d.setComposite(composite);
		}
		g2d.dispose();
	}

	public void init() {
		setSize(800, 600);
		setLayout(null);
		setVisible(true);

		obsLabel = new JLabel("网上图书销售系统");
		obsLabel.setLocation(240, 0);
		obsLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 30));
		obsLabel.setSize(300, 80);

		welcomLabel = new JLabel("欢迎您，总经理:" + Agent.userAgent.getName());
		welcomLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		welcomLabel.setSize(200, 40);
		welcomLabel.setLocation(500, 20);

		exitLabel = new JLabel("退出");
		exitLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		exitLabel.setSize(80, 40);
		exitLabel.setLocation(720, 20);
		exitLabel.addMouseListener(this);

		addProButton = new MButton("设置销售策略");
		addProButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 15));
		addProButton.setFocusable(false);
		addProButton.setSize(130, 40);
		addProButton.setLocation(30, 150);
		addProButton.addActionListener(this);

		this.add(obsLabel);
		this.add(welcomLabel);
		this.add(exitLabel);
		this.add(addProButton);

		resultPanel = new JScrollPane();
		resultPanel.setLocation(180, 150);
		resultPanel.setSize(600, 400);
		resultPanel.setVisible(true);
		resultPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		resultPanel.setOpaque(false);
		resultPanel.getViewport().setOpaque(false);
		this.add(resultPanel);
		validate();
	}

	public void initaddProView() {
		setPromotionPanel = initPanel(setPromotionPanel);

		promotionNameLabel = new JLabel("促销策略名称:");
		promotionNameLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		promotionNameLabel.setSize(150, 40);
		promotionNameLabel.setLocation(80, 5);

		promotionNameField = new MTextField();
		promotionNameField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		promotionNameField.setSize(100, 20);
		promotionNameField.setLocation(230, 15);

		promotionNameWarning = new JLabel("输入不合法!");
		promotionNameWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		promotionNameWarning.setForeground(Color.RED);
		promotionNameWarning.setSize(120, 40);
		promotionNameWarning.setLocation(350, 5);
		promotionNameWarning.setVisible(false);

		equiBond = new JRadioButton("等价券");
		equiBond.setSize(100, 40);
		equiBond.setFocusable(false);
		equiBond.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		equiBond.setLocation(100, 45);
		equiBond.setOpaque(false);

		discountBond = new JRadioButton("打折券");
		discountBond.setSize(100, 40);
		discountBond.setFocusable(false);
		discountBond.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		discountBond.setLocation(300, 45);
		discountBond.setOpaque(false);

		equiPanel = new MPanel();
		equiPanel.setSize(350, 120);
		equiPanel.setLocation(80, 85);
		equiPanel.setLayout(null);
		equiPanel.setVisible(true);

		equiValueLabel = new JLabel("面额:");
		equiValueLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		equiValueLabel.setSize(80, 40);
		equiValueLabel.setLocation(40, 20);

		equiValueField = new MTextField();
		equiValueField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		equiValueField.setSize(80, 20);
		equiValueField.setLocation(120, 30);

		equiValueWarning = new JLabel("输入不合法!");
		equiValueWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		equiValueWarning.setForeground(Color.RED);
		equiValueWarning.setSize(120, 40);
		equiValueWarning.setLocation(220, 20);
		equiValueWarning.setVisible(false);

		useConditionLabel = new JLabel("使用条件:满");
		useConditionLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		useConditionLabel.setSize(100, 40);
		useConditionLabel.setLocation(40, 80);

		useConditionField = new MTextField();
		useConditionField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		useConditionField.setSize(60, 20);
		useConditionField.setLocation(140, 90);

		noUseLabel = new JLabel("元使用该券");
		noUseLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		noUseLabel.setSize(100, 40);
		noUseLabel.setLocation(200, 80);

		useConWarning = new JLabel("输入不合法!");
		useConWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		useConWarning.setForeground(Color.RED);
		useConWarning.setSize(120, 40);
		useConWarning.setLocation(300, 80);
		useConWarning.setVisible(false);

		startDateLabel = new JLabel("开始日期:");
		startDateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startDateLabel.setSize(80, 40);
		startDateLabel.setLocation(100, 216);

		startYearField = new MTextField();
		startYearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startYearField.setSize(80, 20);
		startYearField.setLocation(180, 226);

		startYearLabel = new JLabel("年");
		startYearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startYearLabel.setSize(20, 40);
		startYearLabel.setLocation(260, 216);

		startMonthField = new MTextField();
		startMonthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startMonthField.setSize(40, 20);
		startMonthField.setLocation(280, 226);

		startMonthLabel = new JLabel("月");
		startMonthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startMonthLabel.setSize(20, 40);
		startMonthLabel.setLocation(320, 216);

		startDayfField = new MTextField();
		startDayfField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startDayfField.setSize(40, 20);
		startDayfField.setLocation(340, 226);

		startDayLabel = new JLabel("日");
		startDayLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startDayLabel.setSize(20, 40);
		startDayLabel.setLocation(380, 216);

		startDateWarning = new JLabel("输入不合法!");
		startDateWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		startDateWarning.setForeground(Color.RED);
		startDateWarning.setSize(120, 40);
		startDateWarning.setLocation(420, 216);
		startDateWarning.setVisible(false);

		endDateLabel = new JLabel("结束日期:");
		endDateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endDateLabel.setSize(80, 40);
		endDateLabel.setLocation(100, 266);

		endYearField = new MTextField();
		endYearField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endYearField.setSize(80, 20);
		endYearField.setLocation(180, 276);

		endYearLabel = new JLabel("年");
		endYearLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endYearLabel.setSize(20, 40);
		endYearLabel.setLocation(260, 266);

		endMonthField = new MTextField();
		endMonthField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endMonthField.setSize(40, 20);
		endMonthField.setLocation(280, 276);

		endMonthLabel = new JLabel("月");
		endMonthLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endMonthLabel.setSize(20, 40);
		endMonthLabel.setLocation(320, 266);

		endDayfField = new MTextField();
		endDayfField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endDayfField.setSize(40, 20);
		endDayfField.setLocation(340, 276);

		endDayLabel = new JLabel("日");
		endDayLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endDayLabel.setSize(20, 40);
		endDayLabel.setLocation(380, 266);

		endDateWarning = new JLabel("输入不合法!");
		endDateWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		endDateWarning.setForeground(Color.RED);
		endDateWarning.setSize(120, 40);
		endDateWarning.setLocation(420, 266);
		endDateWarning.setVisible(false);

		leastLabel = new JLabel("最低促销积分:");
		leastLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		leastLabel.setSize(120, 40);
		leastLabel.setLocation(100, 316);

		leastLabelField = new MTextField();
		leastLabelField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		leastLabelField.setSize(80, 20);
		leastLabelField.setLocation(220, 326);

		leastIntegralWarning = new JLabel("输入不合法!");
		leastIntegralWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		leastIntegralWarning.setForeground(Color.RED);
		leastIntegralWarning.setSize(120, 40);
		leastIntegralWarning.setLocation(460, 316);
		leastIntegralWarning.setVisible(false);

		addButton = new MButton("添加");
		addButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		addButton.setSize(120, 40);
		addButton.setLocation(200, 350);
		addButton.addActionListener(this);

		equiPanel.add(equiValueLabel);
		equiPanel.add(useConditionLabel);
		equiPanel.add(startDateLabel);
		equiPanel.add(endDateLabel);
		equiPanel.add(equiValueField);
		equiPanel.add(useConditionField);
		equiPanel.add(noUseLabel);
		equiPanel.add(equiValueWarning);
		equiPanel.add(useConWarning);

		equiBond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				discountPanel.setEnabled(false);
				discountPanel.setVisible(false);
				equiPanel.setEnabled(true);
				equiPanel.setVisible(true);
				setPromotionPanel.repaint();
			}
		});

		discountPanel = new MPanel();
		discountPanel.setSize(350, 120);
		discountPanel.setLocation(80, 85);
		discountPanel.setLayout(null);
		discountPanel.setVisible(true);

		discountRateLabel = new JLabel("打折率:");
		discountRateLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		discountRateLabel.setSize(80, 40);
		discountRateLabel.setLocation(40, 40);

		discountRateField = new MTextField();
		discountRateField.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		discountRateField.setSize(40, 20);
		discountRateField.setLocation(120, 50);

		zheLabel = new JLabel("折");
		zheLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		zheLabel.setSize(20, 40);
		zheLabel.setLocation(170, 40);

		disRateWarning = new JLabel("输入不合法!");
		disRateWarning.setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
		disRateWarning.setForeground(Color.RED);
		disRateWarning.setSize(120, 40);
		disRateWarning.setLocation(200, 40);
		disRateWarning.setVisible(false);

		discountPanel.add(discountRateLabel);
		discountPanel.add(discountRateField);
		discountPanel.add(zheLabel);
		discountPanel.add(disRateWarning);

		discountBond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				equiPanel.setEnabled(false);
				equiPanel.setVisible(false);
				discountPanel.setEnabled(true);
				discountPanel.setVisible(true);
				setPromotionPanel.add(discountPanel);
				setPromotionPanel.repaint();
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(equiBond);
		group.add(discountBond);
		equiBond.setSelected(true);

		setPromotionPanel.add(equiBond);
		setPromotionPanel.add(discountBond);
		setPromotionPanel.add(addButton);
		setPromotionPanel.add(promotionNameLabel);
		setPromotionPanel.add(promotionNameField);
		setPromotionPanel.add(startDateLabel);
		setPromotionPanel.add(startYearLabel);
		setPromotionPanel.add(startYearField);
		setPromotionPanel.add(startMonthLabel);
		setPromotionPanel.add(startMonthField);
		setPromotionPanel.add(startDayLabel);
		setPromotionPanel.add(startDayfField);
		setPromotionPanel.add(endDateLabel);
		setPromotionPanel.add(endYearLabel);
		setPromotionPanel.add(endYearField);
		setPromotionPanel.add(endMonthLabel);
		setPromotionPanel.add(endMonthField);
		setPromotionPanel.add(endDayLabel);
		setPromotionPanel.add(endDayfField);
		setPromotionPanel.add(startDateWarning);
		setPromotionPanel.add(endDateWarning);
		setPromotionPanel.add(promotionNameWarning);
		setPromotionPanel.add(equiPanel);
		setPromotionPanel.add(leastIntegralWarning);
		setPromotionPanel.add(leastLabel);
		setPromotionPanel.add(leastLabelField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addProButton) {
			initaddProView();
			resultPanel.setViewportView(setPromotionPanel);
			setPromotionPanel.requestFocus();
		} else if (e.getSource() == addButton) {
			String startYear = startYearField.getText().trim();
			String startMonth = startMonthField.getText().trim();
			String startDay = startDayfField.getText().trim();
			Calendar startDate = null;
			String endYear = endYearField.getText().trim();
			String endMonth = endMonthField.getText().trim();
			String endDay = endDayfField.getText().trim();
			Calendar endDate = null;
			String name = null;
			int promotionID = 0;
			int leastIntegral = 0;
			double discountRate = 0;
			double equivalentDenomination = 0;
			double bondUseLimit = 0;
			boolean valid = true;
			try {
				startDate = new GregorianCalendar(Integer.parseInt(startYear),
						Integer.parseInt(startMonth) - 1,
						Integer.parseInt(startDay));
			} catch (Exception e1) {
				e1.printStackTrace();
				startDateWarning.setVisible(true);
				valid = false;
			}
			try {
				endDate = new GregorianCalendar(Integer.parseInt(endYear),
						Integer.parseInt(endMonth) - 1,
						Integer.parseInt(endDay));
			} catch (Exception e2) {
				e2.printStackTrace();
				endDateWarning.setVisible(true);
				valid = false;
			}
			if (promotionNameField.getText().trim().equals("")) {
				promotionNameWarning.setVisible(true);
				valid = false;
			} else {
				promotionNameWarning.setVisible(false);
			}
			if (!equiValueField.getText().trim().matches("[\\d]+(\\.)?[\\d]*")) {
				equiValueWarning.setVisible(true);
				valid = false;
			} else {
				equiValueWarning.setVisible(false);
			}
			if (!useConditionField.getText().trim()
					.matches("[\\d]+(\\.)?[\\d]*")) {
				useConWarning.setVisible(true);
				valid = false;
			} else {
				useConWarning.setVisible(false);
			}
			if (!(discountRateField.getText().trim()
					.matches("[\\d]+(\\.)?[\\d]*") && Double
					.parseDouble(discountRateField.getText()) <= 10)) {
				disRateWarning.setVisible(true);
				valid = false;
			} else {
				disRateWarning.setVisible(false);
			}
			if (!SafeCheck.isLegalEndDate(startDate, endDate)) {
				endDateWarning.setVisible(true);
				valid = false;
			} else {
				endDateWarning.setVisible(false);
			}
			if (!SafeCheck.isLegalStartDate(startDate)) {
				startDateWarning.setVisible(true);
				valid = false;
			} else {
				startDateWarning.setVisible(false);
			}
			if (!leastLabelField.getText().matches("[\\d]+")) {
				leastIntegralWarning.setVisible(true);
				valid = false;
			} else {
				leastIntegralWarning.setVisible(false);
			}
			validate();
			if (!valid) {
				return;
			}
			try {
				name = promotionNameField.getText();
				discountRate = Double.parseDouble(discountRateField.getText());
				equivalentDenomination = Double.parseDouble(equiValueField
						.getText());
				bondUseLimit = Double.parseDouble(useConditionField.getText());
				leastIntegral = Integer.parseInt(leastLabelField.getText());
				ResultMessage resultMessage = Agent.promotionService
						.addPromotion(new PromotionPO(promotionID,
								leastIntegral, name, startDate, endDate,
								discountRate, equivalentDenomination,
								bondUseLimit));
				if (resultMessage.isInvokeSuccess()) {
					ImageDialog.showYesImage(this, "添加成功");
					equiValueField.setText(null);
					useConditionField.setText(null);
					startYearField.setText(null);
					startMonthField.setText(null);
					startDayfField.setText(null);
					endYearField.setText(null);
					endMonthField.setText(null);
					endDayfField.setText(null);
					discountRateField.setText(null);
				} else {
					ImageDialog.showNOImage(this, "添加失败");
					equiValueField.setText(null);
					useConditionField.setText(null);
					startYearField.setText(null);
					startMonthField.setText(null);
					startDayfField.setText(null);
					endYearField.setText(null);
					endMonthField.setText(null);
					endDayfField.setText(null);
					discountRateField.setText(null);
				}
			} catch (RemoteException e3) {
				e3.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitLabel && e.getButton() == MouseEvent.BUTTON1) {
			try {
				Agent.userService.logout(Agent.userAgent);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
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

	private MPanel initPanel(MPanel panel) {
		panel = new MPanel();
		panel.setSize(600, 400);
		panel.setLocation(180, 150);
		panel.setLayout(null);
		panel.setVisible(true);
		return panel;
	}
}
