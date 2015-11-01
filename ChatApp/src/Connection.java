
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
	private final int port = 28411;
	private OutputStream os;
	public Socket socket;
	private Message m = null;
	// private String nick;

	public void disconnect() throws IOException {
		socket.close();
	}

	public Connection(String IP, String Nick) {
		try {
			socket = new Socket(IP, port);
			os = socket.getOutputStream();
			m.setTo(IP);
			m.setFrom(socket.getInetAddress().getHostName());
			m.setNick(Nick);
			System.out.println(IP + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void accept() throws IOException {
		DataOutputStream ds = new DataOutputStream(os);
		ds.writeBoolean(true);
		ds.flush();
	}

	public void reject() throws IOException {
		DataOutputStream ds = new DataOutputStream(os);
		ds.writeBoolean(false);
		ds.flush();
	}

	public void sendNickHello(String nick1) throws IOException {
		m.sendNickHello(os, nick1);
	}

	public void sendNickBusy(String nick1) throws IOException {
		m.sendNickBusy(os, nick1);
	}

	public void sendMessage(String text) {
		m = new Message();
		m.setFrom(socket.getInetAddress().getHostName());

		m.setText(text);
		try {
			m.writeToStream(os);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

}
