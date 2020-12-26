import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton exitButton;
    private JPanel PanelMenu;
    private JButton rentButton;
    private JButton employeesButton;
    private JButton carsButton;
    private JButton clientsButton;

    public Menu() {
        exitButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        JFrame menu = new JFrame("RentalApp");
        menu.setContentPane(new Menu().PanelMenu);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.pack();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.setSize(600,400);
    }
}
