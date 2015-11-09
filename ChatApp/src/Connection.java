
import java.io.DataInputStream;
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
	private DataInputStream in;

	public void disconnect() throws IOException {
		out.write("DISCONNECT\n".getBytes());
		out.flush();
		out.close;
		socket.close();
	}

	public Connection(String IP, String Nick) {
		try {
			socket = new Socket(InetAddress.getByName(IP), port);
			os = socket.getOutputStream();
			ds = new DataOutputStream(os);
			in = new DataInputStream(socket.getInputStream());
			//m.setTo(IP);
			//m.setFrom(socket.getInetAddress().getHostName());
			nick = Nick;
			System.out.println(IP + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public Connection(Socket s, String Nick) {
		try {
			socket = s;
			os = socket.getOutputStream();
			ds = new DataOutputStream(os);
			in = new DataInputStream(socket.getInputStream());
			nick = Nick;
			System.out.println(IP + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void accept() throws IOException {
		out.write("ACCEPT\n".getBytes());
		ds.flush();
	}

	public void reject() throws IOException {
		ds.write("REJECTED\n".getBytes());
		ds.flush();
	}

	public void sendNickHello(String nick1) throws IOException {
		out.write(("ChatApp 2015 " + nick+ "\n").getBytes());
		out.flush();
	}

	public void sendNickBusy(String nick1) throws IOException {
		out.write(("ChatApp 2015 " + nick + " busy" + "\n").getBytes());
		out.flush();
	}

	public void sendMessage(String text) {
		out.write("MESSAGE\n".getBytes());
		out.write(text.getBytes());
		out.flush();
	}

	public Command receive() throws IOException {
		String command = "";
		int n;
		while (true) {
			if((n = in.read()) == '\n') {
					break;
			} else
				command += (char) n;
		}
		Command comand = new Command(command);
		return comand;
	}

}
