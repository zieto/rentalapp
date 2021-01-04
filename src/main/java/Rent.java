import java.time.LocalDate;
import java.util.Date;

public class Rent {
    private int id;
    private int car_id;
    private int client_id;
    private int employee_id;
    private Date rent_date;
    private Date return_date;

    private String kName;
    private String kLastName;
    private String pName;
    private String pLastName;
    private String model;

    public Rent(){
    }

    public Rent(int car_id, int client_id, int employee_id, Date rent_date, Date return_date){
        this.car_id = car_id;
        this.client_id = client_id;
        this.employee_id = employee_id;
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

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

