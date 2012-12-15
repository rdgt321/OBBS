package Sale;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PaymentPanel extends JPanel implements ActionListener,
		ItemListener{
	private SaleUIController saleUIController;
	private JPanel crecardpanel;
	private JLabel addressLabel, payTypeLabel;
	private JTextField addressField;
	private JButton ensureButton;
	private JRadioButton creditCardButton, cashButton, icbcButton, abcButton, ccbButton;

	public PaymentPanel(SaleUIController saleUIController) {
		this.saleUIController = saleUIController;
	}

	public void init() {
		setSize(800, 520);
		setLocation(5, 75);
		setVisible(true);
		setLayout(null);

		addressLabel = new JLabel("收获地址：");
		addressLabel.setSize(120, 40);
		addressLabel.setLocation(100, 40);
		addressLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		addressField = new JTextField(60);
		addressField.setSize(300, 40);
		addressField.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		addressField.setLocation(220, 40);
		
		payTypeLabel = new JLabel("付款方式：");
		payTypeLabel.setSize(120, 40);
		payTypeLabel.setLocation(100, 110);
		payTypeLabel.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		
		creditCardButton = new JRadioButton("信用卡");
		creditCardButton.setSize(100, 40);
		creditCardButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		creditCardButton.setFocusable(false);
		creditCardButton.setLocation(230, 110);
		creditCardButton.addActionListener(this);
		
		cashButton = new JRadioButton("货到付款");
		cashButton.setSize(130, 40);
		cashButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		cashButton.setFocusable(false);
		cashButton.setSelected(true);
		cashButton.setLocation(330, 110);
		cashButton.addActionListener(this);
		
		ButtonGroup group = new ButtonGroup();
		group.add(creditCardButton);
		group.add(cashButton);
		
		ensureButton = new JButton("确认");
		ensureButton.setSize(80, 40);
		ensureButton.setLocation(550, 300);
		ensureButton.setFocusable(false);
		ensureButton.setFont(new Font("楷体_gb2312", Font.BOLD, 20));
		ensureButton.addActionListener(this);

		
		
		this.add(addressLabel);
		this.add(addressField);
		this.add(payTypeLabel);
		this.add(creditCardButton);
		this.add(cashButton);		
		this.add(ensureButton);
	}

	private void createCreCard() {
		crecardpanel = new JPanel();
		
		crecardpanel.setSize(500, 100);
		crecardpanel.setVisible(true);
		crecardpanel.setLayout(null);

		JLabel prompt = new JLabel("请选择您所要进行交易的银行");
		prompt.setSize(300, 30);
		prompt.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		prompt.setLocation(0, 5);
		
		icbcButton = new JRadioButton("中国工商银行");
		icbcButton.setSize(150, 30);
		icbcButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		icbcButton.setFocusable(false);
		icbcButton.setSelected(true);
		icbcButton.setLocation(0, 40);
		
		abcButton = new JRadioButton("中国农业银行");
		abcButton.setSize(150, 30);
		abcButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		abcButton.setFocusable(false);
		abcButton.setLocation(160, 40);
		
		ccbButton = new JRadioButton("中国建设银行");
		ccbButton.setSize(150, 30);
		ccbButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		ccbButton.setFocusable(false);
		ccbButton.setLocation(320, 40);
		
		ButtonGroup group = new ButtonGroup();
		group.add(icbcButton);
		group.add(abcButton);
		group.add(ccbButton);
		
		crecardpanel.add(prompt);
		crecardpanel.add(icbcButton);
		crecardpanel.add(abcButton);
		crecardpanel.add(ccbButton);
		crecardpanel.setLocation(120, 160);
		this.add(crecardpanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ensureButton) {
			
		}else if(e.getSource() == creditCardButton){
			if(crecardpanel == null){
				createCreCard();
			}
			icbcButton.setEnabled(true);
			abcButton.setEnabled(true);
			ccbButton.setEnabled(true);
			crecardpanel.setVisible(true);
			validate();
		}else if (e.getSource() == cashButton){
			if(crecardpanel != null){
				icbcButton.setEnabled(false);
				abcButton.setEnabled(false);
				ccbButton.setEnabled(false);
				crecardpanel.setVisible(false);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

	
	public SaleUIController getSaleUIController(){
		return saleUIController;
	}
}
