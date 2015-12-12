import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class FriendList {
    private HashMap<String,String> contacts;
    private RandomAccessFile file;

    FriendList() throws IOException {
        file = new RandomAccessFile(new File("Contacts.dat"), "rw");
        contacts=getNicks();
    }

    //	////////////
    public void addToFile(String IP, String nick)
            throws IOException {
        file.seek(file.length());
        file.writeUTF(nick);
        file.writeUTF(IP);
    }
//////////

    public HashMap<String,String> getNicks() throws IOException {
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

    public void rewriteFile(HashMap<String,String> hm)
            throws IOException {
        file.setLength(0);
        Set<String> n=hm.keySet();
        Iterator<String> itr = n.iterator();
        String s;
        while (itr.hasNext()){
            s=itr.next();
            file.writeUTF(s);
            file.writeUTF(hm.get(s));
        }

    }

    public HashMap<String,String> getContacts(){
        return contacts;
    }


    public void add(String nick,String IP){
        contacts.put(nick, IP);
    }

    public void delete(String nick){
        if(contacts.containsKey(nick))
        contacts.remove(nick);
    }

    public String getIP(String nick){
        String ip ;
        if (contacts.containsKey(nick)){
            ip = contacts.get(nick);}
        else{
            ip = "127.0.0.1";
        }
        return ip;

    }


}
