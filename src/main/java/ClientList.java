import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientList extends JFrame{
    private JTable table2;
    private JPanel ClientPanel;
    private JButton cancelButton;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JButton searchButton;
    private JButton addButton;

    public ClientList(){

        Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Lista klientów");
        setContentPane(ClientPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();

        setSize(700,400);
        textFieldName.setColumns(10);
        textFieldSurname.setColumns(10);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddClient();
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        table2 = new JTable(model);
        model.addColumn("imię");
        model.addColumn("nazwisko");
        model.addColumn("telefon");
        model.addColumn("email");

        List<Client> list = db_connection.listClients();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getFirstName();
            row[1] = list.get(i).getLastName();
            row[2] = list.get(i).getTelephone();
            row[3] = list.get(i).getEmail();
            model.addRow(row);
        }

        JScrollPane jScrollPane = new JScrollPane(table2);
        table2.setVisible(true);
        add(jScrollPane);
    }

}
