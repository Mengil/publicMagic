import products.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainShop {
    private static final Scanner menuScanner = new Scanner(System.in);
    private static final String standardMenuPrompt = "-------------------------------------------------------\n" + "Bitte wählen:";
    static final ArrayList<String> categoryPropertiesArrayList = new ArrayList<>(Arrays.asList("Marke", "Modell", "Preis"));
    static ArrayList<Maus> mausArrayList = new ArrayList<>();
    static ArrayList<Monitor> monitorArrayList = new ArrayList<>();
    static ArrayList<Motherboard> motherboardArrayList = new ArrayList<>();
    static ArrayList<Tastatur> tastaturArrayList = new ArrayList<>();
    public static String newProductBrand, newProductModel, newMotherboardSocket, newKeyboardType;
    public static Integer newSensorResolution;
    public static Double newProductPrice, newScreenSizeInch;

    public static void main(String[] args) {
        dummyData.dummyDataAdd();
        hauptMenue();
    }

private static Integer addEditDeleteProductListsOutput(Integer productCount, String activity){
    System.out.println(
            """
                    -------------------------------------------------------
                    PC-Shop\t\t\tProduktliste\t\t\tvon: Sven Mayer
                    -------------------------------------------------------""");

    productCount = productsOutputToConsole(mausArrayList, productCount, "Mäuse", "Maus");
    productCount = productsOutputToConsole(monitorArrayList, productCount, "Monitore", "Monitor");
    productCount = productsOutputToConsole(motherboardArrayList, productCount, "Motherboards", "Motherboard");
    productCount = productsOutputToConsole(tastaturArrayList, productCount, "Tastaturen", "Tastatur");
    System.out.println("-------------------------------------------------------\nTippe die Nummer eines Produkts ein, um dieses zu " + activity + ":");
    return productCount;
}
    public static void produktLoeschen() {
        int productCount = 0;
        productCount = addEditDeleteProductListsOutput(productCount, "löschen");
        try {
            int productEditInput;
            String productEditInputString = menuScanner.next();
            productEditInput = Integer.parseInt(productEditInputString);

            if (productEditInput >= 1 && productEditInput <= productCount) {
                if (productEditInput <= mausArrayList.size()) {
                    Maus selectedMaus = mausArrayList.get(productEditInput - 1);
                    System.out.println("------------------------------------------------\n" + selectedMaus +
                        "\nSoll der Eintrag " + productEditInput +") zu [" + selectedMaus.getProductModel() + "] wirklich gelöscht werden?" +
                            "\n(J)a    |   (N)ein");
                    String deleteConfirmInput = menuScanner.next();
                    switch(deleteConfirmInput){
                        case "j", "J", "ja", "Ja" -> {
                            mausArrayList.remove(selectedMaus);
                            produktLoeschen();
                        }
                        case "n", "N", "nein", "Nein" -> hauptMenue();
                    }

                } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size()) {
                    Monitor selectedMonitor = monitorArrayList.get(productEditInput - mausArrayList.size() - 1);
                    System.out.println("------------------------------------------------\n" + selectedMonitor +
                            "\nSoll der Eintrag " + productEditInput +") zu [" + selectedMonitor.getProductModel() + "] wirklich gelöscht werden?" +
                            "\n(J)a    |   (N)ein");
                    String deleteConfirmInput = menuScanner.next();
                    switch(deleteConfirmInput){
                        case "j", "J", "ja", "Ja" -> {
                            monitorArrayList.remove(selectedMonitor);
                            produktLoeschen();
                        }
                        case "n", "N", "nein", "Nein" -> hauptMenue();
                    }

                } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size() + motherboardArrayList.size()) {
                    Motherboard selectedMotherboard = motherboardArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - 1);
                    System.out.println("------------------------------------------------\n" + selectedMotherboard +
                            "\nSoll der Eintrag " + productEditInput +") zu [" + selectedMotherboard.getProductModel() + "] wirklich gelöscht werden?" +
                            "\n(J)a    |   (N)ein");
                    String deleteConfirmInput = menuScanner.next();
                    switch(deleteConfirmInput){
                        case "j", "J", "ja", "Ja" -> {
                            motherboardArrayList.remove(selectedMotherboard);
                            produktLoeschen();
                        }
                        case "n", "N", "nein", "Nein" -> hauptMenue();
                    }
                } else {
                    Tastatur selectedTastatur = tastaturArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - motherboardArrayList.size() - 1);
                    System.out.println("------------------------------------------------\n" + selectedTastatur +
                            "\nSoll der Eintrag " + productEditInput +") zu [" + selectedTastatur.getProductModel() + "] wirklich gelöscht werden?" +
                            "\n(J)a    |   (N)ein");
                    String deleteConfirmInput = menuScanner.next();
                    switch(deleteConfirmInput){
                        case "j", "J", "ja", "Ja" -> {
                            tastaturArrayList.remove(selectedTastatur);
                            produktLoeschen();
                        }
                        case "n", "N", "nein", "Nein" -> hauptMenue();
                    }
                }
            }
        } catch (NumberFormatException | InputMismatchException e) {
            errorMessageAndQuit("wrongInput");
        }
    }
    public static void produktSuchen() {
            System.out.println("""
                    -------------------------------------
                    \tGeben Sie einen Suchbegriff ein:
                    -------------------------------------""");
            menuScanner.nextLine();
            String searchTerm = menuScanner.next().toLowerCase().trim();
            List<Product> searchResults = new ArrayList<>();
            List<Product> productList = new ArrayList<>();
                productList.addAll(mausArrayList);
                productList.addAll(monitorArrayList);
                productList.addAll(motherboardArrayList);
                productList.addAll(tastaturArrayList);
            for (Product product : productList) {
                if (productContainsTerm(product, searchTerm)) {
                    searchResults.add(product);
                }
            }
            if (searchResults.isEmpty()) {
                System.out.println("Keine Treffer gefunden.");
            } else {
                System.out.println("-------------------------------------------------------\n[Treffer]");
                for (int i = 0; i < searchResults.size(); i++) {
                    Product produkt = searchResults.get(i);
                    System.out.println(" " + (i + 1) + ") " + produkt.getProductBrand() + " " + produkt.getProductModel());
                }
            }
            System.out.println("""
                    -------------------------------------------------------
                    Möchten Sie erneut suchen?
                    (J)a    |   (N)ein
                    -------------------------------------------------------
                    Bitte wählen:""");
            String searchAgainCheck = menuScanner.next().toLowerCase();
            switch (searchAgainCheck) {
                case "j" -> produktSuchen();
                case "n" -> hauptMenue();
                default -> errorMessageAndQuit("wrongInput");
            }
    }
    public static boolean productContainsTerm(Product product, String suchbegriff) {
        if (product.getProductBrand().toLowerCase().contains(suchbegriff)
            || product.getProductModel().toLowerCase().contains(suchbegriff)
            || String.valueOf(product.getProductPrice()).toLowerCase().contains(suchbegriff)) {
            return true;
        }
        if (product instanceof Maus maus) { return String.valueOf(maus.getSensorResolution()).toLowerCase().contains(suchbegriff);
        } else if (product instanceof Monitor monitor) { return String.valueOf(monitor.getScreenSizeInch()).toLowerCase().contains(suchbegriff);
        } else if (product instanceof Motherboard motherboard) { return motherboard.getMotherboardSocket().toLowerCase().contains(suchbegriff);
        } else if (product instanceof Tastatur tastatur) { return tastatur.getKeyboardType().toLowerCase().contains(suchbegriff);
        }
        return false;
    }
    public static void propertiesAddOrEdit(String menuTitle, String menuHeader, String uniqueProperty, Product product, String addOrEdit) {
        while(true) {
            MenuBuilder.menuBuilder(menuTitle, menuHeader, categoryPropertiesArrayList, standardMenuPrompt);
            String selectPropertyToAdd = menuScanner.next();
            switch (selectPropertyToAdd) {
                case "1", "1)" -> {
                    System.out.println("Gebe die Marke an:");
                    String productBrand = menuScanner.next();
                    if(addOrEdit.equals("anlegen")){
                        newProductBrand = productBrand;
                        product.setProductBrand(productBrand);
                    }else if(addOrEdit.equals("bearbeiten")){
                        newProductBrand = productBrand;
                    }
                }
                case "2", "2)" -> {
                    System.out.println("Gebe das Modell an:");
                    String productModel = menuScanner.next();
                    if(addOrEdit.equals("anlegen")){
                        newProductModel = productModel;
                        product.setProductModel(productModel);
                    }else if(addOrEdit.equals("bearbeiten")){
                        newProductModel = productModel;
                    }
                }
                case "3", "3)" -> {
                    System.out.println("Gebe den Preis(in €) an:");
                    String productPrice = menuScanner.next().replace(",", ".").replace("€", "");
                    if(addOrEdit.equals("anlegen")){
                        try {
                            newProductPrice = (Double.valueOf(productPrice));
                            product.setProductPrice(Double.valueOf(productPrice));
                        } catch (NumberFormatException e) {
                            errorMessageAndQuit("wrongInput");
                        }
                    }else if(addOrEdit.equals("bearbeiten")){
                        try {
                            newProductPrice = (Double.valueOf(productPrice));
                        } catch (NumberFormatException e) {
                            errorMessageAndQuit("wrongInput");
                        }
                    }
                }
                case "4", "4)" -> {//  Unique
                    System.out.println("Gebe " + uniqueProperty + " an:");
                    String productUnique = menuScanner.next().toLowerCase();
                    if (product instanceof Maus) {  //  Maus
                        int dpi = Integer.parseInt(productUnique.replace(",", "").replace("dpi", ""));
                        if(addOrEdit.equals("anlegen")){
                            try {
                                newSensorResolution = dpi;
                                Integer newSensorResolution = dpi;
                                ((Maus) product).setSensorResolution(newSensorResolution);
                            } catch (NumberFormatException e) {
                                errorMessageAndQuit("wrongInput");
                            }
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newSensorResolution = dpi;
                        }
                    } else if (product instanceof Monitor) {  //  Monitor
                        if(addOrEdit.equals("anlegen")){
                            try {
                                newScreenSizeInch = Double.valueOf(productUnique.replace(",", "."));
                                ((Monitor) product).setScreenSizeInch(Double.valueOf(productUnique.replace(",", ".")));
                            } catch (NumberFormatException e) {
                                errorMessageAndQuit("wrongInput");
                            }
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newScreenSizeInch = Double.valueOf(productUnique.replace(",", "."));
                        }
                    } else if (product instanceof Motherboard) {  //  Motherboard
                        if(addOrEdit.equals("anlegen")){
                            newMotherboardSocket = productUnique;
                            ((Motherboard) product).setMotherboardSocket(productUnique);
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newMotherboardSocket = productUnique;
                        }
                    } else if (product instanceof Tastatur) {  //  Tastatur
                        if(addOrEdit.equals("anlegen")){
                            newKeyboardType = productUnique;
                            ((Tastatur) product).setKeyboardType(productUnique);
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newKeyboardType = productUnique;
                        }
                    }
                }
                case "0", "0)" -> {
                    if (product instanceof Maus) {
                        if(checkForEmptyProperty(product, "Maus")){
                            errorMessageAndQuit("emptyValue");
                        } else {
                            if(addOrEdit.equals("anlegen")){
                                System.out.println("Alle Eigenschaften der Maus sind vorhanden und haben gültige Werte.");
                                mausArrayList.add(((Maus) product));
                            }else if(addOrEdit.equals("bearbeiten")) {
                                setProductProperties(product);
                            }
                            clearPropertyValues();
                            addOrEditAnotherProductCheck(addOrEdit);
                        }
                    } else if (product instanceof Monitor) {
                        if (checkForEmptyProperty(product, "Monitor")){
                            errorMessageAndQuit("emptyValue");
                        } else {
                            if(addOrEdit.equals("anlegen")) {
                                System.out.println("Alle Eigenschaften des Monitors sind vorhanden und haben gültige Werte.");
                                monitorArrayList.add((Monitor) product);
                            }else if(addOrEdit.equals("bearbeiten")){
                                setProductProperties(product);
                            }
                            clearPropertyValues();
                            addOrEditAnotherProductCheck(addOrEdit);
                        }
                    } else if (product instanceof Motherboard) {
                        if (checkForEmptyProperty(product, "Motherboard")){
                                errorMessageAndQuit("emptyValue");
                        } else {
                            if(addOrEdit.equals("anlegen")) {
                                System.out.println("Alle Eigenschaften des Motherboards sind vorhanden und haben gültige Werte.");
                                motherboardArrayList.add((Motherboard) product);
                            }else if(addOrEdit.equals("bearbeiten")){
                                setProductProperties(product);
                            }
                            clearPropertyValues();
                            addOrEditAnotherProductCheck(addOrEdit);
                        }
                    } else if (product instanceof Tastatur) {
                        if (checkForEmptyProperty(product, "Tastatur")){
                            errorMessageAndQuit("emptyValue");
                        } else {
                            if(addOrEdit.equals("anlegen")) {
                                System.out.println("Alle Eigenschaften der Tastatur sind vorhanden und haben gültige Werte.");
                                tastaturArrayList.add((Tastatur) product);
                            }else if(addOrEdit.equals("bearbeiten")){
                                setProductProperties(product);
                            }
                            clearPropertyValues();
                            addOrEditAnotherProductCheck(addOrEdit);
                        }
                    }return;
                }default -> errorMessageAndQuit("wrongInput");
            }
        }
    }
    private static void setProductProperties(Product product){
        product.setProductBrand(newProductBrand); product.setProductModel(newProductModel); product.setProductPrice(newProductPrice);
        switch (product.getClass().getSimpleName()) {
            case "Monitor" -> ((Monitor) product).setScreenSizeInch(newScreenSizeInch);
            case "Maus" -> ((Maus) product).setSensorResolution(newSensorResolution);
            case "Motherboard" -> ((Motherboard) product).setMotherboardSocket(newMotherboardSocket);
            case "Tastatur" -> ((Tastatur) product).setKeyboardType(newKeyboardType);
        }
    }
    private static Integer productsOutputToConsole(ArrayList<?> productArrayList, Integer productCount, String productPluralCategory, String productClassName) {
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
    private static void produktBearbeiten() {
        if (mausArrayList.isEmpty() && monitorArrayList.isEmpty() && motherboardArrayList.isEmpty() && tastaturArrayList.isEmpty()) {
            errorMessageAndQuit("productsNotExist");
        } else {
            int productCount = 0;
            productCount = addEditDeleteProductListsOutput(productCount, "bearbeiten");
            try {
                int productEditInput;
                String productEditInputString = menuScanner.next();
                productEditInput = Integer.parseInt(productEditInputString);

                if (productEditInput >= 1 && productEditInput <= productCount) {
                    if (productEditInput <= mausArrayList.size()) {
                        Maus selectedMaus = mausArrayList.get(productEditInput - 1);
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "mausMenu", "Maus");


                        // getter nötig?
                        propertiesAddOrEdit("\t\t\tMaus bearbeiten",selectedMaus.toString(), "die Sensorauflösung", selectedMaus, "bearbeiten");




                    } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size()) {
                        Monitor selectedMonitor = monitorArrayList.get(productEditInput - mausArrayList.size() - 1);
                        selectedMonitor.getMonitor();
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "monitorMenu", "Monitor");
                        propertiesAddOrEdit("\t\t\tMonitor bearb.",selectedMonitor.toString(), "die Größe(Zoll)", selectedMonitor, "bearbeiten");
                    } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size() + motherboardArrayList.size()) {
                        Motherboard selectedMotherboard = motherboardArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - 1);
                        selectedMotherboard.getMotherboard();
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "motherboardMenu", "Motherboard");
                        propertiesAddOrEdit("\t\tMotherboard bearb.",selectedMotherboard.toString(), "den Sockel", selectedMotherboard, "bearbeiten");
                    } else {
                        Tastatur selectedTastatur = tastaturArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - motherboardArrayList.size() - 1);
                        selectedTastatur.getTastatur();
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "tastaturMenu", "Tastatur");
                        propertiesAddOrEdit("\t\t\tTastatur bearb.",selectedTastatur.toString(),"den Tastaturen-Typ", selectedTastatur, "bearbeiten");
                    }
                }
            } catch (NumberFormatException | InputMismatchException e) {
                menuScanner.reset();
                errorMessageAndQuit("wrongInput");
            }
        }
    }
    private static void produktAnlegen(){
        ArrayList<String> productCreateMenuItemArrayList = new ArrayList<>(Arrays.asList("Monitor", "Motherboard", "Tastatur", "Maus"));
        MenuBuilder.menuBuilder("\t\t\tProdukt anlegen", "Produktkategorien", productCreateMenuItemArrayList, standardMenuPrompt);
        String productMenuSelectCategoryInput = menuScanner.next();
        switch (productMenuSelectCategoryInput) {
            case "1", "1)", "Monitor" -> productMenues.monitorMenu();
            case "2", "2)", "Motherboard" -> productMenues.motherboardMenu();
            case "3", "3)", "Tastatur" -> productMenues.tastaturMenu();
            case "4", "4)", "Maus" -> productMenues.mausMenu();
            default -> errorMessageAndQuit("wrongInput");
        }
    }
    private static void hauptMenue(){
        ArrayList<String> mainMenuItemArrayList = new ArrayList<>(Arrays.asList("Produkt anlegen", "Produkt bearbeiten", "Produkt suchen", "Produkt löschen", "Shop beenden"));
        MenuBuilder.menuBuilder("\t\t\t\tHauptmenü", "", mainMenuItemArrayList, standardMenuPrompt);
       String mainMenuSelectInput = menuScanner.next();
        try {
            switch (mainMenuSelectInput) {
                case "1", "1)", "Produkt anlegen" -> produktAnlegen();
                case "2", "2)", "Produkt bearbeiten" -> produktBearbeiten();
                case "3", "3)", "Produkt suchen" -> produktSuchen();    //produktSuchen();
                case "4", "4)", "Produkt löschen" -> produktLoeschen();    //produktLoeschen();
                case "0", "0)", "Shop beenden" -> shopBeenden();
                default -> errorMessageAndQuit("wrongInput");
            }
        }catch (NumberFormatException | InputMismatchException e){
            errorMessageAndQuit("wrongInput");
        }
    }
    private static void addOrEditAnotherProductCheck(String addOrEdit){
        System.out.println(
                "------------------------------------------------\n" +
                "Möchtest du ein weiteres Produkt " + addOrEdit + " ?\n" +
                "------------------------------------------------\n" +
                "(J)a\t|\t (N)ein\n" +
                "------------------------------------------------\n" +
                "Bitte wählen:");
        String addOrEditInput = menuScanner.next().trim();
        if (addOrEditInput.equalsIgnoreCase("j")){
            if (addOrEdit.equals("anlegen")) {
                produktAnlegen();
            } else if (addOrEdit.equals("bearbeiten")) {
                produktBearbeiten();
            }
        } else if (addOrEditInput.equalsIgnoreCase("n")) {
            hauptMenue();
        } else {
            errorMessageAndQuit("wrongInput");
        }
    }
    private static boolean checkForEmptyProperty(Product product, String productCategory){
        boolean isBasePropertyEmptyNull = product.getProductBrand().isEmpty() || product.getProductModel().isEmpty() || product.getProductPrice() == 0.0 ||
                newProductBrand == null || newProductModel == null || newProductPrice == null;
        return switch (productCategory) {
            case "Maus" -> isBasePropertyEmptyNull || ((Maus) product).getSensorResolution() == 0 || newSensorResolution == null;
            case "Monitor" -> isBasePropertyEmptyNull || ((Monitor) product).getScreenSizeInch() == 0.0 || newScreenSizeInch == null;
            case "Motherboard" -> isBasePropertyEmptyNull || ((Motherboard) product).getMotherboardSocket().isEmpty() || newMotherboardSocket == null;
            case "Tastatur" -> isBasePropertyEmptyNull || ((Tastatur) product).getKeyboardType().isEmpty() || newKeyboardType == null;
            default -> false;
        };
    }
    private static void clearPropertyValues() {
        newProductBrand = null; newProductModel = null; newProductPrice = null; newMotherboardSocket = null; newKeyboardType = null; newSensorResolution = null; newScreenSizeInch = null;
    }
    private static void shopBeenden(){
        System.out.println("""
        -------------------------------------
        Soll wirklich beendet werden?
        -------------------------------------
        (J)a\t|\t (N)ein
        -------------------------------------
        Bitte wählen:""");
        String exitConfirmInput = menuScanner.next().trim();
        switch (exitConfirmInput){
            case "j","J" -> {
                System.out.println("-------------------------------------\nPC Shop wurde beendet");
                System.exit(0);
            }
            case "N", "n" -> MainShop.main(null);
            default -> errorMessageAndQuit("wrongInput");
        }
    }
    private static void errorMessageAndQuit(String errorType){
        String errorMessage = null;
        switch(errorType){
            case "wrongInput" -> errorMessage = "\t\t\t\t\tFehlerhafte Eingabe";
            case "emptyValue" -> errorMessage = "Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden";
            case "productsNotExist" -> errorMessage = "\t\t\t\t\tKeine Produkte vorhanden";
        }
        System.out.println("\n+-Info-+--------------------------------------------------------------+-Info-+\n" + "| Info | " + errorMessage + "\n+-Info-+--------------------------------------------------------------+-Info-+");
        hauptMenue();
    }
}