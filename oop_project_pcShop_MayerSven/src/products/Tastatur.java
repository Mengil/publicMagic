package products;

public class Tastatur extends Product {
    private String tastenTyp;

    public Tastatur(String productBrand, String productModel, Double productPrice, String tastenTyp){
        super(productBrand, productModel, productPrice);
        this.tastenTyp = tastenTyp;
    }

    public String getTastenTyp() {
        return tastenTyp;
    }
    public void setTastenTyp(String tastenTyp) {
        this.tastenTyp = tastenTyp;
    }

    public void getTastatur(){
        classOutputFormatter(getProductPrice(), getTastenTyp());
    }
}
