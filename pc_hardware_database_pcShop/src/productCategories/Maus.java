package productCategories;

/**
 * Represents a computer mouse product, extends the product class and adds the unique property of sensor resolution.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class Maus extends Product {
    private Integer sensorResolution;

    /**
     * For constructing a new mouse object with the specified properties.
     * @param productBrand The brand of the mouse, e.g. "Corsair".
     * @param productModel The model of the mouse, e.g. "Skiller SGK5".
     * @param productPrice The price of the mouse, e.g. "33,9€".
     * @param sensorResolution The unique property of the mouse, measured in "dpi".
     */
    public Maus(String productBrand, String productModel, Double productPrice, Integer sensorResolution){
        super(productBrand, productModel, productPrice);
        this.sensorResolution = sensorResolution;
    }

    /**
     * To get the sensor resolution of the mouse.
     * @return Hands over the sensor resolution for further usage.
     */
    public Integer getSensorResolution() {
        return sensorResolution;
    }

    /**
     * To set the sensor resolution value of the mouse.
     * @param sensorResolution To make sure the provided sensorResolution value is transferred to the private Integer sensorResolution.
     */
    public void setSensorResolution(Integer sensorResolution) {
        this.sensorResolution = sensorResolution;
    }

    /**
     * Custom method to display the formatted mouse information in mainshop.productsOutputToConsole();.
     */
    public void getMaus() {classOutputFormatter(getProductPrice(), String.valueOf(getSensorResolution()), "Maus");}

    /**
     * To ensure the custom implementation overrides the method in the superclass.
     * @return For customised output of the mouse properties.
     */
    @Override
    public String toString() {
        return getProductModel() + " - " + getProductBrand() + " - " +getProductPrice() + "€ - " + getSensorResolution() + "dpi";
    }
}