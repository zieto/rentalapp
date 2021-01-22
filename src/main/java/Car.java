public class Car {

    private int id;
    //private int cat_id;
    private String brand;
    private String model;
    private String engine;
    private Boolean rented;
    private CarCategory carCategory;

    private String cat;

    public Car(){
    }

    public Car(CarCategory cat_id, String brand, String model, String engine, Boolean rented){
        this.carCategory = cat_id;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.rented = rented;
    }

    public Car(String cat, String brand, String model, String engine, Boolean rented){
        this.cat = cat;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.rented = rented;
    }

    public CarCategory getCarCategory(){
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory){
        this.carCategory = carCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarCategory getCat_id() {
        return carCategory;
    }

    public void setCat_id(CarCategory cat_id) {
        this.carCategory = cat_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public String getCat(){ return cat;}

    public void setCat(String cat) {
        this.cat = cat;
    }
}
