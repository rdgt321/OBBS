package ClientRunner;

import java.awt.Image;
import java.awt.Toolkit;

public class IMGSTATIC {
	public static Image homepageBG;
	public static Image boring;
	public static Image otherBG;
	public static Image navigatorBG;
	public static Image loginBG;

	public static void startLoading() {
		homepageBG = Toolkit.getDefaultToolkit().getImage("materials/hpbg.png");
		boring = Toolkit.getDefaultToolkit().getImage("materials/boring.gif");
		otherBG = Toolkit.getDefaultToolkit().getImage("materials/otbg.png");
		navigatorBG = Toolkit.getDefaultToolkit().getImage(
				"materials/nvgtbg.png");
		loginBG = Toolkit.getDefaultToolkit().getImage("materials/login.png");

	}
}
