package products;

public class Maus extends Product {
    private Integer sensorAufloesung;

    public Maus(String productBrand, String productModel, Double productPrice, Integer sensorAufloesung){
        super(productBrand, productModel, productPrice);
        this.sensorAufloesung = sensorAufloesung;
    }

    public Integer getSensorAufloesung() {
        return sensorAufloesung;
    }

    public void setSensorAufloesung(Integer sensorAufloesung) {
        this.sensorAufloesung = sensorAufloesung;
    }

    public void getMaus(){
        System.out.println("Maus: " + "[Marke] " + getProductBrand() + " [Modell] " + '"' + getProductModel() + '"' + " [Preis] " + '"' + getProductPrice() + '"' + " [Sensorauflösung] " + sensorAufloesung + " dpi");
    }
}
