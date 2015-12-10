import jdk.nashorn.internal.codegen.CompilerConstants;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Griffon on 29.11.2015.
 */
public class Caller {
    private Connection connection;
    private String nick,ip;

    public Caller(){

    }
    public Caller(String nick, SocketAddress remoteAddres){
        InetSocketAddress inetSocketAddress= (InetSocketAddress) remoteAddres;
        this.ip=inetSocketAddress.getHostName();
        this.nick=nick;
    }
    public Caller(String nick, String ip){
        this.nick=nick;
        this.ip=ip;
    }
    public Connection call()throws Exception{
        Socket socket = new Socket(ip,33333);
        connection= new Connection(socket);
        return connection;
    }

    public static void main(String[] args) {
        Caller caller= new Caller("Griffon","127.0.0.1");
        try {
            Connection connection = caller.call();
            connection.sendMessage("ababalamaga");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
