import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.net.*;

public class CallListener {
	private String localIp,nick;
	private Boolean busy;


	public CallListener(){
		this.nick="unnamed";
		this.busy=false;
	}
	public CallListener(String nick){
		this.nick=nick;
		this.busy=false;
	}
	public CallListener(String nick,String localIP){
		this.nick=nick;
		this.localIp=localIP;
		this.busy=false;
	}
	public void setBusy(Boolean busy){
		this.busy=busy;
	}

	public void setNick(String nick){
		this.nick=nick;
	}
	public String getNick(){
		return nick;
	}

	public Boolean getBusy() {
		return busy;
	}
	public Connection getConnection()throws Exception{
		ServerSocket serverSocket=new ServerSocket(28411);
		this.busy=true;
		return new Connection(serverSocket.accept());
	}
}
