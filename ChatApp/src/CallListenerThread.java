import java.io.IOException;
import java.net.SocketAddress;
import java.util.Observable;

public class CallListenerThread extends Observable implements Runnable {
	private CallListener listener;
	private volatile boolean isAvailable;
	private Thread thread;
	private Caller.CallStatus callStatus;
	
	public CallListenerThread() {
		listener = new CallListener();
		thread =  new Thread(this);
		thread.start();
	}

	public CallListenerThread(String localNick) {
		listener = new CallListener(localNick);
		thread =  new Thread(this);
		thread.start();
	}

	public CallListenerThread(String localNick, String localIP) {
		listener = new CallListener(localNick, localIP);
		thread =  new Thread(this);
		thread.start();
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
    	listener.setListenAddress(listenAddress);
    }
	
	public void run() {
		Connection connection= null;
		try {
			connection = listener.getConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!isAvailable) {
				if (connection == null) 

					callStatus = Caller.CallStatus.valueOf("BUSY"); 

					else 

					callStatus = Caller.CallStatus.valueOf("OK");
			setChanged();
			notifyObservers();
		}

	}

	public void stop() {
		this.isAvailable = true;
	}

}
