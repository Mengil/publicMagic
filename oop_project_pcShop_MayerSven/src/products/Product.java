package products;

public class Product {
    private String productBrand, productModel;
    private Double productPrice;
    public Product(String  productBrand, String productModel, Double productPrice){
        this.productBrand = productBrand;
        this.productModel = productModel;
        this.productPrice = productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
