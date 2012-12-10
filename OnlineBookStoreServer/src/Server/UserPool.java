package Server;

import java.util.ArrayList;

import RMI.UserAgent;

public class UserPool {
	private static ArrayList<UserAgent> userAgents = new ArrayList<UserAgent>();

	public synchronized static ArrayList<UserAgent> getAgents() {
		return userAgents;
	}

	public synchronized static void connect(UserAgent userAgent) {
		userAgents.add(userAgent);
	}

	public synchronized static void disconnect(UserAgent userAgent) {
		for (UserAgent agent : userAgents) {
			if (agent.getId() == userAgent.getId()
					&& agent.getName() == userAgent.getName()) {
				userAgents.remove(agent);
				break;
			}
		}
	}

	public synchronized static void onlineValidate(UserAgent userAgent) {
		for (UserAgent agent : userAgents) {
			if (agent.getId() == userAgent.getId()
					&& agent.getName() == userAgent.getName()) {
				agent.lastRequest = System.currentTimeMillis();
				break;
			}
		}
	}
}
