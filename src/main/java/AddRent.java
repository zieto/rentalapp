import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddRent extends JFrame {
    private JButton cancelButton;
    private JPanel panel1;
    private JComboBox clientComboBox;
    private JComboBox carComboBox;
    private JComboBox employeeComboBox;
    private JButton addButton;
    private JTextField textField1;

    public AddRent(){

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setContentPane(panel1);
        setTitle("Nowe wypożyczenie");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(950,80);


        List<Client> client_list = db_connection.listClients();
        Object[] client_row = new Object[1];
        for (int i = 0; i < client_list.size(); i++){
            client_row[0] = client_list.get(i).getFirstName() + " " + client_list.get(i).getLastName();
            clientComboBox.addItem(client_row[0]);
        }

        List<Employee> employee_list = db_connection.listEmployees();
        Object[] employee_row = new Object[1];
        for (int i = 0; i < employee_list.size(); i++){
            employee_row[0] = employee_list.get(i).getFirstName() + " " + employee_list.get(i).getLastName();
            employeeComboBox.addItem(employee_row[0]);
        }

        List<Car> list = db_connection.listAvailableCars();
        Object[] row = new Object[1];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getBrand() + " " + list.get(i).getModel();
            carComboBox.addItem(row[0]);
        }

        textField1.setColumns(4);


        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String carFull = (String) carComboBox.getSelectedItem();
                String car_split[] = carFull.split(" ");

                String employeeFullname = (String) employeeComboBox.getSelectedItem();
                String ef_split[] = employeeFullname.split(" ");

                String clientFullname = (String) clientComboBox.getSelectedItem();
                String cf_split[] = clientFullname.split(" ");

                String daysString = textField1.getText();
                Integer days = null;
                try {
                    days = Integer.parseInt(daysString);
                }
                catch (NumberFormatException ef){
                    JOptionPane.showMessageDialog(null, "Liczba dni musi być cyfrą!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                if (days!=null) {

                    if (days <= 0) {
                        JOptionPane.showMessageDialog(null, "Liczba dni musi być większa od zera!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        db_connection.addRent(car_split[0], car_split[1], ef_split[0], ef_split[1], cf_split[0], cf_split[1], days);

                        JOptionPane.showMessageDialog(null, "Pomyślnie wynajęto pojazd!");
                        dispose();
                    }
                }
            }
        });

    }
}
