import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.io.*;

public class CallListener {
	public String localNick, remoteNick;
	public Boolean statusBusy=false;
	private final int localPort = 28411;
	private final String IP = "127.0.0.1";
	public SocketAddress remoteAddress;
	public SocketAddress localAddress;
	public ServerSocket serverSocket;

	public CallListener(String userName) {
		localNick = userName;
		this.remoteAddress = new InetSocketAddress(IP, localPort);
	}

	public CallListener(String userName, String remoteAdress) {
		localNick = userName;
		remoteAddress = new InetSocketAddress(IP, localPort);
	}

	public CallListener() {
		localNick = " ";
		remoteAddress = new InetSocketAddress(IP, localPort);
	}

	Connection getConnection() throws IOException {
		if (isStatusBusy()) {
			return null;
		} else {
			ServerSocket ss = new ServerSocket(localPort);
			Connection connect = new Connection(ss.accept(), localNick);
			return connect;
		}

	}

	public SocketAddress getListenAddress() throws IOException {
		return serverSocket.getLocalSocketAddress();
	}

	public String getLocalNick() {
		return localNick;
	}

	public void setLocalNick(String localNick) {
		this.localNick = localNick;
	}

	public void setListenAddress(SocketAddress listenAddress) {
		this.localAddress = listenAddress;//?
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

	public String getRemoteNick() {
		return remoteNick;
	}

	public void setRemoteNick(String remoteNick) {
		this.remoteNick = remoteNick;
	}

	@Override
	/* /� �� ����� ��� ������ ��� ������ ���������������� / */
	public String toString() {
		return "CallListener [localNick=" + localNick + ", IP=" + IP + "]";
	}

	public void setRemoteAddress(SocketAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public int getLocalPort() {
		return localPort;
	}

	public Socket getSokcet() throws Exception{
		return serverSocket.accept();
	}

}
