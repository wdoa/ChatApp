import java.util.Observable;
import java.util.Observer;

/**
 * Created by Griffon on 29.11.2015.
 */
public class CallListenerThread extends Observable implements Runnable {
    private CallListener callListener;
    private Connection connection;
    Boolean flag=true;

    public CallListenerThread(){
        callListener=new CallListener();
    }
    public CallListenerThread(String nick){
        callListener= new CallListener(nick);
    }
    public CallListenerThread(String nick, String ip){
        callListener=new CallListener(nick,ip);
    }

    @Override
    public void run() {
        while (flag){
            try {
                if(!callListener.getBusy()){
                    connection =callListener.getConnection();
                    setChanged();
                    notifyObservers();
                }else {
                    connection.sendNickBusy(callListener.getNick());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setNick(String nick){
        callListener.setNick(nick);
    }
    public void stop(){
        flag=false;
    }
    public void setBusy(Boolean busy){
        callListener.setBusy(busy);
    }
    public Connection incomingCall(){
        return connection;
    }
}
