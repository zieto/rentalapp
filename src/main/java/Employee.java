public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private String telephone;


    public Employee() {
    }

    public Employee(String firstName, String lastName, String tel, int salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = tel;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String imie ) {
        this.firstName = imie;
    }

    public void setLastName( String nazwisko ) {
        this.lastName = nazwisko;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary( int wynagrodzenie ) {
        this.salary = wynagrodzenie;
    }

}
