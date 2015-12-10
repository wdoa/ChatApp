import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Observable;
import java.util.Observer;


public class ChatFrame1 extends JFrame {
    private JButton ConnectButton;
    private JTextField nickTextField;
    private JButton connectButton;
    private JTextField ipTextField;
    private JTextField message;
    private JButton sendButton;
    private JTextArea textArea1;
    private JButton showFriendListButton;
    private JPanel panel;
    private JLabel l1;
    private JButton disconnectButton;
    private JRadioButton teste2;
    private JButton goOnlineButton;
    private JButton goOflineButton;
    private JButton serverClientsButton;


    public ChatFrame1(){
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);




    }
    public void nickAL(ActionListener al){
        nickTextField.addActionListener(al);
    }
    public void ipAL(ActionListener al){
        ipTextField.addActionListener(al);
    }
    public void connectButtonAL(ActionListener al){
        connectButton.addActionListener(al);
    }
    public void messageAL(ActionListener al){
        message.addActionListener(al);
    }
    public void sendAL(ActionListener al){
        sendButton.addActionListener(al);
    }
    public void setGoOnlineButton(ActionListener al){
        goOnlineButton.addActionListener(al);
    }
    public void setGoOflineButton(ActionListener al){
        goOflineButton.addActionListener(al);
    }
    public void setShowFriendListButton(ActionListener al){
        showFriendListButton.addActionListener(al);
    }
    public void setServerClientsButton(ActionListener al){
        serverClientsButton.addActionListener(al);
    }
    public void setDisconnectButton(ActionListener al){
        disconnectButton.addActionListener(al);
    }
    public String getNick(){
        return nickTextField.getText();
    }

    public void setMessage(){
        message.setText("");
    }
    public String getMessage(){
        return message.getText();

    }
    public void connect(String ip){
        connectButton.setEnabled(false);
        ipTextField.setEnabled(false);
        ipTextField.setText(ip);
        l1.setText("Connected");
    }
    public void disconnect(){
        connectButton.setEnabled(true);
        ipTextField.setEnabled(true);
        ipTextField.setEditable(true);
        l1.setText("Disconnected");
    }
    public void addText(String text){
        textArea1.setText(textArea1.getText()+"\n"+text);
    }
    public void addText(String nick,String text){
        textArea1.setText(textArea1.getText()+"\n"+nick+":"+text);
    }
}