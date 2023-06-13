import java.util.ArrayList;

public class MenuBuilder {
    public static void menuBuilder(String menuTitle, String menuHeader, ArrayList<String> menuItems, String menuPrompt){
        System.out.println(
                "-------------------------------------------------------\n" +
                        "PC-Shop" + "\t\t\t\t" + menuTitle + "\t\t" + "von: " + "Sven Mayer" +
                        "\n-------------------------------------------------------");
        if(!menuHeader.isEmpty()){
            System.out.println(menuHeader);
        }
        for (int i = 0; i < menuItems.size(); i++){
            if(menuItems.size() > 4){
                if (i == menuItems.size() - 1) {
                    System.out.println("0) " + menuItems.get(i));
                } else {
                    System.out.println((i + 1) + ") " + menuItems.get(i));
                }
            }else{
                System.out.println((i + 1) + ") " + menuItems.get(i));
            }
        };
        System.out.println(menuPrompt);
    }
}
