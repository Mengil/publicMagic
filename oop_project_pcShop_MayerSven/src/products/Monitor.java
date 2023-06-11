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
        System.out.println("Monitor: " + "[Marke] " + getProductBrand() + " [Modell] " + '"' + getProductModel() + '"' + " [Preis] " + '"' + getProductPrice() + '"' + " [Größe] " + screenSizeInch + " Inch");
    }
}
