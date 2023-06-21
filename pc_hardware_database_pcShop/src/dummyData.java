import productCategories.Maus;
import productCategories.Monitor;
import productCategories.Motherboard;
import productCategories.Tastatur;

/**
 * Provides a method to add dummy data to the product lists in the MainShop Class for testing purposes.
 * To create instances of different product categories, such as "Maus", "Motherboard", "Monitor" and "Tastatur"
 * and adds them to the corresponding ArrayLists in MainShop.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class dummyData {

    /**
     * To add specific instances of each prdocut category to the lists.
     */
    public static void dummyDataAdd(){
        Maus maus = new Maus("Corsair", "Gaming M65 RGB Ultra", 49.90, "26000");
        MainShop.mausArrayList.add(maus);
//        Monitor monitor = new Monitor("Philips", "Gaming-Monitor E-line 272E1GAJ", 138.99, 27.03);
//        MainShop.monitorArrayList.add(monitor);
//        Motherboard motherboard = new Motherboard("Supermicro", "X11SSV-Q bulk", 301.95, "Intel 1151");
//        MainShop.motherboardArrayList.add(motherboard);
//        Tastatur tastatur = new Tastatur("Asus", "90MP0316-BKDA01", 298.89,"mechanisch");
//        MainShop.tastaturArrayList.add(tastatur);
//        Tastatur tastatur2 = new Tastatur("Sharkoon", "Skiller SGK5", 33.90,"Rubber Dome");
//        MainShop.tastaturArrayList.add(tastatur2);
//        addDummyData("Maus", 1, 2);
//        addDummyData("Monitor", 1, 2);
//        addDummyData("Motherboard", 1, 2);
//        addDummyData("Tastatur", 1, 2);
    }

    /**
     * To add a range of dummy data for a specific product category.
     * @param productCategory The category of the product.
     * @param productMin      The minimum amount of dummy data to generate.
     * @param productMax      The maximum amount of dummy data to generate.
     */
    public static void addDummyData(String productCategory, Integer productMin, Integer productMax){
        switch(productCategory){
            case "Maus" -> {
                for (int i = productMin; i <= productMax; i++){
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    String sensorAufloesung = "i"+i;
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
            case "Tastatur" -> {
                for (int i = productMin; i <= productMax; i++) {
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    String keyboardType = "Tastaturentyp" + i;
                    Tastatur tastatur = new Tastatur(productBrand, productModel, productPrice, keyboardType);
                    MainShop.tastaturArrayList.add(tastatur);
                }
            }
        }
    }
}