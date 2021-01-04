import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        Hibernate db_connection = new Hibernate();

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

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddCar();
            }
        });

        DefaultTableModel model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column)
            {
                switch (column)
                {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;

                    default:
                        return String.class;
                }
            }
        };

        table1 = new JTable(model);

        model.addColumn("marka");
        model.addColumn("model");
        model.addColumn("silnik");
        model.addColumn("kategoria");
        model.addColumn("wypożyczony");

        List<Car> list = db_connection.listCars();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getBrand();
            row[1] = list.get(i).getModel();
            row[2] = list.get(i).getEngine();
            row[3] = list.get(i).getCat();
            row[4] = list.get(i).getRented();
            model.addRow(row);
        }


        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

        categoryComboBox.addItem("wszystkie");

        rentedComboBox.addItem("wszystkie");
        rentedComboBox.addItem("wolne");
        rentedComboBox.addItem("wypożyczone");

    }

}
