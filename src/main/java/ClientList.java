import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientList extends JFrame{
    private JTable table2;
    private JPanel ClientPanel;
    private JButton cancelButton;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JButton searchButton;
    private JButton addButton;

    public ClientList(){

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

        DefaultTableModel model = new DefaultTableModel();
        table2 = new JTable(model);
        model.addColumn("imię");
        model.addColumn("nazwisko");
        model.addColumn("telefon");
        model.addColumn("email");

        JScrollPane jScrollPane = new JScrollPane(table2);
        table2.setVisible(true);
        add(jScrollPane);
    }

}
