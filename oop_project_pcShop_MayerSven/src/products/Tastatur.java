package products;

public class Tastatur extends Product {
    private String keyboardType;

    public Tastatur(String productBrand, String productModel, Double productPrice, String keyboardType){
        super(productBrand, productModel, productPrice);
        this.keyboardType = keyboardType;
    }

    public String getKeyboardType() {
        return keyboardType;
    }
    public void setKeyboardType(String keyboardType) {
        this.keyboardType = keyboardType;
    }

    public void getTastatur(){
        classOutputFormatter(getProductPrice(), getKeyboardType(), "Tastatur");
    }
}
