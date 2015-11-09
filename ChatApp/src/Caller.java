import java.io.IOException;
import java.net.*;


public class Caller {

	private String localNick;
	private InetAddress address;
	private int port;
	private String nick;
	private Socket socket;
	
	
	public Caller(InetAddress address, int port) throws IOException {
		this.address=address;
		this.port=port;
		localNick = "nick";
		socket = new Socket(address,port);
	}
	
	public Connection call() throws IOException {
	Connection c = new Connection(socket, localNick);
	c.sendNickHello(localNick);
	Command command = c.receive();
	//todo
	return null;
	}
	
	
	public String getLocalNick() {
		return localNick;
	}

	public void setLocalNick(String localNick) {
		this.localNick = localNick;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}


}
