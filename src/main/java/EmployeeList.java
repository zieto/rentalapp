import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeList extends JFrame{
    private JTable table1;
    private JPanel EmployeePanel;
    private JButton cancelButton;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JButton searchButton;
    private JButton addButton;
    private JButton deleteButton;

    public EmployeeList(){
        Hibernate db_connection = new Hibernate();
        setVisible(true);
        setTitle("Lista pracowników");
        setContentPane(EmployeePanel);
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
                new AddEmployee();
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);
        model.addColumn("imię");
        model.addColumn("nazwisko");
        model.addColumn("telefon");
        model.addColumn("pensja");


        List<Employee> list = db_connection.listEmployees();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getFirstName();
            row[1] = list.get(i).getLastName();
            row[2] = list.get(i).getTelephone();
            row[3] = list.get(i).getSalary();
            model.addRow(row);
        }


        JScrollPane jScrollPane = new JScrollPane(table1);
        table1.setVisible(true);
        add(jScrollPane);

    }
}
