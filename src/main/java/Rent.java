import java.util.Date;

public class Rent {
    private int id;
    private int car_id;
    private int client_id;
    private int employee_id;
    private Date rent_date;
    private Date return_date;

    public Rent(){
    }

    public Rent(int id, int car_id, int client_id, int employee_id, Date rent_date, Date return_date){
        this.id = id;
        this.car_id = car_id;
        this.client_id = client_id;
        this.employee_id = employee_id;
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
}
