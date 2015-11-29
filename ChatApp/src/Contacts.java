import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Contacts {
	private HashMap<String,String> contacts;
	
	Contacts() throws IOException{
		RandomAccessFile file = new RandomAccessFile(new File("Contacts.dat"), "rw");
		contacts=getNicks(file);
	}
	
//	////////////
	public void addToFile(RandomAccessFile file, String IP, String nick)
			throws IOException {
		file.seek(file.length());
		StringBuffer n = new StringBuffer("               ");
		if (nick.length() > 15) {
			nick = nick.substring(0, 15);
		}
		n.replace(0, nick.length(), nick);
		file.writeUTF(n.toString());
		n = new StringBuffer("                    ");
		if (IP.length() > 20) {
			IP = IP.substring(0, 20);
		}
		n.replace(0, IP.length(), IP);
		file.writeUTF(n.toString());
	}
//////////	
	
	public HashMap<String,String> getNicks(RandomAccessFile file) throws IOException {
		file.seek(0);
		HashMap<String,String> nicks = new HashMap<String,String>();
		int b = file.read();
		if (b == -1) {
			return nicks;
		}
		file.seek(file.getFilePointer() - 1);
		while (b != -1) {
				String n =file.readUTF();
				String ip =file.readUTF();
				nicks.put(n, ip);
			b = file.read();
			file.seek(file.getFilePointer() - 1);
		}
		return nicks;

	}
	
	public void rewriteFile(RandomAccessFile file, HashMap<String,String> hm)
			throws IOException {
		file.setLength(0);
		Set<String> n=hm.keySet();
		Iterator<String> itr = n.iterator();
		while (itr.hasNext()){			
			file.writeUTF(itr.next());
			file.writeUTF(hm.get(itr.next()));
		}
		
	}
	
	public void add(String nick,String IP){
		StringBuffer n = new StringBuffer("               ");
		if (nick.length() > 15) {
			nick = nick.substring(0, 15);
		}
		n.replace(0, nick.length(), nick);

		StringBuffer i = new StringBuffer("                    ");
		if (IP.length() > 20) {
			IP = IP.substring(0, 20);
		}
		i.replace(0, IP.length(), IP);
		contacts.put(n.toString(), i.toString());
	}
	
	public String getIP(String nick){
		String ip ;
		StringBuffer n = new StringBuffer("               ");
		if (nick.length() > 15) {
			nick = nick.substring(0, 15);
		}
		n.replace(0, nick.length(), nick);
		if (contacts.containsKey(n.toString())){
		ip = contacts.get(n.toString());
		}
		else{
			ip = "127.0.0.1";
		}
		ip.replace(" ", "");
		return ip;
		
	}
	
	
}
