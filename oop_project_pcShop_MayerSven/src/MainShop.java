import products.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainShop {
    private static final Scanner menuScanner = new Scanner(System.in);
    private static final String standardMenuPrompt = "------------------------------------------------ \n" + "Bitte wählen:";
    static final ArrayList<String> categoryPropertiesArrayList = new ArrayList<>(Arrays.asList("Marke", "Modell", "Preis"));
    static ArrayList<Maus> mausArrayList = new ArrayList<>();
    static ArrayList<Monitor> monitorArrayList = new ArrayList<>();
    static ArrayList<Motherboard> motherboardArrayList = new ArrayList<>();
    static ArrayList<Tastatur> tastaturArrayList = new ArrayList<>();
    public static String newProductBrand, newProductModel, newUniquePropertyInput;
    public static Integer newSensorResolution;
    public static Double newProductPrice;

    public static void main(String[] args) {
        addDummyData.dummyData();
        hauptMenue();
    }

    static void propertyMenu(String menuTitle, String uniqueProperty, Product product, String addOrEditMenu) {

        while (true) {
            MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", MainShop.categoryPropertiesArrayList, standardMenuPrompt);
            String propertyMenuCPropertySelectInput = menuScanner.next();
            switch (propertyMenuCPropertySelectInput) {
                case "1", "1)" -> { //  Marke
                    System.out.println("Gebe die Marke an:");
                    newProductBrand = menuScanner.next();
//                    product.setProductBrand(newProductBrand);
                }
                case "2", "2)" -> { //  Modell
                    System.out.println("Gebe das Modell an:");
                    newProductModel = menuScanner.next();
//                    product.setProductModel(newProductModel);
                }
                case "3", "3)" -> productPriceInput(product);   //  Preis
                case "4", "4)" -> { //  Unique
                    System.out.println("Gebe " + uniqueProperty + " an:");
                    newUniquePropertyInput = menuScanner.next().toLowerCase();
                    if (product instanceof Maus) {  //  Maus
                        newUniquePropertyInput = newUniquePropertyInput.replace(",", "").replace("dpi", "");

                    } else if (product instanceof Monitor) {  //  Monitor
                        newUniquePropertyInput = newUniquePropertyInput.replace(",", ".");




                    } else if (product instanceof Motherboard) {  //  Motherboard
                        ((Motherboard) product).setMotherboardSocket(newUniquePropertyInput);
                    } else if (product instanceof Tastatur) {  //  Tastatur
                        ((Tastatur) product).setTastenTyp(newUniquePropertyInput);
                    }
                }
                case "0", "0)" -> {
                    if (addOrEditMenu.equals("anlegen")) {  //  ANLEGEN
                        if (product instanceof Maus) {
                            product.setProductBrand(newProductBrand);
                            product.setProductModel(newProductModel);
                            product.setProductPrice(newProductPrice);
                            try {
                                newSensorResolution = Integer.valueOf(newUniquePropertyInput);
                                ((Maus) product).setSensorAufloesung(newSensorResolution);
                            } catch (NumberFormatException e) {
                                wrongInputQuit("wrongInput");
                            }

                            // --> siehe productPriceInput
                            if (product.getProductBrand().isEmpty() || product.getProductModel().isEmpty() || product.getProductPrice() == 0.0 || ((Maus) product).getSensorAufloesung() == 0) {
                                wrongInputQuit("emptyValue");
                            } else {
                                System.out.println("Alle Eigenschaften der Maus sind vorhanden und haben gültige Werte.");


                                mausArrayList.add(((Maus) product));
                                addAnotherProductCheck();
                            }
                        } else if (product instanceof Monitor) {
                            if (product.getProductBrand().isEmpty() || product.getProductModel().isEmpty() || product.getProductPrice() == 0.0 || ((Monitor) product).getScreenSizeInch() == 0.0) {
                                wrongInputQuit("emptyValue");
                            } else {
                                System.out.println("Alle Eigenschaften des Monitors sind vorhanden und haben gültige Werte.");
                                try {
                                    ((Monitor) product).setScreenSizeInch(Double.valueOf(newUniquePropertyInput));
                                } catch (NumberFormatException e) {
                                    wrongInputQuit("wrongInput");
                                }
                                monitorArrayList.add((Monitor) product);
                                addAnotherProductCheck();
                            }
                        } else if (product instanceof Motherboard) {
                            if (product.getProductBrand().isEmpty() || product.getProductModel().isEmpty() || product.getProductPrice() == 0.0 || ((Motherboard) product).getMotherboardSocket().isEmpty()) {
                                wrongInputQuit("emptyValue");
                            } else {
                                System.out.println("Alle Eigenschaften des Motherboards sind vorhanden und haben gültige Werte.");
                                motherboardArrayList.add((Motherboard) product);
                                addAnotherProductCheck();
                            }
                        } else if (product instanceof Tastatur) {  //  Tastatur
                            if (product.getProductBrand().isEmpty() || product.getProductModel().isEmpty() || product.getProductPrice() == 0.0 || ((Tastatur) product).getTastenTyp().isEmpty()) {
                                wrongInputQuit("emptyValue");
                            } else {
                                System.out.println("Alle Eigenschaften der Tastatur sind vorhanden und haben gültige Werte.");
                                tastaturArrayList.add((Tastatur) product);
                                addAnotherProductCheck();
                            }
                        }
                        return;
                    } else if (addOrEditMenu.equals("bearbeiten")) {    // BEARBEITEN
                        if (product instanceof Maus) {
                            product.setProductBrand(newProductBrand);
                            editAnotherProductCheck();
                        }
                    }
                }
                default -> wrongInputQuit("wrongInput");
            }
        }

    }
    private static Integer arrayListOutput(ArrayList<?> productArrayList, Integer productCount, String productPluralCategory, String productClassName) {
        if (!productArrayList.isEmpty()) {
            System.out.println("[Produkte der Kategorie " + productPluralCategory + "]");
            for (Object product : productArrayList) {
                productCount++;
                System.out.print("\t" + productCount + ") ");
                try {
                    Method getMethod = product.getClass().getMethod("get" + productClassName);
                    getMethod.invoke(product);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return productCount;
    }
    private static void produktBearbeiten(){
        if(mausArrayList.isEmpty() && monitorArrayList.isEmpty() && motherboardArrayList.isEmpty() && tastaturArrayList.isEmpty()){
            wrongInputQuit("productsNotExist");
        }else{
            int productCount = 0;
            productCount = arrayListOutput(mausArrayList, productCount, "Mäuse", "Maus");
            productCount = arrayListOutput(monitorArrayList, productCount, "Monitore", "Monitor");
            productCount = arrayListOutput(motherboardArrayList, productCount, "Motherboards", "Motherboard");
            productCount = arrayListOutput(tastaturArrayList, productCount, "Tastaturen", "Tastatur");
            System.out.println("Tippe die Nummer eines Produkts ein, um dieses zu bearbeiten:");
            int productEditInput = menuScanner.nextInt();

            if (productEditInput >= 1 && productEditInput <= productCount) {
                if (productEditInput <= mausArrayList.size()) {
                    Maus selectedMaus = mausArrayList.get(productEditInput - 1);
                    selectedMaus.getMaus();
                    PropertiesHandler.propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"mausMenu", "Maus");
                    propertyMenu("Maus bearbeiten", "die Sensorauflösung (in dpi)", selectedMaus, "bearbeiten");


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
            case "1", "1)", "Monitor" -> productMenues.monitorMenu();
            case "2", "2)", "Motherboard" -> productMenues.motherboardMenu();
            case "3", "3)", "Tastatur" -> productMenues.tastaturMenu();
            case "4", "4)", "Maus" -> productMenues.mausMenu();
            default -> wrongInputQuit("wrongInput");
        }
    }
    private static void hauptMenue(){
        ArrayList<String> mainMenuItemArrayList = new ArrayList<>(Arrays.asList("Produkt anlegen", "Produkt bearbeiten", "Produkt suchen", "Produkt löschen", "Shop beenden"));
        MenuBuilder.menuBuilder("Hauptmenü", "", mainMenuItemArrayList, standardMenuPrompt);
        String mainMenuSelectInput = menuScanner.next();
        switch (mainMenuSelectInput) {
            case "1", "1)", "Produkt anlegen" -> produktAnlegen();
            case "2", "2)", "Produkt bearbeiten" -> produktBearbeiten();
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

    private static void editAnotherProductCheck(){
        System.out.println("""
        ------------------------------------------------
        Möchtest du ein weiteres Produkt bearbeiten?
        ------------------------------------------------
        (J)a
        (N)ein
        ------------------------------------------------
        Bitte wählen:""");
        String weiteresProdukt = menuScanner.next().trim();
        if (weiteresProdukt.equalsIgnoreCase("j")) {
            produktBearbeiten();
        } else if (weiteresProdukt.equalsIgnoreCase("n")) {
            hauptMenue();
        } else {
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
            case "wrongInput" -> errorMessage = "\t\t\t\t\tFehlerhafte Eingabe";
            case "emptyValue" -> errorMessage = "Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden";
            case "productsNotExist" -> errorMessage = "\t\t\t\t\tKeine Produkte vorhanden";
        }
        System.out.println("\n+-Info-+----------------------------------------------------------------------\n" + "| Info | " + errorMessage + "\n+-Info-+----------------------------------------------------------------------");
        hauptMenue();
    }

}