import products.Maus;
import products.Monitor;
import products.Motherboard;
import products.Tastatur;

public class addDummyData {

    public static void dummyData(){
        Maus maus = new Maus("Corsair", "Gaming M65 RGB Ultra", 49.90, 26000);
        MainShop.mausArrayList.add(maus);
        Monitor monitor = new Monitor("Philips", "Gaming-Monitor E-line 272E1GAJ", 138.99, 27.03);
        MainShop.monitorArrayList.add(monitor);
        Motherboard motherboard = new Motherboard("Supermicro", "X11SSV-Q bulk", 301.95, "Intel 1151");
        MainShop.motherboardArrayList.add(motherboard);
        Tastatur tastatur = new Tastatur("Asus", "90MP0316-BKDA01", 298.89,"mechanisch");
        MainShop.tastaturArrayList.add(tastatur);
        Tastatur tastatur2 = new Tastatur("Sharkoon", "Skiller SGK5", 33.90,"Rubber Dome");
        MainShop.tastaturArrayList.add(tastatur2);
        addDummyData("Maus", 1, 1);
        addDummyData("Monitor", 1, 1);
        addDummyData("Motherboard", 1, 1);
    }

    public static void addDummyData(String productCategory, Integer productMin, Integer productMax){
        switch(productCategory){
            case "Maus" -> {
                for (int i = productMin; i <= productMax; i++){
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    Integer sensorAufloesung = i+i;
                    Maus maus = new Maus(productBrand, productModel, productPrice, sensorAufloesung);
                    MainShop.mausArrayList.add(maus);
                }
            }
            case "Monitor" -> {
                for (int i = productMin; i <= productMax; i++){
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    Double screenSizeInch = (double) (i+i);
                    Monitor monitor = new Monitor(productBrand, productModel, productPrice, screenSizeInch);
                    MainShop.monitorArrayList.add(monitor);
                }
            }
            case "Motherboard" -> {
                for (int i = productMin; i <= productMax; i++) {
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    String socket = "socket" + i;
                    Motherboard motherboard = new Motherboard(productBrand, productModel, productPrice, socket);
                    MainShop.motherboardArrayList.add(motherboard);
                }
            }
//            case "Tastatur" ->;
        }
    }
}
