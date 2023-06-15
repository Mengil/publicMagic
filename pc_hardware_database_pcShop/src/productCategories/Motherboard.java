package productCategories;

/**
 * Represents a computer motherboard product, extends the product class and adds the unique property of socket.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class Motherboard extends Product {
    private String motherboardSocket;

    /**
     * For constructing a new motherboard object with the specified properties.
     * @param productBrand The brand of the motherboard, e.g. "Supermicro".
     * @param productModel The model of the motherboard, e.g. "X11SSV-Q bulk".
     * @param productPrice The price of the motherboard, e.g. "301,95€".
     * @param motherboardSocket The unique property of the motherboard, specified with "Sockel".
     */
    public Motherboard(String productBrand, String productModel, Double productPrice, String motherboardSocket){
        super(productBrand, productModel, productPrice);
        this.motherboardSocket = motherboardSocket;
    }

    /**
     * To get the socket of the monitor.
     * @return Hands over the socket for further usage.
     */
    public String getMotherboardSocket() {
        return motherboardSocket;
    }

    /**
     * To set the socket value of the motherboard.
     * @param motherboardSocket To make sure the provided socket value is transferred to the private String motherboardSocket.
     */
    public void setMotherboardSocket(String motherboardSocket) {
        this.motherboardSocket = motherboardSocket;
    }

    /**
     * Custom method to display the formatted motherboard information in mainshop.productsOutputToConsole();.
     */
    public void getMotherboard(){
        classOutputFormatter(getProductPrice(), getMotherboardSocket(), "Motherboard");
    }

    /**
     * To ensure the custom implementation overrides the method in the superclass.
     * @return For customised output of the motherboard properties.
     */
    @Override
    public String toString() {
        return getProductModel() + " - " + getProductBrand() + " - " +getProductPrice() + "€ - " + getMotherboardSocket();
    }
}