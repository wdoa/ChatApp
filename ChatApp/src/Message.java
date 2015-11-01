
import java.io.*;
import java.util.Date;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date = new Date();
	private String from;
	private String to;
	private String text;
	private String nick;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date.toString()).append(", From: ").append(from).append(", To: ")
				.append(to).append("] ").append(text).toString();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void writeToStream(OutputStream out) throws IOException {
		send(out, this);
	}

	private void send(OutputStream out, Object o) throws IOException {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		try {
			os.writeObject(o);
		} finally {
			os.flush();
			os.close();
		}

		byte[] packet = bs.toByteArray();

		DataOutputStream ds = new DataOutputStream(out);
		ds.writeInt(packet.length);
		ds.write(packet);
		ds.flush();
		;
	}

	public void sendNickHello(OutputStream out, String nick1) throws IOException {
		String s = "Hello, " + nick + ", my name is " + nick1;

		send(out, s);
	}

	public void sendNickBusy(OutputStream out, String nick1) throws IOException {
		String s = nick1 + ", i'm busy now";
		send(out, s);
	}

	public static Message readFromStream(InputStream in) throws IOException, ClassNotFoundException {
		if (in.available() <= 0)
			return null;

		DataInputStream ds = new DataInputStream(in);
		int len = ds.readInt();
		byte[] packet = new byte[len];
		ds.read(packet);

		ByteArrayInputStream bs = new ByteArrayInputStream(packet);
		ObjectInputStream os = new ObjectInputStream(bs);
		try {
			Message msg = (Message) os.readObject();
			return msg;
		} finally {
			os.close();
		}
	}
}