package ClientRunner;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class MTextField extends JTextField {
	GradientPaint p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, 69,
			new Color(0, 0, 0));
	GradientPaint p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100),
			0, 67, new Color(0, 0, 0, 50));

	public MTextField() {
		super();
		setOpaque(false);
	}
	
	public MTextField(int columns){
		super(columns);
		setOpaque(false);
	}

	@Override
	public void paintBorder(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, width - 1, height - 1, 20, 20);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, width - 3, height - 3, 18, 18);
		g2d.dispose();
	}

}
