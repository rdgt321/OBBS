package ClientRunner;

public class Routines {
	private static Routines routines = null;

	private Routines() {

	}

	public static Routines getInstance() {
		if (routines == null) {
			synchronized (Routines.class) {
				if (routines == null) {
					routines = new Routines();
				}
			}
		}
		return routines;
	}
}
