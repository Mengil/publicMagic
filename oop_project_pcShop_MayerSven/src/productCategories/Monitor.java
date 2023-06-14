package productCategories;

/**
 * Represents a computer monitor product, extends the product class and adds the unique property of screen size.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class Monitor extends Product {
    private Double screenSizeInch;

    /**
     * For constructing a new monitor object with the specified properties.
     * @param productBrand The brand of the monitor, e.g. "Philips".
     * @param productModel The model of the monitor, e.g. "E-line 272E1GAJ".
     * @param productPrice The price of the monitor, e.g. "138,99€".
     * @param sizeInch The unique property of the monitor, measured in "Zoll".
     */
    public Monitor(String productBrand, String productModel, Double productPrice, Double sizeInch){
        super(productBrand, productModel, productPrice);
        this.screenSizeInch = sizeInch;
    }

    /**
     * To get the screen size of the monitor.
     * @return Hands over the screen size for further usage.
     */
    public Double getScreenSizeInch() {
        return screenSizeInch;
    }

    /**
     * To set the screen size value of the monitor.
     * @param sizeInch To make sure the provided sizeInch value is transferred to the private Double screenSizeInch.
     */
    public void setScreenSizeInch(Double sizeInch) {
        this.screenSizeInch = sizeInch;
    }

    /**
     * Custom method to display the formatted monitor information in mainshop.productsOutputToConsole();.
     */
    public void getMonitor(){
        classOutputFormatter(getProductPrice(), String.valueOf(getScreenSizeInch()), "Monitor");
    }

    /**
     * To ensure the custom implementation overrides the method in the superclass.
     * @return For customised output of the monitor properties.
     */
    @Override
    public String toString() {
        return getProductModel() + " - " + getProductBrand() + " - " +getProductPrice() + "€ - " + getScreenSizeInch() + " Zoll";
    }
}