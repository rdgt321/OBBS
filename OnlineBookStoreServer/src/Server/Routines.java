package Server;

import java.util.ArrayList;
import java.util.Calendar;

import RMI.UserAgent;

public class Routines implements Runnable {
	ArrayList<UserAgent> userAgents = null;

	@Override
	public void run() {
		while (true) {
			long loopStart = System.currentTimeMillis();
			checkOnline();

			
			
			
			
			long loopEnd = System.currentTimeMillis();
			long sleeptime = Const.ROUTINE * 1000 - (loopEnd - loopStart);
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkOnline() {
		userAgents = UserPool.getAgents();
		for (UserAgent agent : userAgents) {
			long deadtime = System.currentTimeMillis() - agent.lastRequest;
			if (deadtime > Const.TIMEOUT * 60 * 1000) {
				UserPool.disconnect(agent);
			}
		}
	}
}
