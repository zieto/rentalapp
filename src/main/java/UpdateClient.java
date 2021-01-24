import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateClient extends JFrame{
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JTextField telephoneTextField;
    private JButton updateButton;

    public UpdateClient(String name, String surname, String email, String telephone){

        final Hibernate db_connection = new Hibernate();


        setVisible(true);
        setTitle("Edytuj dane klienta");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        nameTextField.setColumns(10);
        surnameTextField.setColumns(10);
        emailTextField.setColumns(10);
        telephoneTextField.setColumns(8);

        nameTextField.setText(name);
        surnameTextField.setText(surname);
        emailTextField.setText(email);
        telephoneTextField.setText(telephone);

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
                String newEmail = emailTextField.getText();
                String newTelephone = telephoneTextField.getText();

                if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || telephoneTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

                else if (db_connection.updateClient(name, surname, email, telephone, newName, newSurname, newEmail, newTelephone)==1){
                    JOptionPane.showMessageDialog(null, "Pomyślnie zaktualizowano dane klienta!");
                    dispose();
                }
            }
        });




    }
}
