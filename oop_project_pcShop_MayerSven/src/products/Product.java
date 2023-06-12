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
    public void classOutputFormatter(double number, String uniqueProperty) {
        DecimalFormat newNumberFormat = new DecimalFormat("0.##");
        String formattedPrice = newNumberFormat.format(number);
        System.out.println(getProductBrand() + " - " + getProductModel() + " - " + formattedPrice + "€ - " + uniqueProperty);
    }

    public String getProductBrand() {return productBrand;}
    public void setProductBrand(String productBrand) {this.productBrand = productBrand;}

    public String getProductModel() {return productModel;}
    public void setProductModel(String productModel) {this.productModel = productModel;}

    public Double getProductPrice() {return productPrice;}
    public void setProductPrice(Double productPrice) {this.productPrice = productPrice;}
}
