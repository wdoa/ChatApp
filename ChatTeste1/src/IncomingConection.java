import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Griffon on 01.11.2015.
 */
public class IncomingConection extends JFrame {
    private JButton rejectButton;
    private JButton acceptButton;
    private JLabel IncomingCon;
    private JPanel panel;

    public IncomingConection(Connection connection, String nick){
        setContentPane(panel);
        setVisible(true);
        pack();
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection.accept();
                    connection.sendNickHello(nick);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection.reject();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}
