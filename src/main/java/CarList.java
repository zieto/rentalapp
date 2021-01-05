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

        List<CarCategory> cclist = db_connection.listCarCategories();
        Object[] ccrow = new Object[1];
        for (int i = 0; i < cclist.size(); i++){
            ccrow[0] = cclist.get(i).getName();
            categoryComboBox.addItem(ccrow[0]);
        }

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (model.getRowCount() > 0){
                    for (int j=model.getRowCount() -1; j>-1; j--){
                        model.removeRow(j);
                    }
                }

                List<Car> list = db_connection.listCars();
                Object ccb = categoryComboBox.getSelectedItem();
                Object rcb = rentedComboBox.getSelectedItem();

                if (rcb.equals("wolne")){
                    rcb = false;
                }
                if (rcb.equals("wypożyczone")){
                    rcb = true;
                }
                String all = "wszystkie";

                for (int i = 0; i < list.size(); i++) {
                    row[0] = list.get(i).getBrand();
                    row[1] = list.get(i).getModel();
                    row[2] = list.get(i).getEngine();
                    row[3] = list.get(i).getCat();
                    row[4] = list.get(i).getRented();

                    if (rcb.equals(all) && ccb.equals(all)){
                        model.addRow(row);
                    }

                    if (!rcb.equals(all) && ccb.equals(all)){
                        if (row[4].equals(rcb)){
                            model.addRow(row);
                        }
                    }

                    if (rcb.equals(all) && !ccb.equals(all)){
                        if (row[3].equals(ccb)){
                            model.addRow(row);
                        }
                    }

                    if (!rcb.equals(all) && !ccb.equals(all)){
                        if (row[3].equals(ccb) && row[4].equals(rcb)){
                            model.addRow(row);
                        }
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();
                String brand = (String) table1.getValueAt(index, 0);
                String carModel = (String) table1.getValueAt(index, 1);
                String engine = (String) table1.getValueAt(index, 2);
                String category = (String) table1.getValueAt(index, 3);
                Boolean rented = (Boolean) table1.getValueAt(index, 4);

                int cd = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć zaznaczony pojazd?");
                switch (cd){
                    case 0:
                        model.removeRow(index);
                        db_connection.deleteCar(brand, carModel, engine, category, rented);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });

    }

}
