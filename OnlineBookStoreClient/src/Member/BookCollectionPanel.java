package Member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class BookCollectionPanel extends JPanel implements MouseListener,
		ActionListener {
	private final int MAX_NUMBER = 10;
	private MemberUIController memberUIController;
	private String[] collectionBook = { "文学", "小说", "经济", "管理", "考试", "时尚",
			"少儿", "旅游" };
	private JLabel[] bookLabels;
	private JButton[] deleteButtons;
	private JButton[] addCartButton;
	private JScrollPane scrollPane;
	private int size = collectionBook.length;
	private JTable[] catalog;
	private String data[][][];
	private int numberOfBookCollected = 0;

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
		JPanel panel = null;
		if(numberOfBookCollected != 0){
			panel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					for (int i = 1; i <= size; i++) {
						g.draw3DRect(0, 0, 765, 100 * i, true);

					}
				}
			};
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(785, 810));
			scrollPane.setViewportView(panel);

			bookLabels = new JLabel[size];
			addCartButton = new JButton[size];
			deleteButtons = new JButton[size];

			String[] column = { "书名", "作者", "价格" };
			data = new String[size][][];
			JScrollPane scroll[] = new JScrollPane[size];
			for (int i = 0; i < size; i++) {
				bookLabels[i] = new JLabel(collectionBook[i]);
				addCartButton[i] = new JButton("加入购物车");
				deleteButtons[i] = new JButton("删除");
				// catalog[i] = new JTable(data[i], column);
				// scrollPane[i] = new JScrollPane(catalog[i]);
				scroll[i] = new JScrollPane();

				bookLabels[i].setSize(80, 35);
				addCartButton[i].setSize(100, 30);
				deleteButtons[i].setSize(100, 30);
				scroll[i].setSize(500, 80);

				bookLabels[i].setLocation(10, 5 + 100 * i);
				addCartButton[i].setLocation(650, 15 + 100 * i);
				deleteButtons[i].setLocation(650, 60 + 100 * i);
				scroll[i].setLocation(100, 10 + 100 * i);

				bookLabels[i].setFont(new Font("楷体_gb2312", Font.BOLD, 22));
				addCartButton.clone()[i].addActionListener(this);
				deleteButtons[i].addActionListener(this);
				scroll[i].setFont(new Font("楷体_gb2312", Font.BOLD, 18));
				panel.add(bookLabels[i]);
				panel.add(addCartButton[i]);
				panel.add(deleteButtons[i]);
				panel.add(scroll[i]);
			}
		}
		else{
			JLabel nothingLabel = new JLabel("您当前还没有收藏任何图书,赶快收藏吧!");
			nothingLabel.setSize(400, 30);
			nothingLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
			nothingLabel.setForeground(Color.red);
			nothingLabel.setLocation(230, 100);
			
			panel = new JPanel(){
				public void paintComponent(Graphics g){
					super.paintComponent(g);
					ImageIcon imageIcon = new ImageIcon("materials/boring.gif");
					Image image = imageIcon.getImage();
					if(image != null){
						g.drawImage(image, 335, 210, 80, 80, this);
					}
				}
			};
			
			panel.setLayout(null);
			panel.setSize(785, 500);
			panel.add(nothingLabel);
			scrollPane.setViewportView(panel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < size; i++) {
			if (e.getSource() == deleteButtons[i]) {
				int confirm = JOptionPane.showConfirmDialog(this, "是否确认删除该图书？");
				if (confirm == JOptionPane.YES_OPTION) {
					// delete the book the customer choose
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
	
	public MemberUIController getMemberUIController(){
		return memberUIController;
	}

}
