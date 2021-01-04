import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarCategoryList extends JFrame{
    private JTable table1;
    private JPanel CategoryPanel;
    private JButton cancelButton;
    private JButton addButton;
    private JButton deleteButton;

    public CarCategoryList(){

        Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Lista kategorii");
        setContentPane(CategoryPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(700,300);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddCarCategory();
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);

        model.addColumn("nazwa");
        model.addColumn("opis");
        model.addColumn("opiekun");

        List<CarCategory> list = db_connection.listCarCategories();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getName();
            row[1] = list.get(i).getDesc();
            row[2] = list.get(i).getpName() + " " + list.get(i).getpLastName();
            model.addRow(row);
        }

        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

    }
}
