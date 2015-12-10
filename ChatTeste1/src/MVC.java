import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Griffon on 10.12.2015.
 */
    public class MVC {
    private Caller caller;
    private Connection connection;
    private CallListenerThread callListenerThread;
    private CommandListenerThread commandListenerThread;
    private String incNick, myNick,ip;
    private ChatFrame1 chatFrame1;
    private ServerConnection serverConnection = new ServerConnection();
    private Observer commandObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            Command command =commandListenerThread.getLastCommand();
            switch (command.getType()){
                case ACCEPT:
                    try {
                        connection.sendNickHello(myNick);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case DISCONNECT:
                    chatFrame1.addText("DISCONNECT");
                    commandListenerThread.stop();
                    connection=null;
                    chatFrame1.disconnect();
                    callListenerThread.setBusy(false);
                    break;
                case MESSAGE:
                    MessageCommand mc = (MessageCommand)command;
                    chatFrame1.addText(incNick,mc.toString());
                    break;
                case NICK:
                    NickCommand nc = (NickCommand) command;
                    chatFrame1.addText(nc.toString());
                    incNick=nc.getNick();
                    break;
                case REJECT:
                    chatFrame1.addText("REJECT");
                    connection=null;
                    commandListenerThread.stop();
                    chatFrame1.disconnect();
                    callListenerThread.setBusy(false);
                    break;
            }
        }
    };

    ActionListener changeNick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            myNick=chatFrame1.getNick();
            callListenerThread.setNick(myNick);
        }
    };

    ActionListener aLConnect=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            caller=new Caller(myNick,ip);
            try {
                if(connection==null){
                    connection = caller.call();
                    connection.sendNickHello(myNick);
                    commandListenerThread = new CommandListenerThread(connection);
                    commandListenerThread.start();
                    commandListenerThread.addObserver(commandObserver);
                    chatFrame1.connect(ip);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    };
    ActionListener aLSend = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(connection!=null){
                try {
                    connection.sendMessage(chatFrame1.getMessage());
                    chatFrame1.addText(myNick,chatFrame1.getMessage());
                    chatFrame1.setMessage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };
    ActionListener goOnline = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            serverConnection.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
            serverConnection.connect();
            serverConnection.setLocalNick(myNick);
            serverConnection.goOnline();
        }
    };
    ActionListener goOfline=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            serverConnection.goOffline();
            serverConnection.disconnect();
        }
    };
    ActionListener serverClients=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(serverConnection.isConnected()) {
                String ip="";
                LIst lIst = new LIst(ip,serverConnection);
                if(!ip.equals("")){

                }
            }
        }
    };
    ActionListener showFriendlistAL =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                LIst lIst = new LIst(ip);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };
    ActionListener disconnect = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                connection.disconnect();
                commandListenerThread.stop();
                connection=null;
                chatFrame1.disconnect();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };
    Observer callObserver =new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            connection=callListenerThread.incomingCall();
            chatFrame1.connect(connection.getSocketIP());
            commandListenerThread=new CommandListenerThread(connection);
            commandListenerThread.start();
            commandListenerThread.addObserver(commandObserver);
            IncomingConection incomingConection = new IncomingConection(connection, myNick);
        }
    };

    public MVC(ChatFrame1 chatFrame1){
        myNick="Unnamed";
        this.chatFrame1=chatFrame1;
        callListenerThread=new CallListenerThread();
        Thread thread = new Thread(callListenerThread);
        thread.start();
        callListenerThread.addObserver(callObserver);
        chatFrame1.nickAL(changeNick);
        chatFrame1.ipAL(aLConnect);
        chatFrame1.connectButtonAL(aLConnect);
        chatFrame1.messageAL(aLSend);
        chatFrame1.sendAL(aLSend);
        chatFrame1.setGoOflineButton(goOfline);
        chatFrame1.setGoOnlineButton(goOnline);
        chatFrame1.setShowFriendListButton(showFriendlistAL);
        chatFrame1.setServerClientsButton(serverClients);
        chatFrame1.setDisconnectButton(disconnect);
    }
}
