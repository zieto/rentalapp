import java.time.LocalDate;
import java.util.Date;

public class Rent {
    private int id;
    //private int car_id;
    //private int client_id;
    //private int employee_id;
    private Date rent_date;
    private Date return_date;
    private Car car;
    private Client client;
    private Employee employee;

    private String kName;
    private String kLastName;
    private String pName;
    private String pLastName;
    private String model;

    public Rent(){
    }

    public Rent(Car car_id, Client client_id, Employee employee_id, Date rent_date, Date return_date){
        this.car = car_id;
        this.client = client_id;
        this.employee = employee_id;
        this.rent_date = rent_date;
        this.return_date = return_date;
    }

    public Rent(String model, String kName, String kLastName, String pName, String pLastName, Date rent_date, Date return_date){
        this.model = model;
        this.kName = kName;
        this.kLastName = kLastName;
        this.pName = pName;
        this.pLastName = pLastName;
        this.rent_date = rent_date;
        this.return_date = return_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar_id() {
        return car;
    }

    public void setCar_id(Car car_id) {
        this.car = car_id;
    }

    public Client getClient_id() {
        return client;
    }

    public void setClient_id(Client client_id) {
        this.client = client_id;
    }

    public Employee getEmployee_id() {
        return employee;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee = employee_id;
    }

    public Date getRent_date() {
        return rent_date;
    }

    public void setRent_date(Date rent_date) {
        this.rent_date = rent_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public String getkName() {
        return kName;
    }

    public void setkName(String kName) {
        this.kName = kName;
    }

    public String getkLastName() {
        return kLastName;
    }

    public void setkLastName(String kLastName) {
        this.kLastName = kLastName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpLastName() {
        return pLastName;
    }

    public void setpLastName(String pLastName) {
        this.pLastName = pLastName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

