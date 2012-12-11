package Server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observer;

import RMI.UserAgent;

public class UserPool {
	private static ArrayList<UserAgent> userAgents = new ArrayList<UserAgent>();
	private static ArrayList<Observer> observers = new ArrayList<Observer>();

	private static void update() {
		for (Observer observer : observers) {
			observer.update(null, userAgents);
		}
	}

	public static void registry(Observer observer) {
		observers.add(observer);
	}

	public synchronized static ArrayList<UserAgent> getAgents() {
		return userAgents;
	}

	public synchronized static boolean isOnline(UserAgent userAgent) {
		for (UserAgent agent : userAgents) {
			if (agent.getId() == userAgent.getId()
					&& agent.getName().equals(userAgent.getName())
					&& agent.getUserType() == userAgent.getUserType()) {
				return true;
			}
		}
		return false;
	}

	public synchronized static void connect(UserAgent userAgent) {
		userAgents.add(userAgent);
		update();
		Routines.getInstance().log(
				userAgent.toString() + " -connect" + " at "
						+ Calendar.getInstance().getTime());
	}

	public synchronized static void disconnect(UserAgent userAgent) {
		for (UserAgent agent : userAgents) {
			if (agent.getId() == userAgent.getId()
					&& agent.getName().equals(userAgent.getName())
					&& agent.getUserType() == userAgent.getUserType()) {
				userAgents.remove(agent);
				break;
			}
		}
		update();
		Routines.getInstance().log(
				userAgent.toString() + " -disconnect" + " at "
						+ Calendar.getInstance().getTime());
	}

	public synchronized static void onlineValidate(UserAgent userAgent) {
		for (UserAgent agent : userAgents) {
			if (agent.getId() == userAgent.getId()
					&& agent.getName().equals(userAgent.getName())
					&& agent.getUserType() == userAgent.getUserType()) {
				agent.lastRequest = System.currentTimeMillis();
				break;
			}
		}
		update();
		Routines.getInstance().log(
				userAgent.toString() + " -keep alive" + " at "
						+ Calendar.getInstance().getTime());
	}
}
