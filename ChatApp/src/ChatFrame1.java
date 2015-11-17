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
                    InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
                    textArea1.setText(localIPTextField.getText());
                    caller= new Caller(ipAddress,28411);
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    Error error = new Error("Incorect Addres");
                }

            }
        });
        setVisible(true);
    }

}