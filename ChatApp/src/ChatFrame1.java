import sun.net.util.IPAddressUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;


public class ChatFrame1 extends JFrame {
    private JTextField localIPTextField;
    private JButton localConectButton;
    private JTextField nickTextField;
    private JButton conectButton;
    private JTextField IPTextField;
    private JTextField textField4;
    private JButton sendButton;
    private JTextArea textArea1;
    private JButton showFriendListButton;
    private JPanel panel;
    private JLabel l1;
    private Caller caller;
    private Connection connection;


    public ChatFrame1(){
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        localConectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    caller = new Caller(localIPTextField.getText(), 28411);
                    OutputStream sout = caller.getSocket().getOutputStream();
                }catch (Exception g){
                    g.printStackTrace();
                    Error error =new Error(g.getLocalizedMessage());
                }
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    connection.sendMessage(textField4.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        setVisible(true);
        Thread cl = new Thread(new CallListenerThread());

        if(connection!=null){
            textArea1.setText("Welcome to ChatApp 2015! \n Write your massege");
            l1.setText("Coneced");
        }


    }
    public void setText(String text){
        textArea1.setText(textArea1.getText()+"\n"+text);
    }

}