
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
	private final int port = 28411;
	private OutputStream os;
	public Socket socket;
	//private Message m = null;
	private String nick;
	private DataOutputStream ds;

	public void disconnect() throws IOException {
		out.writeUTF("DISCONNECT");
		out.flush();
		out.close;
		socket.close();
	}

	public Connection(String IP, String Nick) {
		try {
			socket = new Socket(InetAddress.getByName(IP), port);
			os = socket.getOutputStream();
			DataOutputStream ds = new DataOutputStream(os);
			//m.setTo(IP);
			//m.setFrom(socket.getInetAddress().getHostName());
			nick = Nick;
			System.out.println(IP + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void accept() throws IOException {
		out.writeUTF("ACCEPT");
		ds.flush();
	}

	public void reject() throws IOException {
		ds.writeUTF("REJECTED");
		ds.flush();
	}

	public void sendNickHello(String nick1) throws IOException {
		out.writeUTF("ChatApp 2015 " + nick);
		out.flush();
	}

	public void sendNickBusy(String nick1) throws IOException {
		out.writeUTF("ChatApp 2015 " + nick + " busy");
		out.flush();
	}

	public void sendMessage(String text) {
		out.writeUTF("MESSAGE");
		out.writeUTF(text);
		out.flush();
	}

}
