import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarList extends JFrame {
    private JTable table1;
    private JPanel ClientPanel;
    private JButton cancelButton;
    private JComboBox categoryComboBox;
    private JButton addButton;
    private JButton categoryButton;
    private JButton searchButton;
    private JComboBox rentedComboBox;
    private JButton deleteButton;

    public CarList() {

        setVisible(true);
        setTitle("Lista samochodow");
        setContentPane(ClientPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(700,400);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        categoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CarCategoryList();
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);

        model.addColumn("marka");
        model.addColumn("model");
        model.addColumn("silnik");
        model.addColumn("kategoria");
        model.addColumn("wypożyczony");

        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

        categoryComboBox.addItem("wszystkie");

        rentedComboBox.addItem("wszystkie");
        rentedComboBox.addItem("wolne");
        rentedComboBox.addItem("wypożyczone");

    }

}
