package Book;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RankPanel extends JPanel implements MouseListener{

	private final int SIZE = 10;
	private BookPO[] bookPO;
	private JLabel[] rankLabels, nameLabels;
	
	public RankPanel(BookPO[] bookPO){
		super();
		this.bookPO = bookPO;
		setLayout(null);
		setSize(220, 400);
		setVisible(true);
		JLabel ranklabel = new JLabel("销售排行榜");
		ranklabel.setSize(150, 50);
		ranklabel.setFont(new Font("宋体", Font.BOLD, 20));
		ranklabel.setLocation(45, 5);
		add(ranklabel);
		init();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 220, 400);
		g.setColor(Color.GRAY);
		g.draw3DRect(0, 0, 200, 390, true);
	}
	
	public void init(){
//		for(int i = 0; i < bookPO.length; i ++){
		for(int i = 0; i < 10; i ++){
//			rankLabel = new JLabel[bookPO.length];
			rankLabels = new JLabel[SIZE];
			rankLabels[i] = new JLabel("" + (i+1) + ".");
			rankLabels[i].setSize(30, 25);
			rankLabels[i].setFont(new Font("宋体", Font.PLAIN, 20));
			rankLabels[i].setForeground(Color.red);
			rankLabels[i].setLocation(10 , 55 + 33 * i);
			
			nameLabels = new JLabel[SIZE];
//			nameLabels[i] = new JLabel(bookPO[i].getName());
			nameLabels[i] = new JLabel("少年Pi的奇幻漂流");
			nameLabels[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			nameLabels[i].setForeground(Color.red);
			nameLabels[i].setSize(150, 25);
			nameLabels[i].setFont(new Font("楷体_gb2312", Font.PLAIN, 18));
			nameLabels[i].setLocation(41 , 55 + 33 * i);
			nameLabels[i].addMouseListener(this);
			
			add(rankLabels[i]);
			add(nameLabels[i]);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < bookPO.length; i ++){
			if(e.getSource() == nameLabels[i]){
				
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
