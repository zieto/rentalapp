import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddRent extends JFrame {
    private JButton cancelButton;
    private JPanel panel1;
    private JComboBox clientComboBox;
    private JComboBox carComboBox;
    private JComboBox employeeComboBox;
    private JFormattedTextField returnDateFormattedTextField;
    private JButton addButton;

    public AddRent(){

        Hibernate db_connection = new Hibernate();

        setVisible(true);
        setContentPane(panel1);
        setTitle("Nowe wypożyczenie");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);


        //TODO
//        clientComboBox.addItem("");
//        carComboBox.addItem("");
//        employeeComboBox.addItem("");

        returnDateFormattedTextField.setColumns(7);

        //TODO
        //add mask
        DateFormat format = new SimpleDateFormat("DD-MM-YYYY");
        returnDateFormattedTextField = new javax.swing.JFormattedTextField(format);


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
