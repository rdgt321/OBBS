package Server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import DBC.ConnectionFactory;
import RMI.UserAgent;

public class ServerView extends JPanel implements ActionListener, Observer {
	// 用户列表
	ArrayList<UserAgent> userAgents = new ArrayList<UserAgent>();

	// 初始化界面的Components
	private JTextField sqlUser = null;
	private JTextField sqlPass = null;
	private JTextArea sqlTip = null;
	private JLabel userLabel = null;
	private JLabel passLabel = null;
	private JLabel failLabel = null;
	private JButton sqlConfirm = null;

	// 服务器工作界面
	private JLabel onlineNum = null;
	private JLabel num = null;
	private tableModel model = null;
	private JScrollPane scrollPane = null;
	private JTable table = null;
	private JButton dumpConfirm = null;
	private JButton loadConfirm = null;
	private JButton config = null;
	private JButton log = null;

	public ServerView(int firstRun) {
		super();
		initViewForSQL(firstRun);
		initComponents(firstRun);
		UserPool.registry(this);
		setVisible(true);
		setSize(800, 600);
		setLayout(null);
		requestFocus();
	}

	public void initComponents(int firstRun) {
		if (firstRun == 1) {
			return;
		}
		onlineNum = new JLabel("在线用户数量:");
		onlineNum.setLayout(null);
		onlineNum.setSize(140, 50);
		onlineNum.setLocation(10, 100);
		onlineNum.setFont(new Font("楷体", Font.PLAIN, 20));

		num = new JLabel("0");
		num.setLayout(null);
		num.setSize(50, 50);
		num.setLocation(150, 100);
		num.setFont(new Font("黑体", Font.BOLD, 30));
		num.setForeground(Color.RED);

		dumpConfirm = new JButton("备份");
		dumpConfirm.setLayout(null);
		dumpConfirm.setSize(120, 50);
		dumpConfirm.setLocation(50, 170);
		dumpConfirm.setFont(new Font("楷体", Font.PLAIN, 30));
		dumpConfirm.addActionListener(this);

		loadConfirm = new JButton("还原");
		loadConfirm.setLayout(null);
		loadConfirm.setSize(120, 50);
		loadConfirm.setLocation(50, 250);
		loadConfirm.setFont(new Font("楷体", Font.PLAIN, 30));
		loadConfirm.addActionListener(this);

		config = new JButton("配置");
		config.setLayout(null);
		config.setSize(120, 50);
		config.setLocation(50, 330);
		config.setFont(new Font("楷体", Font.PLAIN, 30));
		config.addActionListener(this);

		log = new JButton("日志");
		log.setLayout(null);
		log.setSize(120, 50);
		log.setLocation(50, 410);
		log.setFont(new Font("楷体", Font.PLAIN, 30));
		log.addActionListener(this);

		model = new tableModel();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(550, 480);
		scrollPane.setLocation(210, 50);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(onlineNum);
		add(num);
		add(dumpConfirm);
		add(loadConfirm);
		add(config);
		add(log);
		add(scrollPane);
	}

	public void initViewForSQL(int firstRun) {
		if (firstRun == 0) {
			return;
		}
		sqlUser = new JTextField();
		sqlPass = new JPasswordField();
		sqlTip = new JTextArea("首次运行服务器配置，请输入您的一个具备数据库创建权限的MYSQL账户");
		userLabel = new JLabel("账户");
		passLabel = new JLabel("密码");
		failLabel = new JLabel("连接数据库失败，请重新输入");
		sqlConfirm = new JButton("确认");

		sqlUser.setLayout(null);
		sqlUser.setSize(250, 30);
		sqlUser.setLocation(300, 250);
		sqlUser.setFont(new Font("楷体", Font.PLAIN, 20));

		sqlPass.setLayout(null);
		sqlPass.setSize(250, 30);
		sqlPass.setLocation(300, 320);
		sqlPass.setFont(new Font("楷体", Font.PLAIN, 20));

		sqlTip.setLayout(null);
		sqlTip.setSize(480, 100);
		sqlTip.setLocation(150, 120);
		sqlTip.setFont(new Font("黑体", Font.BOLD, 20));
		sqlTip.setForeground(Color.RED);
		sqlTip.setEditable(false);
		sqlTip.setLineWrap(true);

		userLabel.setLayout(null);
		userLabel.setSize(50, 50);
		userLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		userLabel.setLocation(250, 240);

		passLabel.setLayout(null);
		passLabel.setSize(50, 50);
		passLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		passLabel.setLocation(250, 310);

		failLabel.setLayout(null);
		failLabel.setSize(300, 50);
		failLabel.setFont(new Font("黑体", Font.BOLD, 20));
		failLabel.setLocation(250, 370);
		failLabel.setForeground(Color.RED);
		failLabel.setVisible(false);

		sqlConfirm.setLayout(null);
		sqlConfirm.setSize(100, 50);
		sqlConfirm.setFont(new Font("楷体", Font.BOLD, 20));
		sqlConfirm.setLocation(500, 440);
		sqlConfirm.addActionListener(this);

		add(sqlUser);
		add(sqlPass);
		add(sqlTip);
		add(userLabel);
		add(passLabel);
		add(failLabel);
		add(sqlConfirm);
	}

	public void removeSQLView() {
		remove(sqlUser);
		remove(sqlPass);
		remove(sqlTip);
		remove(userLabel);
		remove(passLabel);
		remove(failLabel);
		remove(sqlConfirm);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sqlConfirm) {
			validateSQL();
		} else if (e.getSource() == dumpConfirm) {
			dumpSQL();
		} else if (e.getSource() == loadConfirm) {
			loadSQL();
		} else if (e.getSource() == config) {
			config();
		} else if (e.getSource() == log) {
			log();
		}
	}

	private void validateSQL() {
		String name = sqlUser.getText();
		String pass = sqlPass.getText();
		UserAgent userAgent = new UserAgent(0, name, pass, 0);
		Connection con = ConnectionFactory.firstConnection(userAgent);
		if (con == null) {
			sqlUser.setText("");
			sqlPass.setText("");
			failLabel.setVisible(true);
			sqlUser.requestFocus();
			return;
		}
		boolean success = Routines.getInstance().initSQL();
		if (success) {
			Const.store("FIRSTRUN", "0");
			Const.store("dbuser", name);
			Const.store("dbpass", pass);
			removeSQLView();
			initComponents(Const.FIRST_RUN);
		} else {
			failLabel.setText("未知原因 构建数据库结构失败，请先尝试添加MYSQL环境变量 如问题没有解决请请联系客服人员");
			failLabel.setVisible(true);
		}
	}

	private void dumpSQL() {
		boolean success = Routines.getInstance().dumpSQL();
		if (success) {
			JOptionPane.showMessageDialog(this, "备份成功");
			return;
		}
		JOptionPane.showMessageDialog(this, "备份失败，请联系技术人员");
	}

	private void loadSQL() {
		boolean success = Routines.getInstance().loadSQL();
		if (success) {
			JOptionPane.showMessageDialog(this, "还原成功");
		}
		JOptionPane.showMessageDialog(this, "还原失败，请联系技术人员");
	}

	private void config() {

	}

	private void log() {

	}

	class tableModel extends AbstractTableModel {
		String[] columns = { "用户ID", "用户名", "密码(加密)", "用户类型", "用户最后响应" };

		@Override
		public int getRowCount() {
			return userAgents.size();
		}

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return userAgents.get(rowIndex).getId();
			case 1:
				return userAgents.get(rowIndex).getName();
			case 2:
				return userAgents.get(rowIndex).getPassword();
			case 3:
				return userAgents.get(rowIndex).getUserType();
			case 4:
				return new Date(userAgents.get(rowIndex).lastRequest);
			default:
				return null;
			}
		}

		@Override
		public String getColumnName(int column) {
			return columns[column];
		}
	}

	private void fitColumn() {
		JTableHeader header = table.getTableHeader();
		int rowCount = table.getRowCount();

		Enumeration columns = table.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(
					column.getIdentifier());
			int width = (int) table
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(table,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) table
						.getCellRenderer(row, col)
						.getTableCellRendererComponent(table,
								table.getValueAt(row, col), false, false, row,
								col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // this line is very important
			column.setWidth(width + table.getIntercellSpacing().width);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		userAgents = (ArrayList<UserAgent>) arg;
		num.setText(userAgents.size() + "");
		model.fireTableDataChanged();
		fitColumn();
	}
}
