package ClientRunner;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.imageio.plugins.gif.GIFImageMetadata;

public class GIFImage {
	private int x, y;
	private ImageReader reader;
	private int count = 0;
	protected GifFrame[] frames;
	protected Map<Integer, Integer[]> frameMap = new HashMap<Integer, Integer[]>();
	protected int index = 0;
	private int delayFactor;
	private boolean isLoop = false;
	protected boolean over = false;

	private Thread repaintThread = new Thread(new Delay());

	public void paintComponent(Graphics g) {
		g.drawImage(frames[0].image, frames[0].x, frames[0].y, null);
		if (index > 0) {
			Integer[] array = frameMap.get(index);
			for (Integer i : array) {
				g.drawImage(frames[i].image, frames[i].x, frames[i].y, null);
			}
		}
	}

	public void paintComponent(Graphics g, int imgx, int imgy) {
		g.drawImage(frames[0].image, frames[0].x + imgx, frames[0].y + imgy,
				null);
		if (index > 0) {
			Integer[] array = frameMap.get(index);
			for (Integer i : array) {
				g.drawImage(frames[i].image, frames[i].x + imgx, frames[i].y
						+ imgy, null);
			}
		}
	}

	private int getFirstIndex(int index) {
		int tempIndex = index;
		while (tempIndex > 1) {
			if (tempIndex - 1 > 0 && frames[tempIndex - 1].disposalMethod == 2) {
				return index;
			}
			tempIndex--;
		}
		return tempIndex;
	}

	public GIFImage(File gifFile, int delayFactor, boolean isLoop) {
		this.isLoop = isLoop;
		this.delayFactor = delayFactor;
		try {
			ImageInputStream imageIn = ImageIO.createImageInputStream(gifFile);
			Iterator<ImageReader> iter = ImageIO
					.getImageReadersByFormatName("gif");
			if (iter.hasNext()) {
				reader = iter.next();
			}
			reader.setInput(imageIn, false);
			count = reader.getNumImages(true);
			frames = new GifFrame[count];
			for (int i = 0; i < count; i++) {
				frames[i] = new GifFrame();
				frames[i].image = reader.read(i);
				frames[i].x = ((GIFImageMetadata) reader.getImageMetadata(i)).imageLeftPosition;
				frames[i].y = ((GIFImageMetadata) reader.getImageMetadata(i)).imageTopPosition;
				frames[i].width = ((GIFImageMetadata) reader
						.getImageMetadata(i)).imageWidth;
				frames[i].height = ((GIFImageMetadata) reader
						.getImageMetadata(i)).imageHeight;
				frames[i].disposalMethod = ((GIFImageMetadata) reader
						.getImageMetadata(i)).disposalMethod;
				frames[i].delayTime = ((GIFImageMetadata) reader
						.getImageMetadata(i)).delayTime;
				if (frames[i].delayTime == 0) {
					frames[i].delayTime = 1;
				}
			}
			for (int i = 1; i < count; i++) {
				if (frames[i].disposalMethod == 2) {
					// restoreToBackgroundColor
					frameMap.put(new Integer(i), new Integer[] { i });
					continue;
				}
				// doNotDispose
				int firstIndex = getFirstIndex(i);
				List<Integer> l = new ArrayList<Integer>();
				for (int j = firstIndex; j <= i; j++) {
					l.add(j);
				}
				frameMap.put(new Integer(i), l.toArray(new Integer[] {}));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaintThread.start();
	}

	private class Delay implements Runnable {

		@Override
		public void run() {
			while (!over) {
				try {
					Thread.sleep(frames[index].delayTime * delayFactor);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				index++;
				if (index >= count && !isLoop) {
					over = true;
				} else if (index >= count && isLoop) {
					index = 0;
				}
			}
		}
	}

	class GifFrame implements Serializable {
		public BufferedImage image;
		public int x;
		public int y;
		public int width;
		public int height;
		public int disposalMethod;
		public int delayTime;
	}
}
