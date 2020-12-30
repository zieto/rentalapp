import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCarCategory extends JFrame {
    private JPanel panel1;
    private JButton cancelButton;
    private JTextField nameTextField;
    private JComboBox adminComboBox;
    private JTextField descriptionTextField;
    private JButton addButton;

    public AddCarCategory(){

        Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Dodaj nową kategorię");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        nameTextField.setColumns(10);
        descriptionTextField.setColumns(25);

        //TODO
        //adminComboBox.addItem("");

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

    }

}
