package productCategories;

/**
 * Represents a computer keyboard product, extends the product class and adds the unique property of keyboard type.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class Tastatur extends Product {
    private String keyboardType;

    /**
     * For constructing a new keyboard object with the specified properties.
     * @param productBrand The brand of the keyboard, e.g. "Corsair".
     * @param productModel The model of the keyboard, e.g. "Gaming M65 RGB Ultra".
     * @param productPrice The price of the keyboard, e.g. "49,9€".
     * @param keyboardType The unique property of the keyboard, normally "mechanisch" or "rubber dome".
     */
    public Tastatur(String productBrand, String productModel, Double productPrice, String keyboardType){
        super(productBrand, productModel, productPrice);
        this.keyboardType = keyboardType;
    }

    /**
     * To get the keyboard type of the keyboard.
     * @return Hands over the keyboard type for further usage.
     */
    public String getKeyboardType() {
        return keyboardType;
    }

    /**
     * To set the keyboard type value of the monitor.
     * @param keyboardType To make sure the provided keyboardType value is transferred to the private String keyboardType;.
     */
    public void setKeyboardType(String keyboardType) {
        this.keyboardType = keyboardType;
    }

    /**
     * Custom method to display the formatted keyboard information in mainshop.productsOutputToConsole();.
     */
    public void getTastatur(){
        classOutputFormatter(getProductPrice(), getKeyboardType(), "Tastatur");
    }

    /**
     * To ensure the custom implementation overrides the method in the superclass.
     * @return For customised output of the keyboard properties.
     */
    @Override
    public String toString() {
        return getProductModel() + " - " + getProductBrand() + " - " +getProductPrice() + "€ - " + getKeyboardType();
    }
}