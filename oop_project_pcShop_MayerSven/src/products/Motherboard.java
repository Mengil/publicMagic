package products;

public class Motherboard extends Product {
    private String motherboardSocket;

    public Motherboard(String productBrand, String productModel, Double productPrice, String motherboardSocket){
        super(productBrand, productModel, productPrice);
        this.motherboardSocket = motherboardSocket;
    }
    public String getMotherboardSocket() {
        return motherboardSocket;
    }
    public void setMotherboardSocket(String MotherboardSocket) {
        this.motherboardSocket = MotherboardSocket;
    }

    public void getMotherboard(){
        classOutputFormatter(getProductPrice(), getMotherboardSocket(), "Motherboard");
    }
}
