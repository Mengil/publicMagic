package snippets.menues;

//        while (true) {
//            MenuBuilder.menuBuilder(menuTitle, "Eigenschaften", categoryPropertiesArrayList, standardMenuPrompt);
//            String mausMenuCategorySelectInput = menuScanner.next();
//
//            switch (mausMenuCategorySelectInput) {
//                case "1", "1)" -> {
//                    System.out.println("Gebe die Marke an:");
//                    String productBrand = menuScanner.next();
//                    maus.setProductBrand(productBrand);
//                }
//                case "2", "2)" -> {
//                    System.out.println("Gebe das Modell an:");
//                    String productModel = menuScanner.next();
//                    maus.setProductModel(productModel);
//                }
//                case "3", "3)" -> productPriceInput(maus);
//                case "4", "4)" -> {
//                    System.out.println("Gebe die Sensorauflösung (in dpi) an:");
//                    String sensorAufloesung = menuScanner.next().toLowerCase();
//                    sensorAufloesung = sensorAufloesung.replace(",", "").replace("dpi", "");
//                    try {
//                        Integer aufloesung = Integer.valueOf(sensorAufloesung);
//                        maus.setSensorAufloesung(aufloesung);
//                    } catch (NumberFormatException e) {
//                        wrongInputQuit("wrongInput");
//                    }
//                }
//                case "0", "0)" -> {
//                    if (maus.getProductBrand().isEmpty() || maus.getProductModel().isEmpty() || maus.getProductPrice() == 0.0 || maus.getSensorAufloesung() == 0) {
//                        wrongInputQuit("emptyValue");
//                    } else {
//                        System.out.println("Alle Eigenschaften der Maus sind vorhanden und haben gültige Werte.");
//                        mausArrayList.add(maus);
//                        addAnotherProductCheck();
//                    }
//                    return;
//                }
//                default -> wrongInputQuit("wrongInput");
//            }
//        }