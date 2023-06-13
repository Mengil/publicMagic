import products.Maus;
import products.Monitor;
import products.Motherboard;
import products.Tastatur;


public class productMenues {
    static void mausMenu() {
        PropertiesHandler.propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"mausMenu", "Maus");
        Maus maus = new Maus("", "", 0.0, 0);
        MainShop.propertyMenu("Maus anlegen", "die Sensorauflösung (in dpi)", maus, "anlegen");
    }
    static void tastaturMenu() {
        PropertiesHandler.propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"tastaturMenu", "Tastatur");
        Tastatur tastatur = new Tastatur("", "", 0.0, "");
        MainShop.propertyMenu("Tastatur anlegen", "den Tastaturtyp", tastatur, "anlegen");
    }
    static void motherboardMenu() {
        PropertiesHandler.propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"motherboardMenu", "Motherboard");
        Motherboard motherboard = new Motherboard("", "", 0.0, "");
        MainShop.propertyMenu("Motherboard anlegen", "den Sockel", motherboard, "anlegen");
    }
    static void monitorMenu() {
        PropertiesHandler.propertyArrayListHandler(MainShop.categoryPropertiesArrayList,"monitorMenu", "Monitor");
        Monitor monitor = new Monitor("", "", 0.0, 0.0);
        MainShop.propertyMenu("Monitor anlegen", "die Größe(Zoll)", monitor, "anlegen");
    }

}




