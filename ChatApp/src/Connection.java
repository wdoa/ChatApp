
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
		ds.write("DISCONNECT\n".getBytes());
		ds.flush();
		ds.close();
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
			System.out.println(s.getInetAddress() + " is connected. Type message.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void accept() throws IOException {
		ds.write("ACCEPT\n".getBytes());
		ds.flush();
	}

	public void reject() throws IOException {
		ds.write("REJECTED\n".getBytes());
		ds.flush();
	}

	public void sendNickHello(String nick1) throws IOException {
		ds.write(("ChatApp 2015 " + nick+ "\n").getBytes());
		ds.flush();
	}

	public void sendNickBusy(String nick1) throws IOException {
		ds.write(("ChatApp 2015 " + nick + " busy" + "\n").getBytes());
		ds.flush();
	}

	public void sendMessage(String text) throws IOException {
		ds.write("MESSAGE\n".getBytes());
		ds.write(text.getBytes());
		ds.flush();
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
