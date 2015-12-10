import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable{
	
	private boolean isDisconnect;
	private Connection connection;
	private Command lastCommand;
	
	CommandListenerThread() {
		connection = null;
	}

	CommandListenerThread(Connection con){
		isDisconnect = false;
		connection = con;
	}

	public Command	getLastCommand(){
		return lastCommand;
	} 
	
	public boolean	isDisconnected(){
		return isDisconnect;
	} 
	
	public void	run() {
		while (!isDisconnect) {
			try {
				lastCommand = connection.receive();
				//	System.out.println(lastCommand.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers();

		}
	}
	
	public void	start(){
		Thread thread = new Thread(this);
		thread.start();
	} 
	
	public void	stop(){
		isDisconnect = true;
	} 
		
}
