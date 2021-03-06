import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton exitButton;
    private JPanel PanelMenu;
    private JButton categoriesButton;
    private JButton rentButton;
    private JButton employeesButton;
    private JButton carsButton;
    private JButton clientsButton;
    private JButton viewButton;

    public Menu() {

        new Hibernate();

        setVisible(true);
        setTitle("RentalApp");
        setContentPane(PanelMenu);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(600,400);


        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        employeesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EmployeeList();
            }
        });

        clientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientList();
            }
        });

        carsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CarList();
            }
        });

        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RentList();
            }
        });

        categoriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CarCategoryList();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ChangeView();
            }
        });

    }

    public static void main(String[] args) {
        new Menu();
    }

}
