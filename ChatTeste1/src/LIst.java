import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;
import java.util.Set;

public class LIst extends JFrame {
    private JList list1;
    private JButton addButton;
    private JTextField IPTextField;
    private JTextField nickTextField;
    private JPanel panel;
    private JScrollPane scroll;
    private JButton connectButton;
    private FriendList fl;
    private ArrayList<String> a = new ArrayList<String>();
 //   DefaultListModel listModel;


    public LIst(String IP,ServerConnection serverConnection){
        setContentPane(panel);
        setVisible(true);
        pack();
        addButton.setEnabled(false);
        IPTextField.setEnabled(false);
        nickTextField.setEnabled(false);
        String [] strings = serverConnection.getAllNicks();
        final DefaultListModel listModel = new DefaultListModel();
        list1.setModel(listModel);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setSelectedIndex(0);
        for(int i=0;i<=strings.length-1;i++){
            listModel.addElement(strings[i]);
        }

    }


    public LIst(String IP) throws IOException {
        setContentPane(panel);
        setVisible(true);
        pack();

        final DefaultListModel listModel = new DefaultListModel();
        list1.setModel(listModel);
        fl = new FriendList();
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setSelectedIndex(0);
        Set<String> n=fl.getContacts().keySet();
        Iterator<String> itr = n.iterator();
        while (itr.hasNext()){
            String s=itr.next();
            String ip=fl.getIP(s);
            listModel.addElement(s+" "+ip);
            a.add(ip);
        }


        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int y=list1.getSelectedIndex();
                String s= a.get(y);
                StringBuffer sb = new StringBuffer(s);
                int i=sb.length()-1;
                while(sb.charAt(i)==' '){
                        sb.setLength(sb.length()-1);
                    i--;
                }
            }
        });

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String n = nickTextField.getText();
                        String ip = IPTextField.getText();
                        fl.add(n, ip);
                        try {
                            fl.addToFile(ip, n);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        listModel.addElement(nickTextField.getText() + " " + IPTextField.getText());
                        a.add(IPTextField.getText());
                    }
                });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
