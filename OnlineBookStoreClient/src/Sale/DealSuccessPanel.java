package Sale;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;

import ClientRunner.GIFImage;
import ClientRunner.IMGSTATIC;
import ClientRunner.MButton;
import ClientRunner.MPanel;

@SuppressWarnings("serial")
public class DealSuccessPanel extends MPanel implements ActionListener {
	private SaleUIController saleUIController;
	private JLabel promptLabel;
	private MButton returnButton;
	GIFImage image = new GIFImage(new File("materials/success.gif"), 1, true);
	Thread repaintThread = new Thread(new repaint());

	public DealSuccessPanel(SaleUIController saleUIController) {
		this.saleUIController = saleUIController;
	}

	public void init() {
		setLayout(null);
		setSize(800, 530);
		setLocation(0, 70);

		promptLabel = new JLabel("交易成功!");
		promptLabel.setSize(120, 50);
		promptLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 25));
		promptLabel.setLocation(330, 80);

		returnButton = new MButton("返回");
		returnButton.setSize(80, 40);
		returnButton.setFocusable(false);
		returnButton.setFont(new Font("楷体_gb2312", Font.PLAIN, 23));
		returnButton.setLocation(545, 385);
		returnButton.addActionListener(this);

		add(promptLabel);
		add(returnButton);
		repaintThread.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (IMGSTATIC.homepageBG != null) {
			Composite composite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.8f));
			g2d.drawImage(IMGSTATIC.homepageBG, 0, 0, 800, 530, this);
			image.paintComponent(g2d, 335, 150);
			g2d.setComposite(composite);
		}
		g2d.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == returnButton) {
			saleUIController.getMainFrame().getBookUIController()
					.setReturnView();
		}
	}

	class repaint implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}

	}

}
