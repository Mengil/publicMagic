import productCategories.Maus;
import productCategories.Monitor;
import productCategories.Motherboard;
import productCategories.Tastatur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Static methods to handle the menus for the different product categories.
 * Includes methods to create and manage menus related to specific product categories.
 * Helper methods for handling property arrays and checking property containment are also provided.
 * @author Sven Mayer
 * @version 1.0
 * @since 14.06.23
 */
public class productMenues {

    /**
     * For displaying the menu for managing "Maus" products.
     */
    static void mausMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"mausMenu", "Maus");
        Maus maus = new Maus("", "", 0.0, 0);
        MainShop.propertiesAddOrEdit("\t\t\tMaus anlegen", "Eigenschaften","die Sensorauflösung (in dpi)", maus, "hinzufügen");
    }

    /**
     * For displaying the menu for managing "Tastatur" products.
     */
    static void tastaturMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"tastaturMenu", "Tastatur");
        Tastatur tastatur = new Tastatur("", "", 0.0, "");
        MainShop.propertiesAddOrEdit("\t\tTastatur anlegen", "Eigenschaften","den Tastaturtyp", tastatur, "hinzufügen");
    }

    /**
     * For displaying the menu for managing "Motherboard" products.
     */
    static void motherboardMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"motherboardMenu", "Motherboard");
        Motherboard motherboard = new Motherboard("", "", 0.0, "");
        MainShop.propertiesAddOrEdit("\t\tMotherboard anlegen", "Eigenschaften","den Sockel", motherboard, "hinzufügen");
    }

    /**
     * For displaying the menu for managing "Monitor" products.
     */
    static void monitorMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"monitorMenu", "Monitor");
        Monitor monitor = new Monitor("", "", 0.0, 0.0);
        MainShop.propertiesAddOrEdit("\t\t\tMonitor anlegen", "Eigenschaften","die Größe(Zoll)", monitor, "hinzufügen");
    }

    /**
     * Handles the manipulation of the propertyArrayList based on the specified menu name and product category.
     * @param propertyArrayList  The list of properties to handle.
     * @param menuName           The name of the menu.
     * @param productCategory    The product category.
     */
    public static void propertyArrayListHandler(ArrayList<String> propertyArrayList, String menuName, String productCategory){
        String[] propertiesToRemove = {"Größe(Zoll)", "Monitor Speichern", "Sockel", "Motherboard Speichern", "Tastentyp", "Tastatur Speichern", "Auflösung(dpi)", "Maus Speichern"};

        switch(menuName){
            case "mausMenu" -> {
                List<Integer> indexesToRemove = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
                propertyAtIndexRemove(indexesToRemove, propertyArrayList, propertiesToRemove);
                propertyContainCheck(propertyArrayList, productCategory, "Auflösung(dpi)");
            }
            case "tastaturMenu" -> {
                List<Integer> indexesToRemove = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 6, 7));
                propertyAtIndexRemove(indexesToRemove, propertyArrayList, propertiesToRemove);
                propertyContainCheck(propertyArrayList, productCategory, "Tastentyp");
            }
            case "motherboardMenu" -> {
                List<Integer> indexesToRemove = new ArrayList<>(Arrays.asList(0, 1, 4, 5, 6, 7));
                propertyAtIndexRemove(indexesToRemove, propertyArrayList, propertiesToRemove);
                propertyContainCheck(propertyArrayList, productCategory, "Sockel");
            }
            case "monitorMenu" -> {
                List<Integer> indexesToRemove = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7));
                propertyAtIndexRemove(indexesToRemove, propertyArrayList, propertiesToRemove);
                propertyContainCheck(propertyArrayList, productCategory, "Größe(Zoll)");
            }
        }
    }

    /**
     * To remove properties from the propertyArrayList based on the specified indexes and properties to remove.
     * @param indexesToRemove     The indexes of properties to remove.
     * @param propertyArrayList   The list of properties to modify.
     * @param propertiesToRemove  The array of properties to remove.
     */
    private static void propertyAtIndexRemove(List<Integer> indexesToRemove, ArrayList<String> propertyArrayList, String[] propertiesToRemove){
        for (int index : indexesToRemove) {
            propertyArrayList.remove(propertiesToRemove[index]);
        }
    }

    /**
     * To check if the propertyArrayList contains the specified property and the productCategory-specific "Speichern" property.
     * If not, adds the property and "Speichern" property to the propertyArrayList.
     * @param propertyArrayList  The list of properties to check and modify.
     * @param productCategory    The product category.
     * @param property           The property to check and add.
     */
    private static void propertyContainCheck(ArrayList<String> propertyArrayList, String productCategory, String property){
        if (!propertyArrayList.contains(property) || !propertyArrayList.contains(productCategory + " Speichern")) {
            propertyArrayList.add(property);
            propertyArrayList.add(productCategory + " Speichern");
        }
    }
}