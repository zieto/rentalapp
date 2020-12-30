import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentList extends JFrame {
    private JPanel RentPanel;
    private JTable table1;
    private JButton cancelButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton addClientButton;


    public RentList(){

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

        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

    }

}
