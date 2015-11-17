import java.io.IOException;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable{
	
	private boolean isDisconnect;
	private Connection connection;
	private Command lastCommand;

	
	CommandListenerThread(Connection con) {
		connection = con;
	}

	Command getLastCommand() {
		return lastCommand;
	}

	boolean isDisconnected() {
		return isDisconnect;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void	run(){while (!isDisconnect) {
				try {
					lastCommand = connection.receive();		///?					
				} catch (IOException e) {
					e.printStackTrace();
				}
				setChanged();//
				notifyObservers();
				}
}
 
	public void	start(){
		isDisconnect= false;
		run();
		}  
	
	public void	stop() {
		isDisconnect = true;	
	}

	public boolean isDisconnect() {
		return isDisconnect;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setLastCommand(Command lastCommand) {
		this.lastCommand = lastCommand;
	} 
	
}
