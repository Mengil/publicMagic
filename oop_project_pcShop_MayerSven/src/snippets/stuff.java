//public static String modellMenu(String productCategory){
//        ArrayList<String> modellArrayList = new ArrayList<>();
//        String modellInputPrompt = "Gebe das Modell " + productCategory + " an:";
//        menuBuilder("Modell angeben", "", modellArrayList, modellInputPrompt);
//        return menuScanner.next();
//        }
//public static String preisMenu(String productCategory){
//        ArrayList<String> preisArrayList = new ArrayList<>();
//        String preisInputPrompt = "Gebe den Preis " + productCategory + " an:";
//        menuBuilder("Preis angeben", "", preisArrayList, preisInputPrompt);
//        return menuScanner.next();
//        }
//public static String groesseMenu(String productCategory){
//        ArrayList<String> groesseArrayList = new ArrayList<>();
//        String markeInputPrompt = "Gebe die Größe " + productCategory + " an:";
//        menuBuilder("Größe angeben", "", groesseArrayList, markeInputPrompt);
//        return menuScanner.next();
//        }
//public static void markeMenu(String productCategory){
//        ArrayList<String> markeArrayList = new ArrayList<>();
//        String markeInputPrompt = "Gebe die Marke an:";
//        menuBuilder(productCategory, "", markeArrayList, markeInputPrompt);
//        }


//    public static void productCreator(String productCategory, ArrayList<String> productPropertyList){
//        switch(productCategory){
//            case "Monitor" -> monitorCreateMenu(productPropertyList);
//            case "Motherboard" -> motherboardCreateMenu(productPropertyList);
//            case "Tastatur" -> tastaturCreateMenu(productPropertyList);
//            case "Maus" -> mausCreateMenu(productPropertyList);
//        }
//    }

//    private static void productPropertySelectText(String productCategory) {
//        String productPropertySelectInput = menuScanner.next();
//        String monitorBrandInput = "";
//
//        switch(productCategory){
//            case "Monitor" -> {
//                switch (productPropertySelectInput) {
//                    case "1", "1)" -> {
//                        System.out.println("Gebe die Marke zum Monitor an:");
//                        monitorBrandInput = menuScanner.next();
//                        monitorCreateMenu(categoryPropertiesArrayList);
//                    }
//                    case "2", "2)" -> {
//                        System.out.println("Gebe die das Modell zum Monitor an:");
//                        String monitorModelInput = menuScanner.next();
//
//                    }
//                    case "3", "3)" -> {
//                        System.out.println("Gebe die den Preis zum Monitor an:");
//                        String monitorPriceInput = menuScanner.next();
//
//                    }
//                    case "4", "4)" -> {
//                        System.out.println("Gebe die die Größe(Zoll) zum Monitor an:");
//                        String monitorSizeInput = menuScanner.next();
//
//                    }
//                    case "0", "0)" -> {
//                        if(monitorBrandInput.isEmpty()){
//
//                        }
//                    }
//                }
//            }
//        }
//    }


//        menuBuilder("Monitor anlegen", "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
//        String monitorMenuCategorySelectInput = menuScanner.next();
//        switch (monitorMenuCategorySelectInput) {
//            case "1", "1)" -> {
//                System.out.println("Gebe die Marke an:");
//                String monitorMarke = menuScanner.next();
//                monitor.setProductBrand(monitorMarke);
//                monitorMenu();
//            }
//            case "2", "2)" -> {
//                System.out.println("Gebe das Modell an:");
//                String monitorModell = menuScanner.next();
//                monitor.setProductModel(monitorModell);
//                monitorMenu();
//            }
//            case "3", "3)" -> {
//                System.out.println("Gebe den Preis an:");
//                String monitorPreis = menuScanner.next();
//                monitor.setProductPrice(Double.valueOf(monitorPreis));
//                monitorMenu();
//            }
//            case "4", "4)" -> {
//                System.out.println("Gebe die Größe(Zoll) an:");
//                String monitorGroesse = menuScanner.next();
//                monitor.setMonitorSizeInch(Double.valueOf(monitorGroesse));
//                monitorMenu();
//            }
//            case "0", "0)" -> {
//                if(monitor.getProductBrand().isEmpty() || monitor.getProductModel().isEmpty() || monitor.getProductPrice() == 0.0 || monitor.getMonitorSizeInch() == 0.0) {
//                    System.out.println("Eine oder mehrere Eigenschaften des Monitors sind leer oder haben den Wert 0.0.");
//                } else {
//                    System.out.println("Alle Eigenschaften des Monitors sind vorhanden und haben gültige Werte.");
//                }
//            }
//        }