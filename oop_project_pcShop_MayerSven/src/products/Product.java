package products;

import java.text.DecimalFormat;

public class Product {
    private String productBrand, productModel;
    private Double productPrice;
    public Product(String  productBrand, String productModel, Double productPrice){
        this.productBrand = productBrand;
        this.productModel = productModel;
        this.productPrice = productPrice;
    }
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

    public String getProductBrand() {return productBrand;}
    public void setProductBrand(String productBrand) {this.productBrand = productBrand;}

    public String getProductModel() {return productModel;}
    public void setProductModel(String productModel) {this.productModel = productModel;}

    public Double getProductPrice() {return productPrice;}
    public void setProductPrice(Double productPrice) {this.productPrice = productPrice;}
}
