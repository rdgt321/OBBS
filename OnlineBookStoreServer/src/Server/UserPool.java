package Server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observer;

import RMI.UserAgent;

public class UserPool {
	private static RBT userAgents = new RBT();
	private static ArrayList<Observer> observers = new ArrayList<Observer>();

	private static void update() {
		for (Observer observer : observers) {
			observer.update(null, userAgents);
		}
	}

	public static void registry(Observer observer) {
		observers.add(observer);
	}

	public synchronized static RBT getAgents() {
		return userAgents;
	}

	public synchronized static boolean isOnline(UserAgent userAgent) {
		UserAgent result = userAgents.search(userAgent).userAgent;
		return result != null;
	}

	public synchronized static void connect(UserAgent userAgent) {
		userAgents.insert(userAgent);
		update();
		Routines.getInstance().log(
				userAgent.toString() + " -connect" + " at "
						+ Calendar.getInstance().getTime());
	}

	public synchronized static void disconnect(UserAgent userAgent) {
		userAgents.delete(userAgent);
		update();
		Routines.getInstance().log(
				userAgent.toString() + " -disconnect" + " at "
						+ Calendar.getInstance().getTime());
	}

	public synchronized static void onlineValidate(UserAgent userAgent) {
		UserAgent agent = userAgents.search(userAgent).userAgent;
		if (agent == null) {
			return;
		}
		agent.ip = userAgent.ip;
		agent.lastRequest = System.currentTimeMillis();
		update();
		Routines.getInstance().log(
				userAgent.toString() + " -keep alive" + " at "
						+ Calendar.getInstance().getTime());
	}
}
