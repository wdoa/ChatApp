import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Griffon 17.11.2015
 **/
public class Error extends JFrame{
    private JTextPane textPane1;
    private JButton OKButton;
    private JPanel panel;

    public Error(String text){
        setContentPane(panel);
        pack();
        textPane1.setText(text);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setVisible(true);
    }
}
