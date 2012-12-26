package ClientRunner;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import javax.swing.JSlider;

public class MSlider extends JSlider {
	private boolean hover = false;
	private boolean enabled = false;
	private final int rw = 360;
	private final int w = 300;
	private final int h = 60;
	private GradientPaint p = new GradientPaint(0, 0, new Color(254, 254, 171),
			w - 1, h - 1, new Color(251, 251, 13));
	GradientPaint p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0,
			h - 1, new Color(0, 0, 0));
	GradientPaint p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100),
			0, h - 3, new Color(0, 0, 0, 50));

	private Point[] offset = new Point[10];

	public MSlider() {
		setSize(rw, h);
		setOpaque(false);
		setLocation(300, 20);
		setMaximum(50);
		setMinimum(0);
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (enabled) {
					setValue(e.getX() / 6 + (e.getX() % 6 > 0 ? 1 : 0));
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (enabled) {
					setValue(e.getX() / 6 + (e.getX() % 6 > 0 ? 1 : 0));
					enabled = false;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				hover = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hover = false;
				repaint();
			}
		});
		enabled = true;
	}

	public MSlider(double rate) {
		this();
		setValue((int) (rate * 10));
		enabled = false;
	}

	public void EnableRating() {
		enabled = true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void pushOffset(int x, int y, int r) {
		double trans = 2 * Math.PI / 360;
		double t = r * 0.3819660112501;
		offset[0] = new Point(x, y - r);
		offset[1] = new Point(x + (int) (t * Math.cos(54 * trans)),
				(int) (y - t * Math.sin(54 * trans)));
		offset[2] = new Point((int) (x + r * Math.cos(18 * trans)),
				(int) (y - r * Math.sin(18 * trans)));
		offset[3] = new Point((int) (x + t * Math.cos(18 * trans)),
				(int) (y + t * Math.sin(18 * trans)));
		offset[4] = new Point((int) (x + r * Math.cos(54 * trans)),
				(int) (y + r * Math.sin(54 * trans)));
		offset[5] = new Point(x, (int) (y + t));
		offset[6] = new Point((int) (x - r * Math.cos(54 * trans)),
				(int) (y + r * Math.sin(54 * trans)));
		offset[7] = new Point((int) (x - t * Math.cos(18 * trans)),
				(int) (y + t * Math.sin(18 * trans)));
		offset[8] = new Point((int) (x - r * Math.cos(18 * trans)),
				(int) (y - r * Math.sin(18 * trans)));
		offset[9] = new Point((int) (x - t * Math.cos(54 * trans)),
				(int) (y - t * Math.sin(54 * trans)));
	}

	public double getRate() {
		return (double) getValue() / 10.0;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (!hover) {
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.7f));
		}
		for (int j = 0; j < 5; j++) {
			int x = w / 10 + w / 5 * j;
			int y = h / 2;
			int r = w / 10;
			// 计算五个顶点
			pushOffset(x, y, r);
			GeneralPath star = new GeneralPath();
			star.moveTo(offset[0].x, offset[0].y);
			for (int i = 1; i < 10; i++) {
				star.lineTo(offset[i].x, offset[i].y);
			}
			star.closePath();
			Shape clip = g2d.getClip();
			g2d.clip(star);
			g2d.setPaint(p);
			g2d.fillRect(0, 0, 6 * getValue(), h);
			g2d.setClip(clip);
			g2d.setPaint(p1);
			g2d.draw(star);
			g2d.setPaint(p2);
			g2d.draw(star);
		}
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("微软雅黑", Font.BOLD, 30));
		g2d.drawString(String.valueOf((double) getValue() / 10), w, h * 2 / 3);
		g2d.dispose();
	}
}
