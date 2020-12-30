import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarCategoryList extends JFrame{
    private JTable table1;
    private JPanel CategoryPanel;
    private JButton cancelButton;
    private JButton addButton;
    private JButton deleteButton;

    public CarCategoryList(){

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

        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);

        model.addColumn("nazwa");
        model.addColumn("opis");
        model.addColumn("opiekun");

        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

    }
}
