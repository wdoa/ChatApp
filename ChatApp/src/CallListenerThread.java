import java.io.IOException;
import java.net.SocketAddress;
import java.util.Observable;

public class CallListenerThread extends Observable implements Runnable {
	private CallListener listener;
	private boolean isAvailable;

	public CallListenerThread() {
		listener = new CallListener();
		start();
	}

	public CallListenerThread(String localNick) {
		listener = new CallListener(localNick);
		start();
	}

	public CallListenerThread(String localNick, String localIP) {
		listener = new CallListener(localNick, localIP);
		start();
	}

	public String getLocalNick() {
		return listener.getLocalNick();
	}

	public String getRemoteNick() {
		return listener.getRemoteNick();
	}

	public SocketAddress getListenAddress() throws IOException {
		return listener.getListenAddress();
	}

	public SocketAddress getRemoteAddress() throws IOException {
		return listener.getRemoteAddress();
	}

	public boolean isBusy() {
		return listener.isStatusBusy();
	}

	public void setBusy(boolean busy) {
		listener.setStatus(busy);
	}

	public void setLocalNick(String localNick) {
		listener.setLocalNick(localNick);
	}
    
    public void setListenAddress(SocketAddress listenAddress){
    	//TODO
    }
	
	public void run() {
		while (true) {
			try {
				listener.getConnection();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			hasChanged();
			notifyObservers();
		}

	}

	public void start() {
		this.isAvailable = true;
		run();
	}

	public void stop() {
		this.isAvailable = false;
	}

}
