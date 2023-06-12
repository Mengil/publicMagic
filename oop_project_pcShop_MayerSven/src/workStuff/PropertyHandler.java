package workStuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyHandler {
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






