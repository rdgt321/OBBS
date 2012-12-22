package ClientRunner;

public class Routines implements Runnable {
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

	@Override
	public void run() {
		while (true) {
			if (Agent.alive) {
				Agent.onlineValidate();
			} else {
				Agent.startService();
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
