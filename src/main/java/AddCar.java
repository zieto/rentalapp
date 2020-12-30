import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCar extends JFrame {
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JComboBox engineComboBox;
    private JComboBox categoryComboBox;
    private JButton addButton;

    public AddCar(){

        Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Dodaj samochód");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        brandTextField.setColumns(10);
        modelTextField.setColumns(10);

        engineComboBox.addItem("benzynowy");
        engineComboBox.addItem("diesel");
        engineComboBox.addItem("hybrydowy");
        engineComboBox.addItem("elektryczny");

        //TODO
        //categoryComboBox.addItem("");


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
