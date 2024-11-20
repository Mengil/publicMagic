import productCategories.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * PC Shop database management program for computer hardware
 * The user can create products. He can also view, edit or delete existing ones.
 * For each product, the user must enter brand, model, price and characteristics.
 * The user can search for specific terms and characters within the product properties.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class MainShop {
    private static final Scanner menuScanner = new Scanner(System.in);
    private static final String standardMenuPrompt = "-------------------------------------------------------\n" + "Bitte wählen:";
    static final ArrayList<String> categoryPropertiesArrayList = new ArrayList<>(Arrays.asList("Marke", "Modell", "Preis"));
    static ArrayList<Maus> mausArrayList = new ArrayList<>();
    static ArrayList<Monitor> monitorArrayList = new ArrayList<>();
    static ArrayList<Motherboard> motherboardArrayList = new ArrayList<>();
    static ArrayList<Tastatur> tastaturArrayList = new ArrayList<>();
    public static String newProductBrand, newProductModel, newMotherboardSocket, newKeyboardType;
//    public static Integer newSensorResolution;
    public static String newSensorResolution;
    public static Double newProductPrice, newScreenSizeInch;

    /**
     * Used by the application to initialise and run the programme.
     * Dummy date gets created to test the Edit, Find and Delete menus.
     * To display the main menu, so the user can interact with the programme.
     * @param args Array of strings that can be used to modify the way the application behaves.
     */
    public static void main(String[] args) {
        dummyData.dummyDataAdd();
        hauptMenue();
    }

    /**
     * The main menu prompts the user for input to navigate to the product submenus or to stop the programme.
     * The available submenus are "Produkt anlegen", "Produkt bearbeiten", "Produkt suchen", "Produkt löschen" and "Shop beenden".
     * If an invalid entry is made, an error message is displayed and the main menu gets displayed again.
     */
    private static void hauptMenue(){
        ArrayList<String> mainMenuItemArrayList = new ArrayList<>(Arrays.asList("Produkt anlegen", "Produkt bearbeiten", "Produkt suchen", "Produkt löschen", "Shop beenden"));
        MenuBuilder.menuBuilder("\t\t\t\tHauptmenü", "", mainMenuItemArrayList, standardMenuPrompt);
        String mainMenuSelectInput = menuScanner.next();
            switch (mainMenuSelectInput) {
                case "1", "1)", "Produkt anlegen" -> produktAnlegen();
                case "2", "2)", "Produkt bearbeiten" -> produktBearbeiten();
                case "3", "3)", "Produkt suchen" -> produktSuchen();
                case "4", "4)", "Produkt löschen" -> produktLoeschen();
                case "0", "0)", "Shop beenden" -> shopBeenden();
                default -> errorMessageAndQuit("wrongInput");
            }
    }

    /**
     * The Create New Product submenu prompts the user to select a product category for the new product.
     * The available product categories are "Monitor", "Motherboard", "Tastatur", and "Maus".
     * Based on the selected category, the appropriate menu will be displayed for further product creation.
     * If an invalid entry is made, an error message is displayed and the main menu gets displayed again.
     */
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

    /**
     * The Edit Existing Products submenu checks if any products exist in the lists.
     * The products are displayed and the user can edit them and save the changes.
     * If there are no products or the user has made an incorrect entry, an error message is displayed, and he is taken back to the main menu.
     */
    private static void produktBearbeiten() {
        if (mausArrayList.isEmpty() && monitorArrayList.isEmpty() && motherboardArrayList.isEmpty() && tastaturArrayList.isEmpty()) {
            errorMessageAndQuit("productsNotExist");
        } else {
            int productCount = 0;
            productCount = editDeleteProductListsOutput(productCount, "bearbeiten");
            try {
                int productEditInput;
                String productEditInputString = menuScanner.next();
                productEditInput = Integer.parseInt(productEditInputString);
                if (productEditInput >= 1 && productEditInput <= productCount) {
                    if (productEditInput <= mausArrayList.size()) {
                        Maus selectedMaus = mausArrayList.get(productEditInput - 1);
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "mausMenu", "Maus");
                        propertiesAddOrEdit("\t\t\tMaus bearbeiten",selectedMaus.toString(), "die Sensorauflösung", selectedMaus, "bearbeiten");
                    } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size()) {
                        Monitor selectedMonitor = monitorArrayList.get(productEditInput - mausArrayList.size() - 1);
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "monitorMenu", "Monitor");
                        propertiesAddOrEdit("\t\t\tMonitor bearb.",selectedMonitor.toString(), "die Größe(Zoll)", selectedMonitor, "bearbeiten");
                    } else if (productEditInput <= mausArrayList.size() + monitorArrayList.size() + motherboardArrayList.size()) {
                        Motherboard selectedMotherboard = motherboardArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - 1);
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "motherboardMenu", "Motherboard");
                        propertiesAddOrEdit("\t\tMotherboard bearb.",selectedMotherboard.toString(), "den Sockel", selectedMotherboard, "bearbeiten");
                    } else {
                        Tastatur selectedTastatur = tastaturArrayList.get(productEditInput - mausArrayList.size() - monitorArrayList.size() - motherboardArrayList.size() - 1);
                        productMenues.propertyArrayListHandler(categoryPropertiesArrayList, "tastaturMenu", "Tastatur");
                        propertiesAddOrEdit("\t\t\tTastatur bearb.",selectedTastatur.toString(),"den Tastaturen-Typ", selectedTastatur, "bearbeiten");
                    }
                } else if (productEditInput > productCount){
                    errorMessageAndQuit("wrongInput");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                menuScanner.reset();
                errorMessageAndQuit("wrongInput");
            }
        }
    }

    /**
     * The search menu prompts the user to enter a search term to search the product properties.
     * Each product gets checked if it contains the search term.
     * Matching products get displayed with a number and the user can search again.
     * If the user has made an incorrect entry, an error message is displayed, and he is taken back to the main menu.
     */
    public static void produktSuchen() {
        System.out.println("""
                -------------------------------------------------------
                    \t\tGebe einen Suchbegriff ein:
                -------------------------------------------------------""");
        String searchTerm = menuScanner.next().toLowerCase().trim();
        List<Product> searchResults = new ArrayList<>();
        List<Product> productSearchList = new ArrayList<>();
        productSearchList.addAll(mausArrayList);
        productSearchList.addAll(monitorArrayList);
        productSearchList.addAll(motherboardArrayList);
        productSearchList.addAll(tastaturArrayList);
        for (Product product : productSearchList) {
            if (productContainsTerm(product, searchTerm)) {
                searchResults.add(product);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("Keine Treffer gefunden");
        } else {
            System.out.println("-------------------------------------------------------\n[Treffer]");
                for (int i = 0; i < searchResults.size(); i++) {
                    Product product = searchResults.get(i);
                    System.out.println(" " + (i + 1) + ") " + product.toString());
                }
        }
        System.out.println("""
                    -------------------------------------------------------
                    Willst Du noch einmal suchen?
                    (J)a    |   (N)ein
                    """ + standardMenuPrompt);
        String searchAgainCheck = menuScanner.next().toLowerCase();
        switch (searchAgainCheck) {
            case "j" -> produktSuchen();
            case "n" -> hauptMenue();
            default -> errorMessageAndQuit("wrongInput");
        }
    }

    /**
     * The Delete Products menu displays existing products numbered.
     * The user can delete products by entering the corresponding number and confirming the subsequent confirmation request.
     * If the user has made an incorrect entry, an error message is displayed, and he is taken back to the main menu.
     */
    public static void produktLoeschen() {
        int productCount = 0;

        if (mausArrayList.isEmpty() && monitorArrayList.isEmpty() && motherboardArrayList.isEmpty() && tastaturArrayList.isEmpty()) {
            errorMessageAndQuit("productsNotExist");
        }else {
            productCount = editDeleteProductListsOutput(productCount, "löschen");
            try {
                int productToDeleteInput;
                String productEditInputString = menuScanner.next();
                productToDeleteInput = Integer.parseInt(productEditInputString);
                if (productToDeleteInput >= 1 && productToDeleteInput <= productCount) {
                    if (productToDeleteInput <= mausArrayList.size()) {
                        Maus selectedMaus = mausArrayList.get(productToDeleteInput - 1);
                        productDeleteCheck(productToDeleteInput, selectedMaus, mausArrayList);
                    } else if (productToDeleteInput <= mausArrayList.size() + monitorArrayList.size()) {
                        Monitor selectedMonitor = monitorArrayList.get(productToDeleteInput - mausArrayList.size() - 1);
                        productDeleteCheck(productToDeleteInput, selectedMonitor, monitorArrayList);
                    } else if (productToDeleteInput <= mausArrayList.size() + monitorArrayList.size() + motherboardArrayList.size()) {
                        Motherboard selectedMotherboard = motherboardArrayList.get(productToDeleteInput - mausArrayList.size() - monitorArrayList.size() - 1);
                        productDeleteCheck(productToDeleteInput, selectedMotherboard, motherboardArrayList);
                    } else {
                        Tastatur selectedTastatur = tastaturArrayList.get(productToDeleteInput - mausArrayList.size() - monitorArrayList.size() - motherboardArrayList.size() - 1);
                        productDeleteCheck(productToDeleteInput, selectedTastatur, tastaturArrayList);
                    }
                } else {
                    errorMessageAndQuit("wrongInput");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                errorMessageAndQuit("wrongInput");
            }
        }
    }

    /**
     * The Exit menu prompts the user to confirm the exit from the PC Shop.
     * The programme ends if confirmed. Otherwise, or in case of an incorrect entry, the programme returns to the main menu.
     */
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

    /**
     * Gets handed over placeholder, indicators and products to add or edit a specific property of a product.
     * If the user has made an incorrect entry, an error message is displayed, and he is taken back to the main menu.
     * @param articleProperty Text placeholder for the corresponding property to be added or edited (e.g. "die Marke", "das Modell", "den Preis(in €)").
     * @param addOrEdit To indicate whether the property is to be added or edited.
     * @param product To determine the product object to be modified.
     */
    private static void propertyAddPrompt(String articleProperty, String addOrEdit, Product product){
        System.out.println("Gebe " + articleProperty + " an:");
        String propertyValue = menuScanner.next();
        if (addOrEdit.equals("hinzufügen")) {
            switch (articleProperty) {
                case "die Marke" -> { newProductBrand = propertyValue; product.setProductBrand(propertyValue);}
                case "das Modell" -> { newProductModel = propertyValue; product.setProductModel(propertyValue); }
                case "den Preis(in €)" -> {
                    String productPrice = propertyValue.replace(",", ".").replace("€", "");
                    try {
                        newProductPrice = (Double.valueOf(productPrice));
                        product.setProductPrice(Double.valueOf(productPrice));
                    } catch (NumberFormatException e) {
                        errorMessageAndQuit("wrongInput");
                    }
                }
            }
        } else if (addOrEdit.equals("bearbeiten")) {
            switch (articleProperty) {
                case "die Marke" -> newProductBrand = propertyValue;
                case "das Modell" -> newProductModel = propertyValue;
                case "den Preis(in €)" -> {
                    String productPrice = propertyValue.replace(",", ".").replace("€", "");
                    try {
                        newProductPrice = (Double.valueOf(productPrice));
                    } catch (NumberFormatException e) {
                        errorMessageAndQuit("wrongInput");
                    }
                }
            }
        }
    }

    /**
     * To add or edit properties of a product based on user input.
     * If the user has made an incorrect entry, an error message is displayed, and he is taken back to the main menu.
     * @param menuTitle To set the title for the Edit Product or Add Product menu.
     * @param menuHeader Serves as an indication for the user, e.g. which product has been selected.
     * @param uniqueProperty To specify the special property of a product category.
     * @param product The product to which the properties are being added or edited.
     * @param addOrEdit To determine whether the properties have to be added or edited.
     */
    public static void propertiesAddOrEdit(String menuTitle, String menuHeader, String uniqueProperty, Product product, String addOrEdit) {
        while(true) {
            MenuBuilder.menuBuilder(menuTitle, "Ausgewählt: " + menuHeader, categoryPropertiesArrayList, standardMenuPrompt);
            String selectPropertyToAdd = menuScanner.next();
            switch (selectPropertyToAdd) {
                case "1", "1)" -> propertyAddPrompt("die Marke", addOrEdit, product);
                case "2", "2)" -> propertyAddPrompt("das Modell", addOrEdit, product);
                case "3", "3)" -> propertyAddPrompt("den Preis(in €)", addOrEdit, product);
                case "4", "4)" -> {
                    System.out.println("Gebe " + uniqueProperty + " an:");
                    String productUnique = menuScanner.next().toLowerCase();
                    if (product instanceof Maus) {
                        String dpi = productUnique.replace(",", "").replace(" dpi", "");
//                        int dpi = Integer.parseInt(productUnique.replace(",", "").replace("dpi", ""));
                        if(addOrEdit.equals("hinzufügen")){
                            try {
                                newSensorResolution = dpi;
                                ((Maus) product).setSensorResolution(dpi);
                            } catch (NumberFormatException e) {
                                errorMessageAndQuit("wrongInput");
                            }
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newSensorResolution = dpi;
                        }
                    } else if (product instanceof Monitor) {
                        if(addOrEdit.equals("hinzufügen")){
                            try {
                                newScreenSizeInch = Double.valueOf(productUnique.replace(",", "."));
                                ((Monitor) product).setScreenSizeInch(Double.valueOf(productUnique.replace(",", ".")));
                            } catch (NumberFormatException e) {
                                errorMessageAndQuit("wrongInput");
                            }
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newScreenSizeInch = Double.valueOf(productUnique.replace(",", "."));
                        }
                    } else if (product instanceof Motherboard) {
                        if(addOrEdit.equals("hinzufügen")){
                            newMotherboardSocket = productUnique;
                            ((Motherboard) product).setMotherboardSocket(productUnique);
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newMotherboardSocket = productUnique;
                        }
                    } else if (product instanceof Tastatur) {
                        if(addOrEdit.equals("hinzufügen")){
                            newKeyboardType = productUnique;
                            ((Tastatur) product).setKeyboardType(productUnique);
                        }else if(addOrEdit.equals("bearbeiten")) {
                            newKeyboardType = productUnique;
                        }
                    }
                }
                case "0", "0)" -> {
                    if (product instanceof Maus) {
                        handleProductSaving(product, "Maus", "der Maus", addOrEdit);
                    } else if (product instanceof Monitor) {
                        handleProductSaving(product, "Monitor", "des Monitors" , addOrEdit);
                    } else if (product instanceof Motherboard) {
                        handleProductSaving(product, "Motherboard", "des Motherboards", addOrEdit);
                    } else if (product instanceof Tastatur) {
                        handleProductSaving(product, "Tastatur", "der Tastatur", addOrEdit);
                    }return;
                }default -> errorMessageAndQuit("wrongInput");
            }
        }
    }

    /**
     * To handle the product saving process based on the provided product type.
     * Checks if the user wants to add or edit properties again.
     * If the product has empty entries, an error message is displayed, and the user is taken back to the main menu.
     * @param product To hand over the selected product for editing or adding properties.
     * @param productType To specify the product category to check for empty properties.
     * @param articleProduct Text placeholder to show the product and its article.
     * @param addOrEdit To determine whether the properties have to be added or edited.
     */
    private static void handleProductSaving(Product product, String productType, String articleProduct, String addOrEdit) {
        if (checkForEmptyProperty(product, productType)) {
            errorMessageAndQuit("emptyValue");
        } else {
            if (addOrEdit.equals("hinzufügen")) {
                System.out.println("Alle Eigenschaften " + articleProduct + " sind vorhanden und haben gültige Werte.");
                if (product instanceof Maus) {
                    mausArrayList.add((Maus) product);
                } else if (product instanceof Monitor) {
                    monitorArrayList.add((Monitor) product);
                } else if (product instanceof Motherboard) {
                    motherboardArrayList.add((Motherboard) product);
                } else if (product instanceof Tastatur) {
                    tastaturArrayList.add((Tastatur) product);
                }
            } else if (addOrEdit.equals("bearbeiten")) {
                setProductProperties(product);
            }
            clearPropertyValues();
            addOrEditAnotherProductCheck(addOrEdit);
        }
    }

    /**
     * Formats the menu text for editing or deleting products.
     * @param productCount For numbering the saved products in the Edit or Delete menu.
     * @param editOrDelete Text placeholder to prompt the user to add, edit or delete the product.
     * @return To hand over the updated quantity of existing products to the edit and delete menus.
     */
    private static Integer editDeleteProductListsOutput(Integer productCount, String editOrDelete){
        System.out.println("""
            -------------------------------------------------------
            PC-Shop\t\t\tProduktliste\t\t\tvon: Sven Mayer
            -------------------------------------------------------""");
        productCount = productsOutputToConsole(mausArrayList, productCount, "Mäuse", "Maus");
        productCount = productsOutputToConsole(monitorArrayList, productCount, "Monitore", "Monitor");
        productCount = productsOutputToConsole(motherboardArrayList, productCount, "Motherboards", "Motherboard");
        productCount = productsOutputToConsole(tastaturArrayList, productCount, "Tastaturen", "Tastatur");
        System.out.println("-------------------------------------------------------\nGebe die Nummer eines Produkts ein, um es zu " + editOrDelete + ":");
        return productCount;
    }
    /**
     * To request a deletion confirmation from the user.
     */
    private static void productDeleteCheck(Integer productToDeleteInput, Product product, ArrayList<?> ArrayList){
        System.out.println("------------------------------------------------\nAusgewählt: " + product +
                "\nSoll der Eintrag " + productToDeleteInput +") zu [" + product.getProductModel() +
                "] wirklich gelöscht werden? \n(J)a    |   (N)ein");
        String deleteConfirmInput = menuScanner.next().toLowerCase();
        switch(deleteConfirmInput){
            case "j", "ja", "(j)"-> {
                ArrayList.remove(product);
                produktLoeschen();
            }
            case "n", "nein", "(n)" -> hauptMenue();
        }
    }

    /**
     * To check if any product contains the search term.
     * @param product Defines the product that is searched for the search term.
     * @param searchTerm The term that is being searched for.
     * @return To determine whether the term has been found or not.
     */
    public static boolean productContainsTerm(Product product, String searchTerm) {
        if (product.getProductBrand().toLowerCase().contains(searchTerm)
            || product.getProductModel().toLowerCase().contains(searchTerm)
            || String.valueOf(product.getProductPrice()).toLowerCase().contains(searchTerm)) {
            return true;
        }
        if (product instanceof Maus maus) { return String.valueOf(maus.getSensorResolution()).toLowerCase().contains(searchTerm);
        } else if (product instanceof Monitor monitor) { return String.valueOf(monitor.getScreenSizeInch()).toLowerCase().contains(searchTerm);
        } else if (product instanceof Motherboard motherboard) { return motherboard.getMotherboardSocket().toLowerCase().contains(searchTerm);
        } else if (product instanceof Tastatur tastatur) { return tastatur.getKeyboardType().toLowerCase().contains(searchTerm);
        }
        return false;
    }

    /**
     * For handling the property storing process depending on the specified product category.
     * @param product To determine in which product properties are to be saved.
     */
    private static void setProductProperties(Product product){
        product.setProductBrand(newProductBrand); product.setProductModel(newProductModel); product.setProductPrice(newProductPrice);
        switch (product.getClass().getSimpleName()) {
            case "Monitor" -> ((Monitor) product).setScreenSizeInch(newScreenSizeInch);
            case "Maus" -> ((Maus) product).setSensorResolution(newSensorResolution);
            case "Motherboard" -> ((Motherboard) product).setMotherboardSocket(newMotherboardSocket);
            case "Tastatur" -> ((Tastatur) product).setKeyboardType(newKeyboardType);
        }
    }

    /**
     * Checks whether the specified list is empty and, if not, outputs the numbered products.
     * Fetches a specific method based on the class type passed to use the getter methods of the products.
     * @param productArrayList The list that is being checked.
     * @param productCount To set the number associated with the product.
     * @param productPluralCategory Text placeholder to indicate the plural of the category.
     * @param productClassName For accessing the getter methods of the products.
     * @return For numbering the saved products in the Edit or Delete menu.
     */
    private static Integer productsOutputToConsole(ArrayList<?> productArrayList, Integer productCount, String productPluralCategory, String productClassName) {
        if (!productArrayList.isEmpty()) {
            System.out.println("Posten) [Produkte der Kategorie " + productPluralCategory + "]");
            for (Object product : productArrayList) {
                productCount++;
                System.out.print("\t " + productCount + ") ");
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

    /**
     * To check if the user wants to add or edit another product.
     * If the user has made an incorrect entry, an error message is displayed and he is returned to the main menu.
     * @param addOrEdit Text placeholder to indicate whether to add or edit.
     */
    private static void addOrEditAnotherProductCheck(String addOrEdit){
        System.out.println(
                "-------------------------------------------------------\n" +
                "Möchtest du ein weiteres Produkt " + addOrEdit + "?\n" +
                "-------------------------------------------------------\n" +
                "(J)a\t|\t (N)ein\n" + standardMenuPrompt);
        String addOrEditInput = menuScanner.next().trim();
        if (addOrEditInput.equalsIgnoreCase("j")){
            if (addOrEdit.equals("hinzufügen")) {
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

    /**
     * To check if the handed over product has empty properties or if the variables are null.
     * @param product To specify the product in the add or edit process.
     * @param productCategory To specify the category of the product to be checked.
     * @return To pass on whether the properties are empty or null.
     */
    private static boolean checkForEmptyProperty(Product product, String productCategory){
        boolean isBasePropertyEmptyNull = product.getProductBrand().isEmpty() || product.getProductModel().isEmpty() || product.getProductPrice() == 0.0 ||
                newProductBrand == null || newProductModel == null || newProductPrice == null;
        return switch (productCategory) {
            case "Maus" -> isBasePropertyEmptyNull || ((Maus) product).getSensorResolution().equals("") || newSensorResolution == null;
            case "Monitor" -> isBasePropertyEmptyNull || ((Monitor) product).getScreenSizeInch() == 0.0 || newScreenSizeInch == null;
            case "Motherboard" -> isBasePropertyEmptyNull || ((Motherboard) product).getMotherboardSocket().isEmpty() || newMotherboardSocket == null;
            case "Tastatur" -> isBasePropertyEmptyNull || ((Tastatur) product).getKeyboardType().isEmpty() || newKeyboardType == null;
            default -> false;
        };
    }

    /**
     * Deletes the values of the properties to ensure that the correct properties are saved the next time after they have already been saved.
     */
    private static void clearPropertyValues() {
        newProductBrand = null; newProductModel = null; newProductPrice = null; newMotherboardSocket = null; newKeyboardType = null; newSensorResolution = null; newScreenSizeInch = null;
    }

    /**
     * For error messages.
     * @param errorType To select the error text to be output.
     */
    private static void errorMessageAndQuit(String errorType){
        String errorMessage = null;
        switch(errorType){
            case "wrongInput" -> errorMessage = "\t\t\t\t\t\t\tFehlerhafte Eingabe";
            case "emptyValue" -> errorMessage = "\t Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden";
            case "productsNotExist" -> errorMessage = "\t\t\t\t\tKeine Produkte vorhanden";
        }
        System.out.println("\n+-Info-+--------------------------------------------------------------+-Info-+\n"  +
                errorMessage + "\n+-Info-+--------------------------------------------------------------+-Info-+");
        hauptMenue();
    }
}
