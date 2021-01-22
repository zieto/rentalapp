import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCarCategory extends JFrame {
    private JPanel panel1;
    private JButton cancelButton;
    private JTextField nameTextField;
    private JComboBox adminComboBox;
    private JTextField descriptionTextField;
    private JButton addButton;

    public AddCarCategory(){

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Dodaj nową kategorię");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        nameTextField.setColumns(10);
        descriptionTextField.setColumns(25);

        List<Employee> list = db_connection.listEmployees();
        Object[] row = new Object[1];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
            adminComboBox.addItem(row[0]);
        }

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String desc = descriptionTextField.getText();
                String fullname = (String) adminComboBox.getSelectedItem();
                String split[] = fullname.split(" ");

                if (nameTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                else if(db_connection.addCarCategory(name, desc, split[0], split[1])!=null){
                    JOptionPane.showMessageDialog(null, "Dodano nową kategorię!");
                    dispose();
                }
            }
        });

    }

}
