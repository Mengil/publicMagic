package products;

public class Maus extends Product {
    private Integer sensorResolution;

    public Maus(String productBrand, String productModel, Double productPrice, Integer sensorResolution){
        super(productBrand, productModel, productPrice);
        this.sensorResolution = sensorResolution;
    }

    public Integer getSensorResolution() {
        return sensorResolution;
    }
    public void setSensorResolution(Integer sensorResolution) {
        this.sensorResolution = sensorResolution;
    }

    public void getMaus() {
        classOutputFormatter(getProductPrice(), String.valueOf(getSensorResolution()), "Maus");
    }

}
