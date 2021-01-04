import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RentList extends JFrame {
    private JPanel RentPanel;
    private JTable table1;
    private JButton cancelButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton addClientButton;


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

        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

    }

}
