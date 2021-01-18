import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangeView extends JFrame{
    private JButton adminButton;
    private JPanel panel1;
    private JButton employeeButton;
    private JButton clientButton;

    public ChangeView() {

        setVisible(true);
        setTitle("Zmiana widoku");
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(600,80);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Menu();
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuEmployee();
            }
        });

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClientRent();
            }
        });

    }
}
