package snippets.menues;

public class monitorMenuSnippets {
//    while (true) {
//        MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
//        String monitorMenuCategorySelectInput = menuScanner.next();
//
//        switch (monitorMenuCategorySelectInput) {
//            case "1", "1)" -> {
//                System.out.println("Gebe die Marke an:");
//                monitor.setProductBrand(menuScanner.next());
//            }
//            case "2", "2)" -> {
//                System.out.println("Gebe das Modell an:");
//                monitor.setProductModel(menuScanner.next());
//            }
//            case "3", "3)" -> productPriceInput(monitor);
//            case "4", "4)" -> {
//                System.out.println("Gebe die Größe(Zoll) an:");
//                String productScreenSize = menuScanner.next().replace(",", ".");
//                try {
//                    monitor.setScreenSizeInch(Double.valueOf(productScreenSize));
//                } catch (NumberFormatException e) {
//                    wrongInputQuit("wrongInput");
//                }
//            }
//            case "0", "0)" -> {
//                if (monitor.getProductBrand().isEmpty() || monitor.getProductModel().isEmpty() || monitor.getProductPrice() == 0.0 || monitor.getScreenSizeInch() == 0.0) {
//                    wrongInputQuit("emptyValue");
//                } else {
//                    System.out.println("Alle Eigenschaften des Monitors sind vorhanden und haben gültige Werte.");
//                    monitorArrayList.add(monitor);
//                    addAnotherProductCheck();
//                }
//                return;
//            }default -> wrongInputQuit("wrongInput");
//        }
//    }
}
