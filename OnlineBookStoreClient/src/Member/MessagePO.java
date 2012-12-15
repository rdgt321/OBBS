package Member;

import java.io.Serializable;

public class MessagePO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6682108060775745741L;
	private int messageID;
	private int memberID;
	private String title;
	private String msg;

	public MessagePO(int messageID, int memberID, String title, String msg,
			boolean sent) {
		super();
		this.messageID = messageID;
		this.memberID = memberID;
		this.title = title;
		this.msg = msg;
		this.sent = sent;
	}

	private boolean sent;

	public int getMessageID() {
		return messageID;
	}

	public int getMemberID() {
		return memberID;
	}

	public String getTitle() {
		return title;
	}

	public String getMsg() {
		return msg;
	}

	public boolean isSent() {
		return sent;
	}
}
