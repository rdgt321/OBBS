package ClientRunner;

import java.awt.AlphaComposite;
import java.awt.Color;
<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
=======
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

<<<<<<< HEAD
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
=======
import javax.swing.JComboBox;
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca

public class MComboBox<E> extends JComboBox {
	GradientPaint p1;
	GradientPaint p2;

	public MComboBox() {
		super();
		setOpaque(false);
	}

	public MComboBox(E[] es) {
		super(es);
		setOpaque(false);
	}

	@Override
	protected void paintBorder(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, height - 1,
				new Color(100, 100, 100));
		p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, height - 3,
				new Color(255, 255, 255, 100));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.7f));
		GradientPaint gp = new GradientPaint(0.0F, 0.0F, new Color(205, 255,
				205), 0.0F, height, new Color(255, 255, 255), true);
		g2d.setPaint(gp);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0,
				width - 1, height - 1, 20, 20);
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		g2d.fillRect(0, 0, width, height);
		g2d.setClip(clip);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, width - 1, height - 1, 20, 20);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, width - 3, height - 3, 18, 18);
		g2d.dispose();
<<<<<<< HEAD
=======
		super.paintBorder(g);
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
	}
}