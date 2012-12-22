package ClientRunner;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.awt.AWTUtilities;

public class ImageDialog extends JDialog {
	private static ImageDialog IDG = null;
	private static JLabel label = null;
	private static Image img = null;
	private static ImageIcon yesicon = null;
	private static ImageIcon noicon = null;
	private static boolean reset = false;

	private ImageDialog() {
		setFocusable(false);
<<<<<<< HEAD
		setEnabled(false);
=======
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
	};

	static {
		img = new ImageIcon("materials\\2.png").getImage();
		yesicon = new ImageIcon("materials\\3.png");
		noicon = new ImageIcon("materials\\1.png");
		IDG = new ImageDialog();
		IDG.setUndecorated(true);
		IDG.setVisible(true);
		IDG.setAlwaysOnTop(true);
		IDG.setSize(300, 100);
		Shape shape = new RoundRectangle2D.Double(0, 0, 300, 100, 15D, 15D);
		AWTUtilities.setWindowShape(IDG, shape);
		label = new JLabel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, this);
				super.paint(g);
			}
		};
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(300, 100);
		label.setFont(new Font("楷体", Font.BOLD, 25));
		IDG.add(label);
	}

	public void reset() {
		reset = true;
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class FController implements Runnable {

		public void run() {
			reset = false;
			try {
				for (int i = 50; i >= 0; i--) {
					if (reset) {
						return;
					}
					Thread.sleep(50);
					IDG.setOpacity(((float) i) / 50);
					IDG.setLocation(IDG.getLocation().x,
							IDG.getLocation().y + 1);
					IDG.repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static public void showNOImage(JPanel panel, String str) {
		label.setText(str);
		label.setIcon(noicon);
		IDG.setLocationRelativeTo(panel);
		IDG.animate();
	}

	static public void showYesImage(JPanel panel, String str) {
		label.setText(str);
		label.setIcon(yesicon);
		IDG.setLocationRelativeTo(panel);
		IDG.animate();
	}

	public void animate() {
		reset();
		new Thread(new FController()).start();
	}
}
