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
    private JButton editButton;

    public EmployeeList(){
        Hibernate db_connection = new Hibernate();
        setVisible(true);
        setTitle("Lista pracowników");
        setContentPane(EmployeePanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(700,300);

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
                List<Employee> list = db_connection.listEmployees();

                for (int i = 0; i < list.size(); i++){
                    row[0] = list.get(i).getFirstName();
                    row[1] = list.get(i).getLastName();
                    row[2] = list.get(i).getTelephone();
                    row[3] = list.get(i).getSalary();

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
                int index = table1.getSelectedRow();
                String name = (String) table1.getValueAt(index, 0);
                String surname = (String) table1.getValueAt(index, 1);
                String telephone = (String) table1.getValueAt(index, 2);
                int salary = (int) table1.getValueAt(index, 3);

                int cd = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć zaznaczonego pracownika?");
                switch (cd){
                    case 0:
                        if(db_connection.deleteEmployee(name, surname, telephone, salary)==1){
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
                int index = table1.getSelectedRow();
                String name = (String) table1.getValueAt(index, 0);
                String surname = (String) table1.getValueAt(index, 1);
                String telephone = (String) table1.getValueAt(index, 2);
                int salary = (int) table1.getValueAt(index, 3);
                new UpdateEmployee(name, surname, telephone, salary);
            }
        });

    }
}
