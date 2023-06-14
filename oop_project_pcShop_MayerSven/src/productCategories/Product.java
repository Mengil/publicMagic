package productCategories;

import java.text.DecimalFormat;

/**
 * Represents a generic product as main class that provides properties for the
 * "Motherboard", "Monitor", "Maus" and "Tastatur" classes.
 * Includes a method for formatting and displaying product information based on the product type.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class Product {
    private String productBrand, productModel;
    private Double productPrice;

    /**
     * For constructing  a new Product object with the specified brand, model, and price.
     * @param productBrand The brand of the child product.
     * @param productModel The model of the child product.
     * @param productPrice The price of the child product.
     */
    public Product(String  productBrand, String productModel, Double productPrice){
        this.productBrand = productBrand;
        this.productModel = productModel;
        this.productPrice = productPrice;
    }

    /**
     *Formats and displays product information based on the unique properties of each type of product.
     * @param number The number that has to be formatted, e.g. the price.
     * @param uniqueProperty To output the unique property of the child product, e.g. "Sockel".
     * @param productName To determine which child product properties are to be output, e.g. "Motherboard".
     */
    public void classOutputFormatter(double number, String uniqueProperty, String productName) {
        DecimalFormat newNumberFormat = new DecimalFormat("0.##");
        String formattedNumber = newNumberFormat.format(number);
        switch (productName) {
            case "Maus" -> System.out.println(getProductModel() + " - " + getProductBrand() + " - " + formattedNumber + "€ - " + uniqueProperty + "dpi");
            case "Monitor" -> System.out.println(getProductModel() + " - " + getProductBrand() + " - " + formattedNumber + "€ - " + uniqueProperty + " Zoll");
            case "Motherboard" -> System.out.println(getProductModel() + " - " + getProductBrand() + " - " + formattedNumber + "€ - " + "Sockel " + uniqueProperty);
            case "Tastatur" -> System.out.println(getProductModel() + " - " + getProductBrand() + " - " + formattedNumber + "€ - " + "Typ " + uniqueProperty);
        }
    }

    /**
     * To get the brand of the child product.
     * @return Hands over the brand for further usage.
     */
    public String getProductBrand() {return productBrand;}
    /**
     * To get the model of the child product.
     * @return Hands over the model for further usage.
     */
    public String getProductModel() {return productModel;}
    /**
     * To get the price of the child product.
     * @return Hands over the price for further usage.
     */
    public Double getProductPrice() {return productPrice;}

    /**
     * To set the brand value of the child product.
     * @param productBrand To make sure the provided brand value is transferred to the private String productBrand.
     */
    public void setProductBrand(String productBrand) {this.productBrand = productBrand;}

    /**
     * To set the model value of the child product.
     * @param productModel To make sure the provided brand value is transferred to the private String productModel.
     */
    public void setProductModel(String productModel) {this.productModel = productModel;}

    /**
     * To set the price value of the child product.
     * @param productPrice To make sure the provided price value is transferred to the private Double productPrice;.
     */
    public void setProductPrice(Double productPrice) {this.productPrice = productPrice;}
}