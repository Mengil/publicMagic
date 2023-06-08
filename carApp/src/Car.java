public class Car {
    private String brand, model;
    private Integer id, hp;

    //  constructor
    public Car(Integer id, String brand, String model, Integer hp) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.hp = hp;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Integer getHp() { return hp; }
    public void setHp(Integer hp) { this.hp = hp; }

    public void getCar(){
        System.out.println("Selected Car: " + "[ID] " + id + " [Brand] " + '"' + brand + '"' + " [Model] " + '"' + model + '"' + " [HP] " + hp);
    }
    //    readable output as string
    @Override
    public String toString() {
        return "ID: "  + id + " | " +
                "Brand: " + brand + " " +
                "| Model: " + model + " " +
                "| Horsepower: " + hp;
    }
}