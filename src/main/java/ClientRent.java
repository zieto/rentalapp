import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientRent extends JFrame{
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JTextField telephoneTextField;
    private JComboBox carComboBox;
    private JTextField daysTextField;
    private JButton cancelButton;
    private JButton addButton;
    private JPanel panel;

    public ClientRent(){
        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Wynajmij samochód");
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(350,320);

        nameTextField.setColumns(10);
        surnameTextField.setColumns(10);
        emailTextField.setColumns(10);
        telephoneTextField.setColumns(10);
        daysTextField.setColumns(4);

        List<Car> list = db_connection.listAvailableCars();
        Object[] row = new Object[1];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getBrand() + " " + list.get(i).getModel();
            carComboBox.addItem(row[0]);
        }

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChangeView();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String email = emailTextField.getText();
                String telephone = telephoneTextField.getText();
                int days = Integer.parseInt(daysTextField.getText());
                String carFull = (String) carComboBox.getSelectedItem();
                String car_split[] = carFull.split(" ");
                int clientID;

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || telephoneTextField.getText().isEmpty() || daysTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);

                }
                else {
                    clientID = db_connection.addClient(name, surname, telephone, email);
                    db_connection.addClientRent(car_split[0], car_split[1], clientID, days);
                    JOptionPane.showMessageDialog(null, "Pomyślnie wynajęto pojazd!");
                    dispose();
                    new ChangeView();
                }
            }
        });
    }
}
