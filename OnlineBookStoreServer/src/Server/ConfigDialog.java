package Server;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sun.print.resources.serviceui;
import sun.tools.jar.resources.jar;

public class ConfigDialog extends JDialog implements ActionListener,
		FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3504603928042951350L;

	// refer to serverView
	private ServerView serverView = null;

	// bg
	private Image bgimg = null;
	private Image iconimg = null;

	// components
	private JPanel contentPanel = null;
	private JLabel max_client = null;
	private JLabel time_auto_backup = null;
	private JLabel dbuser = null;
	private JLabel dbpass = null;
	private JLabel time_out = null;
	private JLabel routine = null;
	private JLabel second = null;
	private JLabel minute = null;
	private JLabel day = null;

	private JTextField max_client_field = null;
	private JTextField time_auto_backup_field = null;
	private JTextField dbuser_field = null;
	private JTextField dbpass_field = null;
	private JTextField time_out_field = null;
	private JTextField routine_field = null;
	// buttons
	private JButton confirmbtn = null;
	private JButton cancelbtn = null;
	private JButton defaultbtn = null;

	public ConfigDialog(ServerView serverView) {
		super();
		this.serverView = serverView;
		loadImgs();
		initComponent();
		setTitle("服务器参数配置");
		setIconImage(iconimg);
		setLayout(null);
		setSize(506, 473);
		setLocationRelativeTo(serverView);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void cover() {
		load();
		setLocationRelativeTo(serverView);
		setVisible(true);
	}

	private void loadImgs() {
		bgimg = Toolkit.getDefaultToolkit().getImage("materials\\cfgbg.png");
		iconimg = Toolkit.getDefaultToolkit()
				.getImage("materials\\cfgicon.png");
	}

	private void initComponent() {
		contentPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bgimg, 0, 0, 500, 450, this);
			}
		};
		contentPanel.setLayout(null);
		contentPanel.setSize(500, 450);
		max_client = new JLabel("最大并发用户数(10~500)");
		max_client.setSize(150, 50);
		max_client.setLocation(120, 40);

		time_auto_backup = new JLabel("自动备份周期(1~14)");
		time_auto_backup.setSize(150, 50);
		time_auto_backup.setLocation(120, 90);

		time_out = new JLabel("未响应丢失时间(5~15)");
		time_out.setSize(150, 50);
		time_out.setLocation(120, 140);

		routine = new JLabel("内部逻辑循环周期(1~30)");
		routine.setSize(150, 50);
		routine.setLocation(120, 190);

		dbuser = new JLabel("数据库用户");
		dbuser.setSize(150, 50);
		dbuser.setLocation(120, 240);

		dbpass = new JLabel("数据库密码");
		dbpass.setSize(150, 50);
		dbpass.setLocation(120, 290);

		second = new JLabel("秒");
		second.setSize(50, 50);
		second.setLocation(380, 190);

		minute = new JLabel("分");
		minute.setSize(50, 50);
		minute.setLocation(380, 140);

		day = new JLabel("天");
		day.setSize(50, 50);
		day.setLocation(380, 90);

		max_client_field = new JTextField();
		max_client_field.setSize(100, 30);
		max_client_field.setLocation(270, 50);
		max_client_field.addFocusListener(this);

		time_auto_backup_field = new JTextField();
		time_auto_backup_field.setSize(100, 30);
		time_auto_backup_field.setLocation(270, 100);
		time_auto_backup_field.addFocusListener(this);

		time_out_field = new JTextField();
		time_out_field.setSize(100, 30);
		time_out_field.setLocation(270, 150);
		time_out_field.addFocusListener(this);

		routine_field = new JTextField();
		routine_field.setSize(100, 30);
		routine_field.setLocation(270, 200);
		routine_field.addFocusListener(this);

		dbuser_field = new JTextField();
		dbuser_field.setSize(100, 30);
		dbuser_field.setLocation(270, 250);

		dbpass_field = new JPasswordField();
		dbpass_field.setSize(100, 30);
		dbpass_field.setLocation(270, 300);
		dbpass_field.setVisible(true);

		confirmbtn = new MButton("确定修改");
		confirmbtn.setSize(80, 30);
		confirmbtn.setLocation(200, 350);
		confirmbtn.addActionListener(this);

		defaultbtn = new MButton("默认配置");
		defaultbtn.setSize(80, 30);
		defaultbtn.setLocation(300, 350);
		defaultbtn.addActionListener(this);

		cancelbtn = new MButton("取消");
		cancelbtn.setSize(80, 30);
		cancelbtn.setLocation(400, 350);
		cancelbtn.addActionListener(this);

		contentPanel.add(max_client);
		contentPanel.add(time_auto_backup);
		contentPanel.add(time_out);
		contentPanel.add(routine);
		contentPanel.add(dbuser);
		contentPanel.add(dbpass);
		contentPanel.add(max_client_field);
		contentPanel.add(time_auto_backup_field);
		contentPanel.add(time_out_field);
		contentPanel.add(routine_field);
		contentPanel.add(dbuser_field);
		contentPanel.add(dbpass_field);
		contentPanel.add(second);
		contentPanel.add(minute);
		contentPanel.add(day);
		contentPanel.add(confirmbtn);
		contentPanel.add(defaultbtn);
		contentPanel.add(cancelbtn);
		setContentPane(contentPanel);
		load();
		validate();
		requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == defaultbtn) {
			defaultConfig();
		} else if (e.getSource() == cancelbtn) {
			dispose();
		} else if (e.getSource() == confirmbtn) {
			store();
		}
	}

	private void defaultConfig() {
		Const.store("MAX_CLIENT", "200");
		Const.store("BACKUP", "7");
		Const.store("dbuser", "");
		Const.store("dbpass", "");
		Const.store("TIMEOUT", "5");
		Const.store("ROUTINE", "10");
		load();
	}

	private void load() {
		max_client_field.setText(Const.MAX_CLIENT + "");
		time_auto_backup_field.setText(Const.BACKUP + "");
		dbuser_field.setText(Const.dbuser);
		dbpass_field.setText(Const.dbpass);
		time_out_field.setText(Const.TIMEOUT + "");
		routine_field.setText(Const.ROUTINE + "");
	}

	private void store() {
		Const.store("MAX_CLIENT", max_client_field.getText());
		Const.store("BACKUP", time_auto_backup_field.getText());
		Const.store("dbuser", dbuser_field.getText());
		Const.store("dbpass", dbpass_field.getText());
		Const.store("TIMEOUT", time_out_field.getText());
		Const.store("ROUTINE", routine_field.getText());
	}

	private boolean validateNumber(String string, int min, int max) {
		String reg = "\\d+";
		if (string.matches(reg)) {
			int num = Integer.parseInt(string);
			return num >= min && num <= max;
		}
		return false;
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == max_client_field) {
			if (validateNumber(max_client_field.getText(), 10, 500) == false) {
				max_client_field.requestFocus();
				ImageDialog.showNOImage(contentPanel, "输入不符合要求，请重新输入");
			}
		} else if (e.getSource() == time_auto_backup_field) {
			if (validateNumber(time_auto_backup_field.getText(), 1, 14) == false) {
				time_auto_backup_field.requestFocus();
				ImageDialog.showNOImage(contentPanel, "输入不符合要求，请重新输入");
			}
		} else if (e.getSource() == time_out_field) {
			if (validateNumber(time_out_field.getText(), 5, 15) == false) {
				time_out_field.requestFocus();
				ImageDialog.showNOImage(contentPanel, "输入不符合要求，请重新输入");
			}
		} else if (e.getSource() == routine_field) {
			if (validateNumber(routine_field.getText(), 1, 30) == false) {
				routine_field.requestFocus();
				ImageDialog.showNOImage(contentPanel, "输入不符合要求，请重新输入");
			}
		}
	}
}
