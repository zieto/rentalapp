import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class RentList extends JFrame {
    private JPanel RentPanel;
    private JTable table1;
    private JButton cancelButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton addClientButton;
    private JButton refreshButton;
    private JButton carsButton;
    private JButton editButton;


    public RentList(){

        Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Aktywne wynajmy");
        setContentPane(RentPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(700,400);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddClient();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddRent();
            }
        });

        carsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarList();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);

        model.addColumn("samochód");
        model.addColumn("klient");
        model.addColumn("pracownik");
        model.addColumn("wypożyczono");
        model.addColumn("zwrot");

        List<Rent> list = db_connection.listRents();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getModel();
            row[1] = list.get(i).getkName() +" "+ list.get(i).getkLastName();
            row[2] = list.get(i).getpName() +" "+ list.get(i).getpLastName();
            row[3] = list.get(i).getRent_date();
            row[4] = list.get(i).getReturn_date();
            model.addRow(row);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        table1.getColumnModel().getColumn(0).setPreferredWidth(100);
        table1.getColumnModel().getColumn(1).setPreferredWidth(200);
        table1.getColumnModel().getColumn(2).setPreferredWidth(200);
        table1.getColumnModel().getColumn(3).setPreferredWidth(100);
        table1.getColumnModel().getColumn(4).setPreferredWidth(100);

        for (int i=0; i<5; i++){
            if (i!=1 && i!=2) {
                table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }


        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() > 0){
                    for (int j=model.getRowCount() -1; j>-1; j--){
                        model.removeRow(j);
                    }
                }
                List<Rent> list = db_connection.listRents();
                for (int i = 0; i < list.size(); i++){
                    row[0] = list.get(i).getModel();
                    row[1] = list.get(i).getkName() +" "+ list.get(i).getkLastName();
                    row[2] = list.get(i).getpName() +" "+ list.get(i).getpLastName();
                    row[3] = list.get(i).getRent_date();
                    row[4] = list.get(i).getReturn_date();
                    model.addRow(row);
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();
                String carModel = (String) table1.getValueAt(index, 0);
                String kFullname = (String) table1.getValueAt(index, 1);
                String kf_split[] = kFullname.split(" ");
                String pFullname = (String) table1.getValueAt(index, 2);
                String pf_split[] = pFullname.split(" ");
                Date rent_dt = (Date) table1.getValueAt(index, 3);
                Date ret_dt = (Date) table1.getValueAt(index, 4);

                int cd = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć zaznaczoną pozycję?");
                switch (cd){
                    case 0:
                        model.removeRow(index);
                        db_connection.deleteRent(carModel, kf_split[0], kf_split[1], pf_split[0], pf_split[1], rent_dt, ret_dt);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();
                String carModel = (String) table1.getValueAt(index, 0);
                String kFullname = (String) table1.getValueAt(index, 1);
                String kf_split[] = kFullname.split(" ");
                String pFullname = (String) table1.getValueAt(index, 2);
                String pf_split[] = pFullname.split(" ");
                Date rent_dt = (Date) table1.getValueAt(index, 3);
                Date ret_dt = (Date) table1.getValueAt(index, 4);
                new UpdateRent(kf_split[0], kf_split[1], pf_split[0], pf_split[1], carModel, rent_dt, ret_dt);
            }
        });

    }

}
