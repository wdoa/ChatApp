import sun.net.util.IPAddressUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    Caller caller;


    public ChatFrame1(){
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        localConectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    caller = new Caller("127.0.0.1", 28411);
                }catch (Exception g){
                    g.printStackTrace();
                    Error error =new Error(g.getLocalizedMessage());
                }
            }
        });
        setVisible(true);
    }

}