package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import RMI.UserAgent;

public class Routines implements Runnable, Observer {
	private ArrayList<UserAgent> userAgents = null;
	private static Routines routines = null;
	private Queue<String> logQueue = null;

	private Routines() {
		UserPool.registry(this);
		userAgents = new ArrayList<UserAgent>();
		logQueue = new LinkedList<String>();
	};

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
			long loopStart = System.currentTimeMillis();
			checkOnline();
			checkBackUp();
			writeLog();
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
		for (UserAgent agent : userAgents) {
			long deadtime = System.currentTimeMillis() - agent.lastRequest;
			if (deadtime > Const.TIMEOUT * 60 * 1000) {
				UserPool.disconnect(agent);
			}
		}
	}

	private void checkBackUp() {
		String path = getClass().getResource("").getPath();
		int index = path.lastIndexOf("bin");
		path = path.substring(1, index);
		path = path + Const.BACKUPPATH;
		File backupFile = new File(path);
		long last = backupFile.lastModified();
		if (System.currentTimeMillis() - last > Const.BACKUP * 24 * 60 * 60
				* 1000) {
			dumpSQL();
		}
	}

	private void writeLog() {
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(new File(Const.LOGPATH),
					true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String log = null;
		while ((log = logQueue.poll()) != null) {
			try {
				br.write(log + "\r\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			br.flush();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void log(String log) {
		logQueue.add(log);
	}

	public boolean dumpSQL() {
		String path = getClass().getResource("").getPath();
		int index = path.lastIndexOf("bin");
		path = path.substring(1, index);
		path = path + Const.BACKUPPATH;
		System.out.println(path);
		String command = "cmd /c mysqldump -u " + Const.dbuser + " -p"
				+ Const.dbpass + " --database obss > " + path;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getMYSQLPath() {
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runtime
					.exec("reg query "
							+ "HKEY_LOCAL_MACHINE\\SYSTEM\\CURRENTCONTROLSET\\SERVICES\\MYSQL /v ImagePath");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String out = "";
		try {
			while ((out = in.readLine()) != null) {
				if (out.matches("(.*)ImagePath(.*)")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (out == null) {
			return null;
		}
		int index1 = out.indexOf(":");
		int index2 = out.indexOf("mysql");
		return out.substring(index1 - 1, index2);
	}

	public boolean loadSQL() {
		String path = getClass().getResource("").getPath();
		int index = path.lastIndexOf("bin");
		path = path.substring(1, index);
		path = path + Const.BACKUPPATH;
		System.out.println(path);
		String command = "cmd /c " + getMYSQLPath() + "mysql -u "
				+ Const.dbuser + " -p" + Const.dbpass + " < " + path;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean initSQL() {
		String path = getClass().getResource("").getPath();
		int index = path.lastIndexOf("bin");
		path = path.substring(1, index);
		path = path + Const.SQLPATH;
		String command = "cmd /c " + getMYSQLPath() + "mysql -u" + Const.dbuser
				+ " -p" + Const.dbpass + " < " + path;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		userAgents = (ArrayList<UserAgent>) arg;
	}
}
