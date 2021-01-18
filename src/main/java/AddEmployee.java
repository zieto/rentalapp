import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee extends JFrame{
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField salaryTextField;
    private JButton addButton;
    private JTextField telephoneTextField;

    public AddEmployee(){

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Dodaj pracownika");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        nameTextField.setColumns(10);
        surnameTextField.setColumns(10);
        salaryTextField.setColumns(7);
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
                String telephone = telephoneTextField.getText();
                String s = salaryTextField.getText();
                int salary = Integer.parseInt(s);

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || salaryTextField.getText().isEmpty() || telephoneTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    db_connection.addEmployee(name, surname, telephone, salary);

                    JOptionPane.showMessageDialog(null, "Dodano nowego pracownika!");
                    dispose();
                }
            }
        });

    }

}
