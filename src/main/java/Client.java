public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;


    public Client() {
    }

    public Client(String firstName, String lastName, String tel, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = tel;
        this.email = email;
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

    public String getTelephone (){
        return telephone;
    }

    public void setTelephone ( String telephone ){
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

}
