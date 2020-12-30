import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClient extends JFrame {
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JButton addButton;
    private JTextField telephoneTextField;

    public AddClient() {

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Dodaj klienta");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        nameTextField.setColumns(10);
        surnameTextField.setColumns(10);
        emailTextField.setColumns(10);
        telephoneTextField.setColumns(10);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String email = emailTextField.getText();
                String telephone = telephoneTextField.getText();

                db_connection.addClient(name, surname, telephone, email);

                JOptionPane.showMessageDialog(null, "Dodano nowego klienta!");
                dispose();

            }
        });

    }

}
