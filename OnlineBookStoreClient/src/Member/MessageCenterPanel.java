package Member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ClientRunner.Agent;
import ClientRunner.MPanel;
import RMI.ResultMessage;

@SuppressWarnings("serial")
public class MessageCenterPanel extends MPanel implements MouseListener {
	private MemberUIController memberUIController;
	private MPanel side_barPanel;
	private JTextArea detail;
	private ArrayList<MessagePO> list;
	private int size;
	private JLabel[] name;

	public MessageCenterPanel(MemberUIController memberUIController) {
		this.memberUIController = memberUIController;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		setLayout(null);
		setSize(600, 430);
		try {
			ResultMessage resultMessage = Agent.memberService
					.getMessage(Agent.userAgent);
			list = resultMessage.getResultSet();
			if (list != null) {
				size = list.size();
			} else {
				size = 0;
			}
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		side_barPanel = new MPanel();
		side_barPanel.setLayout(null);

		JLabel title = new JLabel("站内信");
		title.setSize(60, 30);
		title.setForeground(Color.red);
		title.setFont(new Font("楷体_gb2312", Font.PLAIN, 20));
		title.setLocation(40, 10);

		side_barPanel.add(title);
		side_barPanel.setPreferredSize(new Dimension(140, 25 + size * 37));
		side_barPanel.setLocation(0, 0);

		name = new JLabel[size];

		for (int i = 0; i < size; i++) {
			name[i] = new JLabel(list.get(i).getTitle());
			if (!list.get(i).isSent()) {
				name[i].setForeground(Color.red);
			}
			name[i].setLocation(5, 52 + 37 * i);
			name[i].setFont(new Font("楷体_gb2312", Font.PLAIN, 16));
			name[i].setHorizontalAlignment(SwingConstants.CENTER);
			name[i].addMouseListener(this);
			name[i].setSize(156, 20);
			side_barPanel.add(name[i]);
		}

		detail = new JTextArea();
		detail.setSize(440, 430);
		detail.setText("  尚未查看信息");
		detail.setFont(new Font("楷体_gb2312", Font.PLAIN, 50));
		detail.setLineWrap(true);
		detail.setEditable(false);
		detail.setLocation(161, 2);
		detail.setOpaque(false);
		add(detail);

		JScrollPane scrollPane = new JScrollPane(side_barPanel);
		scrollPane.setSize(160, 430);
		scrollPane.setLocation(0, 0);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane);
	}

	public MemberUIController getMemberUIController() {
		return memberUIController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < size; i++) {
			if (e.getSource() == name[i] && e.getButton() == MouseEvent.BUTTON1) {
				try {
					if (!list.get(i).isSent()) {
						Agent.memberService.readMessage(list.get(i)
								.getMessageID());
						name[i].setForeground(Color.black);
					}
					detail.setText("    " + list.get(i).getMsg());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}