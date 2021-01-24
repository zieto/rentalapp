import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private JButton refreshButton;

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

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() > 0) {
                    for (int j = model.getRowCount() - 1; j > -1; j--) {
                        model.removeRow(j);
                    }
                }
                List<CarCategory> list = db_connection.listCarCategories();
                Object[] row = new Object[3];
                for (int i = 0; i < list.size(); i++){
                    row[0] = list.get(i).getName();
                    row[1] = list.get(i).getDesc();
                    row[2] = list.get(i).getpName() + " " + list.get(i).getpLastName();
                    model.addRow(row);
                }
            }
        });

        table1.getColumnModel().getColumn(0).setPreferredWidth(100);
        table1.getColumnModel().getColumn(1).setPreferredWidth(450);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();
                String ccName = (String) table1.getValueAt(index, 0);
                String ccDesc = (String) table1.getValueAt(index, 1);
                String pFullname = (String) table1.getValueAt(index, 2);
                String pf_split[] = pFullname.split(" ");


                int cd = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć zaznaczoną pozycję?");
                switch (cd){
                    case 0:
                        if(db_connection.deleteCarCategory(ccName, ccDesc, pf_split[0], pf_split[1])==1){
                            model.removeRow(index);
                        }
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
