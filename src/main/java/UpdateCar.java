import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateCar extends JFrame{
    private JButton cancelButton;
    private JPanel panel1;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JComboBox categoryComboBox;
    private JButton updateButton;
    private JComboBox engineComboBox;

    public UpdateCar(String brand, String model, String engine, String category){

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Edytuj samochód");
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

        brandTextField.setText(brand);
        modelTextField.setText(model);
        engineComboBox.setSelectedItem(engine);


        List<CarCategory> list = db_connection.listCarCategories();
        Object[] row = new Object[1];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getName();
            categoryComboBox.addItem(row[0]);
        }
        categoryComboBox.setSelectedItem(category);


        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBrand = brandTextField.getText();
                String newModel = modelTextField.getText();
                String newEngine = (String) engineComboBox.getSelectedItem();
                String newCategory = (String) categoryComboBox.getSelectedItem();

                if (brandTextField.getText().isEmpty() || modelTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Wypełnij formę odpowiednimi danymi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

                else {
                    db_connection.updateCar(brand, model, engine, category, newBrand, newModel, newEngine, newCategory);
                    JOptionPane.showMessageDialog(null, "Pomyślnie zaktualizowano samochód!");
                    dispose();
                }
            }
        });




    }
}
