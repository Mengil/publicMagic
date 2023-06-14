import products.Maus;
import products.Monitor;
import products.Motherboard;
import products.Tastatur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class productMenues {
    static void mausMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"mausMenu", "Maus");
        Maus maus = new Maus("", "", 0.0, 0);
        MainShop.propertiesAddOrEdit("\t\t\tMaus anlegen", "Eigenschaften","die Sensorauflösung (in dpi)", maus, "anlegen");
    }
    static void tastaturMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"tastaturMenu", "Tastatur");
        Tastatur tastatur = new Tastatur("", "", 0.0, "");
        MainShop.propertiesAddOrEdit("\t\tTastatur anlegen", "Eigenschaften","den Tastaturtyp", tastatur, "anlegen");
    }
    static void motherboardMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"motherboardMenu", "Motherboard");
        Motherboard motherboard = new Motherboard("", "", 0.0, "");
        MainShop.propertiesAddOrEdit("\t\tMotherboard anlegen", "Eigenschaften","den Sockel", motherboard, "anlegen");
    }
    static void monitorMenu() {
        propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"monitorMenu", "Monitor");
        Monitor monitor = new Monitor("", "", 0.0, 0.0);
        MainShop.propertiesAddOrEdit("\t\t\tMonitor anlegen", "Eigenschaften","die Größe(Zoll)", monitor, "anlegen");
    }
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
    private static void propertyAtIndexRemove(List<Integer> indexesToRemove, ArrayList<String> propertyArrayList, String[] propertiesToRemove){
        for (int index : indexesToRemove) {
            propertyArrayList.remove(propertiesToRemove[index]);
        }
    }
    private static void propertyContainCheck(ArrayList<String> propertyArrayList, String productCategory, String property){
        if (!propertyArrayList.contains(property) || !propertyArrayList.contains(productCategory + " Speichern")) {
            propertyArrayList.add(property);
            propertyArrayList.add(productCategory + " Speichern");
        }
    }
}




