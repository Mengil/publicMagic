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
        addDummyData("Maus", 1, 10);

        hauptMenue();
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
//            case "Monitor" ->;
//            case "Motherboard" ->;
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
                    System.out.println("Gebe die Sensorauflösung an:");
                    String sensorAufloesung = menuScanner.next();
                    sensorAufloesung = sensorAufloesung.replace(",", "");
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
                    }
                    addAnotherProductCheck();
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
                    }
                    addAnotherProductCheck();
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
                    }
                    addAnotherProductCheck();
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
                    }
                    addAnotherProductCheck();
                    return;
                }default -> wrongInputQuit("wrongInput");
            }
        }
    }
    public static void productArrayListAusgeben(ArrayList<? extends Product> productArrayList, String title, String productCategory) {
        System.out.println(title + " Eigenschaften:");
        for (Object product : productArrayList) {
            System.out.println(product.getProductCategory());
        }
        System.out.println("-------------------------");
    }
    private static void produktBearbeiten(){
        productArrayListAusgeben(mausArrayList, "Mäuse |", "Maus");
        productArrayListAusgeben(monitorArrayList, "Monitore |", "Monitor");
        productArrayListAusgeben(motherboardArrayList, "Motherboards |", "Motherboard");
        productArrayListAusgeben(tastaturArrayList, "Tastaturen |", "Tastatur");

//        for (Maus maus : mausArrayList) {
//            maus.getMaus();
//        }
//        for (Monitor monitor : monitorArrayList) {
//            monitor.getMonitor();
//        }
//        for (Motherboard motherboard : motherboardArrayList) {
//            motherboard.getMotherboard();
//        }
//        for (Tastatur tastatur : tastaturArrayList) {
//            tastatur.getTastatur();
//        }

//        Im Menüpunkt „Produkt bearbeiten“ sollen alle gespeicherten Produkte durchnummeriert angezeigt
//        werden. Ist kein Produkt vorhanden, soll die Info „Keine Produkte vorhanden“ angezeigt werden
//        und das Hauptmenü wieder aufgerufen werden. Nach der Auswahl des zu bearbeitenden Produkts
//        sollen die Eigenschaften neu eingegeben werden und das ausgewählte Produkt aktualisiert werden.
//        Sollte eine Eigenschaft fehlen/leer sein, darf das Produkt nicht abgespeichert werden und die Info
//„Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden“ soll angezeigt werden.
//                Anschließend soll das Hauptmenü wieder aufgerufen werden. Wurde das Produkt korrekt
//        aktualisiert und gespeichert, soll abgefragt werden, ob man nochmal ein Produkt bearbeiten möchte
//        oder nicht. Wenn ja, Bearbeiten erneut aufrufen und bei nein geht es zurück zum Hauptmenü.
    }
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
        System.out.println("Gebe den Preis an:");
        String productPrice = menuScanner.next().replace(",", ".");
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
        }
        System.out.println("\n------------------------------------------------\n" + errorMessage + "\n------------------------------------------------");
        hauptMenue();
    }

}