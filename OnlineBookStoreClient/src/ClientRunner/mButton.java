package ClientRunner;

import java.awt.Color;

public class mButton {
	private String context;
	private int x;
	private int y;
	int width;
	private int height1;
	int height2;
    private int size;
	private Color color;
	boolean isHighLighted;
    private float lightindex = 100;
	private float offsetindex = 0;
	
	public float getLightindex() {
		return lightindex;
	}
	public void setLightindex(float lightindex) {
		this.lightindex = lightindex;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public float getOffsetindex() {
		return offsetindex;
	}
	public void setOffsetindex(float offsetindex) {
		this.offsetindex = offsetindex;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight1() {
		return height1;
	}
	public void setHeight1(int height1) {
		this.height1 = height1;
	}
}
