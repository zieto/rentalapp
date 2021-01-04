public class CarCategory {
    private int id;
    private int admin_id;
    private String name;
    private String desc;

    private String pName;
    private String pLastName;

    public CarCategory(){
    }

    public CarCategory(int admin_id, String name, String desc){
        this.admin_id = admin_id;
        this.name = name;
        this.desc = desc;
    }

    public CarCategory(String name, String desc, String pName, String pLastName){
        this.name = name;
        this.desc = desc;
        this.pName = pName;
        this.pLastName = pLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}
