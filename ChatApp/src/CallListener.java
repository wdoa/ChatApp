import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.io.*;

public class CallListener {
	public String userName;
	public Boolean statusBusy;
	private final int localPort = 28411;
	private final String IP = "127.0.0.1";
	public SocketAddress remoteAddress;
	public SocketAddress localAddress;
    
	public CallListener(String userName) {
		this.userName = userName;
		this.remoteAddress = new InetSocketAddress(IP, localPort);
	}
    
	public CallListener() {
		userName ="name";
	    remoteAddress = new InetSocketAddress(IP, localPort);
	}
	Connection getConnection() throws IOException {
		if (isStatusBusy()) {
			return null;
		} else {
		    Connection connect = new Connection("localPort", userName);
			return connect;
		}

	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SocketAddress getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(SocketAddress localAddress) {
		this.localAddress = localAddress;
	}

	public Boolean isStatusBusy() {
		return statusBusy;
	}

	public void setStatus(Boolean statusBusy) {
		this.statusBusy = statusBusy;
	}

	public SocketAddress getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(SocketAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public int getLocalPort() {
		return localPort;
	}


	

}

