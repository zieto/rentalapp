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
    private JButton deleteButton;
    private JButton editButton;

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() > 0){
                    for (int j=model.getRowCount() -1; j>-1; j--){
                        model.removeRow(j);
                    }
                }

                String name = textFieldName.getText();
                String surname = textFieldSurname.getText();
                List<Client> list = db_connection.listClients();

                for (int i = 0; i < list.size(); i++){
                    row[0] = list.get(i).getFirstName();
                    row[1] = list.get(i).getLastName();
                    row[2] = list.get(i).getTelephone();
                    row[3] = list.get(i).getEmail();

                    if (name.length()==0 && surname.length()==0){
                        model.addRow(row);
                    }

                    if (name.length()>0 && surname.length()==0){
                        if (row[0].equals(name)){
                            model.addRow(row);
                        }
                    }

                    if (name.length()==0 && surname.length()>0){
                        if (row[1].equals(surname)){
                            model.addRow(row);
                        }
                    }

                    if (name.length()>0 && surname.length()>0){
                        if(row[0].equals(name) && row[1].equals(surname)){
                            model.addRow(row);
                        }
                    }
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table2.getSelectedRow();
                String name = (String) table2.getValueAt(index, 0);
                String surname = (String) table2.getValueAt(index, 1);
                String telephone = (String) table2.getValueAt(index, 2);
                String email = (String) table2.getValueAt(index, 3);

                int cd = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć zaznaczonego klienta?");
                switch (cd){
                    case 0:
                        if(db_connection.deleteClient(name, surname, telephone, email)==1){
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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table2.getSelectedRow();
                String name = (String) table2.getValueAt(index, 0);
                String surname = (String) table2.getValueAt(index, 1);
                String telephone = (String) table2.getValueAt(index, 2);
                String email = (String) table2.getValueAt(index, 3);

                new UpdateClient(name, surname, email, telephone);

            }
        });


    }

}
