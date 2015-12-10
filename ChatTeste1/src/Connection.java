
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.net.Socket;
import java.util.HashMap;

public class Connection {
	private static final int port = 28411;
	private final char endLine = '\n';
	private String date="2015 ";
	public Socket socket;
	private DataOutputStream ds;
	private DataInputStream in;
	
	
	Connection(Socket s) throws IOException{
		socket = s;
		ds = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		System.out.println(s.getInetAddress() + " is connected. Type message.");
	}

	public String getSocketIP(){
		return socket.getLocalSocketAddress().toString();
	}
	
	public boolean isOpen(){
		return !socket.isClosed();
	}
	
	public void close() throws IOException{
		ds.close();
		in.close();
		socket.close();
	}
	
	public void accept() throws IOException {
		ds.write(("ACCEPTED"+endLine).getBytes());
		ds.flush();
	}

	public void reject() throws IOException {
		ds.write(("Rejected"+ endLine).getBytes());
		ds.flush();
	}

	public void sendNickHello(String nick1) throws IOException {
		ds.write(("ChatApp "+date+"user "+ nick1 +endLine).getBytes());
		ds.flush();
	}

	public void sendNickBusy(String nick1) throws IOException {
		ds.write(("ChatApp "+date +"user "+ nick1 + " busy" +endLine).getBytes());
		ds.flush();
	}

	public void sendMessage(String text) throws IOException {
		ds.write(("MESSAGE"+endLine).getBytes());
		ds.flush();
		ds.write((text+endLine).getBytes());
		ds.flush();
	}
	
	public void disconnect() throws IOException {
		ds.write(("DISCONNECT"+endLine).getBytes());
		ds.flush();
	}
	
	public Command receive() throws IOException{
		int n;
		StringBuffer command = new StringBuffer();
		while((n=in.read())!='\n'){
			command.append((char)n);
		}
		System.out.println(command);
		if(command.toString().startsWith("ChatApp")){
			if(command.toString().endsWith(" busy")){
				return new NickCommand(command.substring(8, 12),command.substring(18, command.length()-4),true);
			} 
			else {
				return new NickCommand(command.substring(8, 12),command.substring(18, command.length()),false);
			}
		}
			if(command.toString().toUpperCase().startsWith("MESSAGE")){
				StringBuffer t = new StringBuffer();
				while((n=in.read())!='\n'){
					t.append((char)n);
				}
				return new MessageCommand(t.toString());
			} 
		
			if(command.toString().toUpperCase().startsWith("ACCEPTED")){
				return new Command(Command.CommandType.ACCEPT);
			} 
			if(command.toString().toUpperCase().startsWith("DISCONNECT")){
				return new Command(Command.CommandType.DISCONNECT);
			} 
			if(command.toString().toUpperCase().startsWith("REJECTED")){
				return new Command(Command.CommandType.REJECT);
			} 

		return null;
	}
	
	
	public static void main(String[] args) throws IOException{
			
	/*	
ServerSocket s = new ServerSocket(Connection.port);
				Command i;		
		Connection co = new Connection(s.accept());
		CommandListenerThread clt = new CommandListenerThread(co);
			clt.start();
			CommandListenerThread clr = new CommandListenerThread(co);
			clr.start();
			
	/*	while(true){
			i = co.receive();
			System.out.println(i.toString());
			System.out.println();
		}
			*/
		
			Socket sc = new Socket(InetAddress.getByName("127.0.0.1"),Connection.port);
		Connection y = new Connection(sc);
		y.sendNickBusy("fuuuuuuuck");
		y.sendNickHello("fuck");
		y.reject();
		y.accept();
		y.sendMessage("hi you");
		y.disconnect();
	}
	
}
