package products;

public class Monitor extends Product {
    private Double screenSizeInch;

    public Monitor(String productBrand, String productModel, Double productPrice, Double sizeInch){
        super(productBrand, productModel, productPrice);
        this.screenSizeInch = sizeInch;
    }
    public Double getScreenSizeInch() {
        return screenSizeInch;
    }
    public void setScreenSizeInch(Double sizeInch) {
        this.screenSizeInch = sizeInch;
    }

    public void getMonitor(){
        classOutputFormatter(getProductPrice(), String.valueOf(getScreenSizeInch()), "Monitor");
    }
    @Override
    public String toString() {
        return "Ausgewählt: " + getProductModel() + " - " + getProductBrand() + " - " +getProductPrice() + "€ - " + getScreenSizeInch() + " Zoll";
    }
}
