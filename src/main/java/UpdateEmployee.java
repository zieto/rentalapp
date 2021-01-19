import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployee extends JFrame {
    private JButton cancelButton;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField telephoneTextField;
    private JTextField salaryTextField;
    private JButton updateButton;
    private JPanel panel1;

    public UpdateEmployee(String name, String surname, String telephone, int salary) {

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Edytuj dane pracownika");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850, 80);

        nameTextField.setColumns(10);
        surnameTextField.setColumns(10);
        telephoneTextField.setColumns(10);
        salaryTextField.setColumns(8);

        nameTextField.setText(name);
        surnameTextField.setText(surname);
        telephoneTextField.setText(telephone);
        salaryTextField.setText(Integer.toString(salary));


        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = nameTextField.getText();
                String newSurname = surnameTextField.getText();
                String newTelephone = telephoneTextField.getText();
                int newSalary = Integer.parseInt(salaryTextField.getText());

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || telephoneTextField.getText().isEmpty() || salaryTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else {
                    db_connection.updateEmployee(name, surname, telephone, salary, newName, newSurname, newTelephone, newSalary);
                    JOptionPane.showMessageDialog(null, "Pomyślnie zaktualizowano dane pracownika!");
                    dispose();
                }
            }
        });
    }
}
