import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCar extends JFrame {
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JComboBox engineComboBox;
    private JComboBox categoryComboBox;
    private JButton addButton;

    public AddCar(){

        final Hibernate db_connection = new Hibernate();

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

        List<CarCategory> list = db_connection.listCarCategories();
        Object[] row = new Object[1];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getName();
            categoryComboBox.addItem(row[0]);
        }


        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String brand = brandTextField.getText();
                String model = modelTextField.getText();
                String engine = (String) engineComboBox.getSelectedItem();
                String cat = (String) categoryComboBox.getSelectedItem();

                if (brandTextField.getText().isEmpty() || modelTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    db_connection.addCar(brand, model, engine, cat);
                    JOptionPane.showMessageDialog(null, "Dodano nowy samochód!");
                    dispose();
                }

            }
        });


    }

}
