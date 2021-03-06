import java.io.IOException;
import java.net.*;


public class Caller {

	private String localNick;
	private InetAddress address;
	private int port;
	private String nick;
	private Socket socket;
	
	
	public Caller(String address, int port) throws IOException {
		this.port=port;
		localNick = "nick";
		this.address=this.address.getByName(address);
		socket = new Socket(this.address,port);
	}
	
	public Connection call() throws IOException {
	if(socket==null){
		socket = new Socket(address,port);
		}
	Connection c = new Connection(socket, localNick);
	//	c.sendNickHello(localNick);
	//	Command command = c.receive();
	//todo
	return c;
	}
	
	static enum CallStatus {
        BUSY, NO_SERVICE, NOT_ACCESSIBLE, OK, REJECTED
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
	public Socket getSocket(){
		return socket;
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
