import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateRent extends JFrame{
    private JButton cancelButton;
    private JComboBox employeeComboBox;
    private JTextField rentDateTextField;
    private JTextField returnDateTextField;
    private JButton updateButton;
    private JPanel panel1;

    public UpdateRent(String cname, String clastname, String ename, String elastname, String model, Date rent_date, Date return_date){

        final Hibernate db_connection = new Hibernate();

        setVisible(true);
        setTitle("Edytuj wynajem");
        setContentPane(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setSize(850,80);

        rentDateTextField.setColumns(7);
        rentDateTextField.setText(String.valueOf(rent_date));
        returnDateTextField.setColumns(7);
        returnDateTextField.setText(String.valueOf(return_date));

        List<Employee> list = db_connection.listEmployees();
        Object[] row = new Object[1];
        for (int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getFirstName() + " " + list.get(i).getLastName();
            employeeComboBox.addItem(row[0]);
        }
        employeeComboBox.setSelectedItem(ename+" "+elastname);


        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String employeeFullname = (String) employeeComboBox.getSelectedItem();
                String ef_split[] = employeeFullname.split(" ");
                String new_rent_date = rentDateTextField.getText();
                String new_return_date = returnDateTextField.getText();

                char[] rent_char = new_rent_date.toCharArray();
                char[] return_char = new_return_date.toCharArray();

                Date rentdt = null;
                Date returndt = null;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    rentdt = formatter.parse(new_rent_date);
                    returndt = formatter.parse(new_return_date);
                }
                catch (Exception ef){
                    ef.printStackTrace();
                }

                if (!(Character.isDigit(rent_char[0]) && Character.isDigit(rent_char[1]) && Character.isDigit(rent_char[2]) && Character.isDigit(rent_char[3])
                        && rent_char[4]=='-' && Character.isDigit(rent_char[5]) && Character.isDigit(rent_char[6])
                        && rent_char[7]=='-' && Character.isDigit(rent_char[8]) && Character.isDigit(rent_char[9]) && rent_char.length==10 && return_char.length==10
                        && Character.isDigit(return_char[0]) && Character.isDigit(return_char[1]) && Character.isDigit(return_char[2]) && Character.isDigit(return_char[3])
                        && return_char[4]=='-' && Character.isDigit(return_char[5]) && Character.isDigit(return_char[6]) && return_char[7]=='-' && Character.isDigit(return_char[8]) &&
                        Character.isDigit(return_char[9])))
                {
                    JOptionPane.showMessageDialog(null, "Daty muszą być w formacie RRRR-MM-DD!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                else if(returndt.before(rentdt)){
                    JOptionPane.showMessageDialog(null, "Daty zwrotu musi być późniejsza niż data wypożyczenia!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    db_connection.updateRent(cname, clastname, ename, elastname, ef_split[0], ef_split[1], model, rent_date, return_date, new_rent_date, new_return_date);
                    JOptionPane.showMessageDialog(null, "Pomyślnie zaktualizowano wynajem!");
                    dispose();
                }
            }
        });




    }
}
