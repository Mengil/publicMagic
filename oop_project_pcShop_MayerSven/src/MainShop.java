import products.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static workStuff.PropertyHandler.propertyArrayListHandler;

public class MainShop {
    private static final Scanner menuScanner = new Scanner(System.in);
    private static final String standardMenuPrompt = "------------------------------------------------ \n" + "Bitte wählen:";
    private static final ArrayList<String> categoryPropertiesArrayList = new ArrayList<>(Arrays.asList("Marke", "Modell", "Preis"));
    private static ArrayList<Maus> mausArrayList;
    private static ArrayList<Monitor> monitorArrayList;
    private static ArrayList<Motherboard> motherboardArrayList;
    private static ArrayList<Tastatur> tastaturArrayList;

    public static void main(String[] args) {
        mausArrayList = new ArrayList<>();
        monitorArrayList = new ArrayList<>();
        motherboardArrayList = new ArrayList<>();
        tastaturArrayList = new ArrayList<>();

        dummyData();
        hauptMenue();
    }
    public static void dummyData(){
        Maus maus = new Maus("Corsair", "Gaming M65 RGB Ultra", 49.90, 26000);
        mausArrayList.add(maus);
        Monitor monitor = new Monitor("Philips", "Gaming-Monitor E-line 272E1GAJ", 138.99, 27.03);
        monitorArrayList.add(monitor);
        Motherboard motherboard = new Motherboard("Supermicro", "X11SSV-Q bulk", 301.95, "Intel 1151");
        motherboardArrayList.add(motherboard);
        Tastatur tastatur = new Tastatur("Asus", "90MP0316-BKDA01", 298.89,"mechanisch");
        tastaturArrayList.add(tastatur);
        Tastatur tastatur2 = new Tastatur("Sharkoon", "Skiller SGK5", 33.90,"Rubber Dome");
        tastaturArrayList.add(tastatur2);
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
                    mausArrayList.add(maus);
                }
            }
            case "Monitor" -> {
                for (int i = productMin; i <= productMax; i++){
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    Double screenSizeInch = (double) (i+i);
                    Monitor monitor = new Monitor(productBrand, productModel, productPrice, screenSizeInch);
                    monitorArrayList.add(monitor);
                }
            }
            case "Motherboard" -> {
                for (int i = productMin; i <= productMax; i++) {
                    String productBrand = productCategory + "Marke" + i;
                    String productModel = productCategory + "Modell" + i;
                    Double productPrice = (double) i;
                    String socket = "socket" + i;
                    Motherboard motherboard = new Motherboard(productBrand, productModel, productPrice, socket);
                    motherboardArrayList.add(motherboard);
                }
            }
//            case "Tastatur" ->;
        }
    }
    private static void mausMenu() {
        String menuTitle = "Maus anlegen";
        String productCategory = "Maus";
        propertyArrayListHandler(categoryPropertiesArrayList,"mausMenu", productCategory);
        Maus maus = new Maus("", "", 0.0, 0);

        while (true) {
            MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
            String mausMenuCategorySelectInput = menuScanner.next();

            switch (mausMenuCategorySelectInput) {
                case "1", "1)" -> {
                    System.out.println("Gebe die Marke an:");
                    String productBrand = menuScanner.next();
                    maus.setProductBrand(productBrand);
                }
                case "2", "2)" -> {
                    System.out.println("Gebe das Modell an:");
                    String productModel = menuScanner.next();
                    maus.setProductModel(productModel);
                }
                case "3", "3)" -> productPriceInput(maus);
                case "4", "4)" -> {
                    System.out.println("Gebe die Sensorauflösung (in dpi) an:");
                    String sensorAufloesung = menuScanner.next().toLowerCase();
                    sensorAufloesung = sensorAufloesung.replace(",", "").replace("dpi", "");
                    try {
                        Integer aufloesung = Integer.valueOf(sensorAufloesung);
                        maus.setSensorAufloesung(aufloesung);
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
                case "0", "0)" -> {
                    if (maus.getProductBrand().isEmpty() || maus.getProductModel().isEmpty() || maus.getProductPrice() == 0.0 || maus.getSensorAufloesung() == 0) {
                        wrongInputQuit("emptyValue");
                    } else {
                        System.out.println("Alle Eigenschaften der Maus sind vorhanden und haben gültige Werte.");
                        mausArrayList.add(maus);
                        addAnotherProductCheck();
                    }
                    return;
                }
                default -> wrongInputQuit("wrongInput");
            }
        }
    }
    private static void tastaturMenu() {
        String productCategory = "Tastatur";
        String menuTitle = "Tastatur anlegen";
        propertyArrayListHandler(categoryPropertiesArrayList,"tastaturMenu", productCategory);
        Tastatur tastatur = new Tastatur("", "", 0.0, "");

        while (true) {
            MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
            String tastaturMenuCategorySelectInput = menuScanner.next();

            switch (tastaturMenuCategorySelectInput) {
                case "1", "1)" -> {
                    System.out.println("Gebe die Marke an:");
                    String productBrand = menuScanner.next();
                    tastatur.setProductBrand(productBrand);
                }
                case "2", "2)" -> {
                    System.out.println("Gebe das Modell an:");
                    String productModel = menuScanner.next();
                    tastatur.setProductModel(productModel);
                }
                case "3", "3)" -> productPriceInput(tastatur);
                case "4", "4)" -> {
                    System.out.println("Gebe den Tastentyp an:");
                    String tastenTyp = menuScanner.next();
                    tastatur.setTastenTyp(tastenTyp);
                }
                case "0", "0)" -> {
                    if (tastatur.getProductBrand().isEmpty() || tastatur.getProductModel().isEmpty() || tastatur.getProductPrice() == 0.0 || tastatur.getTastenTyp().isEmpty()) {
                        wrongInputQuit("emptyValue");
                    } else {
                        System.out.println("Alle Eigenschaften der Tastatur sind vorhanden und haben gültige Werte.");
                        tastaturArrayList.add(tastatur);
                        addAnotherProductCheck();
                    }
                    return;
                }default -> wrongInputQuit("wrongInput");
            }
        }
    }
    private static void motherboardMenu() {
        String productCategory = "Motherboard";
        String menuTitle = "Motherboard anlegen";
        propertyArrayListHandler(categoryPropertiesArrayList,"motherboardMenu", productCategory);

        Motherboard motherboard = new Motherboard("", "", 0.0, "");
        while (true) {
            MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
            String motherboardMenuCategorySelectInput = menuScanner.next();

            switch (motherboardMenuCategorySelectInput) {
                case "1", "1)" -> {
                    System.out.println("Gebe die Marke an:");
                    String productBrand = menuScanner.next();
                    motherboard.setProductBrand(productBrand);
                }
                case "2", "2)" -> {
                    System.out.println("Gebe das Modell an:");
                    String productModel = menuScanner.next();
                    motherboard.setProductModel(productModel);
                }
                case "3", "3)" -> productPriceInput(motherboard);
                case "4", "4)" -> {
                    System.out.println("Gebe den Sockel an:");
                    String motherboardSocket = menuScanner.next();
                    motherboard.setMotherboardSocket(motherboardSocket);
                }
                case "0", "0)" -> {
                    if (motherboard.getProductBrand().isEmpty() || motherboard.getProductModel().isEmpty() || motherboard.getProductPrice() == 0.0 || motherboard.getMotherboardSocket().isEmpty()) {
                        wrongInputQuit("emptyValue");
                    } else {
                        System.out.println("Alle Eigenschaften des Motherboards sind vorhanden und haben gültige Werte.");
                        motherboardArrayList.add(motherboard);
                        addAnotherProductCheck();
                    }
                    return;
                }default -> wrongInputQuit("wrongInput");
            }
        }
    }
    private static void monitorMenu() {
        String productCategory = "Monitor";
        String menuTitle = "Monitor anlegen";
        propertyArrayListHandler(categoryPropertiesArrayList,"monitorMenu", productCategory);

        Monitor monitor = new Monitor("", "", 0.0, 0.0);
        while (true) {
            MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
            String monitorMenuCategorySelectInput = menuScanner.next();

            switch (monitorMenuCategorySelectInput) {
                case "1", "1)" -> {
                    System.out.println("Gebe die Marke an:");
                    monitor.setProductBrand(menuScanner.next());
                }
                case "2", "2)" -> {
                    System.out.println("Gebe das Modell an:");
                    monitor.setProductModel(menuScanner.next());
                }
                case "3", "3)" -> productPriceInput(monitor);
                case "4", "4)" -> {
                    System.out.println("Gebe die Größe(Zoll) an:");
                    String productScreenSize = menuScanner.next().replace(",", ".");
                    try {
                        monitor.setScreenSizeInch(Double.valueOf(productScreenSize));
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
                case "0", "0)" -> {
                    if (monitor.getProductBrand().isEmpty() || monitor.getProductModel().isEmpty() || monitor.getProductPrice() == 0.0 || monitor.getScreenSizeInch() == 0.0) {
                        wrongInputQuit("emptyValue");
                    } else {
                        System.out.println("Alle Eigenschaften des Monitors sind vorhanden und haben gültige Werte.");
                        monitorArrayList.add(monitor);
                        addAnotherProductCheck();
                    }
                    return;
                }default -> wrongInputQuit("wrongInput");
            }
        }
    }

    private static void produktBearbeiten(){
        if(mausArrayList.isEmpty() && monitorArrayList.isEmpty() && motherboardArrayList.isEmpty() && tastaturArrayList.isEmpty()){
            wrongInputQuit("productsNotExist");
        }else{
            int productCount = 0;
            if (!mausArrayList.isEmpty()) {
                System.out.println("[Produkte der Kategorie Mäuse]");
                for (Maus maus : mausArrayList) {
                    productCount++;
                    System.out.print("\t" + productCount + ") ");
                    maus.getMaus();
                }
            }
            if (!monitorArrayList.isEmpty()) {
                System.out.println("[Produkte der Kategorie Monitore]");
                for (Monitor monitor : monitorArrayList) {
                    productCount++;
                    System.out.print("\t" + productCount + ") ");
                    monitor.getMonitor();
                }
            }
            if (!motherboardArrayList.isEmpty()) {
                System.out.println("[Produkte der Kategorie Motherboards]");
                for (Motherboard motherboard : motherboardArrayList) {
                    productCount++;
                    System.out.print("\t" + productCount + ") ");
                    motherboard.getMotherboard();
                }
            }
            if (!tastaturArrayList.isEmpty()) {
                System.out.println("[Produkte der Kategorie Tastaturen]");
                for (Tastatur tastatur : tastaturArrayList) {
                    productCount++;
                    System.out.print("\t" + productCount + ") ");
                    tastatur.getTastatur();
                }
            }
            System.out.println("Tippe die Nummer eines Produkts ein, um dieses zu bearbeiten:");
            int productEditInput = menuScanner.nextInt();

            if (productEditInput >= 1 && productEditInput <= productCount) {
                if (productEditInput <= mausArrayList.size()) {
                    Maus selectedMaus = mausArrayList.get(productEditInput - 1);
                    selectedMaus.getMaus();
                } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size()) {
                    Monitor selectedMonitor = monitorArrayList.get(productEditInput - mausArrayList.size() - 1);
                    selectedMonitor.getMonitor();
                } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size() + motherboardArrayList.size()) {
                    Motherboard selectedMotherboard = motherboardArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - 1);
                    selectedMotherboard.getMotherboard();
                } else {
                    Tastatur selectedTastatur = tastaturArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - motherboardArrayList.size() - 1);
                    selectedTastatur.getTastatur();
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte eine gültige Nummer eingeben.");
            }
        }
    }
//        sollen die Eigenschaften neu eingegeben werden und das ausgewählte Produkt aktualisiert werden.
//        Sollte eine Eigenschaft fehlen/leer sein, darf das Produkt nicht abgespeichert werden und die Info
//„Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden“ soll angezeigt werden.
//                Anschließend soll das Hauptmenü wieder aufgerufen werden. Wurde das Produkt korrekt
//        aktualisiert und gespeichert, soll abgefragt werden, ob man nochmal ein Produkt bearbeiten möchte
//        oder nicht. Wenn ja, Bearbeiten erneut aufrufen und bei nein geht es zurück zum Hauptmenü.





    private static void produktAnlegen(){
        ArrayList<String> productCreateMenuItemArrayList = new ArrayList<>(Arrays.asList("Monitor", "Motherboard", "Tastatur", "Maus"));
        MenuBuilder.menuBuilder("Produkt anlegen", "Produktkategorien", productCreateMenuItemArrayList, standardMenuPrompt);
        String productMenuSelectCategoryInput = menuScanner.next();
        switch (productMenuSelectCategoryInput) {
            case "1", "1)", "Monitor" -> monitorMenu();
            case "2", "2)", "Motherboard" -> motherboardMenu();
            case "3", "3)", "Tastatur" -> tastaturMenu();
            case "4", "4)", "Maus" -> mausMenu();
            default -> wrongInputQuit("wrongInput");
        }
    }
    private static void hauptMenue(){
        ArrayList<String> mainMenuItemArrayList = new ArrayList<>(Arrays.asList("Produkt anlegen", "Produkt bearbeiten", "Produkt suchen", "Produkt löschen", "Shop beenden"));
        MenuBuilder.menuBuilder("Hauptmenü", "", mainMenuItemArrayList, standardMenuPrompt);
        String mainMenuSelectInput = menuScanner.next();
        switch (mainMenuSelectInput) {
            case "1", "1)", "Produkt anlegen" -> produktAnlegen();
            case "2", "2)", "Produkt bearbeiten" -> produktBearbeiten();    // produktBearbeiten();
            case "3", "3)","Produkt suchen" -> MainShop.main(null);    //produktSuchen();
            case "4", "4)","Produkt löschen" -> MainShop.main(null);    //produktLoeschen();
            case "0", "0)","Shop beenden" -> shopBeenden();
            default -> wrongInputQuit("wrongInput");
        }
    }
    private static void productPriceInput(Product product){
        System.out.println("Gebe den Preis(in €) an:");
        String productPrice = menuScanner.next().replace(",", ".").replace("€", "");
        try { product.setProductPrice(Double.valueOf(productPrice));
        } catch (NumberFormatException e) {
            wrongInputQuit("wrongInput");
        }
    }
    private static void addAnotherProductCheck(){
        System.out.println("""
        ------------------------------------------------
        Möchtest du ein weiteres Produkt hinzufügen?
        ------------------------------------------------
        (J)a
        (N)ein
        ------------------------------------------------
        Bitte wählen:""");
        String weiteresProdukt = menuScanner.next().trim();
        if (weiteresProdukt.equalsIgnoreCase("j")) {
            produktAnlegen();
        } else if (weiteresProdukt.equalsIgnoreCase("n")) {
            hauptMenue();
        } else {
            wrongInputQuit("wrongInput");
        }
    }
    private static void shopBeenden(){
        System.out.println("""
        ------------------------------------------------
        Soll wirklich beendet werden?
        ------------------------------------------------
        (J)a
        (N)ein
        ------------------------------------------------
        Bitte wählen:""");
        String exitConfirmInput = menuScanner.next().trim();
        switch (exitConfirmInput){
            case "j","J" -> {
                System.out.println("PC Shop wurde beendet");
                System.exit(0);
            }
            case "N", "n" -> MainShop.main(null);
            default -> wrongInputQuit("wrongInput");
        }
    }
    private static void wrongInputQuit(String errorType){
        String errorMessage = null;
        switch(errorType){
            case "wrongInput" -> errorMessage = "Info: Fehlerhafte Eingabe";
            case "emptyValue" -> errorMessage = "Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden";
            case "productsNotExist" -> errorMessage = "Keine Produkte vorhanden";
        }
        System.out.println("\n------------------------------------------------\n" + errorMessage + "\n------------------------------------------------");
        hauptMenue();
    }

}