import products.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainShop {
    public static Scanner menuScanner = new Scanner(System.in);
    public static String standardMenuPrompt = "------------------------------------------------ \n" + "Bitte wählen:";
    public static ArrayList<String> categoryPropertiesArrayList = new ArrayList<>(Arrays.asList("Marke", "Modell", "Preis"));
//    public static Boolean markeInputCheck, modellInputCheck, preisInputCheck;

    public static void main(String[] args) {
        hauptMenue();
    }

    public static void saveMonitor(){
        System.out.println("wird gespeichert");
    }
    public static void motherboardMenu(ArrayList<String> productPropertyList){
        menuBuilder("Motherboard anlegen", "Eigenschaften", productPropertyList, standardMenuPrompt);
    }
    public static void tastaturMenu(ArrayList<String> productPropertyList){
        menuBuilder("Tastatur anlegen", "Eigenschaften", productPropertyList, standardMenuPrompt);
    }
    public static void mausMenu(ArrayList<String> productPropertyList){
        menuBuilder("Maus anlegen", "Eigenschaften", productPropertyList, standardMenuPrompt);
    }

    public static void menuBuilder(String menuTitle, String menuHeader, ArrayList<String> menuItems, String menuPrompt){
        System.out.println(
                "------------------------------------------------ \n" +
                        "PC-Shop" + "\t\t\t" + menuTitle + "\t\t" + "von: " + "Sven Mayer" +
                        "\n------------------------------------------------");
        if(!menuHeader.isEmpty()){
            System.out.println(menuHeader);
        }
        for (int i = 0; i < menuItems.size(); i++){
            if(menuItems.size() > 4){
                if (i == menuItems.size() - 1) {
                    System.out.println("0) " + menuItems.get(i));
                } else {
                    System.out.println((i + 1) + ") " + menuItems.get(i));
                }
            }else{
                System.out.println((i + 1) + ") " + menuItems.get(i));
            }
        };
        System.out.println(menuPrompt);
    }
    public static void mausMenu() {
        String productCategory = "Maus";
        String menuTitle = "Maus anlegen";

        if (!categoryPropertiesArrayList.contains("Auflösung(dpi)") || !categoryPropertiesArrayList.contains(productCategory + " Speichern")) {
            categoryPropertiesArrayList.add("Auflösung(dpi)");
            categoryPropertiesArrayList.add(productCategory + " Speichern");
        }
        categoryPropertiesArrayList.remove("Größe(Zoll)");
        categoryPropertiesArrayList.remove("Monitor Speichern");
        categoryPropertiesArrayList.remove("Sockel");
        categoryPropertiesArrayList.remove("Motherboard Speichern");
        categoryPropertiesArrayList.remove("Tastatur");
        categoryPropertiesArrayList.remove("Tastatur Speichern");

        Maus maus = new Maus("", "", 0.0, 0);
        while (true) {
            menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
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
                case "3", "3)" -> {
                    System.out.println("Gebe den Preis an:");
                    String productPrice = menuScanner.next();
                    productPrice = productPrice.replace(",", ".");
                    try {
                        Double price = Double.valueOf(productPrice);
                        maus.setProductPrice(price);
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
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
                        System.out.println("Alle Eigenschaften des Motherboards sind vorhanden und haben gültige Werte.");
                        maus.getMaus();
                    }
                    System.out.println(
                            """
                                                 ------------------------------------------------
                                                 Möchtest du ein weiteres Produkt hinzufügen?
                                                 ------------------------------------------------
                                                 (J)a
                                                 (N)ein
                                                 ------------------------------------------------
                                                 Bitte wählen:""");;
                    String weiteresProdukt = menuScanner.next();
                    if (weiteresProdukt.equalsIgnoreCase("j")) {
                        produktAnlegen();
                    } else if (weiteresProdukt.equalsIgnoreCase("n")) {
                        hauptMenue();
                    } else {
                        wrongInputQuit("wrongInput");
                    }
                    return;
                }
                // Default case fehlt!
            }
        }
    }
    public static void tastaturMenu() {
        String productCategory = "Tastatur";
        String menuTitle = "Tastatur anlegen";

        if (!categoryPropertiesArrayList.contains("Tastentyp") || !categoryPropertiesArrayList.contains(productCategory + " Speichern")) {
            categoryPropertiesArrayList.add("Tastentyp");
            categoryPropertiesArrayList.add(productCategory + " Speichern");
        }
        categoryPropertiesArrayList.remove("Größe(Zoll)");
        categoryPropertiesArrayList.remove("Monitor Speichern");
        categoryPropertiesArrayList.remove("Sockel");
        categoryPropertiesArrayList.remove("Motherboard Speichern");
        categoryPropertiesArrayList.remove("Auflösung(dpi)");
        categoryPropertiesArrayList.remove("Maus Speichern");

        Tastatur tastatur = new Tastatur("", "", 0.0, "");
        while (true) {
            menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
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
                case "3", "3)" -> {
                    System.out.println("Gebe den Preis an:");
                    String productPrice = menuScanner.next();
                    productPrice = productPrice.replace(",", ".");
                    try {
                        Double price = Double.valueOf(productPrice);
                        tastatur.setProductPrice(price);
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
                case "4", "4)" -> {
                    System.out.println("Gebe den Tastentyp an:");
                    String tastenTyp = menuScanner.next();
                    tastatur.setTastenTyp(tastenTyp);
                }
                case "0", "0)" -> {
                    if (tastatur.getProductBrand().isEmpty() || tastatur.getProductModel().isEmpty() || tastatur.getProductPrice() == 0.0 || tastatur.getTastenTyp().isEmpty()) {
                        wrongInputQuit("emptyValue");
                    } else {
                        System.out.println("Alle Eigenschaften des Motherboards sind vorhanden und haben gültige Werte.");
                        tastatur.getTastatur();
                    }
                    System.out.println(
                            """
                                                 ------------------------------------------------
                                                 Möchtest du ein weiteres Produkt hinzufügen?
                                                 ------------------------------------------------
                                                 (J)a
                                                 (N)ein
                                                 ------------------------------------------------
                                                 Bitte wählen:""");;
                    String weiteresProdukt = menuScanner.next();
                    if (weiteresProdukt.equalsIgnoreCase("j")) {
                        produktAnlegen();
                    } else if (weiteresProdukt.equalsIgnoreCase("n")) {
                        hauptMenue();
                    } else {
                        wrongInputQuit("wrongInput");
                    }
                    return;
                }
                // Default case fehlt!
            }
        }
    }
    public static void motherboardMenu() {
        String productCategory = "Motherboard";
        String menuTitle = "Motherboard anlegen";
        if (!categoryPropertiesArrayList.contains("Sockel") || !categoryPropertiesArrayList.contains(productCategory + " Speichern")) {
            categoryPropertiesArrayList.add("Sockel");
            categoryPropertiesArrayList.add(productCategory + " Speichern");
        }
        categoryPropertiesArrayList.remove("Größe(Zoll)");
        categoryPropertiesArrayList.remove("Monitor Speichern");
        categoryPropertiesArrayList.remove("Tastatur");
        categoryPropertiesArrayList.remove("Tastatur Speichern");
        categoryPropertiesArrayList.remove("Auflösung(dpi)");
        categoryPropertiesArrayList.remove("Maus Speichern");

        Motherboard motherboard = new Motherboard("", "", 0.0, "");
        while (true) {
            menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
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
                case "3", "3)" -> {
                    System.out.println("Gebe den Preis an:");
                    String productPrice = menuScanner.next();
                    productPrice = productPrice.replace(",", ".");
                    try {
                        Double price = Double.valueOf(productPrice);
                        motherboard.setProductPrice(price);
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
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
                        motherboard.getMotherboard();
                    }
                    System.out.println(
                            """
                             ------------------------------------------------
                             Möchtest du ein weiteres Produkt hinzufügen?
                             ------------------------------------------------
                             (J)a
                             (N)ein
                             ------------------------------------------------
                             Bitte wählen:""");;
                    String weiteresProdukt = menuScanner.next();
                    if (weiteresProdukt.equalsIgnoreCase("j")) {
                        produktAnlegen();
                    } else if (weiteresProdukt.equalsIgnoreCase("n")) {
                        hauptMenue();
                    } else {
                        wrongInputQuit("wrongInput");
                    }
                    return;
                }
                // Default case fehlt!
            }
        }
    }
    public static void monitorMenu() {   //ArrayList<String> productPropertyList){
        String productCategory = "Monitor";
        String menuTitle = "Monitor anlegen";
        if (!categoryPropertiesArrayList.contains("Größe(Zoll)") || !categoryPropertiesArrayList.contains(productCategory + " Speichern")) {
            categoryPropertiesArrayList.add("Größe(Zoll)");
            categoryPropertiesArrayList.add(productCategory + " Speichern");
        }
        categoryPropertiesArrayList.remove("Sockel");
        categoryPropertiesArrayList.remove("Motherboard Speichern");
        categoryPropertiesArrayList.remove("Tastatur");
        categoryPropertiesArrayList.remove("Tastatur Speichern");
        categoryPropertiesArrayList.remove("Auflösung(dpi)");
        categoryPropertiesArrayList.remove("Maus Speichern");

        Monitor monitor = new Monitor("", "", 0.0, 0.0);
        while (true) {
            menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
            String monitorMenuCategorySelectInput = menuScanner.next();

            switch (monitorMenuCategorySelectInput) {
                case "1", "1)" -> {
                    System.out.println("Gebe die Marke an:");
                    String productBrand = menuScanner.next();
                    monitor.setProductBrand(productBrand);
                }
                case "2", "2)" -> {
                    System.out.println("Gebe das Modell an:");
                    String productModel = menuScanner.next();
                    monitor.setProductModel(productModel);
                }
                case "3", "3)" -> {
                    System.out.println("Gebe den Preis an:");
                    String productPrice = menuScanner.next();
                    productPrice = productPrice.replace(",", ".");
                    try {
                        Double price = Double.valueOf(productPrice);
                        monitor.setProductPrice(price);
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
                case "4", "4)" -> {
                    System.out.println("Gebe die Größe(Zoll) an:");
                    String productScreenSize = menuScanner.next();
                    productScreenSize = productScreenSize.replace(",", ".");
                    try {
                        Double sizeInch = Double.valueOf(productScreenSize);
                        monitor.setScreenSizeInch(sizeInch);
                    } catch (NumberFormatException e) {
                        wrongInputQuit("wrongInput");
                    }
                }
                case "0", "0)" -> {
                    if (monitor.getProductBrand().isEmpty() || monitor.getProductModel().isEmpty() || monitor.getProductPrice() == 0.0 || monitor.getScreenSizeInch() == 0.0) {
                        wrongInputQuit("emptyValue");
                    } else {
                        System.out.println("Alle Eigenschaften des Monitors sind vorhanden und haben gültige Werte.");
                        monitor.getMonitor();
                    }
                    System.out.println(
                            """
                             ------------------------------------------------
                             Möchtest du ein weiteres Produkt hinzufügen?
                             ------------------------------------------------
                             (J)a
                             (N)ein
                             ------------------------------------------------
                             Bitte wählen:""");;
                    String weiteresProdukt = menuScanner.next();
                        if (weiteresProdukt.equalsIgnoreCase("j")) {
                            produktAnlegen();
                        } else if (weiteresProdukt.equalsIgnoreCase("n")) {
                            hauptMenue();
                        } else {
                            wrongInputQuit("wrongInput");
                        }
                        return;
                }
                // Default case fehlt!
            }
        }
    }
    public static void produktAnlegen(){
        ArrayList<String> productCreateMenuItemArrayList = new ArrayList<>(Arrays.asList("Monitor", "Motherboard", "Tastatur", "Maus"));
        menuBuilder("Produkt anlegen", "Produktkategorien", productCreateMenuItemArrayList, standardMenuPrompt);
        String productMenuSelectCategoryInput = menuScanner.next();
        switch (productMenuSelectCategoryInput) {
            case "1", "1)", "Monitor" -> {
                monitorMenu();
            }
            case "2", "2)", "Motherboard" -> {
                motherboardMenu();
            }
            case "3", "3)", "Tastatur" -> {
                tastaturMenu();
            }
            case "4", "4)", "Maus" -> {
                mausMenu();
            }
            default -> {
                wrongInputQuit("wrongInput");
                menuScanner.close();
            }
        }
    }
    public static void hauptMenue(){
        ArrayList<String> mainMenuItemArrayList = new ArrayList<>(Arrays.asList("Produkt anlegen", "Produkt bearbeiten", "Produkt suchen", "Produkt löschen", "Shop beenden"));
        menuBuilder("Hauptmenü", "", mainMenuItemArrayList, standardMenuPrompt);
        String mainMenuSelectInput = menuScanner.next();
        switch (mainMenuSelectInput) {
            case "1", "1)", "Produkt anlegen" -> produktAnlegen();
            case "2", "2)", "Produkt bearbeiten" -> MainShop.main(null);    // produktBearbeiten();
            case "3", "3)","Produkt suchen" -> MainShop.main(null);    //produktSuchen();
            case "4", "4)","Produkt löschen" -> MainShop.main(null);    //produktLoeschen();
            case "0", "0)","Shop beenden" -> shopBeenden();
            default -> {
                wrongInputQuit("wrongInput");
                menuScanner.close();
            }
        }
    }
    public static void shopBeenden(){
        System.out.println(
                """
                        ------------------------------------------------
                        Soll wirklich beendet werden?
                        ------------------------------------------------
                        (J)a
                        (N)ein
                        ------------------------------------------------
                        Bitte wählen:""");;
        String exitConfirmInput = menuScanner.next();
        switch (exitConfirmInput){
            case "j","J" -> {
                System.out.println("PC Shop wurde beendet");
                System.exit(0);
            }
            case "N", "n" -> MainShop.main(null);
            default -> {
                wrongInputQuit("wrongInput");
                main(null);
            }
        }
    }
    public static void wrongInputQuit(String errorType){
        String errorMessage = null;
        switch(errorType){
            case "wrongInput" -> errorMessage = "Info: Fehlerhafte Eingabe";
            case "emptyValue" -> errorMessage = "Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden";
        }
        System.out.println("\n------------------------------------------------\n" + errorMessage + "\n------------------------------------------------");
        main(null);
    }

}